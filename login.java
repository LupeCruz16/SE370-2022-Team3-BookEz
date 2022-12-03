import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class login extends JPanel implements ActionListener{
    
    private JButton log, newAccount;
    private JTextField user, pass;
    private JLabel welcome, userField, passField;

    public login(){

        setLayout(new BorderLayout());

        //panels for locations
        JPanel topPanel = new JPanel();
        JPanel midPanel = new JPanel();
        JPanel bottomPanel = new JPanel();

        //creating buttons
        log = new JButton("Login");//creating login button 
        log.addActionListener(this);//monitor if clicked
        newAccount = new JButton("Create new account");//creating new account button
        newAccount.addActionListener(this);//monitor if clicked
        
        //creating lables
        welcome = new JLabel("Login");
        welcome.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        userField = new JLabel("Username: ");
        userField.setFont(new Font("Arial", Font.PLAIN, 15));
        passField = new JLabel("Password: ");
        passField.setFont(new Font("Arial", Font.PLAIN, 15));

        //creating text field for user entry 
        user = new JTextField(20);//create text field for username
        pass = new JTextField(20);//create text field for password

        //adding elements into the panels
        topPanel.add(welcome);

        midPanel.add(userField);
        midPanel.add(user);
        midPanel.add(passField);
        midPanel.add(pass);

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
        bottomPanel.add(log); 
        bottomPanel.add(newAccount);

        //ordering displayes of panels
        add(topPanel, BorderLayout.NORTH);
        add(midPanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

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
