import java.io.*;
import java.util.*;

public class Main {
    static int[] arr;
    static int[][][] dp;
    static int INF = Integer.MAX_VALUE/2;

    public static void main(String args[]) throws IOException {
        // DP dp[left][right][idx] = idx번째까지 진행했을때 왼발이 left, 오른발이 right에 있을때 필요한 최소 힘
        // 내가 구해야할 건, 최소 힘! left와 right 위치 조합별 최소 힘중에서 최소값 구하기

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<Integer> list = new ArrayList<>();
        while(st.hasMoreTokens()){
            int num = Integer.parseInt(st.nextToken());
            if(num == 0) break;
            list.add(num);
        }

        int size = list.size();

        arr = new int[size];
        for(int i=0; i<size; i++){
            arr[i] = list.get(i);
        }

        dp = new int[5][5][size+1];

        for(int[][] d1 : dp){
            for(int[] d2 : d1){
                Arrays.fill(d2, INF);
            }
        }


        dp[0][0][0] = 0;

        for(int i=0; i<size; i++){

            int next = arr[i];

            for(int left = 0; left<5; left++){
                for(int right = 0; right<5; right++){
                    if(dp[left][right][i]==INF) continue;


                    // System.out.println("left: "+left+", right: "+ right+ ", i: "+i+"  "+dp[left][right][i]);

                    // 왼발 이동
                    dp[next][right][i+1] = Math.min(dp[next][right][i+1], dp[left][right][i]+getValue(left,next));

                    // 오른발 이동
                    dp[left][next][i+1] = Math.min(dp[left][next][i+1], dp[left][right][i]+getValue(right,next));
                }
            }
        }

        int result = INF;
        for(int left = 0; left<5; left++){
            for(int right = 0; right<5; right++){
                result = Math.min(result, dp[left][right][size]);
            }
        }

        System.out.println(result);





    }

    public static int getValue(int from, int to){
        if(from == to) return 1; // 반복
        if(from == 0) return 2; // 중심에서
        if(Math.abs(from-to) == 2) return 4; // 대각선
        return 3;
    }



}