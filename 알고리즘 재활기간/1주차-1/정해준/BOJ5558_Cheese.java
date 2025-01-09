import java.awt.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ5558_Cheese {
    static class Mouse{
        int x;
        int y;
        int walk;
        Mouse(){

        }

        Mouse(int x, int y, int walk){
            this.x = x;
            this.y = y;
            this.walk = walk;
        }
    }

    static int[] dx = {-1,0,0,1};
    static int[] dy = {0,-1,1,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt(); // 세로
        int W = sc.nextInt(); // 가로
        int N = sc.nextInt(); // 공장의 개수
        boolean[][] v = new boolean[H][W];
        int[][] map = new int[H][W];

        Mouse start = new Mouse(); // 출발지점

        for(int  i = 0; i < H; i++){
            String str = sc.next();
            for(int j = 0; j < W; j++){
                char c = str.charAt(j);
                if(c == 'S'){
                    start.x = i;
                    start.y = j;
                    start.walk = 0;
                    v[i][j] = true;
                } else if(c == 'X'){
                    map[i][j] = -1;
                } else if(c == '.'){
                    map[i][j] = 0;
                } else{
                  map[i][j] = c - '0';
                }
            }
        } // 맵 완료
        int count = 0;
        int hp = 1;
        Queue<Mouse> q = new LinkedList<>();
        q.offer(start); // 출발 지점 저장
        while(hp <= N){
            while(!q.isEmpty()){
                Mouse now = q.poll();
                if(map[now.x][now.y] == hp){ // 만약 지금 위치의 치즈가 있을 경우
                    count += now.walk; // 거기까지의 거리만큼 추가
                    q.clear(); // 도달했으니까 초기화
                    v = new boolean[H][W]; // 맵도 초기화
                    v[now.x][now.y] = true; // 현재위치를 시작지점으로
                    q.offer(new Mouse(now.x,now.y,0));
                    break; // 다음으로
                }

                for(int i = 0; i < 4; i++){
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    if(nx >= 0 && ny >= 0 && nx < H && ny < W && !v[nx][ny] && map[nx][ny] != -1){
                        v[nx][ny] = true;
                        q.offer(new Mouse(nx,ny,now.walk + 1)); // 한걸음 추가
                    }
                }
            }
            hp++;
        }


        System.out.println(count);
    }
}
