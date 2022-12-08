import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.lang.reflect.*;

public class photoPanel extends JPanel implements ActionListener{
    
    private JButton back, upload;
    private JLabel name,prompt, blank;
    static JTextArea fileList;
    //private JScrollPane scroll;

    public photoPanel(){
        
        Color ezBlue= new Color( 80,145,230);

        //creating JPanels  
        JPanel main = new JPanel();
        JPanel space = new JPanel();
        JPanel space2 = new JPanel();
        JPanel title = new JPanel();
        JPanel message = new JPanel();
        JPanel file = new JPanel();
        JPanel button = new JPanel();

        //creating JButtons 
        back = new JButton("<-");//create back button 
        back.addActionListener(this);//monitor if clicked 
        back.setForeground(ezBlue);

        upload = new JButton("Select Files");
        upload.addActionListener(this);
        upload.setForeground(ezBlue);

       //creating JLabels
       name = new JLabel("Upload Files");
       name.setFont(new Font("Arial", Font.BOLD, 35));//resizing text within label
       name.setForeground(Color.white);

       blank = new JLabel("");
       prompt = new JLabel("Ensure files are in a PDF format");
       prompt.setFont(new Font("Arial", Font.PLAIN, 15));//resizing text within label
       prompt.setForeground(Color.white);

        //creating text area 
        fileList = new JTextArea();
        fileList.setLineWrap(true);
        fileList.setWrapStyleWord(true);
        fileList.setBounds(80, 20, 400, 200);
        fileList.setEditable(false);


        //adding elements to panels 
        //title.setLayout(new BoxLayout(title, BoxLayout.Y_AXIS));
        title.add(name);
        title.setPreferredSize(new Dimension(820, 40));
        title.setBackground(ezBlue);

        space.add(blank);
        space.setPreferredSize(new Dimension(820, 10));
        space.setBackground(ezBlue);

        message.add(prompt);
        message.setPreferredSize(new Dimension(820, 30));
        message.setBackground(ezBlue);

        space2.add(blank);
        space2.setPreferredSize(new Dimension(820, 10));
        space2.setBackground(ezBlue);

        file.add(upload);
        file.add(fileList);
        button.add(back);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(title);
        main.add(space);
        main.add(message);
        main.add(space2);
        main.add(file);
        main.add(button);

        //adding for display
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