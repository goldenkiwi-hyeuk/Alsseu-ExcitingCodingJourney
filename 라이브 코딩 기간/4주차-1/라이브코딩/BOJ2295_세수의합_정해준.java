// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class BOJ2295_세수의합_정해준 {
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        int[] arr = new int[N];
        Set<Integer> set = new HashSet<>(); // 조합을 넣을 자료구조

        for(int i = 0 ; i < N; i++) { // N;
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        for(int i = 0; i < N; i++) { //N^2
            for(int j = 0; j < N; j++) {
                set.add(arr[i] + arr[j]);
            }
        }

        for(int i = N - 1; i >= 1;i--){
            for(int j = 0; j < i; j++) {
                int tmp = arr[i] - arr[j];
                if(set.contains(tmp)) {
                    System.out.println(arr[i]);
                    return;
                }
            }
        }



    }

}