package src.sd3.com;
 
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercise2V1 {
 
   public static boolean isLychrel(long number, int iterations) {
 
      for (int i = 0; i < iterations; i++) {
         number = number + reverse(number);
         if (isPalindrome(number)) {
            return false;
         }
      }
      return true;
   }
 
   private static boolean isPalindrome(final long number) {
      return number == reverse(number);
   }
 
   private static long reverse(long number) {
      long reverse = 0;
 
      while (number > 0) {
         long remainder = number % 10;
         reverse = (reverse * 10) + remainder;
         number = number / 10;
      }
      return reverse;
   }
 
   public static void main(String args[]) throws InterruptedException, ExecutionException {
       
         ExecutorService exe = Executors.newCachedThreadPool();
         Future<Integer> future = exe.submit(new FindLyhrelV1(10000, 50));
         
         
         //output total of Lychrel numbers
         System.out.println("Count of Lychrel Numbers: " + future.get());
         exe.shutdown();
   
   }
}

class FindLyhrelV1 implements Callable<Integer> {

    int LIMIT;
    int ITERATIONS;

    public FindLyhrelV1(int LIMIT, int ITERATIONS) {

        this.LIMIT = LIMIT;
        this.ITERATIONS = ITERATIONS;
    }
    
    
    @Override
    public Integer call() throws Exception {
      int count = 0;
      long number = 1;
      while (number <= LIMIT) {
         if (Exercise2V1.isLychrel(number, ITERATIONS)) {
            System.out.println(number);
            count++;
         }
         number++;
      }    
    
      return count;
    }
    
}

