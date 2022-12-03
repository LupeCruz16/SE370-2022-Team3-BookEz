import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.pdfbox.pdmodel.PDDocument; 
import org.apache.pdfbox.text.PDFTextStripper;
import java.io.PrintWriter;

public class ROIManager {

    static File output;

    //user selecting files from device 
    private static void readInFiles(){

        JFileChooser fileUpload = new JFileChooser();//creating file chooser//testing 
        fileUpload = new JFileChooser();//creating file chooser
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PDF Documents", "pdf");
        fileUpload.setFileFilter(filter);//applying pdf filter to files uploaded
        
        fileUpload.setMultiSelectionEnabled(true);//testing 

        //int response = fileUpload.showSaveDialog(null);//saves users device for file selection
        int response = fileUpload.showOpenDialog(null);//saves users device for file selection

        roiHeader();//writing in roi header to text file

        if(response == JFileChooser.APPROVE_OPTION){//make sure file selecteds path is retrieved
        
            try{
                File files[] = fileUpload.getSelectedFiles(); 

                for(File file : files){
                 
                    String path = file.getAbsolutePath();

                    System.out.println(path);

                    photoPanel.fileList.setText(path);//displaying what file was uploaded 

                    FileInputStream fis = new FileInputStream(path);//create new input stream

                    PDDocument pdfDocument = PDDocument.load(fis);//load in pdf document 
                    PDFTextStripper pdfTextStripper = new PDFTextStripper();//obtain text
                    String docText = pdfTextStripper.getText(pdfDocument);//turning text into string 

                    extractInfo(docText);//getting info from each pdf

                    pdfDocument.close();//closing document
                    fis.close();//closing file input stream

                }
                
            } catch(java.io.IOException ex){//catching exception thrown for invalid document inputs
                System.out.println("File cannot be opened: " + ex);//printing error message 
            } 
        }

    }//end of readInFiles

    //used to extract the desired information from an ebay order reciept 
    private static void extractInfo(String s){

        String orderNum, total, shipCost, soldPrice, shipPaid, tax;

        //gets order number
        int orderNumStart = s.indexOf("Order");
        int orderNumEnd = s.indexOf("\n");
        orderNum = s.substring(orderNumStart + 13, orderNumEnd);

        //gets order total
        int totalStart = s.indexOf("$", orderNumEnd);//looks for order after end of order number
        int totalEnd = s.indexOf("\n", totalStart);
        total = s.substring(totalStart, totalEnd);
        
        //gets shipping cost
        int shipCostStart = s.indexOf("Cost: ");
        int shipCostEnd = s.indexOf("\n", shipCostStart);
        shipCost = s.substring(shipCostStart + 6, shipCostEnd);

        //gets what item sold for 
        int soldPriceStart = s.indexOf("$", shipCostEnd);
        int soldPriceEnd = s.indexOf("\n", soldPriceStart);
        soldPrice = s.substring(soldPriceStart, soldPriceEnd);

        //gets buyer paid for shipping
        int shipPaidStart = s.indexOf("$", soldPriceEnd);
        int shipPaidEnd = s.indexOf("\n", shipPaidStart);
        shipPaid = s.substring(shipPaidStart, shipPaidEnd);

        //gets sales tax
        int taxStart = s.indexOf("$", shipPaidEnd);
        int taxEnd = s.indexOf("\n", taxStart);
        tax = s.substring(taxStart, taxEnd);

        //calculating profit after costs
        String profitC = profitCalc(total, shipCost, tax);

        addInfoToTable(orderNum, total, shipCost, soldPrice, shipPaid, tax, profitC);//creating .text file with information read in

    }//end of extractInfo

    private static String profitCalc(String total, String shipCost, String tax){
        double profit = 0.0;
        
        //turning each string into a double for calculations 
        double t = Double.parseDouble(total.substring(1));
        double sC = Double.parseDouble(shipCost.substring(1));
        double ta = Double.parseDouble(tax.substring(1));
        
        profit = t - sC - ta;//calculating profit 
        
        profit = Math.round(profit * 100) / 100.0;//rounding off for set precision 
        String profitString = "$" + profit + "";//changing to string and adding $ for formatting

        return profitString;//returning profit obtained 
    }
    
    //error here in reopening and writing into file /////////////////////////////////////////////////////////
    private static void addInfoToTable(String orderNum, String total, String shipCost, String soldPrice, String shipPaid, String tax, String profit){
        
        try(FileWriter writer = new FileWriter("output.txt", true);
            BufferedWriter bw = new BufferedWriter(writer);
            PrintWriter out = new PrintWriter(bw))
        {

            out.println(total + "\t" + soldPrice + "\t" + shipPaid + "\t" + shipCost + "\t" + tax + "\t" + profit + "\t" + orderNum + "\n");
            
        } catch(java.io.IOException ex){//catching exception thrown for invalid document inputs
            System.out.println("File cannot be opened: " + ex);//printing error message 
            System.out.println("Error here");
        } 
    
    }//end of creating ROI table 

    private static void roiHeader(){
        output = new File("output.txt");

        try{
            FileWriter writer = new FileWriter(output);

            writer.write("ROI Table\n");
            writer.write("Order Total\tSold For\tShip Charged\tShip Cost\tTaxes\tProfit\tOrder Number\t\n");
           
            writer.flush();
            writer.close();
            
        } catch(java.io.IOException ex){//catching exception thrown for invalid document inputs
            System.out.println("File cannot be opened: " + ex);//printing error message 
        } 
    }//end of ROI Header 

}//end of class

