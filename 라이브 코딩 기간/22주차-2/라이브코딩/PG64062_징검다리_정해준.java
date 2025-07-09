import java.util.*;

public class PG64062_징검다리_정해준 {

    class Solution {
        public int solution(int[] stones, int k) {
            int answer = 0;
            int min = 1;
            int max = 200000000;
            while(min <= max) {
                int mid = (min + max) / 2;

                if(canGo(stones, k, mid)) { // 다 건널 수 있는 경우
                    min = mid + 1;
                    answer = Math.max(answer, mid);
                } else {
                    max = mid - 1;
                }
            }


            return answer;
        }

        public boolean canGo(int[] arr, int k, int n){
            int cnt = 0;
            for(int a : arr) {
                if(a - n < 0) { // 만약에 인원수 만큼 다 건넜을 경우 0이하가 되면
                    cnt++;
                } else {
                    cnt = 0;
                }

                if(cnt == k) // 못건너는 다리가 k개랑 같아진다면
                    return false;
            }
            return true;
        }
    }

}
