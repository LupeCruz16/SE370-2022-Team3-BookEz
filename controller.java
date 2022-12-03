import java.awt.CardLayout;
import javax.swing.*;
import java.awt.*;

public class controller extends JPanel{
    
    private static controller instance;

    JPanel cards;//create panel of cards
    //creating each classes panel to add to cards
    login loginPanel;
    main mainPanel;
    photoPanel photoPanel;
    roiPanel roiPanel;
    newAccountPanel accountPanel;

    public controller(){
        setLayout(new BorderLayout());
        setSize(500, 500);
        cards = new JPanel(new CardLayout());

        loginPanel = new login();
        mainPanel = new main();
        photoPanel = new photoPanel();
        roiPanel = new roiPanel();
        accountPanel = new newAccountPanel();

        cards.add(loginPanel, "Login");
        cards.add(mainPanel, "Homescreen");
        cards.add(photoPanel, "Upload Photos");
        cards.add(roiPanel, "View ROI Table");
        cards.add(accountPanel, "Create Account");

        add(cards);
        setVisible(true);
    }

    //dispalying homescreen panel to begin 
    private static void createAndDisplay(){
        JFrame frame = new JFrame("Login");

        instance = new controller();

        frame.getContentPane().add(instance);
        frame.setSize(800, 800);
        frame.setVisible(true);
    }

    public static void main(String[] args){
        createAndDisplay();
    }

    //to move between panels 
    public void changeCard(String card){
        CardLayout c1 = (CardLayout) (cards.getLayout());
        c1.show(cards, card);
    }

    public static controller getInstance(){
        return instance;
    }

}//end of home 
