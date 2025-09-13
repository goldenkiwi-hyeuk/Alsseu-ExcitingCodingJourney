import java.util.*;

class Solution {
    private static class Person{
        String name;
        String parent;
        int profit;
        
        Person(){}
        
        Person(String name, String parent ,int profit){
            this.name = name;
            this.parent = parent;
            this.profit = profit;
        }
    }
    
    static Map<String, Person> map;
    public static int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        map = new HashMap<>();
        init(enroll, referral);
        distribution1(seller, amount);
        
        int[] answer = new int[enroll.length];
        for(int i = 0; i<enroll.length; ++i){
            answer[i] = map.get(enroll[i]).profit;
        }
        return answer;
    }
    
    private static void init(String[] enroll, String[] referral){
        for(int i = 0; i<enroll.length; ++i){
            map.put(enroll[i], new Person(enroll[i], referral[i], 0));
        }
    }
    
    
    private static void distribution1(String[] seller, int[] amount){
        for(int i = 0; i<seller.length; ++i){
            distribution2(seller[i], 100*amount[i]);
        }
    }
    
    private static void distribution2(String seller, int money){
        if(seller.equals("-") || money == 0){
            return;
        }
        int trans = money/10;
        money -= trans;
        map.get(seller).profit += money;
        distribution2( map.get(seller).parent, trans);
    }
}