// (c) 2015 Andrew Bennett
// By Andrew Bennett
// Made for the UAHS AP Calculus class as part of the end-of-the-year project

import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.*;

public class MainGUI
{
  // This initilizes the main JFrame
  public void MainGUI()
  {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Data Deriver");
    //frame.setContentPane(makeGUI(frame));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(300,180);
    frame.setLocationRelativeTo(null);
    frame.setResizable(true);
    frame.setVisible(true);
  }
  // This creates the action listeners and populates the main JPanel, which is then sent back to the main JFrame
  private JPanel makeGUI(final JFrame frame)
  {
    // First all the action listeners are created
    class ActOne extends AbstractAction
    {
      public void actionPerformed(ActionEvent e)
      {
      }
    }
    
    class ActTwo extends AbstractAction
    {
      public void actionPerformed(ActionEvent e)
      {
      }
    }
    
    class ActThree extends AbstractAction
    {
      public void actionPerformed(ActionEvent e)
      {
      }
    }
    
    // Next the JPanel is created and populated
    
    JPanel main = new JPanel();
    main.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
    
    JLabel randomlabel = new JLabel("Here is a label.");
    randomlabel.setForeground(new Color(232,195,55));
    randomlabel.setFont(new Font("Helvetica", Font.BOLD, 14));
    main.add(randomlabel);
    
    JButton randombutton = new JButton("Random");
    randombutton.addActionListener(new ActOne());
    main.add(randombutton);
    
    JButton randombutton2 = new JButton("Random2");
    randombutton2.addActionListener(new ActTwo());
    main.add(randombutton2);
    
    return main;
  }
  
  public static void main(String[] args)
  {
    new MainGUI();
  }
}