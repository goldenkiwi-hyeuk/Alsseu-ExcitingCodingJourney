import java.io.*;
import java.util.*;

class Main {
    
    // 기본 아이디어 : 에라토스테네스의 체
    // 근데 이게 왜 for문 2번보다 빠른걸까요?...
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        int max = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        Set<Integer> set = new HashSet<>(); // 확인해줄 set구성
        for(int i = 0; i<N ; ++i){
            arr[i] = Integer.parseInt(st.nextToken());
            set.add(arr[i]);
            if(arr[i] > max){
                max= arr[i];
            }
        }
        int[] arr2 = new int[max+1];
        for(int num : arr){
            for(int j = num*2;j<=max;j+=num){
                if(set.contains(j)){ // 존재하면 점수 추가하고 해당 배수는 점수 -1점
                    ++arr2[num];
                    --arr2[j];
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N ; ++i){
            sb.append(arr2[arr[i]]).append(" ");
        }
        System.out.println(sb);
    }
}