import java.io.*;
import java.util.*;

class Main {
    
    // 기본아이디어 : 위상정렬
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1]; // 나보다 높은 위상을 저장하기 위한 배열
        Map<Integer, List<Integer>> map = new HashMap<>(); // 간선을 저장하기 위한 Map
        for(int i = 0; i<=N;++i){
            List<Integer> list = new ArrayList<>();
            map.put(i,list);
        }
        for(int i = 0; i<M;++i){
            str = br.readLine();
            st = new StringTokenizer(str);
            int start = Integer.parseInt(st.nextToken()); // 선행되어야 하는 문제
            int end = Integer.parseInt(st.nextToken()); // 후에 풀어야 하는 문제
            ++arr[end]; // 후에 풀어야 하는 문제가 위상이 높음
            map.get(start).add(end); // 선행되어야 하는 문제의 간선에 후에 풀어야 하는 문제 저장
        }
        PriorityQueue<Integer> pq = new PriorityQueue<>(); // 조건3 난이도 순서로 풀기 위한 조건을 위해서 pq 선언
        for(int i = 1; i<=N;++i){
            if(arr[i] == 0){ // 위상이 0인 문제들을 pq에 저장
                pq.add(i);
            }
        }
        StringBuilder sb = new StringBuilder();
        while(!pq.isEmpty()){ // pq가 텅빌때까지
            int num = pq.poll(); // 꺼내고
            sb.append(num).append(" ");
            for(int end : map.get(num)){ // 간선이 연결된 문제들의 위상을 하나씩 낮추고
                --arr[end];
                if(arr[end] == 0){ // 위상이 0이라면 pq에 넣는다.
                    pq.add(end);
                }
            }
        }
        System.out.println(sb);
    }
}