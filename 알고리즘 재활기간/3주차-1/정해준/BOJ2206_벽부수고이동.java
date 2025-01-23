import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ2206_벽부수고이동 {
    static class Node {
        int x;
        int y;
        int w;
        boolean flag;

        public Node(int x, int y, int w, boolean flag) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.flag = flag;
        }

    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[][] map = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            String str = sc.next();
            for (int j = 1; j <= M; j++) {
                map[i][j] = str.charAt(j - 1) - '0'; // 맵 완료
            }
        }
        int dr[] = { 0, 0, -1, 1 };
        int dc[] = { 1, -1, 0, 0 };
        boolean v[][][] = new boolean[N + 1][M + 1][2]; // 부섰을 경우, 안 부섰을 경우
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(1, 1, 1, false)); // 출발위치를 큐에 삽입
        int min = 987654321;

        while (!q.isEmpty()) {
            Node curr = q.poll();

            if (curr.x == N && curr.y == M) {
                    min = Math.min(min, curr.w);
            }

            for (int i = 0; i < 4; i++) {
                int nx = curr.x + dr[i];
                int ny = curr.y + dc[i];

                if (nx < 1 || ny < 1 || nx > N || ny > M) // 범위 밖일 경우
                    continue;

                if(map[nx][ny] == 0) { // 벽이 아닐 경우
                    if(!curr.flag && !v[nx][ny][0]) { // 벽을 안 부섰을 경우
                        q.offer(new Node(nx, ny, curr.w + 1, false));
                        v[nx][ny][0] = true;
                    } else if(curr.flag && !v[nx][ny][1]) { // 벽을 부섰지만 방문하지 않았을 경우
                        q.offer(new Node(nx, ny, curr.w + 1, true));
                        v[nx][ny][1] = true;
                    }
                } else { // 벽일 경우
                    if(!curr.flag && !v[nx][ny][1]) {
                        q.offer(new Node(nx, ny, curr.w + 1, true)); // 부수기
                        v[nx][ny][1] = true;
                    }
                }
            }

        }
        if(min == 987654321) {
            System.out.println(-1);
            return;
        }
        System.out.println(min);
    }
}
