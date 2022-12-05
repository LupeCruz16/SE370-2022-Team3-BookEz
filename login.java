import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class login extends JPanel implements ActionListener{
    
    private JButton log, newAccount;
    private JTextField user, pass;
    private JLabel name, newLine, userField, passField;

    public login(){

        //setLayout(new BorderLayout());

        //panels for locations

        JPanel main = new JPanel();
        JPanel title = new JPanel();
        JPanel userName = new JPanel();
        JPanel password = new JPanel();
        JPanel button = new JPanel();
        JPanel space = new JPanel();
        /* 
        JPanel topPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel bottomPanel = new JPanel();
        */

        //creating buttons
        log = new JButton("Login");//creating login button 
        log.addActionListener(this);//monitor if clicked
        newAccount = new JButton("Create new account");//creating new account button
        newAccount.addActionListener(this);//monitor if clicked
        
        //creating lables
        name = new JLabel("BookEZ");
        name.setFont(new Font("Arial", Font.BOLD, 40));//resizing text within label
        userField = new JLabel("Username: ");
        userField.setFont(new Font("Arial", Font.PLAIN, 15));
        passField = new JLabel("Password: ");
        passField.setFont(new Font("Arial", Font.PLAIN, 15));
        newLine = new JLabel("");

        //creating text field for user entry 
        user = new JTextField(20);//create text field for username
        pass = new JTextField(20);//create text field for password

        //adding elements into the panels

        title.add(name);
        userName.add(userField);
        userName.add(user);
        password.add(passField);
        password.add(pass);
        button.add(log);
        button.add(newAccount);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(title);
        main.add(space);
        main.add(userName);
        main.add(password);
        main.add(button);

        /*
        topPanel.add(welcome);
        midPanel.add(userField);
        midPanel.add(user);
        midPanel.add(passField);
        midPanel.add(pass);
        */

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

        //adding other buttons to panel 
        /* 
        bottomPanel.add(log); 
        bottomPanel.add(newAccount);
        */

        //ordering displayes of panels
        add(main);
        /*
        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        */

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
