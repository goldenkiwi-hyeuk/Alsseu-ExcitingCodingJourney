import java.util.*;
import java.io.*;

class Main {
    // 기본 아이디어 : 그리디
    // 끝을 기준으로 오름차순 정렬
    // 끝점이 같다면 시작점을 기준으로 오름차순 정렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int tc = 1; tc<= T ; ++tc){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            boolean[] books = new boolean[N+1];
            int[][] arr = new int[M][2];
            for(int i = 0; i< M; ++i){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                arr[i][0] = a;
                arr[i][1] = b;
            }
            
            Arrays.sort(arr, (o1,o2)->{
                if(o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }
                return o1[1]-o2[1];
            });
            
            int ans = 0;
            // 범위를 탐색하며 주지 않은 책 건네주기
            for(int i = 0; i<M; ++i){
                int a = arr[i][0];
                int b = arr[i][1];
                for(int j = a ; j<=b ; ++j){
                    if(!books[j]){
                        ++ans;
                        books[j]= true;
                        break;
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}