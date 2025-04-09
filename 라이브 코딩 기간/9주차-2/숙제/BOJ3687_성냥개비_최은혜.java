import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Map;

public class Main {
    static int n;
    static long[] minVal; // 최소값 구하기
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        minVal = new long[101]; //

        int[] arr = {1,7,4,2,0,8}; // 성냥개비 개수로 만들 수 있는 최소 수

        Arrays.fill(minVal, Long.MAX_VALUE);
        minVal[2] = 1;
        minVal[3] = 7;
        minVal[4] = 4;
        minVal[5] = 2;
        minVal[6] = 6;
        minVal[7] = 8;
        minVal[8] = 10;

        for(int i=9; i<101; i++){
            for(int j=2; j<8; j++){
                String val = String.valueOf(minVal[i-j])+String.valueOf(arr[j-2]);
                minVal[i] = Math.min(Long.parseLong(val),minVal[i]);
            }
        }

        for(int i=0; i<n; i++){

            sb = new StringBuilder();
            int num = Integer.parseInt(br.readLine());

            getMinval(num);

            sb.append(" ");

            getMaxval(num);

            System.out.println(sb);

        }

    }

    public static void getMinval(int num){
        sb.append(minVal[num]);
    }

    public static void getMaxval(int num){
        if(num%2==0){
            for(int i=0; i<num/2; i++){
                sb.append(1);
            }
        } else {
            sb.append(7);
            for(int i=0; i<(num-3)/2; i++) {
                sb.append(1);
            }
        }
    }
}
