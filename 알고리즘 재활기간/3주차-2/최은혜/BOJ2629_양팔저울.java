import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int num = Integer.parseInt(br.readLine()); // 추의 개수
        int[] weight = new int[num+1]; // 추의 무게
        st = new StringTokenizer(br.readLine());
        for(int i=1; i<num+1; i++){
            weight[i]= Integer.parseInt(st.nextToken());
        }


        boolean[] dp = new boolean[15001];
        dp[0] = true;

        for(int i=1; i<=num; i++){
            boolean[] newDp = dp.clone();
            for(int j=15000; j>=0; j--){
                if(dp[j]){
                    if(j+weight[i] <= 15000) newDp[j+weight[i]] = true;
                    newDp[Math.abs(j-weight[i])] = true;
                }
            }
            dp = newDp;
        }





        int T = Integer.parseInt(br.readLine()); // 구슬 개수
        st = new StringTokenizer(br.readLine());
        for(int tc=0; tc<T; tc++){
            int bead = Integer.parseInt(st.nextToken());

            if(bead > 15000) {
                sb.append("N").append(" ");
            } else if(dp[bead]) {
                sb.append("Y").append(" ");
            } else {
                sb.append("N").append(" ");
            }
        }

        System.out.println(sb);

    }
}
