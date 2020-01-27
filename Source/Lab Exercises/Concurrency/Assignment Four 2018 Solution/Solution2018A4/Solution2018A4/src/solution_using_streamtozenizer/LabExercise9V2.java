package solution_using_streamtozenizer;

import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import sd3.com.model.Name;

 
public class LabExercise9V2 {
 
    static List<Name> list = new ArrayList();
    static NumberFormat nf = NumberFormat.getPercentInstance();
     
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        parseFile("CA.txt");
 
        nf.setMaximumFractionDigits(2); 
        ExecutorService exe = Executors.newCachedThreadPool();
                
        String searchName = "Mary";
        int year1 = 1916;
        int year2 = 1912;
        
        Future<Double> f1 = exe.submit(new Search(list, searchName, year1, year2));
        System.out.format("Percentage Diff for '%s' when using %d and %d for comparision is %s\n", searchName, year1, year2, nf.format(f1.get())); 

        exe.submit(new Print(list, searchName));
        
        exe.shutdown();
    }
 
    private static void parseFile(String file) {
        FileReader frs;
        StreamTokenizer in;
 
        String timeZone;
        String gender; //could use a char instead of a String
        int year;
        String name;
        int occurrences;
 
        try {
            frs = new FileReader(file);
            in = new StreamTokenizer(frs);
 
            in.ordinaryChar('-');
 
            //read headers
 
            in.nextToken();
            in.nextToken();
            in.nextToken();
            in.nextToken();
            in.nextToken();
 
            in.nextToken();// first token proper
 
            while (in.ttype != StreamTokenizer.TT_EOF) {
               
                timeZone = in.sval;
                in.nextToken();
 
                timeZone += ((char) in.ttype);
                
                in.nextToken();
                 
                timeZone +=  in.nval;
                
                 
                in.nextToken();
                gender = in.sval;
 
                in.nextToken();
                year = (int) in.nval;//year
 
 
                in.nextToken();
                name = in.sval;
 
                in.nextToken();
                occurrences = (int) in.nval; //births
 
                in.nextToken();
 
                list.add(new Name(timeZone, gender, year, name, occurrences));
 
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
 
    }
 
}
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
    }
}
 
 
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
        int totals[] = new int[2];
         
        for (Name aName : list) {
            if (aName.getYear() == y1 & aName.getName().equalsIgnoreCase(name)) {
               totals[0] = aName.getOccurrences();
            }
            if (aName.getYear() == y2 & aName.getName().equalsIgnoreCase(name)) {
                totals[1] = aName.getOccurrences();
            }
        }
 
        int diff = totals[0] - totals[1];
        return  (double) diff/totals[0];
         
         
             
    }
 
}