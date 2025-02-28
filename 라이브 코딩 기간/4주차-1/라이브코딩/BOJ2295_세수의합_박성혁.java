import java.io.*;
import java.util.*;

public class Main {
    // 기본 아이디어 : 2분탐색
    // 가장 큰 수 부터 탐색을 하며 목표 수(k)를 고르고, 2개의 숫자(y,z)를 고른다.
    // x가 배열안에 존재하는지 2분 탐색의 과정을 통해서 찾는다.
    
  public static void main(String args[]) throws IOException {
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    int N = Integer.parseInt(br.readLine());
    int[] arr = new int[N];
    for(int i = 0; i<N;++i){
        arr[i] = Integer.parseInt(br.readLine());
    }
    Arrays.sort(arr); // 배열이 순서대로 주어진다는 보장이 없으므로 정렬 
    out : for(int i = N-1; i>=1;--i){ // k 수
        for(int j = i-1; j>=0;--j){ // z 수
            for(int k = j; k>=0;--k){
                int target = arr[i] - arr[j] - arr[k]; // x수 
                if(target<0){ // 자연수만 주어짐으로 0보다 작다면 패스
                    continue;
                } else { // 그외의 경우는 이분탐색 시작
                    int left = 0;
                    int right = k;
                    while(left<= right){
                        int mid = (left+right)/2;
                        if(target == arr[mid]){ // 이분 탐색 결과 찾았다면 가장 큰 수부터 탐색을 시작했으므로 출력후 반복문 종료
                            System.out.println(arr[i]);
                            break out;
                        } else if(target<arr[mid]) {
                            right = mid-1;
                        } else {
                            left = mid+1;
                        }
                    }
                }
            }    
        }
    }

  }
}