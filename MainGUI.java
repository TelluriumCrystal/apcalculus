// (c) 2015 Andrew Bennett
// Made for the UAHS AP Calculus class as part of the end-of-the-year project

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class MainGUI
{
  private File selectedfile;
  final JFrame frame;
  private JPanel position;
  private JPanel velocity;
  private JPanel acceleration;
  
  // This initilizes the main JFrame
  public MainGUI()
  {
    JFrame.setDefaultLookAndFeelDecorated(true);
    frame = new JFrame("Data Deriver");
    frame.setContentPane(makeGUI(frame));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(false);
    frame.setMinimumSize(new Dimension(360,240));
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }
  // This creates the action listeners and populates the main JPanel, which is then sent back to the main JFrame
  private JPanel makeGUI(final JFrame frame)
  {
    // The panel components are instantiated
    JPanel main = new JPanel();
    JPanel buttons = new JPanel();
    JPanel graph = new JPanel();
    JPanel lowerbuttons = new JPanel();
    position = new JPanel();
    velocity = new JPanel();
    acceleration = new JPanel();
    JFileChooser filechooser = new JFileChooser();
    JLabel filename = new JLabel("No file loaded.");
    JButton openfile = new JButton("Open");
    JButton clearfile = new JButton("Clear");
    JButton positionbutton = new JButton("Position");
    JButton velocitybutton = new JButton("Velocity");
    JButton accelerationbutton = new JButton("Acceleration");
    CardLayout cardlayout = new CardLayout();
    
    // The action listeners are defined
    
    class OpenFileAction extends AbstractAction
    {
      public void actionPerformed(ActionEvent e)
      {
        int returnVal = filechooser.showOpenDialog(frame);
        if(returnVal == JFileChooser.APPROVE_OPTION)
        {
          selectedfile = filechooser.getSelectedFile();
          filename.setText(filechooser.getSelectedFile().getName() + " loaded.");
          processfile(filechooser.getSelectedFile());
          positionbutton.setEnabled(true);
          velocitybutton.setEnabled(true);
          accelerationbutton.setEnabled(true);
          frame.pack();
        }
      }
    }
    class ClearFileAction extends AbstractAction
    { 
      public void actionPerformed(ActionEvent e)
      {
        selectedfile = null;
        filename.setText(" No file loaded.");
        cleanwindow();
        positionbutton.setEnabled(false);
        velocitybutton.setEnabled(false);
        accelerationbutton.setEnabled(false);
        frame.pack();
      }
    }
    class PositionAction extends AbstractAction
    { 
      public void actionPerformed(ActionEvent e)
      {
        cardlayout.show(graph, "Position");
      }
    }
    class VelocityAction extends AbstractAction
    { 
      public void actionPerformed(ActionEvent e)
      {
        cardlayout.show(graph, "Velocity");
      }
    }
    class AccelerationAction extends AbstractAction
    { 
      public void actionPerformed(ActionEvent e)
      {
        cardlayout.show(graph, "Acceleration");
      }
    }
    
    // The JPanel is populated with components, and is broken up into two main parts: the button panel and the graph panel
    
    main.setLayout(new GridBagLayout());
    GridBagConstraints con = new GridBagConstraints();  // The main layout uses GridBagLayout, which uses a GridBagConstraints class to function
    main.setPreferredSize(new Dimension(720,510));
    con.fill = GridBagConstraints.HORIZONTAL;
    con.anchor = GridBagConstraints.PAGE_START;
    con.weightx = 1;
    con.gridx = 0;
    con.gridy = 0;
    main.add(buttons,con);
    graph.setLayout(cardlayout);
    con.fill = GridBagConstraints.BOTH;
    con.weightx = 1;
    con.weighty = 1;
    con.gridx = 0;
    con.gridy = 1;
    main.add(graph,con);
    graph.add(position, "Position");
    graph.add(velocity, "Velocity");
    graph.add(acceleration, "Acceleration");
    con.fill = GridBagConstraints.HORIZONTAL;
    con.anchor = GridBagConstraints.PAGE_END;
    con.weightx = 1;
    con.gridx = 0;
    con.gridy = 2;
    main.add(lowerbuttons,con);
    buttons.add(filename);
    openfile.addActionListener(new OpenFileAction());
    buttons.add(openfile);
    clearfile.addActionListener(new ClearFileAction());
    buttons.add(clearfile);
    positionbutton.addActionListener(new PositionAction());
    positionbutton.setEnabled(false);
    lowerbuttons.add(positionbutton);
    velocitybutton.addActionListener(new VelocityAction());
    velocitybutton.setEnabled(false);
    lowerbuttons.add(velocitybutton);
    accelerationbutton.addActionListener(new AccelerationAction());
    accelerationbutton.setEnabled(false);
    lowerbuttons.add(accelerationbutton);
    
    
    // And finally the JPanel is sent back to the JFrame
    
    return main;
  }

  private void processfile(File file)
  {
    DerivativeCalculator calc = new DerivativeCalculator(file);
    try
    {
      DataDisplay.importData(calc.getPositionData(calc.processData()), calc.calculateDerivative(calc.getPositionData(calc.processData())), calc.getAccelerationData(calc.getPositionData(calc.processData()), calc.calculateDerivative(calc.getPositionData(calc.processData()))));
    }
    catch(FileNotFoundException e)
    {
      System.out.println(e);
      System.exit(1); // Kill the program. Obviously if this particular exception is thrown at this point then there is little to be done to salvage the situation.
    }
    DataDisplay positiongraph = new DataDisplay("Position");
    DataDisplay velocitygraph = new DataDisplay("Velocity");
    DataDisplay accelerationgraph = new DataDisplay("Acceleration");
    try
    {
      position.add(positiongraph.getGraph());
    }
    catch(FileNotFoundException e)
    {
      System.out.println(e);
      System.exit(1); // Kill the program. Obviously if this particular exception is thrown at this point then there is little to be done to salvage the situation.
    }
    try
    {
      velocity.add(velocitygraph.getGraph());
    }
    catch(FileNotFoundException e)
    {
      System.out.println(e);
      System.exit(1); // Kill the program. Obviously if this particular exception is thrown at this point then there is little to be done to salvage the situation.
    }
    try
    {
      acceleration.add(accelerationgraph.getGraph());
    }
    catch(FileNotFoundException e)
    {
      System.out.println(e);
      System.exit(1); // Kill the program. Obviously if this particular exception is thrown at this point then there is little to be done to salvage the situation.
    }
  }
  
  private void cleanwindow()
  {
    position.removeAll();
    position.revalidate();
    velocity.removeAll();
    velocity.revalidate();
    acceleration.removeAll();
    acceleration.revalidate();
    frame.revalidate();
    frame.pack();
  }

  public static void main(String[] args)
  {
    new MainGUI();
  }
}