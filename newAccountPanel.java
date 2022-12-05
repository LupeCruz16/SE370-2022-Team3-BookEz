import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class newAccountPanel extends JPanel implements ActionListener{
    
    private JButton back, signUp;
    private JLabel createMessage, blank1, blank2, userField, emailField, passField, passConfirm;
    private JTextField user, email, pass, passC;

    public newAccountPanel(){

        //panels for each element
        JPanel main = new JPanel();
        JPanel title = new JPanel();
        JPanel userName = new JPanel();
        JPanel emailAcc = new JPanel();
        JPanel passWord = new JPanel();
        JPanel confirm = new JPanel();
        JPanel button = new JPanel();
        JPanel space1 = new JPanel();
        JPanel space2 = new JPanel();

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
        blank1 = new JLabel("");
        blank2 = new JLabel("");

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
        title.add(createMessage);
        space1.add(blank1);
        space2.add(blank2);

        userName.setLayout(new GridLayout());
        userName.add(userField);
        userName.add(user);

        emailAcc.setLayout(new GridLayout());
        emailAcc.add(emailField);
        emailAcc.add(email);

        passWord.setLayout(new GridLayout());
        passWord.add(passField);
        passWord.add(pass);

        confirm.setLayout(new GridLayout());
        confirm.add(passConfirm);
        confirm.add(passC);

        button.add(back);
        button.add(signUp);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(title);
        main.add(space1);
        main.add(userName);
        main.add(emailAcc);
        main.add(passWord);
        main.add(confirm);
        main.add(space2);
        main.add(button);

        //ordering display of panels
        add(main);

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
    