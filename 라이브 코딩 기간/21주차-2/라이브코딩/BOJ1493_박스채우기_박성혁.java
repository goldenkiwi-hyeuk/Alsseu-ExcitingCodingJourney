import java.util.*;
import java.io.*;

class Main {
    // 기본아이디어 : 분할정복
    static int[] arr; // 박스의 크기와 갯수를 담을 배열
    static int use; // 여태 사용한 박스의 갯수
    static boolean fail = false; // 채우지 못하면 true

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int length = Integer.parseInt(st.nextToken());
        int width = Integer.parseInt(st.nextToken());
        int height = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(br.readLine());
        arr = new int[20];
        for(int i = 0; i<n; ++i){
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            arr[A] = B;
        }
        use = 0;
        useBox(length, width, height);
        if(fail){
            System.out.println(-1);
        } else {
            System.out.println(use);    
        }
    }
    
    private static void useBox(int length, int width, int height){
        if(length == 0 || width == 0 || height == 0){ // 세변중 하나라도 0이면 박스를 넣을 공간이 없음
            return;    
        }
        if(fail){ // 적절한 박스를 찾지 못하면 실패함
            return;
        }
        int min = Math.min(Math.min(length, width), height); // 가장 작은변 구하기
        int k = (int)(Math.log(min) / Math.log(2)); // 가장 큰 박스 크기 찾기
        while (k >= 0) {
            int side = 1 << k;
            if (arr[k] > 0 && side <= length && side <= width && side <= height) {
                arr[k]--;
                use++;

                // 박스를 채우고 남는 부피는 분할정복으로 찾아가기
                useBox(length, width, height-side); 
                useBox(length, width-side, side);
                useBox(length-side, side, side);
                return;
            }
            k--;
        }
        
        // 적절한 박스를 찾지 못한 경우
        fail = true;
    }
}