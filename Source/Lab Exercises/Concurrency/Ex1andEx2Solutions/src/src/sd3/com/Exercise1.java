package src.sd3.com;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Exercise1 {

    static final int LIMIT = 1000; //don't use this

    static int[] numbers = {1, 1, 2, 5, 5, 3, 1, 1, 1, 3, 1, 1, 5, 3, 1, 4, 1, 1, 1, 4, 1, 1};

    public static void main(String[] args) throws InterruptedException, ExecutionException {

//        for (int i = 0; i < LIMIT; i++) {
//            numbers[i] = ThreadLocalRandom.current().nextInt(1, 5 + 1);
//        }
 
        Callable<Integer> oddTask = () -> {
            int oddCount = 0;
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] % 2 != 0) {
                    oddCount++;
                }
            }
            return oddCount;
            

        };

        Callable<Integer> evenTask = () -> {
            int evenCount = 0;
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] % 2 == 0) {
                    evenCount++;
                }
            }
           return evenCount;

        };
        
        
        Runnable printList = () -> {

            for (int i = 0; i < numbers.length; i++) {

                System.out.print(numbers[i] + " ");

                while (numbers[i] == 1) {

                    int n = numbers[i + 1];

                    if (n != 1) {
                        System.out.print(n + " ");
                    }

                    i++;
                }//end while

            }//end for
 
        };
        

        ExecutorService exe = Executors.newCachedThreadPool();
        Future<Integer> f1 = exe.submit(oddTask);
        Future<Integer> f2 = exe.submit(evenTask);
        exe.submit(printList);
        System.out.println("Odd Count " + f1.get());
        System.out.println("EvenCount " + f2.get());
        exe.shutdown();

  
    }
}
