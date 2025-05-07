// 중간에 더하기가 추가 발생하는 문제
import java.util.*;

public class BOJ16946_벽부수고이동하기4_정해준 {
        static int[][] groupId;
        static int[][] map;
        static int[][] ans;
        static int N, M, K;
        static int[] dr = {-1,1,0,0};
        static int[] dc = {0,0,-1,1}; // 상하좌우
        static HashMap<Integer, Integer> hash = new HashMap<>();
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
            N = sc.nextInt();
            M = sc.nextInt();
            map = new int[N][M];
            groupId = new int[N][M];
            ans = new int[N][M];
            List<int[]> list = new ArrayList<>();
            for(int i = 0; i < N; i++) {
                String str = sc.next();
                for(int j = 0; j < M; j++) {
                    map[i][j] = str.charAt(j) - '0';
                    if(map[i][j] == 1) {
                        list.add(new int[]{i,j}); // 벽 만 저장
                    }
                }
            }
            K = list.size();

            grouping();
            for(int[] node : list) {
                count(node);
            }
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    sb.append(ans[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }

        public static void grouping(){ // 칸마다 한번에 갈수 있는 영역을 나눔
            int idx = 1;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    if(map[i][j] != 0 || groupId[i][j] != 0) // 벽이고 이미 그룹인 경우
                        continue;
                    Queue<int[]> q = new LinkedList<>();
                    q.offer(new int[] {i,j});
                    groupId[i][j] = idx; // 시작지점 처리
                    int cnt = 1;
                    while(!q.isEmpty()) {
                        int[] now = q.poll();
                        int r = now[0];
                        int c = now[1];
                        for(int k = 0; k < 4; k++) {
                            int nr = r + dr[k];
                            int nc = c + dc[k];
                            if(nr < 0 || nc < 0 || nr >= N || nc >= M) // 범위 밖이면
                                continue;
                            if(map[nr][nc] != 0) // 빈 곳이 아닌 경우
                                continue;
                            if(groupId[nr][nc] != 0) // 빈영역이 아니면
                                continue;
                            q.offer(new int[]{nr,nc});
                            groupId[nr][nc] = idx; // 빈영역을 그룹으로 변경
                            cnt++; // 갯수 추가
                        }
                    }
                    hash.put(idx,cnt);// 그룹의 갯수를 추가
                    idx++;
                }
            }

        }

        public static void count(int[] node) {
            int r = node[0];
            int c = node[1];
            boolean[] visited = new boolean[hash.size() + 1]; // 그룹의 중복 방지
            int total = 1;
            for(int i = 0; i < 4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];
                if(nr < 0 || nc < 0 || nr >= N || nc >= M)
                    continue;
                if(map[nr][nc] != 0)
                    continue;
                int idx = groupId[nr][nc]; // 다음 지점의 그룹 번호
                if(visited[idx])
                    continue;
                visited[idx] = true; // 이영역은 사용함함
                total += hash.get(idx);
            }
            ans[r][c] = total % 10;
        }

}
