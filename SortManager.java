import java.util.Collections;
import java.util.Vector;

public class SortManager{
    
    private static ROIManager roi = new ROIManager();
    private static Integer next;
    
     //sorting by profit made 
     //boolean type is for method of being sorted 
     public static void profitSort(boolean type){

        insertionSort(ROIManager.v, type);//sort based on type 

        try{

            roi.roiHeader();//write file header 

            //write vectors elements into file as they were sorted 
            for(int i = 0; i < ROIManager.v.size(); i++){
                roi.outputWriter(ROIManager.v.get(i), false);
            }

        }catch(Exception ex){//catching exception thrown for invalid document inputs
            System.out.println("Exception thrown: " + ex);//printing error message 
        } 

    }//end of highest profit sort

    static void insertionSort(Vector<String> v, boolean type){

        String total, shipCost, tax, profitC, soldPriceTemp, shipPaidTemp;
        double key, elem;
    
        //ittterate through entire vector starting at 1
        for(int i = 1; i < v.size(); i++){

            //reset next to 0
            next = 0;
            //obtain necessary info for profit calculation
            total = convertAndFind(v.get(i), "$", next, 0);
            shipCost = convertAndFind(v.get(i), "Cost: ", next, 6);
            soldPriceTemp = convertAndFind(v.get(i), "$", next, 0);
            shipPaidTemp = convertAndFind(v.get(i), "$", next, 0);
            tax = convertAndFind(v.get(i), "$", next, 0);

            //calculating profit from vectors i position
            profitC = roi.profitCalc(total, shipCost, tax);
            key = Double.parseDouble(profitC.substring(1));//key starts at 1

            int j = i - 1;//create integer j that starts at 0

            next = 0;//reset next to 0
            //obtain necessary info for profit calculation
            total = convertAndFind(v.get(j), "$", next, 0);
            shipCost = convertAndFind(v.get(j), "Cost: ", next, 6);
            soldPriceTemp = convertAndFind(v.get(j), "$", next, 0);
            shipPaidTemp = convertAndFind(v.get(j), "$", next, 0);
            tax = convertAndFind(v.get(j), "$", next, 0);

            //calculating profit from vectors i position
            profitC = roi.profitCalc(total, shipCost, tax);
            elem = Double.parseDouble(profitC.substring(1));//element starts at 0

            if(type == true){//highest to lowest sort 
                while(j >= 0 && elem < key){
                    Collections.swap(v, j + 1, j);
                    j = j - 1;
                }
            } else if (type == false){//lowest to highest sort
                while(j >= 0 && elem > key){
                    Collections.swap(v, j + 1, j);
                    j = j - 1;
                }
            }
        }

    }//end insertion sort

    //utility function, for finding a string at the end of a line 
    static String convertAndFind(String docText, String id, int idInt, int mod){
        String result;//result to be returned 
        next = idInt;//setting to idInt 

        int startResult = docText.indexOf(id, next);//finding the string from next location
        int endResult = docText.indexOf("\n", startResult);//finding end of line 
        result = docText.substring(startResult + mod, endResult);//getting result from positions 

        next = endResult;//setting to the end of result in order to continue to look through the text
        return result;//returning result 

    }//end of convert and find 

}//end of Sort Managerz