import java.util.Scanner;

public class BOJ2283_구간자르기_정해준 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        int[] lines = new int[1000001];
        int max = -1;
        int min = 1000001;

        for(int i = 0; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            lines[a]++;
            lines[b]--; // 끝나기 때문에 누적합을 구할 때 빠짐을 표시

            min = Math.min(a, min);
            max = Math.max(b, max);
        }

        for(int i = min + 1; i <= max; i++) {
            lines[i] += lines[i - 1]; // 해당 구간까지의 누적합을 구함
        }

        int p1 = min, p2 = min;
        int A = 0, B = 0;
        int sum = 0;

        while(p2 <= max) {
            if(sum < K) { // 합이 목표값보다 작을 경우
                sum += lines[p2++]; // 오른쪽 포인터를 옮기면서 값을 더해줌
            } else if(sum == K) {
                A = p1;
                B = p2;
                break;
            } else { //왼쪽 포인터를 이동하면서 그전 값을 빼줌
                sum -= lines[p1++];
            }
        }

        if(A == min) // 0에서 짤라도 되기 때문
            A = 0;

        System.out.println(A + " " + B);

    }
}
