import javax.swing.JPanel;
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
import org.jfree.chart.plot.PlotOrientation; 
import org.jfree.data.xy.XYSeriesCollection; 
import org.jfree.chart.renderer.xy.XYSplineRenderer;

public class DataDisplay extends ApplicationFrame
{
  static float[] p, v, a;
  public static void importData(float[] position, float[] velocity, float[] acceleration)
  {
    p = position;
    v = velocity;
    a = acceleration;
  }
  public DataDisplay(String applicationTitle, String chartTitle )
   {
      super(applicationTitle);
      JFreeChart xylineChart = ChartFactory.createXYLineChart(
         chartTitle ,
         "Time" ,
         "m(/s(^2))" ,
         createDataset() ,
         PlotOrientation.VERTICAL ,
         true , true , false);
         
      ChartPanel chartPanel = new ChartPanel( xylineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      final XYPlot plot = xylineChart.getXYPlot( );
      XYSplineRenderer renderer = new XYSplineRenderer( );
      renderer.setSeriesPaint( 0 , Color.RED );
      renderer.setSeriesPaint( 1 , Color.GREEN );
      renderer.setSeriesPaint( 2 , Color.YELLOW );
      renderer.setSeriesStroke( 0 , new BasicStroke( 4.0f ) );
      renderer.setSeriesStroke( 1 , new BasicStroke( 3.0f ) );
      renderer.setSeriesStroke( 2 , new BasicStroke( 2.0f ) );
      plot.setRenderer( renderer ); 
      setContentPane( chartPanel ); 
   }
  private XYDataset createDataset()
  {
    final XYSeries position = new XYSeries( "Position" );
    for(int i = 0; i<p.length; i++)
    {
      position.add(i,p[i]);
    }
    final XYSeries velocity = new XYSeries( "Velocity" );
    for(int i = 0; i<v.length; i++)
    {
      velocity.add(i,v[i]);
    }
    final XYSeries acceleration = new XYSeries( "Acceleration" );
    for(int i = 0; i<a.length; i++)
    {
      acceleration.add(i,a[i]);
    }
    final XYSeriesCollection dataset = new XYSeriesCollection();
    dataset.addSeries(position);
    dataset.addSeries(velocity);
    dataset.addSeries(acceleration);
    return dataset;
  }
  public static void main(String[] args)
  {
    float[] velocity = {1,5,2,4,3};
    DataDisplay.importData(new float[4], velocity, new float[2]);
    DataDisplay chart = new DataDisplay("Position, Velocity, and Acceleration", "");
      chart.pack( );          
      RefineryUtilities.centerFrameOnScreen( chart );          
      chart.setVisible( true );
  }
}