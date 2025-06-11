import java.util.*;
import java.io.*;

class Main {
    static List<Integer> groupParent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<T; ++i){
            int F = Integer.parseInt(br.readLine());
            Map<String, Integer> myGroup = new HashMap<>();
            List<Integer> groupList = new ArrayList<>();
            groupParent = new ArrayList<>();
            groupParent.add(0);
            groupList.add(0);
            for(int j = 0; j < F; ++j){
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name1 = st.nextToken();
                String name2 = st.nextToken();
                // 두명이 각각 그룹이 존재할때
                if(myGroup.containsKey(name1)&&myGroup.containsKey(name2)){
                    int group1 = myGroup.get(name1);
                    int group2 = myGroup.get(name2);
                    int groupP1 = findParent(group1);
                    int groupP2 = findParent(group2);
                    // 두 그룹이 서로 다른 그룹일때
                    if(groupP1 != groupP2){
                        groupParent.set(Math.max(groupP1,groupP2),Math.min(groupP1,groupP2));
                        groupList.set(Math.min(groupP1,groupP2),groupList.get(Math.max(groupP1,groupP2))+groupList.get(Math.min(groupP1,groupP2)));
                        groupList.set(Math.max(groupP1,groupP2),0);
                        sb.append(groupList.get(Math.min(groupP1,groupP2))).append("\n");
                    } else { // 두 그룹이 서로 같은 그룹일때
                        sb.append(groupList.get(groupP1)).append("\n");
                    }
                } else if(myGroup.containsKey(name1)||myGroup.containsKey(name2)){ // 한명만 그룹이 존재할때
                    int group = 0;
                    if(myGroup.containsKey(name1)){
                        group = myGroup.get(name1);
                    } else {
                        group = myGroup.get(name2);
                    }
                    int groupP = findParent(group);
                    myGroup.put(name1, groupP);
                    myGroup.put(name2, groupP);
                    groupList.set(groupP, groupList.get(groupP)+1);
                    sb.append(groupList.get(groupP)).append("\n");
                } else { // 둘다 그룹이 존재하지 않을때
                    int newGroupNum = groupParent.size();
                    myGroup.put(name1, newGroupNum);
                    myGroup.put(name2, newGroupNum);
                    groupParent.add(newGroupNum);
                    groupList.add(2);
                    sb.append(2).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
    
    private static int findParent(int num){
        if(num == groupParent.get(num)){
            return num;
        }
        int groupP = findParent(groupParent.get(num));
        groupParent.set(num, groupP);
        return groupP;
    }
        
}