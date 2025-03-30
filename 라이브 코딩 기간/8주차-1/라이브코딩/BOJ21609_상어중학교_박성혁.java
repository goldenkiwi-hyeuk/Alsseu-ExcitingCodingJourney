import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 기본아이디어 : 구현
    // 구현 사항
    // 1) 가장 큰 블록 그룹 찾기 : dfs
    // 2) 블록 그룹 제거
    // 3) 격자에 중력
    // 4) 90도 반시계 회전

    static int N, M;
    static int[][] delta = {{-1, 1, 0, 0}, {0, 0, -1, 1}};
    static int[][] map;
    static boolean[][] visited;

    private static class Block implements Comparable<Block> {
        int rainbowCnt, r, c;
        Set<Integer> set;

        public Block(int rainbowCnt, int r, int c, Set<Integer> set) {
            this.rainbowCnt = rainbowCnt;
            this.r = r;
            this.c = c;
            this.set = set;
        }

        @Override
        public int compareTo(Block o) {
            if (this.set.size() != o.set.size()) { // 블록 크기로 비교
                return o.set.size() - this.set.size();
            } else if (this.rainbowCnt != o.rainbowCnt) { // 무지개색 블록 갯수로 비교
                return o.rainbowCnt - this.rainbowCnt;
            } else if (this.r != o.r) { // 기준블록의 행번호로 비교
                return o.r - this.r ;
            } else { // 기준블록의 열번호로 비교
                return o.c - this.c;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int ans = 0;
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true){
            visited = new boolean[N][N];
            PriorityQueue<Block> pq = new PriorityQueue<>();
            for (int i = 0; i < N; i++) { // 가장 큰 블록 탐색
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] > 0) {
                        Block init = new Block(0,987654321,987654321,new HashSet<>());
                        Block block = checkBlock(map[i][j],i,j, init);
                        pq.add(block);
                    }
                }
            }
            if (pq.isEmpty()){ // 블록이 없다면 게임 종료
                break;
            }
            Block b = pq.poll();
            Block b2 = pq.poll();
            if (b.set.size()<=1){
                break;
            }
            ans = ans + b.set.size()*b.set.size(); // 점수 추가
            for (int num : b.set){  // 가장 큰 블록을 꺼내서 제거
                map[num/100][num%100] = -2;
            }
            gravity();
            turn();
            gravity();
        }
        System.out.println(ans);
    }

    private static void turn() {
        int[][] map2 = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map2[i][j] = map[j][N-1-i];
            }
        }
        map = map2;
    }

    private static void gravity() {
        for (int r = N-1; r >= 0; r--) {
            for (int c = 0; c <N; ++c) {
                if (map[r][c] == -2){
                    for (int l = 1; l<N;++l){
                        int nr = r+ delta[0][0]*l;
                        int nc = c+ delta[1][0]*l;
                        if (inRange(nr,nc)){
                            if (map[nr][nc]>=0){
                                map[r][c] = map[nr][nc];
                                map[nr][nc] = -2;
                                break;
                            } else if (map[nr][nc] == -1){
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private static Block checkBlock(int target, int r, int c, Block block) {
        if (visited[r][c] == true) {
            return block;
        }
        if (map[r][c] == 0){
            if (block.set.contains(r*100+c)){
                return block;
            }
            block.rainbowCnt = block.rainbowCnt+1;
            block.set.add(r*100+c);
            for (int dir = 0; dir<4; dir++) {
                int nr = r + delta[0][dir];
                int nc = c + delta[1][dir];
                if (inRange(nr, nc)&& map[nr][nc] >=0) {
                    block = checkBlock(target,nr,nc,block);
                }
            }
        } else if (map[r][c] == target){
            visited[r][c] = true;
            if (r<block.r){
                block.r = r;
                block.c = c;
            } else if (r==block.r){
                if (c<block.c){
                    block.c = c;
                }
            }
            block.set.add(r*100+c);
            for (int dir = 0; dir<4; dir++) {
                int nr = r + delta[0][dir];
                int nc = c + delta[1][dir];
                if (inRange(nr, nc)&& map[nr][nc] >=0) {
                    block = checkBlock(target,nr,nc,block);
                }
            }
        }
        return block;
    }

    private static boolean inRange(int r, int c) {
        if ((r>=0)&&(r<N)&&(c>=0)&&(c<N)){
            return true;
        }
        return false;
    }
}
