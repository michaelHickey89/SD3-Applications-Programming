package src.sd3.com;

import java.math.BigInteger;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;


//This solution uses BigInteger

public class Exercise2V2 {
     
    static final int START = 10;
    static final int LIMIT = 10000;
    static final int ITERATIONS = 50;
 
    public static void main(String[] args) throws InterruptedException, ExecutionException {
 
         ExecutorService exe = Executors.newCachedThreadPool();
         Future<Integer> future = exe.submit(new FindLychrelV2(START, LIMIT, ITERATIONS));
         
         //output total of Lychrel numbers
         System.out.println("Count of Lychrel Numbers: " + future.get());
         exe.shutdown();
 
    }//end main
}//end class
 
class FindLychrelV2 implements Callable<Integer>{
     
    int START;
    int LIMIT;
    int ITERATIONS;
    
    BigInteger nextNum;
    String nextNumAsString;
 
    BigInteger numberReversed;
    StringBuilder numberReversedAsString;
 
    FindLychrelV2(int START, int LIMIT, int ITERATIONS) {
        this.START = START;
        this.LIMIT = LIMIT;
        this.ITERATIONS = ITERATIONS;
    }
 
    
    @Override
    public Integer call() throws Exception {
        int count = 0;
 
        for (int i = START; i < LIMIT; i++) {
 
            //convert next number in sequence to BigDecimal
            nextNum = new BigInteger(Integer.toString(i));
 
            for (int j = 1; j <= ITERATIONS; j++) {
 
                //reverse the current number
                numberReversed = new BigInteger(new StringBuffer(nextNum.toString()).reverse().toString());
 
                //add current number and its reverse together
                nextNum = nextNum.add(numberReversed);
 
                //convert both BigDecimals to Strings
                nextNumAsString = nextNum.toString();
                numberReversedAsString = new StringBuilder(nextNumAsString).reverse();
 
                //check if we have a palindrome
                if (nextNumAsString.equals(numberReversedAsString.toString())) {
                    break;
                }//end if
 
                //check iterations
                if (j == ITERATIONS) {
                    System.out.println(i);
                    count++;
                }//end if
 
            }//end for
        }//end for
 
        return count;
    }
}