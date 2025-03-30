// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class BOJ21609_상어중학교_정해준{
    static int[] di = {-1,1,0,0};
    static int[] dj = {0,0,-1,1};
    static int[][] map;
    static int N, M;
    static boolean[][] visited;
    static class Blocks implements Comparable<Blocks>{
        int cnt;
        int special;
        int r, c;
        List<int[]> blocks = new ArrayList<>();

        Blocks(int cnt, int special, int r, int c, List<int[]> blocks) {
            this.cnt = cnt;
            this.special = special;
            this.r = r;
            this.c = c;
            this.blocks = blocks;
        }

        @Override
        public int compareTo(Blocks o) {
            if(this.cnt == o.cnt){
                if(this.special == o.special) {
                    if(this.r == o.r) {
                        return o.c - this.c;
                    }
                    return o.r - this.r;
                }
                return o.special - this.special;
            }
            return o.cnt - this.cnt;

        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();

        map = new int[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // 가장 큰 블록 집단을 찾기
        List<Blocks> list = new ArrayList<>();
        findBlocks(list);
        int answer = 0;
        while(list.size() > 0) { // 블록 집단이 있을 경우 반복
            Collections.sort(list);
            answer += Math.pow(list.get(0).cnt,2);

            for(int[] b : list.get(0).blocks){
                map[b[0]][b[1]] = -2; //블록 제거
            }
            falling(); // 떨어지고
            rotate(); // 회전하고
            falling(); //다시 떨어지고
            list = new ArrayList<>();
            findBlocks(list); // 다시 블록 탐색
        }
        System.out.println(answer);
    }

    public static void findBlocks(List<Blocks> list){
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(visited[i][j] || map[i][j] == -1 || map[i][j] == -2 || map[i][j] == 0)
                    continue;
                bfs(i,j,list);
            }
        }
    }

    public static void rotate(){
        int[][] tmp = new int[N][N];
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                tmp[i][j] = map[j][N-1-i];
            }
        }

        map = tmp;
    }

    public static void falling() {
        for(int i = N-2; i >= 0; i--) { // 밑에서 부터 내려올 수 있는지 판단
            for(int j = 0; j < N; j++) {
                if(map[i][j] == -1 || map[i][j] == -2)
                    continue;
                int next = i + 1;
                while(next < N && map[next][j] == -2){
                    next++;
                }
                if(next-1 != i) { // 갱신
                    map[next-1][j] = map[i][j];
                    map[i][j] = -2;
                }
            }
        }
    }

    public static void bfs(int r, int c, List<Blocks> list){
        Queue<int[]> q = new LinkedList<>();
        List<int[]> blocks = new ArrayList<>();
        List<int[]> specials = new ArrayList<>();
        q.offer(new int[]{r,c}); //시작 위치
        int color = map[r][c];
        blocks.add(new int[]{r,c});
        visited[r][c] = true;
        int cnt = 1;
        int special = 0;
        while(!q.isEmpty()) {
            int[] now = q.poll();

            for(int i = 0; i < 4; i++) {
                int nx = now[0] + di[i];
                int ny = now[1] + dj[i];

                if(nx < 0 || ny < 0 || nx >= N || ny >= N || map[nx][ny] == -1 || map[nx][ny] == -2 || visited[nx][ny])
                    continue; // 범위밖, 검은 블록, 삭제한 블록, 이미 방문한 곳일 경우 다음

                if(map[nx][ny] != 0 && map[nx][ny] != color)
                    continue; // 무지개 색이 아닌데 다른 색일 경우도 다음음

                visited[nx][ny] = true;
                if(map[nx][ny] == 0) { // 무지개일 경우
                    special++; //무지개 블록 수 상승
                    specials.add(new int[]{nx,ny});
                }
                blocks.add(new int[]{nx,ny});
                cnt++;
                q.offer(new int[]{nx,ny});
            }
        }

        // 블록 갯수 기준 미달
        if(blocks.size() < 2) {
            for(int[] b : blocks) {
                visited[b[0]][b[1]] = false;
            }
            return;
        }
        // 무지개 블록 원상 복구
        for(int[] b : specials) {
            visited[b[0]][b[1]] = false;
        }

        blocks.sort(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                else {
                    return o1[0] - o2[0];
                }
            }
        });

        int startX = 0, startY = 0;
        for(int[] b : blocks) {
            if(map[b[0]][b[1]] == 0)
                continue;
            startX = b[0];
            startY = b[1];
            break;
        }

        list.add(new Blocks(cnt,special,startX,startY,blocks));
    }
}