import java.util.*;
import java.io.*;

class Main {
    // 기본 아이디어 : 노드를 활용한 무언가
    private static class Node{
        int idx;
        List<Integer> children;
        
        public Node(){}
        
        public Node(int idx, List<Integer> children){
            this.idx = idx;
            this.children = children;
        }
    }
    
    static List<Node> nodeList;
    static boolean[] visited;
    static TreeSet<Integer> set1;
    static TreeSet<Integer> set2;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new  BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        nodeList = new ArrayList<>();
        visited = new boolean[n+1];
        for(int i =0; i<=n; ++i){
            nodeList.add(new Node(i, new ArrayList<Integer>()));
        }
        
        // 연관관계를 children 속성에 넣기
        for(int i = 1; i<=n ;++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());
            for(int j =0; j<k ; ++j){
                int num = Integer.parseInt(st.nextToken());
                nodeList.get(i).children.add(num);
            }
        }
        
        set1 = new TreeSet<>();
        set2 = new TreeSet<>();
        
        // 인덱스로 접근하며 n까지 모든 사람 확인
        // 따로 팀이 지정되어 있지 않다면 1팀에 배정정
        for(int i = 1; i<=n ;++i){
            findTeam(i,1);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(set1.size()).append("\n");
        while(!set1.isEmpty()){
            sb.append(set1.pollFirst()).append(" ");
        }
        sb.append("\n");
        sb.append(set2.size()).append("\n");
        while(!set2.isEmpty()){
            sb.append(set2.pollFirst()).append(" ");
        }
        System.out.println(sb.toString());
    }
    
    private static void findTeam(int idx, int team){
        // 이미 방문한 사람이라면 넘어가고
        if(visited[idx]){
            return;
        }
        
        visited[idx] = true;
        
        // 팀의 번호에 따라 팀에 배정
        // 연관 관계의 사람들은 해당 사람과 다른 팀에 배정되도록 team의 값을 바꾸어서 재귀 함수를 실행
        if(team == 1){
            set1.add(idx);
            for(int child : nodeList.get(idx).children){
                findTeam(child, 2);
            }
        } else {
            set2.add(idx);
            for(int child : nodeList.get(idx).children){
                findTeam(child, 1);
            }
        }
    }
}