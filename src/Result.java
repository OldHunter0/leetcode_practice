import java.util.List;

public class Result {
    /*
     * Complete the 'fizzBuzz' function below.
     *
     * The function accepts INTEGER n as parameter.
     */

    public static void fizzBuzz(int n) {
        // Write your code here
        for(int i=1;i<=n;i++){
            boolean flag=false;
            if(i%3==0) {
                System.out.print("Fizz");
                flag=true;
            }
            if(i%5==0) {
                System.out.print("Buzz");
                flag=true;
            }
            if(!flag) System.out.print(i);
            System.out.println();
        }
    }

    public static int balancedSum(List<Integer> arr) {
        // Write your code here
        int sum=0,leftSum=0;
        for (Integer num : arr) {
            sum+=num;
        }
        for (int i = 0; i < arr.size(); i++) {
            if(leftSum==sum-leftSum- arr.get(i))   return i;
            leftSum+=arr.get(i);
        }
        return -1;
    }

    public static int updateTimes(List<Integer> signalOne, List<Integer> signalTwo) {
        // Write your code here
        int m=signalOne.size();
        int n=signalTwo.size();
        int update=0;
        int max=-1;
        int N = Math.min(m, n);
        for (int i = 0; i < N; i++) {
            Integer one = signalOne.get(i);
            Integer two = signalTwo.get(i);
            if(one.equals(two)&&one>max){
                max=one;
                update++;
            }
        }
        return update;
    }
}
