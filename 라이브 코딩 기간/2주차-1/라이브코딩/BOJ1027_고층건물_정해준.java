import java.util.*;

// 1027 고층건물
public class BOJ1027_고층건물_정해준{
    static String txt = "10 1000000000 999999999 999999998 999999997 999999996 1 2 3 4 5";
    public static void main(String[] args) {
        // Scanner sc = new Scanner(txt);
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //빌딩의 개수
        //빌딩의 높이
        int[] buildings = new int[N];

        for(int i = 0; i < N; i++) {
            buildings[i] = sc.nextInt();
        }

        // 하나의 건물에서 양 옆 방향으로 보일 수 있는 건물들의 총합 가장 많은 것을  출력
        int answer = 0;
        for(int i = 0; i < N; i++) { //건물들 중 하나를 지정
            int cnt = 0;
            double tmp = 0;

            for(int j = i + 1; j < N; j++) { // 건물 기준으로 오른쪽 방향 건물들 탐색
                double next = (double) (buildings[j] - buildings[i])/(j - i);
                if(i+1 == j) {
                    cnt++;
                    tmp = next;
                } else {
                    if(tmp < next) {
                        cnt++;
                        tmp = next;
                    } // 기준에서 다음 기울기가 클 때만 갱신

                }
            }

            for(int j = i-1; j >= 0; j--) { // 건물 기준 왼쪽 탐색
                double next = (double) (buildings[j] - buildings[i])/(j - i);
                if(i-1 == j) {
                    cnt++;
                    tmp = next;
                } else {
                    if(tmp > next) {
                        cnt++;
                        tmp = next;
                    } // 기준에서 다음 기울기가 작을 때만 갱신

                }


            }

            answer = Math.max(cnt , answer);
        }

        System.out.println(answer);
    }
}