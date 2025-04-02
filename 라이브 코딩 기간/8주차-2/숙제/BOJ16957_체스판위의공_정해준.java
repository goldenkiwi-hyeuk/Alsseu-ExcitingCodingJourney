import java.util.Scanner;

public class BOJ16957_체스판위의공_정해준 {
    static int R,C;
    static int[] parent;
    static int[] di = {1,1,1,0,0,-1,-1,-1};
    static int[] dj = {-1,0,1,-1,1,-1,1,0};
    static int[][] arr;

    // Union Find의 Find 함수를 이용해서 원애 있던 공이 최종적으로 어디로 떨어지는지를 dfs로 찾는다.
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        R = sc.nextInt();
        C = sc.nextInt();
        arr = new int[R][C];
        parent = new int[R * C];
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                arr[i][j] = sc.nextInt();
                parent[i * C + j] = i * C + j ; // 이차원 위치를 일차원으로 변환
            }
        }

        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(parent[i * C + j] == i * C + j) { // 값이 번호랑 다른 경우는 이미 방문한 경우
                    dfs(i,j);
                }
            }
        }

        int[] answer = new int[R * C];
        for(int i = 0; i < R * C; i++) {  // 해당 위치의 구슬을 증가
            answer[find(i)] += 1;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0 ; i < R; i++) {
            for(int j = 0 ; j < C ; j++) {
                sb.append(answer[i * C + j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }


    static int find(int x) {
        if(x == parent[x])
            return x;
        return parent[x] = find(parent[x]);
    }

    static void dfs(int x, int y) {
        int min = arr[x][y]; // 가장 낮은 숫자
        int minX = x; // 낮은 위치
        int minY = y; //낮은 위치

        for(int i = 0; i < 8; i++) { // 8방향 중 가장 최적의 위치를 탐색
            int nx = x + di[i];
            int ny = y + dj[i];
            if(nx < 0 || ny < 0 || nx >= R || ny >= C) // 범위를 벗어났을 경우
                continue;
            if(arr[nx][ny] >= min) // 다음 위치가 원래 값보다 크거나 같을 경우
                continue;

            min = arr[nx][ny];
            minX = nx;
            minY = ny;
        }

        if(min < arr[x][y]) { // 다음 위치로 향할 경우
            parent[x * C + y] = minX * C + minY; // parent 함수를 최신화
            if(parent[minX * C + minY] == minX * C + minY) { // 아직 해당 점을 방문하지 않았거나 이 지점이 끝일 경우
                dfs(minX, minY);
            }
        }
    }
}
