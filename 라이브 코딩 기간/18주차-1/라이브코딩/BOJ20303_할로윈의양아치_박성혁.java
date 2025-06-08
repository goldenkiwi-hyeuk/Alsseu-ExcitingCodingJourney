import java.util.*;
import java.io.*;

class Main {
    
    // 기본아이디어 : 유니온 파이드 + 배낭문제
    // 먼저 유니온 파인드를 통해서 아이들을 그룹짓고 그룹의 인원수와 전체 사탕갯수를 체크한다.
    // 그룹의 인원수를 배낭의 무게, 사탕의 갯수를 물건의 가치로 선정하여 냅색을 한다.
    static int N,M,K;
    static int[] candy, parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        candy = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i<=N;++i){
            candy[i] = Integer.parseInt(st.nextToken());
        }
        
        parent = new int[N+1];
        for(int i = 1; i<=N;++i){
            parent[i] = i;
        }
        
        // 아이들 그룹짓기
        for(int i = 1; i<=M;++i){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            unionParent(a,b);
        }
        
        // 캔디리스트의 각 행의 [0]에는 각 그룹의 인원수, [1]에는 각 그룹의 사탕갯수가 체크된다.
        int[][] candyList = new int[N+1][2];
        for(int i = 1; i<=N;++i){
            int parentNum = findParent(i);
            ++candyList[parentNum][0];
            candyList[parentNum][1] += candy[i];
        }
        
        // 그룹 인원수를 기준으로 오름차순 정렬, 인원수가 같다면 캔디를 기준으로 내림차순 정렬
        Arrays.sort(candyList, (o1,o2)->{
            if(o1[0]== o2[0]){
                return o2[1]-o1[1];
            }else {
                return o1[0]-o2[0];
            }
        });
        
        int[][] dp = new int [N+1][K];
        for(int i = 1; i<=N; ++i){
            // 배열을 넉넉하게 만들었기에 그룹이 0명인 그룹은 체크하지 않고 넘어간다.
            if(candyList[i][0] == 0){
                continue;
            }
            // 기본적인 배낭문제
            for(int j = 1; j<K; ++j){
                if(candyList[i][0]<=j){
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-candyList[i][0]]+candyList[i][1]);
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1]);
                }
            }
        }
        
        System.out.println(dp[N][K-1]);
    }
    
    private static int findParent(int num){
        if(parent[num]==num) return num;
        return parent[num] = findParent(parent[num]);
    }
    
    private static void unionParent(int num1, int num2){
        int num1P = findParent(num1);
        int num2P = findParent(num2);
        if(num1P != num2P){
            parent[Math.max(num1P,num2P)] = Math.min(num1P,num2P);
        }
    }
}