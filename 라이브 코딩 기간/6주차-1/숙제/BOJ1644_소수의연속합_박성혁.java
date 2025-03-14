import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    // 기본 아이디어 : 에라토스테네스의 체로 소수를 구한다음 twoPointer 탐색
    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        boolean[] arr = new boolean[N+1];
        arr[0] = true;
        arr[1] = true;
        for (int i = 2; i < Math.sqrt(N)+1; i++) { // 에라토스테네스의 체로 소수 구하기
            for (int j = i*i; j <= N; j+=i) {
                arr[j] = true;
            }
        }

        if (N==1){ // N이 1부터 들어올수 있으며 1이면 0이 정답임
            System.out.println(0);
        } else {
            int ans = 0;
            int total = 2;
            int left = 2; // 왼쪽 포인터
            int right = 2; // 오른쪽 포인터
            while(left!=N||right!=N) { // 투포인터 탐색 시작
                if (total > N){ // total이 크다면 왼쪽 포인터 오른쪽으로 옮기기
                    total -= left;
                    ++left;
                    while(arr[left]&&left!=N){
                        ++left;
                    }
                } else if (total < N){ // total이 작다면 오른쪽 포인터 오른쪽으로 옮기기
                    ++right;
                    while(arr[right]&&right!=N){
                        ++right;
                    }
                    total += right;
                } else { // 동일하다면 ans 를 늘려주고 왼쪽 포인터 오른쪽으로 옮기기
                    ++ans;
                    total -= left;
                    ++left;
                    while(arr[left]&&left!=N){
                        left++;
                    }
                }
            }
            if (!arr[N]){ // 그 자체가 소수라면 ans 늘려주기
                ++ans;
            }
            System.out.println(ans);
        }
    }
}
