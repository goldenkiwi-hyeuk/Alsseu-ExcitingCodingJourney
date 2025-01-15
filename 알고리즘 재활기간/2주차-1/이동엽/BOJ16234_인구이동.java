import java.util.*;

public class BOJ16234_인구이동 {

    static int arr[][], n, l, r;
    static boolean visited[][];
    static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n = sc.nextInt();
        l = sc.nextInt();
        r = sc.nextInt();
        arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = sc.nextInt();
            }
        }
        sc.close();

        int ans = 0;
        while (true) {
            visited = new boolean[n][n];
            boolean isMoved = false;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (!visited[i][j]) {
                        if (bfs(i, j)) {
                            isMoved = true;
                        }
                    }
                }
            }

            if (!isMoved) break;
            ans++;
        }

        System.out.println(ans);
    }

    static boolean bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        List<int[]> union = new ArrayList<>();

        q.offer(new int[]{x, y});
        union.add(new int[]{x, y});
        visited[x][y] = true;

        int sum = arr[x][y];

        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int currX = curr[0];
            int currY = curr[1];

            for (int i = 0; i < 4; i++) {
                int nx = currX + dx[i];
                int ny = currY + dy[i];

                if (0 <= nx && nx < n && 0 <= ny && ny < n && !visited[nx][ny]) {
                    if (l <= Math.abs(arr[currX][currY] - arr[nx][ny]) && Math.abs(arr[currX][currY] - arr[nx][ny]) <= r) {
                        q.offer(new int[]{nx, ny});
                        union.add(new int[]{nx, ny});
                        visited[nx][ny] = true;
                        sum += arr[nx][ny];
                    }
                }
            }
        }

        if (union.size() > 1) {
            int avg = sum / union.size();
            for (int[] u : union) {
                arr[u[0]][u[1]] = avg;
            }
            return true;
        }
        return false;
    }
}
