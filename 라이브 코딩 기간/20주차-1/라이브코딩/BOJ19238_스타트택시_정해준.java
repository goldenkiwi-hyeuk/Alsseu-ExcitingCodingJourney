// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;
class BOJ19238_스타트택시_정해준 {
    static class Position implements Comparable<Position> {
        int x;
        int y;
        int v;

        public Position(int x, int y, int v) {
            this.x = x;
            this.y = y;
            this.v = v;
        }

        @Override
        public int compareTo(Position o){
            if(this.v == o.v) {
                if(this.x == o.x) {
                    return this.y - o.y;
                }
                return this.x - o.x;
            }
            return this.v - o.v;
        }
    }



    static int N, M, K;
    static int[] dx = {0,0,-1,1};
    static int[] dy = {-1,1,0,0};
    static int[][] map;
    static int[][] users;
    static boolean[] userCheck;
    static int[][] userMap;
    static boolean[][] visited;

    static Position taxi;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();
        map = new int[N + 1][N + 1];
        users = new int[M + 1][4];
        userMap = new int[N + 1][N + 1];
        userCheck = new boolean[M + 1];
        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= N; j++) {
                map[i][j] = sc.nextInt();
            }
        }
        int a = sc.nextInt();
        int b = sc.nextInt();

        taxi = new Position(a, b, 0);

        for(int i = 1; i <= M; i++) {
            for(int j = 0; j < 4; j++) {
                int n = sc.nextInt();
                users[i][j] = n;
            }
            userMap[users[i][0]][users[i][1]] = i;
        }

        System.out.println(drive());
    }

    public static int drive() {
        while(M > 0) {
            Position pos = bfs();
            if(pos == null)
                return -1;
            int id = userMap[pos.x][pos.y];
            int[] user = users[id];
            if(K < pos.v)
                return -1;
            K -= pos.v;

            taxi = new Position(pos.x, pos.y, 0);
            userMap[pos.x][pos.y] = 0;

            Position dest = go(user[2], user[3]);
            if (dest == null) return -1;

            if (K < dest.v) return -1;  // 연료 부족
            K += dest.v;  // 목적지까지 거리 * 2 - v는 go 내부에서 거리 계산됨
            taxi = new Position(dest.x, dest.y, 0);  // 택시 위치 이동
            userCheck[id] = true;  // 승객 완료 처리
            M--;  // 남은 승객 수 감소

        }


        return K;
    }

    public static Position bfs() {
        visited = new boolean[N + 1][N + 1];
        Queue<Position> q = new LinkedList<>();
        PriorityQueue<Position> pq = new PriorityQueue<>();
        visited[taxi.x][taxi.y] = true;
        q.offer(taxi);
        while(!q.isEmpty()) {
            Position now = q.poll();
            if(userMap[now.x][now.y] > 0 && !userCheck[userMap[now.x][now.y]]) {
                pq.offer(new Position(now.x, now.y, now.v));
            }

            for(int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if(nx < 1 || ny < 1 || nx > N || ny > N)
                    continue;

                if(visited[nx][ny] || map[nx][ny] == 1)
                    continue;

                visited[nx][ny] = true;
                q.offer(new Position(nx, ny, now.v + 1));
            }
        }

        return pq.isEmpty() ? null : pq.poll();
    }

    public static Position go(int tx, int ty) {
        visited = new boolean[N + 1][N + 1];
        Queue<Position> q = new LinkedList<>();
        q.offer(new Position(taxi.x, taxi.y, 0));
        visited[taxi.x][taxi.y] = true;

        while (!q.isEmpty()) {
            Position now = q.poll();
            if (now.x == tx && now.y == ty) {
                return new Position(tx, ty, now.v);
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (nx < 1 || ny < 1 || nx > N || ny > N) continue;
                if (visited[nx][ny] || map[nx][ny] == 1) continue;

                visited[nx][ny] = true;
                q.offer(new Position(nx, ny, now.v + 1));
            }
        }

        return null;  // 도달 불가
    }
}








