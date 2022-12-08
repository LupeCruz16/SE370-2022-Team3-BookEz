import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.io.BufferedReader;

public class roiPanel extends JPanel implements ActionListener{
    
    private JButton back, display, highProfitSort, lowProfitSort;
    private JTextArea roiTable;
    private JLabel sortMess;

    Color ezBlue= new Color( 80,145,230);

    public roiPanel(){
        
        setLayout(new BorderLayout());

        //creating panels
        JPanel topPanel = new JPanel();
        //topPanel.setPreferredSize(new Dimension(100, 100));
        JPanel leftPanel = new JPanel();
        leftPanel.setPreferredSize(new Dimension(115, 500));
        JPanel middlePanel = new JPanel();
        //middlePanel.setPreferredSize(new Dimension(200, 200));
        JPanel bottomPanel = new JPanel();

        //creating buttons 
        back = new JButton("<-");//creating back button 
        back.addActionListener(this);//monitor if clicked
        back.setForeground(ezBlue);

        display = new JButton("Display ROI Table"); 
        display.addActionListener(this);
        display.setForeground(ezBlue);

        highProfitSort = new JButton("Highest Profit");
        highProfitSort.addActionListener(this);
        highProfitSort.setForeground(ezBlue);

        lowProfitSort = new JButton("Lowest Profit");
        lowProfitSort.addActionListener(this);
        lowProfitSort.setForeground(ezBlue);

        //creating JTextAreas
        roiTable = new JTextArea("Empty");

        //creating JLables
        sortMess = new JLabel("Sort By: ");
        sortMess.setForeground(ezBlue);

        //adding element into panels
        topPanel.add(display);
        leftPanel.add(sortMess);
        leftPanel.add(highProfitSort);
        leftPanel.add(lowProfitSort);
        middlePanel.add(roiTable);
        bottomPanel.add(back); 

        //formatting panels 
        add(topPanel, BorderLayout.NORTH);
        add(leftPanel, BorderLayout.WEST);
        add(middlePanel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        //displaying panel
        setVisible(true);
        setSize(500, 500);
    }

    //if button is clocked, move to a different panel
    public void actionPerformed(ActionEvent e){
        //checking if display or a sotring button was clicked 
        if(e.getSource() == display || e.getSource() == highProfitSort || e.getSource() == lowProfitSort){
            //if sort was clicked 
            if(e.getSource() == highProfitSort || e.getSource() == lowProfitSort){
                try{
                    Class<?> c = SortManager.class;//create instance of class 
                    Object o = c.getDeclaredConstructor().newInstance();

                    //utilize method to obtain function needed to be used
                    Method m = SortManager.class.getDeclaredMethod("profitSort", new Class[]{boolean.class});
                    m.setAccessible(true);

                    //based on source, sort and print to text file
                    if(e.getSource() == highProfitSort){
                        m.invoke(o, true);
                    } else if(e.getSource() == lowProfitSort){
                        m.invoke(o, false);
                    }
                    
                }catch(Exception ex){//catching exception thrown for invalid document inputs
                    System.out.println("Exception thrown: " + ex);//printing error message 
                } 

            } 
            //default to display roi table
            try
                {  
                    FileReader reader = new FileReader(ROIManager.output.getPath());
                    BufferedReader br = new BufferedReader(reader);
                    roiTable.read( br, null );
                    br.close();
                    roiTable.requestFocus();
                } catch(Exception e2) { 
                    System.out.println(e2); 
                }
        }
        else if(e.getSource() == back){
            controller.getInstance().changeCard("Homescreen");
        }
    }
}