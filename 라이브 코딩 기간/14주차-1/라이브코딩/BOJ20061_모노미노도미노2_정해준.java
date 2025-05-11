import java.util.*;

public class BOJ20061_모노미노도미노2_정해준 {
    static boolean[][] blue = new boolean[4][10]; // 빨간 색과 파란색을 합침, [x][4],[x][5] 연한 존
    static boolean[][] green = new boolean[10][4]; // 빨간 색과 초록색 을 합침, [4][x],[5][x] 연한 존
    static int score = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        while(N-- > 0) {
            int t = sc.nextInt();
            int x = sc.nextInt();
            int y = sc.nextInt();
            //파란 맵을  처리
            moveBlue(x,y,t); // 이동
            //계산
            checkBlue();
            //초록맵 처리
            moveGreen(x,y,t);
            //계산
            checkGreen();
        }
        int g = 0;
        int b = 0;
        for(int i = 6; i <= 9; i++){
            for(int j = 0; j <= 3; j++) {
                if(blue[j][i])
                    b++;
                if(green[i][j])
                    g++;
            }
        }

        System.out.println(score);
        System.out.println(b + g);
    }

    public static void moveBlue(int x, int y, int t) { // 오른쪽으로 이동
        if(t == 1){ // 1x1
            int i;
            for(i = 4; i <= 9; i++){
                if(i == 9 || blue[x][i+1]) { // 끝에 도달하거나 다음 칸이 채워져 있으면
                    blue[x][i] = true;
                    break;
                }
            }
        } else if(t == 2) { // 1x2
            int i;
            for(i = 4; i <= 8; i++){
                if(i == 8 || blue[x][i+2]) { // 끝에 도달하거나 다음 칸이 채워져 있으면
                    blue[x][i] = true;
                    blue[x][i+1] = true;
                    break;
                }
            }
        } else if(t == 3) { // 2x1
            int i;
            for(i = 4; i <= 9; i++){
                if(i == 9 || blue[x][i+1] || blue[x+1][i+1]) { // 끝에 도달하거나 다음 칸이 채워져 있으면
                    blue[x][i] = true;
                    blue[x+1][i] = true;
                    break;
                }
            }
        }
    }

    public static void moveGreen(int x, int y, int t) { //아래로 이동
        if(t == 1){ // 1x1
            int i;
            for(i = 4; i <= 9; i++){
                if(i == 9 || green[i+1][y]) { // 끝에 도달하거나 다음 칸이 채워져 있으면
                    green[i][y] = true;
                    break;
                }
            }
        } else if(t == 2) { // 1x2
            int i;
            for(i = 4; i <= 9; i++){
                if(i == 9 || green[i+1][y] || green[i+1][y+1]) { // 끝에 도달하거나 다음 칸이 채워져 있으면
                    green[i][y] = true;
                    green[i][y+1] = true;
                    break;
                }
            }
        } else if(t == 3) { // 2x1
            int i;
            for(i = 4; i <= 8; i++){
                if(i == 8 || green[i+2][y]) { // 끝에 도달하거나 다음 칸이 채워져 있으면
                    green[i][y] = true;
                    green[i+1][y] = true;
                    break;
                }
            }
        }
    }

    public static void checkBlue(){
        // 꽉 찬 열 처리
        for(int i = 6; i <= 9; i++){ // 연한 영역을 제외하고 체크
            boolean isFull = true;
            for(int j = 0; j <= 3; j++) {
                if(!blue[j][i]) {
                    isFull = false;
                    break;
                }
            }
            if(isFull) {
                score++;
                // 해당 열 삭제 및 이동
                for(int k = i; k >= 5; k--){
                    for(int j = 0; j <= 3; j++) {
                        blue[j][k] = blue[j][k-1];
                    }
                }
                // 맨 왼쪽 열 초기화
                for(int j = 0; j <= 3; j++) {
                    blue[j][4] = false;
                }
                i--; // 다시 체크
            }
        }

        // 연한 영역 처리
        int lightCount = 0;
        for(int i = 4; i <= 5; i++){
            boolean hasBlock = false;
            for(int j = 0; j <= 3; j++) {
                if(blue[j][i]) {
                    hasBlock = true;
                    break;
                }
            }
            if(hasBlock) {
                lightCount++;
            }
        }

        // 연한 영역에 블록이 있으면 그만큼 맨 오른쪽부터 삭제
        for(int c = 0; c < lightCount; c++){
            // 모든 열을 한 칸씩 오른쪽으로 이동
            for(int i = 9; i > 4; i--){
                for(int j = 0; j <= 3; j++) {
                    blue[j][i] = blue[j][i-1];
                }
            }
            // 맨 왼쪽 열 초기화
            for(int j = 0; j <= 3; j++) {
                blue[j][4] = false;
            }
        }
    }

    public static void checkGreen(){
        // 꽉 찬 행 처리
        for(int i = 6; i <= 9; i++){ // 연한 영역을 제외하고 체크
            boolean isFull = true;
            for(int j = 0; j <= 3; j++) {
                if(!green[i][j]) {
                    isFull = false;
                    break;
                }
            }
            if(isFull) {
                score++;
                // 해당 행 삭제 및 이동
                for(int k = i; k >= 5; k--){
                    for(int j = 0; j <= 3; j++) {
                        green[k][j] = green[k-1][j];
                    }
                }
                // 맨 위 행 초기화
                for(int j = 0; j <= 3; j++) {
                    green[4][j] = false;
                }
                i--; // 다시 체크 이동 후 또 꽉 찬 행이 있을 수 있음
            }
        }

        // 연한 영역 처리
        int lightCount = 0;
        for(int i = 4; i <= 5; i++){
            boolean hasBlock = false;
            for(int j = 0; j <= 3; j++) {
                if(green[i][j]) {
                    hasBlock = true;
                    break;
                }
            }
            if(hasBlock) {
                lightCount++;
            }
        }

        // 연한 영역에 블록이 있으면 그만큼 맨 아래쪽부터 삭제
        for(int c = 0; c < lightCount; c++){
            // 모든 행을 한 칸씩 아래로 이동
            for(int i = 9; i > 4; i--){
                for(int j = 0; j <= 3; j++) {
                    green[i][j] = green[i-1][j];
                }
            }
            // 맨 위 행 초기화
            for(int j = 0; j <= 3; j++) {
                green[4][j] = false;
            }
        }
    }
}