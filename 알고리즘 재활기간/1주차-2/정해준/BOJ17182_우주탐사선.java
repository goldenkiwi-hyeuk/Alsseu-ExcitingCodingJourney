import java.util.Scanner;

public class BOJ17182_우주탐사선 {
    static int N; // 행성의 개수
    static int K; // 시작 위치
    static boolean[] v;
    static int[][] map;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        map = new int[N][N];
        v = new boolean[N];

        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = sc.nextInt();
            }
        }

        for(int k = 0; k < N; k++){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    map[i][j] = Math.min(map[i][k] + map[k][j], map[i][j]);
                }
            }
        }
        v[K] = true;

        dfs(1,K,0);

        System.out.println(min);
    }

    public static void dfs(int depth, int start, int dist){
        if(depth == N){
            min = Math.min(min, dist);
            return;
        }
        for(int i = 0; i < N; i++){
            if(i==start)
                continue;
            if(!v[i]){
                v[i] = true;
                dfs(depth+1, i, dist+map[start][i]);
                v[i] = false;
            }
        }
    }
}
