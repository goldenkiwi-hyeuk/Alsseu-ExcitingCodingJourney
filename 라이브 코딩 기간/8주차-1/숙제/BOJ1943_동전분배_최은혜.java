import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        for(int tc=0; tc<3; tc++){
            int N = Integer.parseInt(br.readLine());
            int[][] coin = new int[N][2];
            int total = 0;

            for(int i=0; i<N; i++){
                st = new StringTokenizer(br.readLine());
                int val = Integer.parseInt(st.nextToken());
                int cnt = Integer.parseInt(st.nextToken());
                total += (val*cnt);
                coin[i][0] = val;
                coin[i][1] = cnt;
            }

            if(total % 2 !=0){
                sb.append(0).append("\n");
                continue;
            }

            int target = total/2;
            boolean[] dp = new boolean[target+1]; // 해당 가격을 만들 수 있는가
            dp[0] = true;

            for(int i=0; i<N; i++){
                int val = coin[i][0];
                int cnt = coin[i][1];


                for(int j=target; j>=0; j--){
                    if(!dp[j]) continue;

                    for(int k=1; k<=cnt; k++){
                        int next = j+val*k;
                        if(next>target) break;
                        dp[next] = true;
                    }
                }
            }


            if(dp[target]) sb.append(1).append("\n");
            else sb.append(0).append("\n");

        }

        System.out.println(sb);
    }
}
