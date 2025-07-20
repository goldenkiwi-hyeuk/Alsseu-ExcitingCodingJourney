import java.util.*;

class Solution {
    // 기본 아이디어 : 유니온 파인드
    static Map<Integer, Integer> parent;
    public static int[] solution(int[] nodes, int[][] edges) {
        int[] answer = {0,0};
        Map<Integer, Integer> edgeCount = new HashMap<>();
        parent = new HashMap<>();
        
        // 부모 초기화
        for(int node : nodes){
            parent.put(node, node);
        }
        
        // 엣지들을 돌면서 유니온파인드 실행
        for(int[] edge : edges){
            int a = edge[0];
            int b = edge[1];
            union(a,b);
            edgeCount.put(a,edgeCount.getOrDefault(a,0)+1);
            edgeCount.put(b,edgeCount.getOrDefault(b,0)+1);
        }
        
        Map<Integer, int[]> tree = new HashMap<>();
        for(int node : nodes){
            int nodeP = findParent(node);
            int edge = edgeCount.getOrDefault(node,0);
            if(tree.containsKey(nodeP)){
                int[] checkNode = tree.get(nodeP);
                if(node%2 == 1){
                    if(edge %2 == 1){
                        ++checkNode[0];
                    } else {
                        ++checkNode[1];
                    }
                } else {
                    if(edge %2 == 1){
                        ++checkNode[2];
                    } else {
                        ++checkNode[3];
                    }
                }
                tree.put(nodeP, checkNode);
            } else {
                int[] checkNode = new int[4];
                if(node%2 == 1){
                    if(edge %2 == 1){
                        ++checkNode[0];
                    } else {
                        ++checkNode[1];
                    }
                } else {
                    if(edge %2 == 1){
                        ++checkNode[2];
                    } else {
                        ++checkNode[3];
                    }
                }
                tree.put(nodeP, checkNode);
            }
        }
        
        for(int treeP : tree.keySet()){
            int[] arr = tree.get(treeP);
            
            if(arr[0] + arr[3] == 1){
                ++answer[0];
            }
            if(arr[1] + arr[2] == 1){
                ++answer[1];
            }
        }
        return answer;
    }
    
    private static int findParent(int num){
        if(num == parent.get(num)){
            return num;
        }
        int parentNum = findParent(parent.get(num));
        parent.put(num, parentNum);
        return parentNum;
    }
    
    private static void union(int a, int b){
        int aP = findParent(a);
        int bP = findParent(b);
        int min = Math.min(aP,bP);
        int max = Math.max(aP,bP);
        parent.put(max, min);
    }
}