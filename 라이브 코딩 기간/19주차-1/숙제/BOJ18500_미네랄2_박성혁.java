import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int R, C, idx;
    static char[][] map;
    static int[][] cluster;
    static int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
    static List<Boolean> isTouchGround;

    // 기본아이디어 : 구현
    // 구현사항
    // 1. 창쏘기
    // 2. 클러스터 탐색
    // 3. 공중에 있는 클러스터(= 바닥에 붙어있지 않은 클러스터 찾기) 존재시 중력효과 적용
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char [R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            shootSpear(i, Integer.parseInt(st.nextToken()));
            cluster = new int[R][C];
            idx = 0;
            isTouchGround = new ArrayList<Boolean>();
            for (int r = 0; r<R; ++r){
                for (int c = 0; c<C; ++c){
                    if (map[r][c]=='x' && cluster[r][c] == 0){
                        ++idx;
                        isTouchGround.add(false);
                        checkCluster(r,c, idx);
                    }
                }
            }

            for (int j = 0; j< isTouchGround.size(); ++j){
                if (!isTouchGround.get(j)){
                    gravityMap(j+1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int r = 0; r<R; ++r){
            for (int c = 0; c<C; c++){
                sb.append(map[r][c]);
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void gravityMap(int idx) {
        int target = 987654321;
        // 클러스터를 탐색하면서 바닥 또는 아래에 위치한 클러스간의 간격이 가장 작은 값을 찾아냄
        for (int c = 0; c<C; c++){
            for (int r = R-1; r>=0; --r){
                int target2 = -1;
                if (cluster[r][c] == idx){
                    for (int k = r+1; k<R; k++){
                        if (cluster[k][c] == 0 || cluster[k][c] == idx){
                            target2 = k-r;
                        } else {
                            break;
                        }
                    }
                    if (target2 != -1){
                        target = Math.min(target, target2);
                    }
                }
            }

        }
        // 찾아낸 간격만큼 클러스터 전부 내리기
        for (int r = R-2; r>=0; --r){
            for (int c = 0; c<C; ++c){
                if (cluster[r][c] == idx){
                    cluster[r][c] = 0;
                    cluster[r+target][c] = idx;
                    map[r][c] = '.';
                    map[r+target][c] = 'x';
                }
            }
        }

    }

    private static void checkCluster(int r, int c, int idx) {
        cluster[r][c] = idx;
        if (r == R-1){
            isTouchGround.set(idx-1,true);
        }
        for (int dir = 0 ; dir<4 ; ++dir){
            int nr = r + delta[0][dir];
            int nc = c + delta[1][dir];
            if (inRange(nr,nc) && map[nr][nc] == 'x' && cluster[nr][nc] == 0){
                checkCluster(nr,nc,idx);
            }
        }
    }

    private static boolean inRange(int nr, int nc) {
        if ((nr>=0)&&(nr<R)&&(nc>=0)&&(nc<C)){
            return true;
        }
        return false;
    }

    private static void shootSpear(int order, int shootLoc) {
        if (order%2 == 0){ // 왼쪽에서 던질때
            for (int c = 0; c<C; c++){
                if (map[R-shootLoc][c] == 'x'){
                    map[R-shootLoc][c] = '.';
                    break;
                }
            }
        } else { // 오른쪽에서 던질때
            for (int c = C-1; c>=0; c--){
                if (map[R-shootLoc][c] == 'x'){
                    map[R-shootLoc][c] = '.';
                    break;
                }
            }
        }
    }

}
