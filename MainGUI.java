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
    JPanel buttons = new JPanel();
    JPanel graph = new JPanel();
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
    
    // The JPanel is populated with components, and is broken up into two main parts: the button panel and the graph panel
    
    main.setLayout(new GridBagLayout());
    GridBagConstraints con = new GridBagConstraints();  // The main layout uses GridBagLayout, which uses a GridBagConstraints class to function
    main.setPreferredSize(new Dimension(1080,720));
    con.fill = GridBagConstraints.HORIZONTAL;
    con.anchor = GridBagConstraints.PAGE_START;
    con.weightx = 1;
    con.gridx = 0;
    con.gridy = 0;
    main.add(buttons,con);
    con.fill = GridBagConstraints.BOTH;
    con.weightx = 1;
    con.weighty = 1;
    con.gridx = 0;
    con.gridy = 1;
    main.add(graph,con);
    buttons.add(filename);
    openfile.addActionListener(new OpenFileAction());
    buttons.add(openfile);
    clearfile.addActionListener(new ClearFileAction());
    buttons.add(clearfile);
    
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