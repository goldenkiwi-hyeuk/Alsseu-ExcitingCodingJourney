import java.util.*;
import java.awt.*;

public class PG388353_지게차와크레인_정해준 {
    class Solution {
        static int[] dx = {0,0,1,-1};
        static int[] dy = {1,-1,0,0};
        static int N,M;
        public int solution(String[] storage, String[] requests) {
            N = storage.length;
            M = storage[0].length();
            int answer = 0;
            char[][] map = new char[N + 2][M + 2];
            for(int i = 0; i <= N + 1; i++) {
                for(int j = 0; j <= M + 1; j++) {
                    if(i == 0 || j == 0 || i == N + 1 || j == M + 1) {
                        map[i][j] = ' ';
                    } else {
                        map[i][j] = storage[i-1].charAt(j-1);
                    }

                }
            }

            for(String req : requests) {
                change(map, req);
            }

            for(int i = 0; i <= N+1; i++) {
                for(int j = 0; j <= M+1; j++){
                    if(map[i][j] != ' ')
                        answer++;
                }
            }
            return answer;
        }

        public static void change(char[][] map, String req) { //bfs로 완전탐색
            char target = req.charAt(0);
            boolean[][] out = new boolean[N + 2][M + 2]; // 0  하고 N + 1, M + 1은 벽
            ArrayList<Point> tmp = new ArrayList<>();
            if(req.length() == 2) { // 다 추출할 경우
                for(int i = 1; i <= N; i++) {
                    for(int j = 1; j <= M; j++) {
                        if(map[i][j] == target) {
                            tmp.add(new Point(i,j));
                        }
                    }
                }
            } else { // 접근 가능한 부분만 추출할 경우

                Queue<Point> q = new LinkedList<>();
                q.offer(new Point(0,0));
                while(!q.isEmpty()) {
                    Point node = q.poll();
                    for(int i = 0; i < 4; i++) {
                        int nx = node.x + dx[i];
                        int ny = node.y + dy[i];

                        if(nx >= 0 && nx <= N + 1 && ny >= 0 && ny <= M + 1 && !out[nx][ny] && map[nx][ny] == ' ') {
                            out[nx][ny] = true;
                            q.offer(new Point(nx,ny));
                        }
                    }
                }
                for(int i = 0; i <= N + 1; i++) {
                    for(int j = 0; j <= M + 1; j++) {
                        if(map[i][j] == target) {
                            for(int k = 0; k < 4; k++) {
                                int x = i + dx[k];
                                int y = j + dy[k];
                                if(out[x][y]){
                                    tmp.add(new Point(i,j));
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            for(Point now : tmp) {
                map[now.x][now.y] = ' ';
            }
        }

    }
}
