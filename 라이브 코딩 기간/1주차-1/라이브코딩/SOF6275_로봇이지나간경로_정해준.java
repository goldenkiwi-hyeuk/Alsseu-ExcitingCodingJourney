import java.util.*;

public class SOF6275_로봇이지나간경로_정해준 {
    // > , < , v , ^
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static char[][] map;

    static int H;
    static int W;
    static int dir;
    static char[] direction = {'^','<' , 'v' ,'>'};
    static int[] start = new int[2];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        H = sc.nextInt(); // 세로
        W = sc.nextInt(); // 가로
        map = new char[H][W]; // 맵

        for(int i = 0; i < H; i++){
            String str = sc.next();
            map[i] = str.toCharArray();
        }
        findStart();
        System.out.println((start[0]+1) + " " + (start[1]+1));
        System.out.println(direction[dir]); // 시작 위치 출력
        map[start[0]][start[1]] = '.'; // 시작 위치 초기화
        simul();

    }

    static void simul(){
        int x = start[0];
        int y = start[1];
        int d = dir;
        while(true){
            // System.out.println("방향" + dir + " " + d);
            if(d == dir) { // 방향과 다음 위치의 방향이 같을 경우 2번 전진
                int nx = x + dx[dir];
                int ny = y + dy[dir];
                // System.out.println("지금 " + x + " " + y);
                // System.out.println("다음 " + nx + " " + ny);
                x = nx;
                y = ny;
                map[nx][ny] = '.'; // 방문한 곳은 . 처리
                nx = x + dx[dir];
                ny = y + dy[dir];
                x = nx;
                y = ny;
                map[nx][ny] = '.';
                System.out.print('A');
                dir = d;
                d = checkDir(x,y);

            } else { // 회전 해야 되는 경우
                if(d == -1)
                    return;
                else if((dir - d) == 3 || (dir - d) == -1 ){ // 위,왼,아,우 순이기 때문에 회전할 때 값이 정해져 있음
                    System.out.print('L');
                } else if((dir - d) == -3 || (dir - d) == 1){
                    System.out.print('R');
                }
                dir = d;
            }

        }
    }

    static int checkDir(int x, int y){
        int cnt = 0;
        int d = 0;

        for(int i = 0; i < 4; i++) {

            int nx = x + dx[i];
            int ny = y + dy[i];

            if( nx >= 0 && nx < H && ny >= 0 && ny < W && map[nx][ny] == '#') {
                // System.out.print(i + "번 " + nx + " " + ny + " ");
                d = i;
                cnt++;
            }
        }
        return cnt != 1 ? -1 : d; // 시작일 경우나 이동하는 경우 주변에 #이 하나만 존재해야함
    }

    //시작 위치 확인
    static void findStart(){
        int cnt = 0;
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++){
                if(map[i][j] != '#')
                    continue;
                int check = checkDir(i,j);
                if(check != -1) { //만약 주변에 하나만 있는 # 일 경우 종료
                    start[0] = i;
                    start[1] = j;
                    dir = check;
                    return;
                }
            }
        }

    }
}

