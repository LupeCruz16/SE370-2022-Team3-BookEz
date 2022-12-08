import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class login extends JPanel implements ActionListener{
    
    private JButton log, newAccount;
    private JTextField user, pass;
    private JLabel name, newLine, userField, passField, prompt;

    public login(){

        //color creation
        Color ezBlue= new Color( 80,145,230);

        //panels for locations
        JPanel main = new JPanel();
        JPanel title = new JPanel();
        JPanel description = new JPanel();
        JPanel userName = new JPanel();
        JPanel password = new JPanel();
        JPanel button = new JPanel();
        JPanel space = new JPanel();


        //creating buttons
        log = new JButton("Login");//creating login button 
        log.addActionListener(this);//monitor if clicked
        log.setForeground(ezBlue);

        newAccount = new JButton("Create new account");//creating new account button
        newAccount.addActionListener(this);//monitor if clicked
        newAccount.setForeground(ezBlue);
        
        //creating labels
        name = new JLabel("BookEZ");
        name.setFont(new Font("Arial", Font.BOLD, 50));//resizing text within label
        name.setForeground(Color.white);

        prompt = new JLabel("Bookkeeping made easy");
        prompt.setFont(new Font("Arial", Font.PLAIN, 15));
        prompt.setForeground(Color.white);

        userField = new JLabel("Username: ");
        userField.setFont(new Font("Arial", Font.PLAIN, 15));
        userField.setForeground(ezBlue);

        passField = new JLabel("Password: ");
        passField.setFont(new Font("Arial", Font.PLAIN, 15));
        passField.setForeground(ezBlue);

        newLine = new JLabel("");

        //creating text field for user entry 
        user = new JTextField(20);//create text field for username
        pass = new JTextField(20);//create text field for password

        //adding elements into the panels

        //title.setLayout(new BoxLayout(title, BoxLayout.Y_AXIS));
        title.add(name);
        title.add(prompt);
        title.setPreferredSize(new Dimension(820, 70));
        title.setBackground(ezBlue);

        description.add(prompt);
        description.setPreferredSize(new Dimension(820, 35));
        description.setBackground(ezBlue);

        space.add(newLine);
        space.setPreferredSize(new Dimension(820, 10));
        space.setBackground(ezBlue);

        userName.add(userField);
        userName.add(user);
        password.add(passField);
        password.add(pass);
        button.add(log);
        button.add(newAccount);
        

        

        //Panel layout
        
        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(space);
        main.add(title, BorderLayout.NORTH);
        main.add(description);
        main.add(userName);
        main.add(password);
        main.add(button);


        //blocks user from clicking login when username and password fields are empty 
        log.setEnabled(false);

        //for checking jtextfields 
        DocumentListener docListener = new DocumentListener() {
            @Override
            public void removeUpdate(DocumentEvent e){
                checkForText();
            }
            @Override
            public void insertUpdate(DocumentEvent e){
                checkForText();
            }
            @Override
            public void changedUpdate(DocumentEvent e){
                checkForText();
            }

            private void checkForText(){
                boolean filled = !user.getText().trim().isEmpty() && !pass.getText().trim().isEmpty();
                log.setEnabled(filled);
            }
        };
        //adding document listener to both username and password fields 
        user.getDocument().addDocumentListener(docListener);
        pass.getDocument().addDocumentListener(docListener);

        //ordering displayes of panels
        add(main);

        //displaying panel
        setVisible(true);
        setSize(500, 500);

    }

    //if button is clocked, move to a different panel
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == log){
            controller.getInstance().changeCard("Homescreen");
        }
        else if(e.getSource() == newAccount){
            controller.getInstance().changeCard("Create Account");
        }
        //clearing textfields when ever leaving screen
        user.setText("");
        pass.setText("");
    }

}//end of class 
