import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            int num = Integer.parseInt(st.nextToken());
            if(checkArr(num)>=0){
                sb.append(1).append("\n");
            } else{
                sb.append(0).append("\n");
            }
        }

        System.out.println(sb);
    }

    public static int checkArr(int num){

        int left = 0;
        int right = arr.length-1;

        while (left<= right){
            int mid = (left+right)/2;

            if(num<arr[mid]){
                right = mid -1;
            } else if(num>arr[mid]){
                left = mid+1;
            } else {
                return mid;
            }
        }

        return -1;
    }
}
