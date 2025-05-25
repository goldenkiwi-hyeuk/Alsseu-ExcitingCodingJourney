import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ2143_두배열의합_정해준 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long T = Long.parseLong(br.readLine());
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for(int i = 0; i < N; i++)
            A[i] = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        int[] B = new int[M];
        for(int i = 0; i < M; i++)
            B[i] = Integer.parseInt(st.nextToken());


        List<Integer> sumA = new ArrayList<>();
        List<Integer> sumB = new ArrayList<>();

        for(int i = 0; i < N; i++) {
            int sum = 0;
            for(int j = i; j < N; j++) {
                sum += A[j];
                sumA.add(sum);
            }
        }

        for(int i = 0; i < M; i++) {
            int sum = 0;
            for(int j = i; j < M; j++) {
                sum += B[j];
                sumB.add(sum);
            }
        }

        Collections.sort(sumA);
        Collections.sort(sumB, Collections.reverseOrder());

        long cnt = 0;
        int pA = 0, pB = 0;

        while(pA < sumA.size() && pB < sumB.size()) {
            int sA = sumA.get(pA);
            int sB = sumB.get(pB);
            long total = (long) sA + sB;

            if(total == T) {
                long cntA = 0, cntB = 0;
                while (pA < sumA.size() && sumA.get(pA) == sA) {
                    pA++;
                    cntA++;
                }
                while (pB < sumB.size() && sumB.get(pB) == sB) {
                    pB++;
                    cntB++;
                }
                cnt += cntA * cntB;
            } else if(total < T) {
               pA++;
            } else {
                pB++;
            }

        }
        System.out.println(cnt);
    }
}
