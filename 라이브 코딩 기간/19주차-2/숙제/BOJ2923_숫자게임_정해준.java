import java.util.PriorityQueue;
import java.util.Scanner;

public class BOJ2923_숫자게임_정해준 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] cntA = new int[100];
        int[] cntB = new int[100];
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();

            cntA[a]++;
            cntB[b]++;

            int pivot1 = 1;
            int pivot2 = 99;
            int A = cntA[pivot1];
            int B = cntB[pivot2];
            int answer = 0;

            while (pivot1 <= 99 && pivot2 >= 1) {
                // A 값이 없으면 다음
                while (pivot1 <= 99 && A == 0) {
                    pivot1++;
                    if (pivot1 <= 99) A = cntA[pivot1];
                }

                // B 값이 없으면 이전
                while (pivot2 >= 1 && B == 0) {
                    pivot2--;
                    if (pivot2 >= 1) B = cntB[pivot2];
                }

                // 포인터가 벗어나면 종료
                if (pivot1 > 99 || pivot2 < 1) break;


                answer = Math.max(answer, pivot1 + pivot2);


                int take = Math.min(A, B);
                A -= take;
                B -= take;
            }

            // 현재 라운드의 결과
            sb.append(answer).append("\n");
        }
        System.out.print(sb);
    }
}
