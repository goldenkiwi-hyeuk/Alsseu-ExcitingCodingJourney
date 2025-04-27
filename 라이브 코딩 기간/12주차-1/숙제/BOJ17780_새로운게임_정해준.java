import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class BOJ17780_새로운게임_정해준 {
    static class Horse{
        int x;
        int y;
        int d;
        public Horse(int x, int y, int d){
            this.x = x;
            this.y = y;
            this.d = d;
        }
    }

    static int N, K;
    static int[][] map;
    static int[] dr = {0,0,-1,1};
    static int[] dc = {1,-1,0,0};
    static HashMap<Integer, Horse> horse;
    static List<Integer>[][] cnt; // 해당 위치의 몇개의 말이 있는지 확인
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        cnt = new ArrayList[N][N];
        horse = new HashMap<>();
        int answer = 0;
        for(int i = 0 ; i< N; i++) { // 맵 정보 저장
            st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0 ; j< N ; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                cnt[i][j] = new ArrayList<>();
            }
        }
        // 말의 위치를 저장
        for(int i = 0 ; i< K; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            horse.put(i+ 1, new Horse(x-1,y-1,d - 1));
            cnt[x-1][y-1].add(i + 1); // 0,0 에 조정해서 위취에 말 번호를 추가
        }
        loop : while(++answer <= 1000) {
            for(int i = 1; i <= K; i++) {
                Horse now = horse.get(i);
                List<Integer> up = new ArrayList<>();
                int x = now.x;
                int y = now.y;
                if(cnt[x][y].get(0) != i)
                    continue; // 가장 아래가 자신이 아닌 경우

                for(int j = 0; j < cnt[x][y].size(); j++) {
                    up.add(cnt[x][y].get(j));
                }

                int nx = x + dr[now.d];
                int ny = y + dc[now.d];
                if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2) { // 범위 밖이거나 파란색일 경우 반전
                    //원상복귀
                    nx -= dr[now.d];
                    ny -= dc[now.d];
                    //change
                    int direction = now.d % 2 == 0 ? now.d + 1 : now.d - 1;
                    nx += dr[direction];
                    ny += dc[direction];
                    now.d = direction;
                    if(nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2)
                        continue;
                    if(map[nx][ny] == 1) {
                        for(int j = up.size() - 1; j >= 0; j--) { // 위에 있는 말들의 순서가 바뀜
                            cnt[nx][ny].add(up.get(j));
                            Horse h = horse.get(up.get(j));
                            horse.put(up.get(j), new Horse(nx, ny, h.d));
                        }
                    } else { // 흰칸일 때는 이동
                        for(Integer num : up) {
                            cnt[nx][ny].add(num);
                            Horse h = horse.get(num);
                            horse.put(num, new Horse(nx, ny, h.d));
                        }
                    }

                } else if(map[nx][ny] == 1) {
                    for(int j = up.size() - 1; j >= 0; j--) { // 위에 있는 말들의 순서가 바뀜
                        cnt[nx][ny].add(up.get(j));
                        Horse h = horse.get(up.get(j));
                        horse.put(up.get(j), new Horse(nx, ny, h.d));
                    }
                } else {
                    for(Integer num : up) {
                        cnt[nx][ny].add(num);
                        Horse h = horse.get(num);
                        horse.put(num, new Horse(nx, ny, h.d));
                    }
                }
                // 이동한 말 제거
                for(int j = cnt[x][y].size() - 1; j >=  0; j--) {
                    cnt[x][y].remove(j);
                }

                if(cnt[nx][ny].size() >= 4) { // 게임 종료 조건
                    break loop;
                }

            }
        }
        System.out.println(answer > 1000 ? -1 : answer);
    }
}
