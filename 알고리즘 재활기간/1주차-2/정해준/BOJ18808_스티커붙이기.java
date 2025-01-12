import java.util.Scanner;

public class BOJ18808_스티커붙이기 {

    static int[][] map = new int[41][41];
    static int N, M, K;
    static int[][] sticker = new int[11][11];

    static int R,C;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        K = sc.nextInt();

        while(K-- > 0){ // 스티커 다 확인 할 때 까지
            R = sc.nextInt();
            C = sc.nextInt();

            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    sticker[i][j] = sc.nextInt();
                }
            }

            loop : for(int i = 0; i < 4; i++){ // 총 4번 회전시킴
                for(int j = 0; j <= N - R; j++){
                    for(int k = 0; k <= M - C; k++) {
                        if(canDo(j,k)) { // 이미 적합한 장소일 경우 종료
                            break loop;
                        }
                    }

                }
                if(i < 3)
                    rotation();
            }

        }
        int answer = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                answer += map[i][j];
            }
        }
        System.out.println(answer);

    }

    public static boolean canDo(int x, int y){
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++) {
                if(map[i + x][j + y] == 1 && sticker[i][j] == 1){ // 만약 스티커 위치에 이미 공간이 차지하여 있을 경우 실패
                    return false;
                }
            }
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++) {
                if(sticker[i][j] == 1){ //위에 단계에서 걸러졌기 때문에 비어있는 경우만 남아 있음
                    map[i + x][j + y] = 1;
                }
            }
        }
        return true;
    }

    public static void rotation(){
        int[][] tmp = new int[12][12];
        for(int i = 0; i < R; i++){ // 원래 스티커 모양을 복사
            System.arraycopy(sticker[i], 0, tmp[i], 0, C);
        }

        for(int i = 0; i < C; i++){ // 스티커를 90도 이동
            for(int j = 0; j < R; j++){
                sticker[i][j] = tmp[R - 1 - j][i];
            }
        }
        int n = R;
        R = C;
        C = n;

    }
}
