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

// This file uses the third-party libraries jcommon 1.0.23 and jfreechart 1.0.19
// These can be downloaded from: http://sourceforge.net/projects/jfreechart/files/


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
  
  //returns a graph in a jpanel whose border automatically fits the size of the graph
  public JPanel getGraph() throws FileNotFoundException
  {
    String unit = "";
    XYSeries data;
    Color color = Color.BLACK;
    DerivativeCalculator it = new DerivativeCalculator();
    float av = 0;
    float stdev = 0;
    //creates a dataset based on what kind of graph the person wants, position, acceleration, or velocity based on what the class was created as
    if(title.toLowerCase().equals("position"))
    {
      unit = "Feet";
      data = new XYSeries("Position");
      color = Color.RED;
      for(int i = 0; i<p.size(); i+=2)
      {
        data.add(p.get(i),p.get(i+1));
      }
      //finds the average and standard deviation and stores them
      av = DerivativeCalculator.getAverageOfPosition(it.getPositionData(it.processData()));
      stdev = DerivativeCalculator.getStandardDeviationOfPosition(it.getPositionData(it.processData()));
    }
    else if(title.toLowerCase().equals("acceleration"))
    {
      unit = "Feet/Second^2";
      data = new XYSeries( "Acceleration" );
      color = Color.YELLOW;
      for(int i = 0; i<a.size(); i++)
      {
        data.add(i/10.0,a.get(i));
      }
      //finds the average and standard deviation and stores them
      av = DerivativeCalculator.getAverage(it.getAccelerationData(it.getPositionData(it.processData()), it.calculateDerivative(it.getPositionData(it.processData()))));
      stdev = DerivativeCalculator.getStandardDeviation(it.getAccelerationData(it.getPositionData(it.processData()), it.calculateDerivative(it.getPositionData(it.processData()))));
    }
    else if(title.toLowerCase().equals("velocity"))
    {
      unit = "Feet/Second";
      data = new XYSeries("Velocity");
      color = Color.GREEN;
      for(int i = 0; i<v.size(); i++)
      {
        data.add(i/10.0,v.get(i));
      }
      //finds the average and standard deviation and stores them
      av = DerivativeCalculator.getAverage(it.calculateDerivative(it.getPositionData(it.processData())));
      stdev = DerivativeCalculator.getStandardDeviation(it.calculateDerivative(it.getPositionData(it.processData())));
    }
    else
    {
      data = new XYSeries("");
    }
    //stores the XYSeries in a XYSeriesCollection
    XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(data);
    //creates a graph with the data and a title based on the way the class was instantiated
    JFreeChart ok = ChartFactory.createXYLineChart(
                                                   title,
                                                   "Time",
                                                   unit,
                                                   dataset,
                                                   PlotOrientation.VERTICAL,
                                                   true, true, false);
    //creates a plot to set the range and domain
    XYPlot plot = (XYPlot)ok.getPlot();
    NumberAxis range = (NumberAxis)plot.getRangeAxis();
    System.out.println(av+(stdev*3));
    if(title.toLowerCase().equals("position"))
      range.setRange(0,av+(stdev*2));
    else if(title.toLowerCase().equals("velocity") || title.toLowerCase().equals("acceleration"))
      range.setRange(av-(stdev*2), av+(stdev*2));
    range.setTickUnit(new NumberTickUnit((av+stdev*3)/10));
    //puts the plot into a chartpanel which allows it to be put in the j panel
    ChartPanel ch = new ChartPanel(ok);
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
  
  //just a bunch of testing
  public static void main(String[] args) throws FileNotFoundException
  {
    DerivativeCalculator calc = new DerivativeCalculator();
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
    DataDisplay ch = new DataDisplay("Acceleration");
    JFrame fra = new JFrame();
    fra.add(ch.getGraph());
    fra.pack();
    fra.setVisible(true);
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