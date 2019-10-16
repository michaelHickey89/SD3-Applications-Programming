package lambdas.basic;

import java.util.Arrays;

public class TestMathOperation {

    public static void main(String[] args) {
        new TestMathOperation();
    }

    public TestMathOperation() {
         MathOperation addition = (int a, int b) -> a + b;
         System.out.println("10 + 20 = " + operate(10, 20, addition));
         
         MathOperation subtraction = (a, b) -> b - a;
         System.out.println("\n20 - 10 = " + operate(10, 20, subtraction));
         
         MathOperation multiplication = (int a, int b) -> { return a * b; };
         System.out.println("\n10 * 20 = " + operate(10, 20, multiplication));
         
         MathOperation division  = (int a, int b) -> b / a;
         System.out.println("\n20 / 10  = " + operate(10, 20, division));
         
         int[] arr = {1,2,3,4,5,6,7,8,9,10};
         
         CountOperation countOdd   = (int i) -> {  return i % 2 != 0;};
         CountOperation countEven  = (int i) -> {  return i % 2 != 0;};
         CountOperation countSeven = (int i) -> {  return i == 7;};
         CountOperation countPrime = (int i) -> {  
             if (i%2==0) return false;
                for(int j=3;j*j<=i;j+=2) {
                    if(j%i==0)
                        return false;
                }
                return true;
         };
       
       
        
         
        System.out.println("Number of odd numbers in " + Arrays.toString(arr) + countIt(arr, countOdd) );
        System.out.println("Number of even numbers in " + Arrays.toString(arr) + countIt(arr, countEven) );
        System.out.println("Number of 7's numbers in " + Arrays.toString(arr) + countIt(arr, countSeven) );
        System.out.println("Number of prime numbers in " + Arrays.toString(arr) + countIt(arr, countPrime) );
    
        Integer[] arr2 = {1,7,7,4};
        CountOperation1 countit = (Object o) -> { int i = (Integer)o; return i == 7;}; 
        System.out.println("test  generic " +  countIt2(arr2, countit) );
    
        String[] arr3 = {"Alan", "Aoife", "Erin", "Rebel"};
        CountOperation1 countit2 = (Object o) -> { String s = (String)o; return s.startsWith("A");}; 
        System.out.println(countIt2(arr3, countit2));
    }//end method
    
    public int operate(int a, int b, MathOperation math) {
        
        return math.operation(a, b);
    }//end method
    
    
    public int countIt(int arr[], CountOperation op) {
        int count = 0;
         for (int i : arr) {
             if (op.test(i)) 
                 count++;
         }
         
         return count;
        
    }
    public <T> int countIt2(T arr[], CountOperation1 op) {
        int count = 0;
         for (T i : arr) {
             if (op.test(i)) 
                 count++;
         }
         
         return count;
        
    }

}//end class
