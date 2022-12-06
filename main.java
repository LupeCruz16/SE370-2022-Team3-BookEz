import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class main extends JPanel implements ActionListener{
    
    private JButton photo, roi, exit;
    private JLabel appName;

    public main(){

        setLayout(new BorderLayout());//creating border layout

        //Creating Panels
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //Creating buttoms
        photo = new JButton("Upload Images");//creating uploading photos button 
        photo.addActionListener(this);//monitor if clicked 
        roi = new JButton("View ROI Table");//creating view ROI table button
        roi.addActionListener(this);//monitor if clicked
        exit = new JButton("Logout");//creating logout button 
        exit.addActionListener(this);//monitor if clicked

        //Creating Lables
        appName = new JLabel("Welcome to BookEz");
        appName.setFont(new Font("Arial", Font.BOLD, 40));//resizing text within label

        //Adding elements to panels 
        topPanel.add(appName);

        middlePanel.add(photo);
        middlePanel.add(roi); 

        bottomPanel.add(exit);

        //Setting panels within the borderlayout 
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //displaying panel
        setVisible(true);
        setSize(500, 500);

    }//end of main class
 
    //if button is clicked preform an action
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == photo){//if user wants to upload photos, move to photo panel
            controller.getInstance().changeCard("Upload Photos");
        }
        else if(e.getSource() == roi){
            controller.getInstance().changeCard("View ROI Table");
        }
        else if(e.getSource() == exit){
            controller.getInstance().changeCard("Login");
            File f = new File("output.text");

            if(f.exists()){
                ROIManager.output.delete();
            }
        }

    }//end of action preformed 

}//end of class 
