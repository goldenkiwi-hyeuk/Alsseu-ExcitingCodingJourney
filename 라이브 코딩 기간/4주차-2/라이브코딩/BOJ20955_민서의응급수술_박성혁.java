import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {

    // 기본 아이디어 : 유니온 파인드
    
    static int[] parent; // 자신의 부모를 저장할 배열
    static int ans; // 정답
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        parent = new int[N+1];
        ans = 0;
        for (int i = 1; i <= N; i++) { // 초기화
            parent[i] = i;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            union(u,v);
        }
        Set<Integer> set = new HashSet<>(); // set으로 현재의 부모 상태 체크
        for (int i = 1; i <= N; i++) {
            set.add(findParent(i));
        }
        System.out.println(set.size()-1 + ans); // 부모 상태가 1개뿐이라면 ans만 그 이상이라면 set.size()-1+ans를 출력
    }

    private static int findParent(int num) { // 부모를 찾는 함수
        if(num == parent[num]){
            return num;
        }
        return parent[num] = findParent(parent[num]); // 부모를 찾음과 동시에 갱신
    }

    private static void union(int num1, int num2) { // union 함수
        int num1P = findParent(num1);
        int num2P = findParent(num2);
        if(num1P > num2P){ 
            parent[num1P] = num2P;
        } else if (num2P > num1P){
            parent[num2P] = num1P;
        } else { // 이미 같은 부모라면 굳이 연결할 필요가 없고 오히려 끊어야 함 ans 증가
            ans++;
        }
    }
}
