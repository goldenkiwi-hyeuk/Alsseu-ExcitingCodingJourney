import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    // 배열에 수열 넣고 -> 새로운 값과 비교해서, 추가할지 교체할지 정한다. -> 이때 비교하는 과정에서 이분탐색 활용

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] LIS = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        LIS[0] = arr[0];
        int idx = 1;

        for(int i=1; i<N; i++){
            int val = arr[i];

            if(LIS[idx-1] < val) {
                idx++;
                LIS[idx-1] = val;
            } else{
                int left = 0;
                int right = idx;

                while(left<right){
                    int mid = (left + right)/2;

                    if(LIS[mid] < val){
                        left = mid+1;
                    } else {
                        right = mid;
                    }
                }
                LIS[left] = val;
            }

        }

        System.out.println(idx);

    }


}
