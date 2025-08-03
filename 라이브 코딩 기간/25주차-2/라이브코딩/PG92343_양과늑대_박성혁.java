import java.util.*;

class Solution {
    static List<List<Integer>> edgeList;
    static int[] infoList;
    static int answer;
    public static int solution(int[] info, int[][] edges) {
        edgeList = new ArrayList<>();
        infoList = new int[info.length];
        answer = 0;
        for(int i = 0; i< info.length; ++i){
            edgeList.add(new ArrayList<>());
            infoList[i] = info[i];
        }
        
        // 갈수 있는 엣지 정리하기
        for(int[] edge : edges){
            edgeList.get(edge[0]).add(edge[1]);
        }
        
        checkSheep(0,0,0,new HashSet<>());
        return answer;
    }
    
    private static void checkSheep(int idx, int sheep, int wolf, Set<Integer> nextList){
        int newSheep = sheep;
        int newWolf = wolf;
        
        if(infoList[idx] == 0){
            newSheep += 1;
        } else {
            newWolf += 1; 
        }
        
        if(newWolf>= newSheep){
            return;
        } else {
            answer = Math.max(answer, newSheep);
        }
        
        // 갈수 있는 목록 추가하기
        Set<Integer> nextSet = new HashSet<>(nextList);
        for(int next : edgeList.get(idx)){
            nextSet.add(next);
        }
        
        nextSet.remove(idx);
        
        for(int next : nextSet){
            checkSheep(next, newSheep, newWolf, nextSet);
        }
    }
}