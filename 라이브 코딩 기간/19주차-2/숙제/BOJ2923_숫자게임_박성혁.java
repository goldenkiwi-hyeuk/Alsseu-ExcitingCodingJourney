import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 기본아이디어 : 투포인터
    // A배열에서 i번째로 작은수와 B배열에서 i번째 큰수의 합 중에서 가장 큰값이
    // 가장 큰 값을 가능한 작게 만드는 방법이다.
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        // 숫자를 전부 저장하는게 아니라 1~100 배열을 만들고 등장한 횟수를 늘리는 형태로 함
        int[] arrA = new int[101];
        int[] arrB = new int[101];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            ++arrA[A];
            ++arrB[B];;
            int ans = 0;
            int[] tempA = arrA.clone();
            int[] tempB = arrB.clone();
            int a = 1;
            int b = 100;
            while(a <= 100 && b >= 1) {
                // 0이 아닌 수 찾기, 현재 배열에 있는 숫자 체크
                if (tempA[a] == 0) {
                    a++;
                    continue;
                }

                if (tempB[b] == 0) {
                    b--;
                    continue;
                }
                
                ans = Math.max(ans, a+b);
                
                // 배열의 비교를 빠르게 하기 위한 방법
                if (tempA[a] > tempB[b]) {
                    tempA[a] -= tempB[b];
                    b--;
                } else if (tempA[a] < tempB[b]) {
                    tempB[b] -= tempA[a];
                    a++;
                } else {
                    a++;
                    b--;
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}
