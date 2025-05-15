import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 기본아이디어 : 구현
    // 1. 물고기 움직임
    // 2. 상어 움직임
    // 3. 물고기 냄새
    // 4. 물고기 복제

    // 물고기 냄새를 표시할 맵
    static int[][] map;

    // 상어 움직임
    static int[][] sharkdelta = {{-1, 0, 1, 0}, {0, -1, 0, 1}};
    // 물고기 움직임
    static int[][] fishdelta = {{0, -1, -1, -1, 0, 1, 1, 1}, {-1, -1, 0, 1, 1, 1, 0, -1}};
    // 물고기 리스트
    static Map<Integer, List<Integer>> fishList;
    static Map<Integer, List<Integer>> temp;
    static int sharkRow, sharkCol, nextSharkRow, nextSharkCol;
    static int maxEat;
    static boolean[][] visited;
    static int[] directions;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        map = new int[5][5];
        int M = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        fishList = new HashMap<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());
            if (fishList.containsKey(row * 10 + col)) {
                fishList.get(row * 10 + col).add(dir-1);
            } else {
                List<Integer> list = new ArrayList<>();
                list.add(dir-1);
                fishList.put(row * 10 + col, list);
            }
        }
        st = new StringTokenizer(br.readLine());
        sharkRow = Integer.parseInt(st.nextToken());
        sharkCol = Integer.parseInt(st.nextToken());

        while (S != 0) {
            temp = new HashMap<>();
            // 물고기 움직이기
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 5; j++) {
                    if (fishList.containsKey(i * 10 + j)) {
                        for (int dir : fishList.get(i * 10 + j)) {
                            int[] nextLoc = fishMove(i,j,dir);
                            if (temp.containsKey(nextLoc[0] * 10 + nextLoc[1])) {
                                temp.get(nextLoc[0] * 10 + nextLoc[1]).add(nextLoc[2]);
                            } else {
                                List<Integer> list = new ArrayList<>();
                                list.add(nextLoc[2]);
                                temp.put(nextLoc[0] * 10 + nextLoc[1], list);
                            }
                        }
                    }
                }
            }


            // 상어움직이기
            maxEat = -1;
            directions = new int[3];
            visited = new boolean[5][5];
            sharkMoveCandidate(sharkRow,sharkCol,3,0, new int[3]);
            for (int dir : directions) {
                if (temp.containsKey(dir)){
                    temp.remove(dir);
                    map[dir/10][dir%10] = 3;
                }
            }
            sharkRow = nextSharkRow;
            sharkCol = nextSharkCol;


            // 물고기냄새 제거
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 5; j++) {
                    if (map[i][j]>0){
                        --map[i][j];
                    }
                }
            }

            // 복제 마법 완료
            for (int i = 1; i < 5; i++) {
                for (int j = 1; j < 5; j++) {
                    if (temp.containsKey(i * 10 + j)) {
                        if (fishList.containsKey(i * 10 + j)) {
                            for (Integer dir : temp.get(i * 10 + j)) {
                                fishList.get(i * 10 + j).add(dir);
                            }
                        } else {
                            List<Integer> list = new ArrayList<>(temp.get(i * 10 + j));
                            fishList.put(i * 10 + j, list);
                        }
                    }
                }
            }
            --S;
        }

        int answer = 0;
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                if (fishList.containsKey(i * 10 + j)) {
                    answer += fishList.get(i * 10 + j).size();
                }
            }
        }
        System.out.println(answer);
    }

    private static void sharkMoveCandidate(int row, int col, int i, int cnt, int[] dirs) {
        if (i == 0){
            if (cnt>maxEat){
                maxEat = cnt;
                nextSharkRow = row;
                nextSharkCol = col;
                directions[0] = dirs[0];
                directions[1] = dirs[1];
                directions[2] = dirs[2];
            }
            return;
        }

        for (int dir=0 ; dir<4 ; ++dir){
            int nr = row + sharkdelta[0][dir];
            int nc = col + sharkdelta[1][dir];
            if (inRange(nr,nc)){
                int size = 0;
                if (temp.containsKey(nr * 10 + nc) && !visited[nr][nc]) {
                    size = temp.get(nr * 10 + nc).size();
                }
                dirs[3-i] = nr*10+nc;
                if (visited[nr][nc]){
                    sharkMoveCandidate(nr, nc, i-1, cnt+size, dirs);
                } else {
                    visited[nr][nc] = true;
                    sharkMoveCandidate(nr, nc, i-1, cnt+size, dirs);
                    visited[nr][nc] = false;
                }
            }
        }
    }

    private static int[] fishMove(int row, int col, int dir) {
        int[] answer = new int[3];
        boolean canMove = false;
        for (int dir2 = 8; dir2>=1; --dir2){
            int nr = row + fishdelta[0][(dir+dir2)%8];
            int nc = col + fishdelta[1][(dir+dir2)%8];
            if (inRange(nr,nc)&&map[nr][nc]==0){
                if((nr==sharkRow)&&(nc==sharkCol)){
                    continue;
                }
                answer[0] = nr;
                answer[1] = nc;
                answer[2] = (dir+dir2)%8;
                canMove = true;
                break;
            }
        }
        if (!canMove){
            answer[0] = row;
            answer[1] = col;
            answer[2] = dir;
        }
        return answer;
    }

    private static boolean inRange(int nr, int nc) {
        if ((nr>=1)&&(nr<5)&&(nc>=1)&&(nc<5)){
            return true;
        }
        return false;
    }
}