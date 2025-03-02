import java.util.Scanner;

public class BOJ17779_게리맨더링2_정해준 {
    static int N;
    static int[][] people; //인구수에 대한 정보
    static int[] part;
    static boolean[][] line;
    static int total;
    static int MIN = Integer.MAX_VALUE;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        people = new int[N][N];

        for(int i = 0; i < N; i++) { // 인구 수 에 대한 정보를 확인
            for(int j = 0; j < N; j++) {
                people[i][j] = sc.nextInt();
                total += people[i][j];
            }
        }

        setNum();
        System.out.println(MIN);
    }

    public static void setNum() { // 변수를 반복문으로 설정 (d1, d2 ≥ 1, 1 ≤ x < x+d1+d2 ≤ N, 1 ≤ y-d1 < y < y+d2 ≤ N)
        for(int x = 0; x < N; x++) {
            for(int y = 0; y < N; y++) {
                for(int d1 = 1; d1 < N; d1++) {
                    for(int d2 = 1; d2 < N; d2++) {
                        if(x + d1 + d2 >= N || y - d1 < 0 || y + d2 >= N)
                            continue;
                        MIN = Math.min(MIN,numGap(x, y, d1, d2));
                    }
                }
            }
        }
    }


    public static int numGap(int x, int y, int d1, int d2) {
        line = new boolean[N+1][N+1];
        part = new int[6];

        // 경계선 안에 있거나 걸쳐 있을 경우를 확인
        for(int i = 0; i <= d1; i++) {
                line[x+i][y-i] = true;
                line[x+d2+i][y+d2-i] = true;
        }

        for(int i = 0; i <= d2; i++) {
                line[x+i][y+i] = true;
                line[x+i+d1][y+i-d1] = true;
        }

        // 1구역 1 ≤ r < x+d1, 1 ≤ c ≤ y
        for(int i = 0; i < x + d1; i++) {
            for(int j = 0; j <= y; j++) {
                if(line[i][j])
                    break; // 경계에 진입시 종료

                part[1] += people[i][j];
            }
        }

        // 2구역 1 ≤ r ≤ x+d2, y < c ≤ N
        for(int i = 0; i <= x + d2; i++) {
            for(int j = N - 1; j > y; j--) {
                if(line[i][j])
                    break;

                part[2] += people[i][j];
            }
        }

        // 3구역 x+d1 ≤ r ≤ N, 1 ≤ c < y-d1+d2
        for(int i = x + d1; i < N; i++) {
            for(int j = 0; j < y - d1 + d2; j++) {
                if(line[i][j])
                    break;

                part[3] += people[i][j];
            }
        }

        // 4구역 x+d2 < r ≤ N, y-d1+d2 ≤ c ≤ N
        for(int i = x + d2 + 1; i < N; i++) {
            for(int j = N - 1; j >= y-d1+d2; j--) {
                if(line[i][j])
                    break;
                part[4] += people[i][j];
            }
        }

        part[5] = total;
        for(int i = 1; i <= 4; i++) {
            part[5] -= part[i];
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <=5; i++) {
            max = Math.max(part[i],max);
            min = Math.min(part[i],min);
        }


        return max - min;
    }
}
