import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    // 기본 아이디어 : dfs

    static char[][] map; // 방향을 저장할 배열
    static boolean[][] visited; // 방문 처리 배열
    static int[][] area; // 그룹번호를 저장할 배열
    static int areaNum; // 그룹번호
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        visited = new boolean[N][M];
        area = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        areaNum = 1; // 첫번째 그룹번호는 1

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == false) {
                    int num = checkArea(i,j,areaNum);
                    area[i][j] = num;
                    if (num == areaNum) {
                        areaNum++;
                    }
                }
            }
        }

        System.out.println(areaNum-1);
    }

    private static int checkArea(int i, int j, int areaNum) {
        if (visited[i][j]) { // 방문한 적이 있는데
            if (area[i][j] == 0){ // 해당번호가 0이다? 여태까지 방문한 모든 구역이 새로운 그룹번호 부여받음
                return areaNum;
            } else {
                return area[i][j]; // 존재하는 그룹번호가 있다? 발견한 그룹번호로 그룹을 처리
            }
        }
        visited[i][j] = true; // 방문처리

        int num = -1;
        switch (map[i][j]) {
            case 'U':
                num = checkArea(i-1,j,areaNum);
                area[i][j] = num;
                break;
            case 'D':
                num = checkArea(i+1,j,areaNum);
                area[i][j] = num;
                break;
            case 'L':
                num = checkArea(i,j-1,areaNum);
                area[i][j] = num;
                break;
            case 'R':
                num = checkArea(i,j+1,areaNum);
                area[i][j] = num;
                break;
        }
        return num;
    }
}
