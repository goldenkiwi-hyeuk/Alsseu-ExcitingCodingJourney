import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    // 기본아디이어 : 유니온파인드
    static int N, M, island;
    static int[][] map;
    static int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
    static int[] parent;

    private static class Edge implements Comparable<Edge> {
        int start, end, cost;

        public Edge() {}

        public Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        island = 2;
        // 섬에 라벨링 하기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    checkIsland(i,j);
                    island++;
                }
            }
        }

        parent = new int[island];
        for (int i = 0; i<island; ++i){
            parent[i] = i;
        }

        // 후보군 다리 찾기
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // 바다가 아니라면
                if (map[i][j] > 0) {
                    for (int dir = 0; dir < 4; dir++) {
                        int[] ans = checkBridge(i,j,dir, map[i][j],0);
                        if(ans[0]>1){ // 다리는 길이가 1보다 커야한다.
                            pq.add(new Edge(map[i][j],ans[1],ans[0]));
                        }
                    }
                }
            }
        }

        int ans = 0;
        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            int startP = findParent(edge.start);
            int endP = findParent(edge.end);
            if (startP!=endP){
                ans += edge.cost;
                union(edge.start,edge.end);
            }
        }
        
        // 연결되지 않은 도시가 있는지 확인
        boolean flag = false;
        for (int i = 3; i<island; i++) {
            findParent(i);
            if (parent[i]!=2){
                flag = true;
                break;
            }
        }
        if (flag){
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }

    }
    private static void union(int num1, int num2){
        int num1P = findParent(num1);
        int num2P = findParent(num2);
        int min = Math.min(num1P,num2P);
        int max = Math.max(num1P,num2P);
        parent[max] = min;
    }

    private static int findParent(int num){
        if (parent[num] == num) return num;
        return parent[num] = findParent(parent[num]);
    }

    private static int[] checkBridge(int r, int c, int dir, int start, int size) {
        int nr = r+delta[0][dir];
        int nc = c+delta[1][dir];
        if (inRange(nr,nc)){
            // 자기섬을 만난 경우
            if (map[nr][nc] == start){
                return new int[] {-1,-1};
            } else if(map[nr][nc] == 0) { // 바다를 만난 경우
                return checkBridge(nr,nc,dir,start,size+1);
            } else {
                return new int[] {size,map[nr][nc]}; // 다른 섬을 만난 경우
            }
        } else {
            // 범위 밖으로 나간 경우
            return new int[] {-1,-1};
        }
    }

    private static void checkIsland(int r, int c) {
        map[r][c] = island;
        for(int dir = 0; dir < 4; dir++) {
            int nr = r + delta[0][dir];
            int nc = c + delta[1][dir];
            if(inRange(nr,nc)&&map[nr][nc] == 1){
                checkIsland(nr,nc);
            }
        }
    }

    private static boolean inRange(int nr, int nc) {
        if ((nr>=0)&&(nr<N)&&(nc>=0)&&(nc<M)){
            return true;
        }
        return false;
    }
}
