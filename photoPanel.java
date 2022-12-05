import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.lang.reflect.*;

public class photoPanel extends JPanel implements ActionListener{
    
    private JButton back, upload;
    private JLabel name,prompt, blank;
    static JTextField fileList;

    public photoPanel(){

        //creating JPanels  
        JPanel main = new JPanel();
        JPanel space = new JPanel();
        JPanel title = new JPanel();
        JPanel message = new JPanel();
        JPanel file = new JPanel();
        JPanel button = new JPanel();
 
        //creating JButtons 
        back = new JButton("<-");//create back button 
        back.addActionListener(this);//monitor if clicked 
        upload = new JButton("Select Files");
        upload.addActionListener(this);

        //creating JLabels
        name = new JLabel("Upload Files");
        name.setFont(new Font("Arial", Font.PLAIN, 25));//resizing text within label
        blank = new JLabel("");
        prompt = new JLabel("Ensure files are in a PDF format");
        prompt.setFont(new Font("Arial", Font.PLAIN, 15));//resizing text within label
        
        //creating text field
        fileList = new JTextField(40);
        fileList.setBounds(80, 20, 250, 20);

        //adding elements to panels 

        title.add(name);
        space.add(blank);
        message.add(prompt);
        file.add(upload);
        file.add(fileList);
        button.add(back);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(title);
        main.add(space);
        main.add(message);
        main.add(file);
        main.add(button);

        //ordering panels in borderlayout
        add(main);

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
