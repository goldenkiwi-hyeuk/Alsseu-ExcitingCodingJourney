import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] combi = new int[2];
    static int[] arr;
    static int min;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        min = Integer.MAX_VALUE;
        checkCombi(0, N - 1);
        System.out.println(combi[0] + " " + combi[1]);
    }

    private static void checkCombi(int left, int right) {
        if (Math.abs(min) > Math.abs(arr[left] + arr[right])) {
            min = Math.abs(arr[left] + arr[right]);
            combi[0] = arr[left];
            combi[1] = arr[right];
        }
        if (arr[left] + arr[right] < 0) {
            if (left + 1 < right) {
                checkCombi(left+1, right);
            }
        } else if (arr[left] + arr[right] > 0) {
            if (right - 1 > left) {
                checkCombi(left, right-1);
            }
        }
    }
}
