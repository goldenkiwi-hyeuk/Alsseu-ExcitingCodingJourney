import java.io.*;
import java.util.*;

public class Main {
    public static void main(String args[]) throws IOException{

        // 소수인지 아닌지 판별 필요
        // 들어오면서 판별 X -> 우선 입력 다 받아야함.
        // 배열을 만들고 -> 해당 숫자를 만들수있는(배수)의 인덱스 ++

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] score = new int[1000001]; // 점수판 -> 이게 결과값임
        boolean[] getNum = new boolean[1000001];

        for(int i : arr){
            getNum[i] = true;
        }


        for(int i=1; i<1000001; i++){
            if(getNum[i]){
                for(int j=i+i; j<1000001; j+=i){
                    if(getNum[j]){
                        score[i] += 1;
                        score[j] -= 1;
                    }
                }
            }
        }

        for(int i : arr){
            sb.append(score[i]).append(" ");
        }


        System.out.println(sb);
    }
}