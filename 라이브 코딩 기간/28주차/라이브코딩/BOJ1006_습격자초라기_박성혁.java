import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc<T; ++tc){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            int[][] arr = new int[2][N];
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; ++i){
                arr[0][i] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i<N; ++i){
                arr[1][i] = Integer.parseInt(st.nextToken());
            }
            
            if(N == 1){
                if(arr[0][0]+arr[1][0]<=W){
                    sb.append(1).append("\n");
                } else {
                    sb.append(2).append("\n");
                }
                continue;
            }
            
            int ans = Integer.MAX_VALUE;
            
            // 맨앞과 맨뒤에 함께 들어가는 조가 없음
            ans = Math.min(ans, solveCase(arr,N,W, false, false));
            
            // 안쪽은 맨앞과 맨뒤가 함께 들어감
            if(arr[0][0] + arr[0][N-1] <= W){
                ans = Math.min(ans, solveCase(arr,N,W, true, false));
            }
            
            // 바깥은 맨앞과 맨뒤가 함께 들어감
            if(arr[1][0] + arr[1][N-1] <= W){
                ans = Math.min(ans, solveCase(arr,N,W, false, true));
            }
            
            // 안쪽과 바깥 모두 맨앞과 맨뒤 함께 들어감
            if(arr[0][0] + arr[0][N-1] <= W && arr[1][0] + arr[1][N-1] <= W){
                ans = Math.min(ans, solveCase(arr,N,W, true, true));
            }
            
            sb.append(ans).append("\n");
        }
        
        System.out.print(sb.toString());
    }
    
    private static int solveCase(int[][] arr, int N, int W, boolean top, boolean bottom){
        int[] a = new int[N];
        int[] b = new int[N];
        int[] c = new int[N+1];
        Arrays.fill(a, 987654321);
        Arrays.fill(b, 987654321);
        Arrays.fill(c, 987654321);
        
        // 점화식 초기 셋팅
        c[0] = 0;
        if(!top && !bottom){
            a[0] = 1;
            b[0] = 1;
            if(arr[0][0] + arr[1][0]<= W){
                c[1] = 1;
            } else {
                c[1] = 2;
            }
        } else if(top && !bottom){
            a[0] = 1;
            b[0] = 2;
            c[1] = 2;
        } else if(!top && bottom){
            a[0] = 2;
            b[0] = 1;
            c[1] = 2;
        } else if(top && bottom){
            a[0] = 2;
            b[0] = 2;
            c[1] = 2;
        }
        
        for(int i = 1; i<N; ++i){
            a[i] = c[i]+1;
            if(!((i == N-1 || i == 1) && top) && arr[0][i-1]+arr[0][i]<=W){
                a[i] = Math.min(a[i],b[i-1]+1); // 윗 가로 묶기
            }
            
            b[i] = c[i]+1;
            if(!((i == N-1 || i == 1) && bottom) && arr[1][i-1]+arr[1][i]<=W){
                b[i] = Math.min(b[i],a[i-1]+1); // 아래 가로 묶기
            }
                
            c[i+1] = 987654321;
            if(!(i == N-1 && bottom)){
                c[i+1] = Math.min(c[i+1], a[i]+1);
            }
            if(!(i == N-1 && top)){
                c[i+1] = Math.min(c[i+1], b[i]+1);
            }
            
            if(!(i == N-1 && (top||bottom)) && arr[1][i]+arr[0][i]<=W){
                c[i+1] = Math.min(c[i+1], c[i]+1);
            }
            if(!((i == N-1 || i == 1) && (top||bottom)) && (arr[1][i-1]+arr[1][i]<=W)&&(arr[0][i-1]+arr[0][i]<=W)){
                c[i+1] = Math.min(c[i+1], c[i-1]+2);
            }
        }
        
        if(!top && !bottom) return c[N];
        if(top && !bottom) return b[N-1];
        if(!top && bottom) return a[N-1];
        return c[N-1];
    }
}