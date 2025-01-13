import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Loc implements Comparable<Loc> {
        int loc, cost;

        public Loc() {
        }

        public Loc(int loc, int cost) {
            this.loc = loc;
            this.cost = cost;
        }

        @Override
        public int compareTo(Loc o) {
            return Integer.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Deque<Loc> pq = new ArrayDeque<>();
        pq.add(new Loc(N, 0));
        int min = Integer.MAX_VALUE;
        int cnt = 0;
        int[] time = new int[100001];
        while (!pq.isEmpty()) {
            Loc loc = pq.poll();
            if (loc.cost > min) {
                break;
            }
            if (loc.loc == K) {
                min = Math.min(min, loc.cost);
                cnt++;
                continue;
            }
            if (loc.loc - 1 >= 0 && (time[loc.loc - 1] == 0 || time[loc.loc - 1] == loc.cost + 1)) {
                pq.add(new Loc(loc.loc - 1, loc.cost + 1));
                time[loc.loc - 1] = loc.cost+1;
            }
            if (loc.loc + 1 <= 100000 && (time[loc.loc + 1] == 0 || time[loc.loc + 1] == loc.cost + 1)) {
                pq.add(new Loc(loc.loc + 1, loc.cost + 1));
                time[loc.loc + 1] = loc.cost+1;
            }
            if (loc.loc * 2 <= 100000 && (time[loc.loc * 2] == 0 || time[loc.loc * 2] == loc.cost + 1)) {
                pq.add(new Loc(loc.loc * 2, loc.cost + 1));
                time[loc.loc * 2] = loc.cost+1;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(min).append("\n");
        sb.append(cnt);
        System.out.println(sb);
    }
}
