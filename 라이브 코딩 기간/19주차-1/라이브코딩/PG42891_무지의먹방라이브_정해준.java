import java.util.*;

public class PG42891_무지의먹방라이브_정해준 {
    class Food implements Comparable<Food> {
        int idx;
        int time;

        public Food(int idx, int time) {
            this.idx = idx;
            this.time = time;
        }

        @Override
        public int compareTo(Food o1) {
            return this.time - o1.time;
        }
    }

    class Solution {
        public int solution(int[] food_times, long k) {
            long sum = 0;
            for(int i = 0; i < food_times.length; i++) {
                sum += food_times[i];
            }
            if(sum <= k)
                return -1; // 다 먹음

            List<Food> list = new ArrayList<>();
            for(int i = 0; i < food_times.length; i++) {
                list.add(new Food(i + 1, food_times[i]));
            }
            Collections.sort(list);
            int size = list.size();
            int prev = 0; // 이전 음식을 먹는 시간
            for(int i = 0; i < list.size(); i++) {
                Food now = list.get(i);
                long gap = now.time - prev;
                if(gap != 0) { // 같은 음식이 여러개 있을 수 있기 때문

                    long eatTime = gap * size; // 이 음식을 다 먹기까지의 시간
                    if(eatTime <= k) { // 시간 내에 먹을 수 있을 경우
                        k -= eatTime;
                        prev = now.time; // 이전 음식을 먹은 시간을 갱신
                    } else { // 먹지 못할 경우
                        k %= size; //크기로 나눈 남어지를 이용해서 몇번째인지 확인
                        //남아있는 음식들로 자르기
                        list.subList(i, list.size()).sort((o1, o2) -> o1.idx - o2.idx);
                        return list.get(i + (int) k).idx;
                    }
                }
                size--;
            }

            return -1;
        }
    }
}
