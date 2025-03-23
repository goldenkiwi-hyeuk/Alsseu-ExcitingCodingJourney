import java.util.Scanner;
// 혼자서 못 품, 백준의 질문 게시판에서 아이디어 차용
public class BOJ1799_비숍_정해준 {
    static int N;
    static int[][] map;
    static boolean[][] visited;
    static int[] di = {1,1,-1,-1};
    static int[] dj = {-1,1,-1,1}; //대각선
    static int bk, wh;
    static int[][] colors;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        map = new int[N][N];
        colors = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                colors[i][j] = (i+j) % 2;
            }
        }
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt(); //0이면 갈수 없고 1이면 갈 수 있음
            }
        }
        dfs(0,0,colors[0][0],0);
        dfs(0,1,colors[0][1],0);
        System.out.println(wh + bk);

    }
    public static void dfs(int x, int y, int color, int cnt) {
        if(x >= N) { // 세로를 넘을 경우 종료
            if(color == 0) {
                bk = Math.max(bk,cnt);
            } else {
                wh = Math.max(wh,cnt);
            }
            return;
        }
        int nx = x;
        int ny = y + 2;
        if(ny >= N) {
            nx++; // 가로 넘을 경우 세로 하나 증가
            if(nx < N) {
                ny = color == colors[nx][0] ? 0 : 1; // 처음이 자신의 색일 경우 그대로 아닐 경우 하나 전진
            }
        }

        if(map[x][y] == 0) {
            dfs(nx, ny, color, cnt); // 두지 않고 넘김
        } else {
            if(move(x,y)) { // 둘 수 있으면
                visited[x][y] = true;
                dfs(nx, ny, color, cnt + 1);
                visited[x][y] = false;
            }
            dfs(nx, ny, color, cnt); // 두지 않는 경우의 수
        }



    }

    public static boolean move(int x, int y) { // 비숍 만나는지 확인
        for(int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            while(true) {
                nx = nx + di[i];
                ny = ny + dj[i];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N)
                    break;
                if(visited[nx][ny]) // 비숍 만남
                    return false;
            }
        }
        return true;
    }
}
