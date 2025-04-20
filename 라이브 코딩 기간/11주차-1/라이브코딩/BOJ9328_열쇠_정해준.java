// Online Java Compiler
// Use this editor to write, compile and run your Java code online
// 문이 여러개일 경우도 생각해야함
import java.util.*;
class BOJ9328_열쇠_정해준 {
    static class Door { // 문에 대한 정보
        int x;
        int y;
        public Door(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    static int[] dx = {0,0,1,-1};
    static int[] dy = {1,-1,0,0};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        int T = sc.nextInt();
        while(T-- > 0) {
            int N = sc.nextInt();
            int M = sc.nextInt();
            char[][] map = new char[N][M];
            HashMap<Integer,Set<Door>> hash = new HashMap<>(); // 문의 정보 알파벳 : 위치
            for(int i = 0; i < N; i++) {
                String line = sc.next();
                for(int j = 0; j < M; j++) {
                    map[i][j] = line.charAt(j);
                }
            }
            boolean[] keys = new boolean[26]; // 0 ~ 25 'a' - 'a'로 열쇠여부 확인
            String str = sc.next();
            if(!str.equals("0")) {
                for(int i = 0; i < str.length(); i++) {
                    keys[str.charAt(i) - 'a'] = true;
                }
            }
            boolean[][] visited = new boolean[N][M];
            Queue<int[]> q = new LinkedList<>();
            int answer = 0;
            for (int i = 0; i < M; ++i) {
                q.add(new int[] {0, i});
                q.add(new int[] {N - 1, i});
                visited[0][i] = true;
                visited[N - 1][i] = true;
            }
            for (int i = 1; i < N - 1; ++i) {
                q.add(new int[] {i, 0});
                q.add(new int[] {i, M - 1});

                visited[i][0] = true;
                visited[i][M - 1] = true;
            }

            while(!q.isEmpty()) {
                int[] now = q.poll();
                int x = now[0];
                int y = now[1];
                if(map[x][y] == '*') // 벽일 경우
                    continue;
                if(map[x][y] - 'A' >= 0 && map[x][y] - 'A' <= 25){ // 대문자
                    int idx = map[x][y] - 'A';
                    if(!keys[idx]) { // 열쇠가 없는 문일 경우
                        Set<Door> doorPos = hash.getOrDefault(idx, new HashSet<>());
                        doorPos.add(new Door(x,y));
                        hash.put(idx, doorPos);
                        continue;
                    }
                }
                if(map[x][y] - 'a' >= 0 && map[x][y] - 'a' <= 25){ // 열쇠일 경우
                    int idx = map[x][y] - 'a';
                    keys[idx] = true;
                    if(hash.containsKey(idx)) { // 이미 방문했었던 문이 있다면
                        for(Door d : hash.get(idx)) {
                            q.offer(new int[]{d.x,d.y});
                            visited[d.x][d.y] = true;
                        }
                    }
                }
                if(map[x][y] == '$') { // 문서일 경우
                    answer++;
                }
                for(int i = 0; i < 4; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
                    if(nx < 0 || ny < 0 || nx >= N || ny >= M)
                        continue;
                    if(visited[nx][ny])
                        continue;
                    visited[nx][ny] = true;
                    q.offer(new int[]{nx,ny});
                }

            }
            sb.append(answer).append("\n");
        }
        System.out.println(sb);
    }
}