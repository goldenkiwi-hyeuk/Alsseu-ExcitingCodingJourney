import java.util.*;

class Solution {
    
    // 기본아이디어 : 그리디
    
    Set<Integer> originCard, addCard;
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int len = cards.length;
        originCard = new HashSet();
        addCard = new HashSet();
        
        int idx = len / 3;
        for(int i = 0 ; i < idx; ++i){
            originCard.add(cards[i]);
        }
        int target = len + 1;   
        while(true){
            answer++;
            
            // 최종 카드까지 다 체크했다면 끝내기
            if(idx >= len){
                break;
            }
            addCard.add(cards[idx]);
            addCard.add(cards[idx+1]);
            idx += 2;
            boolean isok = false;
            // 최초 카드 더미에서 조합을 해낼수 있는지.
            for(int card : originCard){
                if(originCard.contains(target - card)){
                    originCard.remove(card);
                    originCard.remove(target - card);
                    isok = true;
                    break;
                }
            }
            
            // 최초 카드더미와 추가 카드더미에서 해낼수 있는지
            if(!isok){
                if(coin > 0){ // 추가 카드를 하나 사용하므로 코인이 1개 이상 있어야 한다.
                    for(int card : originCard){
                        if(addCard.contains(target - card)){
                            originCard.remove(card);
                            addCard.remove(target - card);
                            --coin;
                            isok = true;
                            break;
                        }
                    }
                }
            }
            
            // 추가 카드로 해낼수 있는지 확인
            if(!isok){
                if(coin > 1){ // 추가 카드를 2개 이상 사용하므로 코인이 2개 이상이어야 한다.
                    for(int card : addCard){
                        if(addCard.contains(target - card)){
                            addCard.remove(card);
                            addCard.remove(target - card);
                            coin -= 2;
                            isok = true;
                            break;
                        }
                    }
                }
            }
            
            // 완성되지 않았다.
            if(!isok){
                break;    
            }
        }
        return answer;
    }
}