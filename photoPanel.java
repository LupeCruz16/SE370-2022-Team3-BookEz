import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.lang.reflect.*;

public class photoPanel extends JPanel implements ActionListener{
    
    private JButton back, upload;
    private JLabel prompt;
    static JTextField fileList;

    public photoPanel(){

        setLayout(new BorderLayout());

        //creating JPanels  
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        JPanel leftPanel = new JPanel();

        //creating JButtons 
        back = new JButton("<-");//create back button 
        back.addActionListener(this);//monitor if clicked 
        upload = new JButton("Select Files");
        upload.addActionListener(this);

        //creating JLabels
        prompt = new JLabel("Ensure files are in a PDF format");
        prompt.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        
        //creating text field
        fileList = new JTextField(40);
        fileList.setBounds(80, 20, 250, 20);

        //adding elements to panels 
        topPanel.add(prompt);
        leftPanel.add(upload);
        middlePanel.add(fileList);
        bottomPanel.add(back); 

        //ordering panels in borderlayout
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //displaying panel
        setVisible(true);
        setSize(500, 500);

    }//end of photoPanel

    //if button is clocked, move to a different panel/preform actions
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == upload){//uploading order reciepts
            try{
                Class<?> c = ROIManager.class;
                Object o = c.getDeclaredConstructor().newInstance();

                Method m = ROIManager.class.getDeclaredMethod("readInFiles");
                m.setAccessible(true);
                m.invoke(o);

            }catch(Exception ex){//catching exception thrown for invalid document inputs
                System.out.println("Exception thrown: " + ex);//printing error message 
            } 
        }
        else if(e.getSource() == back){//returning to homescreen
            controller.getInstance().changeCard("Homescreen");
            fileList.setText("");//reset text field to be empty
        }

    }//end of actionPreformed 

}//end of class
