import java.awt.*;
import java.awt.event.*;
import java.io.File;

import javax.swing.*;

public class main extends JPanel implements ActionListener{
    
    private JButton photo, roi, exit;
    private JLabel appName, blank;

    public main(){

        //setLayout(new BorderLayout());//creating border layout

        //Creating Panels
        JPanel main = new JPanel();
        JPanel space = new JPanel();
        JPanel space2 = new JPanel();
        JPanel title = new JPanel();
        JPanel middle = new JPanel();
        JPanel button = new JPanel();

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
        blank = new JLabel("");
        //Adding elements to panels 

        title.add(appName);

        middle.setLayout(new GridLayout());
        middle.add(photo);
        middle.add(roi); 

        button.add(exit);
        space.add(blank);
        space2.add(blank);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(title);
        main.add(space);
        main.add(middle);
        main.add(space2);
        main.add(button);

        //Setting panels within the borderlayout 
        add(main);
        

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
