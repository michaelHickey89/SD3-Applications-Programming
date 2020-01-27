package solution_using_stringtokenizer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import sd3.com.model.Name;

/**
 *
 * @author alan.ryan
 */
public class LabExercise9V1 {

    static List<Name> nameList = new ArrayList();

    public static void main(String[] args) {
        Path p = Paths.get("CA.txt");

        List<String> lines = null;
        try {
            lines = Files.readAllLines(p);
        } catch (IOException ex) {
            System.err.println(ex);
        }
 
        if (lines == null || lines.isEmpty()) {
            System.err.println("ERROR: The requested file appears to be empty");
            System.exit(0);
        }    
            
        lines.remove(0); //remove file header with the col headings
        
        for (String line : lines) {
            nameList.add(splitRecord(line)); //convert each record in the file to a Name object
        }

        ExecutorService exe = Executors.newCachedThreadPool();
        String searchName = "Zack";
        int year1 = 2004;
        int year2 = 2017;
        
        
        exe.submit(new Print(nameList, searchName));
        
        
        Future<Double> f1 = exe.submit(new Search(nameList, searchName, year1, year2));

        NumberFormat nf = NumberFormat.getPercentInstance();
        nf.setMaximumFractionDigits(2);
        try {
           System.out.format("Percentage Diff for '%s' when using %d and %d for comparision is %s\n", searchName, year1, year2, nf.format(f1.get()));
        } catch (Exception ex) {
            System.err.println(ex);
        }
       

        exe.shutdown();
              
    }

    private static Name splitRecord(String line) {

        String timeZone;
        String gender;
        int year;
        String name;
        int occurrences;

        StringTokenizer st = new StringTokenizer(line);

        while (st.hasMoreElements()) {

            timeZone = st.nextToken();
            gender = st.nextToken();
            year = Integer.parseInt(st.nextToken());
            name = st.nextToken();
            occurrences = Integer.parseInt(st.nextToken());

           return new Name(timeZone, gender, year, name, occurrences);

        }//end while


        return null; //shouldn't get here
    }//end method splitRecord

}//end class


class Print implements Runnable {
    List<Name> list;
    String name;
 
    public Print(List<Name> list, String name) {
        this.list = list;
        this.name = name;
    }
     
    @Override
    public void run() {
        int counter = 0;
        for (Name n : list) {
            if(n.getName().equalsIgnoreCase(name)) {
                System.out.println(n.getName() + "\t" + n.getYear() + "\t" + n.getOccurrences());
                counter++;
            }
            
        }
     System.out.println(name + " was found " + counter + " times");
    }//end run
}//end class Print
 
 
class Search implements Callable<Double> {
 
    List<Name> list;
    String name;
    int y1;
    int y2;
 
    public Search(List<Name> list, String name, int y1, int y2) {
        this.name = name;
        this.list = list;
        this.y1 = y1;
        this.y2 = y2;
    }
 
    @Override
    public Double call() throws Exception {
        int totalYr1 = 0, totalYr2 = 0;
         
        for (Name aName : list) {
            if (aName.getYear() == y1 & aName.getName().equalsIgnoreCase(name)) {
               totalYr1 = aName.getOccurrences();
            }
            if (aName.getYear() == y2 & aName.getName().equalsIgnoreCase(name)) {
                totalYr2 = aName.getOccurrences();
            }
        }
 
        int diff = totalYr1 - totalYr2;
        return  (double) diff/totalYr1;
         
         
             
    }
 
}