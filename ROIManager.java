import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.PrintWriter;
import java.util.Vector;

public class ROIManager{

    public static File output;//output.text file collects all information from PDFs
    public static Vector<String> v = new Vector<String>();
    private Integer nextEnd;

    //user selecting files from device 
    private void readInFiles(){

        JFileChooser fileUpload = new JFileChooser();//creating file chooser//testing 
        fileUpload = new JFileChooser();//creating file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileUpload.setFileFilter(filter);//applying pdf filter to files uploaded
        
        fileUpload.setMultiSelectionEnabled(true);//testing 

        int response = fileUpload.showOpenDialog(null);//saves users device for file selection

        roiHeader();//writing in roi header to text file

        if(response == JFileChooser.APPROVE_OPTION){//make sure file selecteds path is retrieved
        
            try{
                File files[] = fileUpload.getSelectedFiles();//array of files that contains selected files  

                for(File file : files){//itterate through collected files 
                 
                    //getting path name and convering into a displayable method for user 
                    String path = file.getAbsolutePath() + "\n";//collect path 
                    path = convertAndFind(path, "Ebay Orders/", 0, 12);
                    photoPanel.fileList.append(path + "\n");//displaying what file was uploaded

                    FileInputStream fis = new FileInputStream(file.getAbsolutePath());//create new input stream
                    PDDocument pdfDocument = PDDocument.load(fis);//load in pdf document 
                    PDFTextStripper pdfTextStripper = new PDFTextStripper();//obtain text
                    String docText = pdfTextStripper.getText(pdfDocument);//turning text into string 

                    outputWriter(docText, true);//getting info from each pdf and adding to output.text file

                    pdfDocument.close();//closing document
                    fis.close();//closing file input stream
                }
                
            } catch(java.io.IOException ex){//catching exception thrown for invalid document inputs
                System.out.println("File cannot be opened: " + ex);//printing error message 
            } 
        }

    }//end of readInFiles

    //used to extract the desired information from an ebay order reciept pdf and add new info to output.text file
    //boolean used to determine wether the extracted information should be stored in the vector
    protected void outputWriter(String s, boolean vector){

        //strings to collect information
        String orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC;
  
        nextEnd = 0;//setting int to 0 for each new file being read 

        //collecting each string segment from the files 
        orderNum = convertAndFind(s, "Order number ", nextEnd, 13);
        total = convertAndFind(s, "$", nextEnd, 0);
        shipCost = convertAndFind(s, "Cost: ", nextEnd, 6);
        soldPrice = convertAndFind(s, "$", nextEnd, 0);
        shipPaid = convertAndFind(s, "$", nextEnd, 0);
        tax = convertAndFind(s, "$",nextEnd, 0);

        //if info should be added to vector 
        if(vector == true){
            //if sales taxes were not collected, set tax to 0
            if(s.indexOf("Sales tax (eBay collected)") == -1){
                tax = "$0";
            }

            //adding extracted info into a vector of strings 
            v.add("Order number " + orderNum + "\n" + total + "\nCost: " + shipCost + "\n" + soldPrice + "\n" + shipPaid + "\n" + tax + "\n");//fix order
        }

        //calculating profit after costs and if sales tax was collected 
        profitC = profitCalc(total, shipCost, tax);

        //adding all collected information to output.text file
        addInfoToTable(orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC);

    }//end of extractInfo

    //utility function, for finding a string at the end of a line 
    String convertAndFind(String docText, String id, int idInt, int mod){
        String result;//result to be returned 
        nextEnd = idInt;//setting to idInt 

        int startResult = docText.indexOf(id, nextEnd);//finding the string from next location
        int endResult = docText.indexOf("\n", startResult);//finding end of line 
        result = docText.substring(startResult + mod, endResult);//getting result from positions 

        nextEnd = endResult;//setting to the end of result in order to continue to look through the text
        return result;//returning result 

    }//end of convert and find 

    //calculates profit
    String profitCalc(String total, String shipCost, String tax){

        double profit = 0.0;//used for calculations
        
        //turning each string into a double for calculations 
        double t = Double.parseDouble(total.substring(1));
        double sC = Double.parseDouble(shipCost.substring(1));
        double ta = Double.parseDouble(tax.substring(1));
        
        profit = t - sC - ta;//calculating profit 
        
        profit = Math.round(profit * 100) / 100.0;//rounding off for set precision 
        String profitString = "$%. 2f" + profit + "";//changing to string and adding $ for formatting

        return profitString;//returning profit obtained 

    }//end of profit calculation
    
    //adding each files information into output.text file 
    private static void addInfoToTable(String orderNum, String total, String shipCost, String soldPrice, String shipPaid, String tax, String profit){
        
        //try adding information to file
        try(FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw))
        {
            //adding in the information to file 
            out.println(total + "\t" + soldPrice + "\t" + shipPaid + "\t" + shipCost + "\t" + tax + "\t" + profit + "\t" + orderNum + "\n");
            
        } catch(java.io.IOException ex){//catching exception thrown for invalid document or document not existing 
            System.out.println("File cannot be opened: " + ex);//printing error message 
            System.out.println("Error here");
        } 
    
    }//end of creating ROI table 

    //wiriting in header for each column and file formatting 
    protected void roiHeader(){

        output = new File("output.txt");//creating output.text file 

        try{
            FileWriter writer = new FileWriter(output);//writing in file 

            //header information for each column 
            writer.write("ROI Table\n");
            writer.write("Order Total\tSold For\tShip Charged\tShip Cost\tTaxes\tProfit\tOrder Number\t\n");
           
            writer.flush();
            writer.close();//closing writer 
            
        } catch(java.io.IOException ex){//catching exception thrown for invalid document used
            System.out.println("File cannot be opened: " + ex);//printing error message 
        } 
    }//end of ROI Header 

}//end of class

