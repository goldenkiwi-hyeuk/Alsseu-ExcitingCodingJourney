import java.util.Scanner;

public class BOJ2931_가스관_정해준 {
    static int N, M;
    static char[][] arr;
    static char[] pipe = {'.', '|', '-', '+', '1', '2', '3', '4'};
    static int total;
    static boolean[][] visited;
    static int tx, ty;
    static int ansX, ansY;
    static boolean end;
    static char answer;
    static int[] dx = {-1,1,0,0};
    static int[] dy = {0,0,-1,1};  // 상, 하, 좌 우
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        arr = new char[N][M];
        visited = new boolean[N][M];
        int x = 0; // 시작위치
        int y = 0;


        for(int i = 0; i < N; i++) {
            String str = sc.next();
            for(int j = 0; j < M; j++) {
                char C = str.charAt(j);
                if(C != '.' && C != 'M' && C != 'Z') {
                    total++;
                } else if(C == 'M') {
                    x = i;
                    y = j;
                }
                arr[i][j] = C;
            }
        }
        //필요 파이프 수 바꿔치기 된거 추가
        total++;
        for(int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if(nx < 0 || ny < 0 || nx >= N || ny >= M || arr[nx][ny] == '.')
                continue;

            dfs(nx,ny,i,0,false);
        }
        ansX++;
        ansY++;
        System.out.println(ansX + " " + ansY + " " + answer);
    }

    public static void dfs(int x, int y, int dir, int cnt, boolean flag) {
        if(end)
            return; // 종료
        if(cnt == total) { //파이프 따라가다가 '.' 발견했을 때
            ansX = tx;
            ansY = ty;
            answer = arr[ansX][ansY];
            end = true; // 종료
            flag = false;
            return;
        }
        int nextDir = next(arr[x][y], dir);
        if(nextDir == -1)
            return; //파이프가 없음
        int nx = x + dx[nextDir];
        int ny = y + dy[nextDir];

        if(nx < 0 || ny < 0 || nx >= N || ny >= M) return;

        if(arr[nx][ny] == '.') {
            if(!flag) { // 아직 파이프를 갈아끼지 않았다면
                for(int i = 1; i <= 7; i++) {
                    tx = nx;
                    ty = ny;
                    arr[nx][ny] = pipe[i]; // 하나씩 끼워넣기
                    visited[nx][ny] = true;
                    dfs(nx, ny, nextDir, cnt + 1, true); // 갈아낌
                    arr[nx][ny] = '.';
                    visited[nx][ny] = false;
                }
            }
        } else {
            if(visited[nx][ny]) { // 이미 방문한 경우
                dfs(nx, ny, nextDir, cnt, flag); // 이미 존재한 파이프이기 때문에 숫자를 증가 안함
            } else {
                visited[nx][ny] = true;
                dfs(nx, ny, nextDir, cnt + 1, flag);
                visited[nx][ny] = false;
            }
        }
    }

    public static int next(char pipe, int dir) {
        if(pipe == '|') {
            return dir == 0 || dir == 1 ? dir : -1; // 상, 하 일 때만
        } else if(pipe == '-') {
            return dir == 2 || dir == 3 ? dir : -1; // 좌, 우 일 때만
        } else if(pipe == '+') {
            return dir; // 그 방향 그대로
        } else if(pipe == '1') { //좌 -> 하, 상  -> 우
           if(dir == 2) return 1;
           if(dir == 0) return 3;
        } else if(pipe == '2') { // 하 - > 우, 좌 -> 상
            if(dir == 1) return 3;
            if(dir == 2) return 0;
        }else if(pipe == '3') { // 우 -> 상, 하 -> 좌
            if(dir == 3) return 0;
            if(dir == 1) return 2;
        }else if(pipe == '4') { // 우 -> 하, 상 -> 좌
            if(dir == 3) return 1;
            if(dir == 0) return 2;
        }
        return -1;
    }
}
