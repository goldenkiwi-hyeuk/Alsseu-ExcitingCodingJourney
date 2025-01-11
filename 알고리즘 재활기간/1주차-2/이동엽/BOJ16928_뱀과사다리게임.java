import java.util.*;

public class BOJ16928 {

    static Map<Integer, Integer> ladder, snake;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 사다리 수
        int m = sc.nextInt(); // 뱀 수

        ladder = new HashMap<>();
        while (n-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            ladder.put(x, y);
        }
        snake = new HashMap<>();
        while (m-- > 0) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            snake.put(x, y);
        }
        sc.close();
        System.out.println(escape());
    }

    static int escape() {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[101];
        int[] map = new int[101];

        queue.add(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            if (now == 100) {
                return map[now];
            }
            for (int i = 1; i <= 6; i++) {
                int next = now + i;
                if (next > 100) continue;
                next = snakeAndLadder(next);
                if (!visited[next]) {
                    visited[next] = true;
                    map[next] = map[now] + 1;
                    queue.add(next);
                }
            }
        }
        return -1;
    }

    static int snakeAndLadder(int now) {
        if (ladder.containsKey(now)) return ladder.get(now);
        if (snake.containsKey(now)) return snake.get(now);
        return now;
    }
}
