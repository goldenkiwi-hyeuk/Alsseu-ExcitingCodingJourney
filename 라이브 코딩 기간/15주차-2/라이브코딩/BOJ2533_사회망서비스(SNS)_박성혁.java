import java.util.*;
import java.io.*;

class Main {
    // 기본아이디어 : dp
    
    static Map<Integer, List<Integer>> branchList;
    static int[][] dp;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        branchList = new HashMap<>();
        int N = Integer.parseInt(br.readLine());
        dp = new int[N+1][2];
        visited = new boolean[N+1];
        for(int i = 0; i < N-1 ; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            if(branchList.containsKey(s)){
                branchList.get(s).add(e);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(e);
                branchList.put(s,list);
            }
            if(branchList.containsKey(e)){
                branchList.get(e).add(s);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(s);
                branchList.put(e,list);
            }
        }
        checkChild(1);
        System.out.println(Math.min(dp[1][0],dp[1][1]));
    }
    private static void checkChild(int num){
        visited[num] = true;
		dp[num][0] = 0; // 내가 얼리어답터가 아닐때
		dp[num][1] = 1;	// 내가 얼리어답터일때
        if(branchList.containsKey(num)){
            for(int child : branchList.get(num)) {
    			if(!visited[child]) {
    				checkChild(child);
    				dp[num][0] += dp[child][1]; // 얼리 어답터가 아니라면 자식들 모두가 얼리어답터
    				dp[num][1] += Math.min(dp[child][0], dp[child][1]);	// 얼리어답터라면 자식은 얼리어답터 일수도 아닐수도
    			}
    		}    
        }
    }
}