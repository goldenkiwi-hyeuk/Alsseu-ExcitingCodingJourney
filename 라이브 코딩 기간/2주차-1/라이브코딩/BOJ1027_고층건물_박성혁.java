import java.io.*;
import java.util.*;

public class Main {
    
    // 기본 아이디어 : 완탐
    // cnt할 점을 찍고(A 빌딩, i), 모든 빌딩 하나 씩 찾아감(B 빌딩, j)
    // 두 건물사이의 접선을 구하고 A,B빌딩 사이에 (k가 접하는지 체크)
    
    static int N;
    static int[] arr;
    
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        for(int i =0; i<N;++i){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int max = 0;
        for(int i =0; i<N;++i){
            int cnt = 0;
            for(int j =0; j<N;++j){
                if(i == j) {
                    continue;
                }
                boolean isok = true;
                double[] list = find(i,arr[i],j,arr[j]);
                for(int k = Math.min(i,j)+1; k <Math.max(i,j);++k){
                    if(arr[k]>= list[0]*k+list[1]){ //접하거나 크다면 cnt 불가 
                        isok = false;
                        break;
                    }
                }
                if(isok){
                    cnt++;    
                }
            }
            if(cnt > max){
                max = cnt;
            }
        }
        System.out.println(max);
    }
    
    private static double[] find(int x1, int y1, int x2, int y2){ // 두점을 지나는 접선의 기울기와 y절편을 구해주는 함수
        double[] list = new double[2];
        list[0] = ((double) y2- y1)/ ((double) x2-x1); // 접선의 기울기 
        list[1] = (double) y1 - list[0]*(double)x1; // 접선의 y절편
        return list;
    }
}