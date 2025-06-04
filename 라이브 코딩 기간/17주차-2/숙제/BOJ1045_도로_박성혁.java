import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    
    // 기본아이디어 : 유니온파인드
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        // 우선순위를 정렬해주는 pq생성
        PriorityQueue<int[]> pq1 = new PriorityQueue<>((o1,o2)->{
            if (o1[0]==o2[0]){
                return o1[1]-o2[1];
            } else {
                return o1[0]-o2[0];
            }
        });
        PriorityQueue<int[]> pq2 = new PriorityQueue<>((o1,o2)->{
            if (o1[0]==o2[0]){
                return o1[1]-o2[1];
            } else {
                return o1[0]-o2[0];
            }
        });

        for (int i = 0; i < N; i++) {
            String str  = br.readLine();
            for (int j = i + 1; j < N; j++) {
                if (str.charAt(j)=='Y'){
                    pq1.add(new int[]{i,j});
                }
            }
        }
        
        // 도로의 끝점을 카운트 하는 배열
        int[] connect = new int[N];
        parent = new int[N];
        for (int i = 0; i < N; i++) {
            parent[i] = i;
        }
        while(!pq1.isEmpty()){
            int[] edge = pq1.poll();
            int parentA = findParent(edge[0]);
            int parentB = findParent(edge[1]);
            
            // 서로 부모가 다르다면 유니온
            // 해당 도로는 필수적으로 들어가야하는 도로
            if (parentA != parentB){
                unionParent(parentA,parentB);
                ++connect[edge[0]];
                ++connect[edge[1]];
                --M;
            } else {
                // 부모가 같다면 pq2에 넣기
                pq2.add(new int[]{edge[0],edge[1]});
            }
        }

        // 모든 도시가 이어졌는지 확인
        boolean flag = false;
        for (int i = 0; i < N; i++) {
            int parentNum = findParent(i);
            if (parentNum != 0){
                flag = true;
                break;
            }
        }
        
        // 도시가 이어지지 못하는 경우
        if (flag) {
            System.out.println(-1);
        } else {
            // 도시가 이어지기 위해서 M개의 도로를 다 사용한 케이스
            if (M == 0){
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i<N; ++i){
                    sb.append(connect[i]).append(" ");
                }
                System.out.println(sb.toString());
            } else if (pq2.size()>=M){
                // 도시는 이어졌고 남은 도로를 우선순위에 따라 연결 하는 경우
                for (int i = 0; i < M; ++i) {
                    int[] edge = pq2.poll();
                    ++connect[edge[0]];
                    ++connect[edge[1]];
                }
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i<N; ++i){
                    sb.append(connect[i]).append(" ");
                }
                System.out.println(sb.toString());
            } else {
                // 도시는 이어졌으나 M개의 도로를 사용하기에는 도로 갯수가 부족한 경우
                System.out.println(-1);
            }

        }
    }

    private static void unionParent(int parentA, int parentB) {
        int min = Math.min(parentA,parentB);
        int max = Math.max(parentA,parentB);
        parent[max] = min;
    }

    private static int findParent(int i) {
        if (parent[i] == i) return i;
        return parent[i] = findParent(parent[i]);
    }

}
