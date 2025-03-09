import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 기본 아이디어 : ccw 알고리즘 활용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int x1 = Integer.parseInt(st.nextToken());
        int y1 = Integer.parseInt(st.nextToken());
        int x2 = Integer.parseInt(st.nextToken());
        int y2 = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int x3 = Integer.parseInt(st.nextToken());
        int y3 = Integer.parseInt(st.nextToken());
        int x4 = Integer.parseInt(st.nextToken());
        int y4 = Integer.parseInt(st.nextToken());

        int result = check(x1, y1, x2, y2, x3, y3, x4, y4);
        System.out.println(result);
    }

    public static int check(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        int abc = ccw(x1, y1, x2, y2, x3, y3);
        int abd = ccw(x1, y1, x2, y2, x4, y4);
        int cda = ccw(x3, y3, x4, y4, x1, y1);
        int cdb = ccw(x3, y3, x4, y4, x2, y2);

        if (abc * abd == 0 && cda * cdb == 0) { // 두 선분이 일직선 상에 있거나, 한 선분의 끝점이 다른 선분 위에 있는 경우
            if(sameline(x1, y1, x2, y2, x3, y3, x4, y4)){
                return 1;
            }
            return 0;
        }
        if (abc * abd <= 0 && cda * cdb <= 0){ // 선분의 끝점이 서로 다른 방향에 있거나, 또는 선분위에 있는 경우
            return 1;
        }
        return 0;
    }

    public static int ccw(int x1, int y1, int x2, int y2, int x3, int y3) {
        long result = (long) (x2 - x1) * (y3 - y1) - (long) (y2 - y1) * (x3 - x1);
        if (result > 0) return 1;
        else if (result < 0) return -1;
        else return 0;
    }

    public static boolean sameline(int x1, int y1, int x2, int y2, int x3, int y3, int x4, int y4) {
        if (Math.min(x1, x2) <= Math.max(x3, x4) && Math.min(x3, x4) <= Math.max(x1, x2)
                && Math.min(y1, y2) <= Math.max(y3, y4) && Math.min(y3, y4) <= Math.max(y1, y2)) {
            return true;
        }
        return false;
    }
}