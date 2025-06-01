import java.util.*;

public class PG258707_n_1카드게임_정해준 {


    class Solution {
        public int solution(int coin, int[] cards) {
            int answer = 0;
            int N = cards.length;
            HashSet<Integer> nums = new HashSet<>(); // 기존 카드 풀
            HashSet<Integer> news = new HashSet<>(); // 새로 두장을 뽑은 카드 풀
            int idx = N/3;
            for(int i = 0; i < idx; i++) {
                nums.add(cards[i]);
            }

            while(true) {
                answer++; // 라운드 증가
                if(idx >= N)
                    break;
                // 일단 두장을 뽑아
                news.add(cards[idx]);
                news.add(cards[idx + 1]);
                idx += 2;
                boolean end = false;

                // 기존 카드에서 해결이 가능할 때
                for(int n : nums) {
                    if(nums.contains(N + 1 - n)) {
                        nums.remove(N + 1 - n);
                        nums.remove(n);
                        end = true;
                        break;
                    }
                }

                // 뽑은 카드 중 하나만 있으면 되는 경우
                if(!end) {
                    if(coin > 0) {
                        for(int n : nums) {
                            if(news.contains(N + 1 - n)) {
                                nums.remove(n);
                                news.contains(N + 1 - n);
                                coin--;
                                end = true;
                                break;
                            }
                        }
                    }
                }

                // 새로 뽑은 두 카드가 필요할 경우
                if(!end) {
                    if(coin > 1) {
                        for(int n : news) {

/* news로 뽑은 카드를 따로 관리하는 이유 -> 나중에 그 카드를 이용할 수 있기 때문에 관리를 하는 형식
예를 들면 첫번째 뽑은 1이 3번째에 뽑은 다른 수와 결합되서 할 수 있기 때문
그 가능 여부를 코인으로만 판단
만약에 여기 까지 오지 않고 기존의 카드풀에서 해결할 수 있어도 "더" 오래 플레이 하기 위해서이기 때문에 낼 수 있어도 코인을 소모할 수 있음 */

                            if(news.contains(N + 1 - n)) {
                                news.remove(n);
                                news.remove(N + 1 - n);
                                coin -= 2; // 카드를 두장 뽑아야 함
                                end = true;
                                break;
                            }
                        }
                    }
                }

                if(!end) { // 이래도 안끝나
                    break; //종료
                }

            }
            return answer;
        }
    }
}
