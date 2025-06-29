import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 기본 아이디어 : 투포인터
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 각 구간별 존재하는 막대의 갯수를 표현하는 배열
        // arr[1]에는 0~1의 구간을 갖고있는 막대의 갯수가 저장되게됨
        int[] arr = new int[1000002];
        for(int i = 0 ; i<N ; ++i){
            st = new StringTokenizer(br.readLine());
            int num1 = Integer.parseInt(st.nextToken());
            int num2 = Integer.parseInt(st.nextToken());
            for (int j = num1+1; j<=num2; ++j){
                ++arr[j];
            }
        }

        int left = 0;
        int right = 0;
        int sum = 0;
        while(right<1000001){
            if(K== sum){
                break;
            }
            if (sum < K){
                // 총합이 K보다 작다면 오른쪽 포인터 옮기기
                sum += arr[++right];
            } else {
                // 총합이 K보다 크다면 왼쪽 포인터 옮기기
                sum -= arr[++left];
            }
        }

        if (K == sum){
            System.out.println(left+" "+right);
        } else  {
            System.out.println("0 0");
        }
    }
}
