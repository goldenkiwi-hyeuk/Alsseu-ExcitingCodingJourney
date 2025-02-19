import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 기본 아이디어 : 위상정렬
    // 나보다 나중에 나와야 하는 팀이 있다면 나의 위상은 +1
    // 이걸 배열로 관리하고 내가 나왔다면 나보다 앞에 나와야 하는 팀들의 위상을 -1
    // 0이된 팀들을 큐에 넣고 출력

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N+1]; // 위상을 관리할 배열
        List<List<Integer>> edgelist = new ArrayList<>(); // 내 바로 앞에 나오는 존재들을 edgelist로 관리
        for (int i = 0; i <= N; i++) {
            edgelist.add(new ArrayList<>());
        }
        for (int i = 0; i < M; i++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            int total = Integer.parseInt(st.nextToken());
            int a = Integer.parseInt(st.nextToken()); 
            for (int j = 0; j<total-1;++j){
                int b = Integer.parseInt(st.nextToken()); // a는 항상 b보다 먼저 나와야 함
                edgelist.get(b).add(a);  // b의 엣지에는 a로 가는 엣지가 존재
                arr[a]++; // a의 위상은 +1
                a = b;
            }
        }
        Deque<Integer> deq = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (arr[i] == 0){  // 위상이 0인 즉 나보다 나중에 나와야할 존재들이 없는 팀들을 deq에 넣기
                deq.addLast(i);
            }
        }
        List<Integer> list = new ArrayList<>(); // 출력의 역순을 먼저 리스트에 저장
        while (!deq.isEmpty()){
            int num = deq.pollFirst(); // 덱에서 하날 꺼냄
            list.add(num);
            for (int child : edgelist.get(num)) { // 나에게 연결된 모든 엣지들을 조사
                arr[child]--; // 위상을 하나씩 줄여줌 (why? 내가 나왔기에 나보다 앞에 나와야 할 팀은 위상이 -1되어야 함 )
                if (arr[child] == 0){ // 해당 팀의 위상이 0이 되었다? deq에 추가
                    deq.addLast(child);
                }
            }
        }
        boolean isok = true;
        for (int i = 1; i<=N; ++i){ // 전체 위상 배열을 돌면서 위상이 0이상인 팀을 찾음 만약 존재한다면 팀 줄세우는게 불가능한 케이스
            if (arr[i]>0){
                isok = false;
                break;
            }
        }
        if (isok){ // 줄세우는게 가능하다면 리스트 역순으로 출력 준비
            StringBuilder sb = new StringBuilder();
            for (int i = list.size()-1; i>=0;--i){ 
                sb.append(list.get(i)).append("\n");
            }
            System.out.print(sb);
        } else { // 줄세우는게 불가능 하다면 0 출력
            System.out.println(0);
        }
    }
}
