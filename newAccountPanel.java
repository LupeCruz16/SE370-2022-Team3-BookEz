import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class newAccountPanel extends JPanel implements ActionListener{
    
    private JButton back, signUp;
    private JLabel createMessage, userField, emailField, passField, passConfirm;
    private JTextField user, email, pass, passC;

    public newAccountPanel(){
        setLayout(new BorderLayout());

        //panels for each element
        JPanel topPanel = new JPanel();
        JPanel middlePanel = new JPanel();        
        JPanel bottomPanel = new JPanel();

        //creating buttons 
        back = new JButton("<-");//creating back button 
        back.addActionListener(this);//monitor if clicked
        signUp = new JButton("Sign Up");//creating sign up button
        signUp.addActionListener(this);//moitor if clicked

        //creating labels 
        createMessage = new JLabel("Create Account");
        createMessage.setFont(new Font("Arial", Font.PLAIN, 20));//resizing text within label
        userField = new JLabel("Username: ");
        userField.setFont(new Font("Arial", Font.PLAIN, 15));
        emailField = new JLabel("Email: ");
        emailField.setFont(new Font("Arial", Font.PLAIN, 15));
        passField = new JLabel("Password: ");
        passField.setFont(new Font("Arial", Font.PLAIN, 15));
        passConfirm = new JLabel("Confirm Password: ");
        passConfirm.setFont(new Font("Arial", Font.PLAIN, 15));

        //creating textfields 
        user = new JTextField(20);
        email = new JTextField(20);
        pass = new JTextField(20);
        passC = new JTextField(20);

        //blocks user from clicking sign up when username and password fields are empty 
        signUp.setEnabled(false);

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
                Boolean filled = !user.getText().trim().isEmpty() && !email.getText().trim().isEmpty() && !pass.getText().trim().isEmpty() && !passC.getText().trim().isEmpty();
                if(filled){
                    if(pass.getText().equals(passC.getText())){
                        signUp.setEnabled(filled);
                    }
                    else {
                        signUp.setEnabled(false);
                    }
                }
                
                //signUp.setEnabled(filled);
            }
        };
        //adding document listener to both username and password fields 
        user.getDocument().addDocumentListener(docListener);
        email.getDocument().addDocumentListener(docListener);
        pass.getDocument().addDocumentListener(docListener);
        passC.getDocument().addDocumentListener(docListener);

        //creating grid layout for middle panels format
        middlePanel.setLayout(new GridLayout(4, 2, 0, 200));

        //adding elements into each panel
        topPanel.add(createMessage);

        middlePanel.add(userField);
        middlePanel.add(user);
        middlePanel.add(emailField);
        middlePanel.add(email);
        middlePanel.add(passField);
        middlePanel.add(pass);
        middlePanel.add(passConfirm);
        middlePanel.add(passC);

        bottomPanel.add(back); 
        bottomPanel.add(signUp);

        //ordering displayes of panels
        add(topPanel, BorderLayout.NORTH);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //displaying panel
        setVisible(true);
        setSize(500, 500);
    }

    //if button is clocked, move to a different panel
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == signUp){
            controller.getInstance().changeCard("Homescreen");
        }
        else if(e.getSource() == back){
            controller.getInstance().changeCard("Login");
        }
        //resetting entered fields to empty 
        user.setText("");
        email.setText("");
        pass.setText("");
        passC.setText("");
        signUp.setEnabled(false);//set signup button to false after leaving panel to do
    }
}//end of class
    