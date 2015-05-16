// (c) 2015 Andrew Bennett
// By Andrew Bennett
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
  
  // This initilizes the main JFrame
  public MainGUI()
  {
    JFrame.setDefaultLookAndFeelDecorated(true);
    JFrame frame = new JFrame("Data Deriver");
    frame.setContentPane(makeGUI(frame));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setResizable(true);
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
    JFileChooser filechooser = new JFileChooser();
    JLabel filename = new JLabel("No file loaded.");
    JButton openfile = new JButton("Open");
    JButton clearfile = new JButton("Clear");
    
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
        frame.pack();
      }
    }
    
    // The JPanel is populated with components
    
    main.setLayout(new FlowLayout(FlowLayout.CENTER,5,5));
    main.setPreferredSize(new Dimension(1080,720));
    main.add(filename);
    openfile.addActionListener(new OpenFileAction());
    main.add(openfile);
    clearfile.addActionListener(new ClearFileAction());
    main.add(clearfile);
    
    // And finally the JPanel is sent back to the JFrame
    
    return main;
  }

  public void processfile(File file)
  {
    
  }

  public static void main(String[] args)
  {
    new MainGUI();
  }
}