import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ2212_센서 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] arr = new int[N];
        for(int i = 0; i < N; i++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int i = 0; i < N- 1; i++){
            pq.offer(arr[i+1] - arr[i]);
        }

        int sum = 0;
        for(int i = 0; i < N - K; i++){
            sum += pq.poll();
        }

        System.out.println(sum);
    }
}
