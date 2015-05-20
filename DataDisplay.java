import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.awt.Color; 
import java.awt.BasicStroke; 
import org.jfree.chart.ChartPanel; 
import org.jfree.chart.JFreeChart; 
import org.jfree.data.xy.XYDataset; 
import org.jfree.data.xy.XYSeries; 
import org.jfree.ui.ApplicationFrame; 
import org.jfree.ui.RefineryUtilities; 
import org.jfree.chart.plot.XYPlot; 
import org.jfree.chart.ChartFactory; 
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.plot.PlotOrientation; 
import java.io.FileNotFoundException;
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYSplineRenderer;
import java.awt.BorderLayout;
import java.awt.geom.Ellipse2D;
public class DataDisplay extends ApplicationFrame
{
  static ArrayList<Float> p, v, a;
  private String title;
  
  //imports the position velocity and acceleration to the class in the form of ArrayLists
  public static void importData(ArrayList<Float> position, ArrayList<Float> velocity, ArrayList<Float> acceleration)
  {
    p = position;
    v = velocity;
    a = acceleration;
  }
  
  //Constructor for class, takes in a string either "Position" "Velocity" or "Acceleration"
  public DataDisplay(String applicationTitle)
   {
      super(applicationTitle);
      title = applicationTitle;   
   }
  private XYDataset createDataset()
  {
    final XYSeries position = new XYSeries( "Position" );
    for(int i = 0; i<p.size(); i+=2)
    {
      position.add(p.get(i),p.get(i+1));
    }
    final XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(position);
    return dataset;
  }
  
  //returns a graph in a jpanel whose border automatically fits the size of the graph
  public JPanel getGraph()
  {
    String unit = "";
    XYSeries data;
    Color color = Color.BLACK;
    if(title.toLowerCase().equals("position"))
    {
      unit = "Feet (in thousands)";
      data = new XYSeries("Position");
      color = Color.RED;
      for(int i = 0; i<p.size(); i+=2)
      {
        data.add(p.get(i),p.get(i+1));
      }
    }
    else if(title.toLowerCase().equals("acceleration"))
    {
      unit = "Feet/Second^2 (in thousands)";
      data = new XYSeries( "Acceleration" );
      color = Color.YELLOW;
      for(int i = 0; i<a.size(); i++)
      {
        data.add(i/10.0,a.get(i));
      }
    }
    else if(title.toLowerCase().equals("velocity"))
    {
      unit = "Feet/Second (in thousands)";
      data = new XYSeries("Velocity");
      color = Color.GREEN;
      for(int i = 0; i<v.size(); i++)
      {
        data.add(i/10.0,v.get(i));
      }
    }
    else
    {
      data = new XYSeries("");
    }
    XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(data);
    JFreeChart ok = ChartFactory.createXYLineChart(
                                                   title,
                                                   "Time",
                                                   unit,
                                                   dataset,
                                                   PlotOrientation.VERTICAL,
                                                   true, true, false);
    XYPlot pl = (XYPlot)ok.getPlot();
    NumberAxis range = (NumberAxis)pl.getRangeAxis();
    range.setRange(0,2);
    range.setTickUnit(new NumberTickUnit(0.2));
    ChartPanel ch = new ChartPanel(ok);
    final XYPlot plot = ok.getXYPlot();
    XYSplineRenderer renderer = new XYSplineRenderer();
    renderer.setSeriesPaint(0, color);
    renderer.setSeriesStroke(0, new BasicStroke(1.0f));
    renderer.setSeriesShape(0, new Ellipse2D.Double(0,0,0,0));
    plot.setRenderer(renderer); 
    setContentPane(ch);  
    JPanel j = new JPanel();
    j.setLayout(new java.awt.BorderLayout());
    j.add(ch, BorderLayout.CENTER);
    j.validate();
    return j;
  }
  public static void main(String[] args) throws FileNotFoundException
  {
    derivativecalculator calc = new derivativecalculator();
    DataDisplay.importData(calc.getPositionData(calc.processData()), calc.calculateDerivative(calc.getPositionData(calc.processData())), calc.getAccelerationData(calc.getPositionData(calc.processData()), calc.calculateDerivative(calc.getPositionData(calc.processData()))));
    DataDisplay chart = new DataDisplay("Position");
    JFrame frame = new JFrame();
    frame.add(chart.getGraph());
    frame.pack();
    frame.setVisible(true);
    DataDisplay cha = new DataDisplay("Velocity");
    JFrame fram = new JFrame();
    fram.add(cha.getGraph());
    fram.pack();
    fram.setVisible(true);
//    chart.pack();
//    RefineryUtilities.centerFrameOnScreen(chart);  
//    chart.setVisible(true);
//    DataDisplayVelocity.importData(calc.calculateDerivative(calc.getPositionData(calc.processData())));
//    DataDisplayVelocity cha = new DataDisplayVelocity("Velocity", "");
//    cha.pack();          
//    RefineryUtilities.centerFrameOnScreen(cha);          
//    cha.setVisible(true);
//    DataDisplayAcceleration.importData(calc.getAccelerationData(calc.getPositionData(calc.processData()), calc.calculateDerivative(calc.getPositionData(calc.processData()))));
//    DataDisplayAcceleration ch = new DataDisplayAcceleration("Acceleration", "");
//    ch.pack();          
//    RefineryUtilities.centerFrameOnScreen(ch);          
//    ch.setVisible(true);
  }
}