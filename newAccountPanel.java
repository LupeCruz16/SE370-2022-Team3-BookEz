import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class newAccountPanel extends JPanel implements ActionListener{
    
    private JButton back, signUp;
    private JLabel createMessage, blank, userField, emailField, passField, passConfirm;
    private JTextField user, email, pass, passC;

    public newAccountPanel(){

        Color ezBlue= new Color( 80,145,230);

        //panels for each element
        JPanel main = new JPanel();
        JPanel create = new JPanel();
        JPanel title = new JPanel();
        JPanel userName = new JPanel();
        JPanel emailAcc = new JPanel();
        JPanel passWord = new JPanel();
        JPanel confirm = new JPanel();
        JPanel button = new JPanel();
        JPanel space = new JPanel();

        //creating buttons 
        back = new JButton("<-");//creating back button 
        back.addActionListener(this);//monitor if clicked
        back.setForeground(ezBlue);

        signUp = new JButton("Sign Up");//creating sign up button
        signUp.addActionListener(this);//moitor if clicked
        signUp.setForeground(ezBlue);

        //creating labels 
        createMessage = new JLabel("Create Account");
        createMessage.setFont(new Font("Arial", Font.BOLD, 40));//resizing text within label
        createMessage.setForeground(Color.white);

        userField = new JLabel("Username: ");
        userField.setFont(new Font("Arial", Font.PLAIN, 15));
        userField.setForeground(ezBlue);

        emailField = new JLabel("Email: ");
        emailField.setFont(new Font("Arial", Font.PLAIN, 15));
        emailField.setForeground(ezBlue);

        passField = new JLabel("Password: ");
        passField.setFont(new Font("Arial", Font.PLAIN, 15));
        passField.setForeground(ezBlue);

        passConfirm = new JLabel("Confirm Password: ");
        passConfirm.setFont(new Font("Arial", Font.PLAIN, 15));
        passConfirm.setForeground(ezBlue);

        blank = new JLabel(" ");

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
        title.setPreferredSize(new Dimension(820, 80));
        title.setBackground(ezBlue);

        space.add(blank);

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

        create.setLayout(new BoxLayout(create, BoxLayout.Y_AXIS));
        create.add(title);

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));
        main.add(userName);
        main.add(emailAcc);
        main.add(passWord);
        main.add(confirm);
        main.add(space);
        main.add(button);

        //ordering display of panels
        //add(space1);
        add(title);
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
    