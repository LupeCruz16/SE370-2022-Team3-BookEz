import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.FileReader;
import java.io.BufferedReader;

public class roiPanel extends JPanel implements ActionListener{
    
    private JButton back, display;
    private JTextArea roiTable;

    public roiPanel(){
        
        setLayout(new BorderLayout());

        //creating panels
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //creating buttons 
        back = new JButton("<-");//creating back button 
        back.addActionListener(this);//monitor if clicked
        display = new JButton("Display ROI Table"); 
        display.addActionListener(this);


        //creating JTextArea 
        roiTable = new JTextArea("Empty");

        final JTextArea edit = new JTextArea(10, 60);
        edit.setText("one\ntwo\nthree");
        edit.append("\nfour\nfive");

        //adding element into panels
        topPanel.add(display);
        middlePanel.add(roiTable); 
        bottomPanel.add(back); 

        //formatting panels 
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //displaying panel
        setVisible(true);
        setSize(500, 500);
    }

    //if button is clocked, move to a different panel
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == display){
            try
            {  
                FileReader reader = new FileReader(ROIManager.output.getPath());
                BufferedReader br = new BufferedReader(reader);
                roiTable.read( br, null );
                br.close();
                roiTable.requestFocus();
            } catch(Exception e2) { 
                System.out.println(e2); 
            }
        }
        else if(e.getSource() == back){
            controller.getInstance().changeCard("Homescreen");
        }
    }
}
