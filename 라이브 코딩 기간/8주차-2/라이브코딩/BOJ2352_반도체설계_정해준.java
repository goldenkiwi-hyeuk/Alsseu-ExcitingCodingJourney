// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class BOJ2352_반도체설계_정해준 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] answer = new int[N];
        int length = 1;
        int idx = 0;
        for(int i = 0; i < N; i++) {
            int n = sc.nextInt();
            if(i == 0){
                answer[0] = n;
            } else if(n > answer[length - 1]) { // 최대값일 경우 갱신
                answer[length] = n;
                length++;
            } else {
                idx = binary(answer, 0, length,n);
                answer[idx] = n;
            }
        }
        System.out.println(length);
    }


    public static int binary(int[] arr, int low, int high, int num){
        int mid = 0;
        while(low < high) {
            mid = (low + high) / 2;
            if(arr[mid] < num) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }
        return low;
    }

}