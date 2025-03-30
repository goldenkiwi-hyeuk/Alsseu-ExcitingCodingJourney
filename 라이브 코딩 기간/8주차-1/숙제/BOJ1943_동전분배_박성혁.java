import java.io.*;
import java.util.*;

class Main {
    // 기본아이디어 : 배낭문제(문제 유형 보고 품)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        for(int tc = 0; tc<3 ;++tc){
            int N = Integer.parseInt(br.readLine());
            int[][] arr = new int[N][2]; // 동전의 형태를 체크해줄 배열
            int sum = 0; // 총액
            int total = 0; // 동전의 개수
            for(int i = 0; i<N; ++i){
                StringTokenizer st = new StringTokenizer(br.readLine());
                arr[i][0] = Integer.parseInt(st.nextToken());
                arr[i][1] = Integer.parseInt(st.nextToken());
                total += arr[i][1];
                sum = sum + (arr[i][0]*arr[i][1]);
            }
            Arrays.sort(arr, (o1,o2)-> {
                return o1[0] - o2[0];
            });
            if(sum%2!=0){ // 총액이 2의 배수가 아니면 애초에 정확히 나눠줄수 없음
                sb.append(0).append("\n");
            } else {
                boolean[] dp = new boolean[(sum/2)+1]; // ~번째 동전까지 사용했을때 ~원을 만들수 있는가?
                dp[0] = true;
                for(int i = 0; i<N;++i){
                    int coin = arr[i][0];
                    int cnt = arr[i][1];
                    for(int k = sum/2; k>=coin;--k){ // 역으로 해야 갱신된 dp를 참조하지 않음
                        if(dp[k-coin] == true){ // 예 : 5원 구현시 3원 동전 사용해야 하는 상황일 때 2원이 이미 구현 가능한 상황이면 5원은 당연히 구현 가능
                            for(int j = 0; j < cnt; ++j) {
                                if(k+coin*j<=(sum/2)){
                                    dp[k+coin*j] = true;    
                                }
                            }
                        }
                    }
                    
                }
                if(dp[sum/2]){
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}