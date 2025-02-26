public class PG150365_미로탈출명령어_정해준 {
    class Solution {
        //명령어의 정렬이 빠른 순으로
        static int[] dx = {1,0,0,-1};
        static int[] dy = {0,-1,1,0};
        static char[] command = {'d','l','r','u'};
        static int[] end = new int[2];
        static int N, M, K;
        static int min; // 최단거리
        static String root;
        // static StringBuilder sb = new StringBuilder();
        public String solution(int n, int m, int x, int y, int r, int c, int k) {

            N = n;
            M = m;
            K = k;
            end[0] = r;
            end[1] = c;
            //최단 거리, 장애물이 없으니까 위치를 빼면 된다.
            min = Math.abs(r - x) + Math.abs(c - y);
            if(K < min || (K - min)%2 == 1) { //만약 이동해야 되는 거리가 최단거리보다 크거나 차가 홀수 일 경우 불가능
                return "impossible";
            }
            StringBuilder sb = new StringBuilder();
            dfs(x,y,0,sb);
            // dfs(x, y, 0);
            return root == null ? "impossible" : root;
        }

        static void dfs(int x, int y, int depth, StringBuilder sb) {
            if(depth + Math.abs(end[0] - x) + Math.abs(end[1] - y) > K)
                return; //지금까지 온 거리에서 목적지까지의 최단거리가 K 보다 클 경우 종료
            if(root != null)
                return; // 이미 끝까지 간 적이 있다.
            if(x == end[0] && y == end[1]) {
                if(depth == K){
                    root = sb.toString();
                    return;
                }
            }

            for(int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if(nx < 1 || nx > N || ny < 1 || ny > M)
                    continue; // 범위를 벗어남
                sb.append(command[i]);
                dfs(nx, ny, depth + 1, sb); //는 안됨
                sb.delete(depth, depth + 1);
                // sb.append(command[i]);
                // dfs(nx, ny, depth + 1);
                // sb.delete(depth, depth + 1);
            }
        }
    }
}
