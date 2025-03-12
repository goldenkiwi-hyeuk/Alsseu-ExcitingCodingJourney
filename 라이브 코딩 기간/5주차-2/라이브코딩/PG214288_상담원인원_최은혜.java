import java.io.*;
import java.util.*;

class Solution {
    static int type; // 상담 유형 개수
    static int mento; // 추가 상담원 수
    static int[][] map; // 상담 요청 배열
    static int[] arr; // 각 상담 유형에 배정된 상담원 수
    static int result = Integer.MAX_VALUE;
    static boolean[] visited;
    public int solution(int k, int n, int[][] reqs) {

        type = k;
        mento = n-k;
        map = reqs;
        arr = new int[k+1];
        visited = new boolean[k+1];

        for(int i=1; i<k+1; i++){
            arr[i] = 1;
        }


        getMento(0);


        return result;
    }

    // 상담원 배치
    public static void getMento(int num){
        if(num==mento){
            getTime();
            return;
        }


        for(int i=1; i<type+1; i++){
            if(!visited[i]){
                visited[i] = true;
                for(int j=1; j<=mento-num; j++){
                    arr[i] += j;
                    getMento(num+j);
                    arr[i] -= j;
                }
                visited[i] = false;
            }
        }

    }

    // 시간 계산
    public static void getTime(){
        PriorityQueue<Integer>[] pq = new PriorityQueue[type+1];
        int waitTime = 0;


        for(int i=0; i<type+1; i++){
            pq[i] = new PriorityQueue<>();
            for(int j=0; j<arr[i]; j++){
                pq[i].add(0);
            }
        }

        for(int[] input : map){
            int start = input[0];
            int total = input[1];
            int val = input[2];

            int nextMento = pq[val].poll(); // 가장 빨리 끝나는 상담원
            if(nextMento <= start){
                pq[val].add(start+total);
            } else{
                pq[val].add(nextMento+total);
                waitTime += (nextMento-start);
                if(waitTime >= result){
                    return;
                }
            }
        }

        result = Math.min(result, waitTime);

    }
}


