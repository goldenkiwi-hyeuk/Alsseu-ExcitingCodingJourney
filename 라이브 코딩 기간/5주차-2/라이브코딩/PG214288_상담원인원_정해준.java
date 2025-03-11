import java.util.*;

public class PG214288_상담원인원_정해준 {

    class Solution {
        static ArrayList<int[]> list = new ArrayList<>();
        static int K;
        static int ans = 987654321;
        public int solution(int k, int n, int[][] reqs) {
            int[] types = new int[k + 1];
            Arrays.fill(types, 1);
            K = k;
            dfs(types, n - k, 1, reqs);


            return ans;
        }



        static void dfs(int[] types, int cnt, int idx,int[][] reqs) {
            if(cnt <= 0) { // 종료
                simul(types, reqs);
                return;
            }

            for(int i = idx; i < types.length; i++) {
                types[i] = types[i] + 1;
                dfs(types, cnt - 1, i,reqs);
                types[i] = types[i] - 1;
            }
        }

        static void simul(int[] types,int[][] reqs) {
            PriorityQueue<Integer>[] pq = new PriorityQueue[K + 1];
            int wait = 0;

            for(int i = 1; i <= K; i++) {
                System.out.print(types[i] + " ");

            }
            System.out.println();
            for(int  i = 1; i <= K; i++) {
                pq[i] = new PriorityQueue<Integer>();
                for(int j = 0; j < types[i]; j++) {
                    pq[i].offer(0); // 상담자의 수만큼 추가
                }
            }

            for(int[] req : reqs) {
                int start = req[0];
                int time = req[1];
                int type = req[2];

                int now = pq[type].poll();

                if(now > start) {
                    wait += now - start;
                    pq[type].offer(now + time);
                } else if(now < start) {
                    pq[type].offer(start + time);
                } else {
                    pq[type].offer(now + time);
                }

            }

            ans = Math.min(ans, wait);
        }

    }
}
