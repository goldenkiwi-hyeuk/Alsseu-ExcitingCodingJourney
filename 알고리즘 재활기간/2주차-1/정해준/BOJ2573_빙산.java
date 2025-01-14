import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2573_빙산 {
    static class Snow{
        int x;
        int y;

        Snow(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    static int N, M;
    static int[][] map;
    static int[][] sim; // 시뮬 용 맵
    static ArrayList<Snow> snows = new ArrayList<>();
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];

        sim = new int[N][M];

        for(int i = 0; i < N; i++){
            for(int j = 0; j <M; j++){
                map[i][j] = sc.nextInt();
                sim[i][j] = map[i][j];
                if(map[i][j] != 0){
                    snows.add(new Snow(i,j)); // 빙산의 정보를 저장
                }
            }
        }

        int time = 0;
        while(!snows.isEmpty()){ // 빙산이 다 0이 되었을 때
            time++; // 시간이 흐르고
            for(int s = snows.size() - 1; s >= 0; s--){
                int cnt = 0;
                for(int i = 0; i < 4; i++){
                    int nx = snows.get(s).x + dx[i];
                    int ny = snows.get(s).y + dy[i];
                    if(nx >= 0 && nx < N && ny >= 0 && ny < M){ //범위 내에 있을 경우
                        if(map[nx][ny] == 0){
                            cnt++;
                        }
                    }
                }
                sim[snows.get(s).x][snows.get(s).y] = Math.max(sim[snows.get(s).x][snows.get(s).y] - cnt, 0); // 0보다 작아지지 않도록 조치
                if(sim[snows.get(s).x][snows.get(s).y] == 0){
                    snows.remove(s); // 사라진 빙산은 제거
                }
            }
            // 한 덩어리인지 확인
            if(snows.isEmpty() || !checkSnow()){
                break;
            }
            // 맵 복사
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    map[i][j] = sim[i][j];
                }
            }
        }
        System.out.println(snows.isEmpty() ? 0 : time); // 만약 빙산이 다 사라진거면 0 아니면 얼마나 걸렸는지 표시

    }
    static boolean checkSnow(){
        boolean[][] visited = new boolean[N][M];
        int cnt = 0;
        for(Snow s : snows){
            if(sim[s.x][s.y] != 0 && visited[s.x][s.y] == false){ // 빙산이 연결되어있는지 확인
                cnt++;
                if(cnt > 1) // 카운팅이 1이상이면 2개 이상으로 나눠짐
                    return false;
                visited[s.x][s.y] = true; // 출발 지점 저장
                Queue<Snow> q = new LinkedList<>();
                q.offer(s);
                while(!q.isEmpty()){
                    Snow start = q.poll();
                    for(int i = 0; i < 4; i++){ // bfs로 루트 확인
                        int nx = start.x + dx[i];
                        int ny = start.y + dy[i];
                        if(nx >= 0 && nx < N && ny >= 0 && ny < M &&sim[nx][ny] != 0 && !visited[nx][ny]){
                            visited[nx][ny] = true;
                            q.offer(new Snow(nx,ny));
                        }
                    }
                }
            }
        }
        return true;
    }

}
