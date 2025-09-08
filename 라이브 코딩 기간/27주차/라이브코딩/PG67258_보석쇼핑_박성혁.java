import java.util.*;

class Solution {
    public int[] solution(String[] gems) {
        Map<String, Integer> map = new HashMap<>();
        int point = -1;
        int len = -1;
        int min = 1;
        for(int i = 0; i<gems.length; ++i){
            if(map.containsKey(gems[i])){
                if(map.get(gems[i])==min){
                    map.put(gems[i],i+1);
                    int newMin = 987654321;
                    for(String jewel : map.keySet()){
                        newMin = Math.min(newMin, map.get(jewel));
                    }
                    if( newMin<=i+1 && i+1-newMin < len){
                        len = i+1-newMin;
                        point = i+1;
                    }
                    min = newMin;
                } else {
                    map.put(gems[i],i+1);    
                }
            } else {
                map.put(gems[i],i+1);
                len = i+1-min;
                point = i+1;
            }
        }
        int[] answer = new int[] {point-len, point};
        return answer;
    }
}