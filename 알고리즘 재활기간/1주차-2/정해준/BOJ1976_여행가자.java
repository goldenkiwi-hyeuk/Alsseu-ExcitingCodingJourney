import java.util.Scanner;

public class BOJ1976_여행가자 {
    static int[] parent;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 도시의 개수
        int M = sc.nextInt(); // 여행지 수

        parent = new int[N+1];
        for(int i = 1; i <= N; i++){
            parent[i] = i; // 초기화
        }

        for(int i = 1; i <= N; i++){
            for(int j = 0; j < N; j++){
                int can = sc.nextInt();
                if(can == 1)
                    union(i,j+1); // 서열 정리
            }
        }
        int prev = sc.nextInt();
        String answer = "YES";
        for(int i = 1; i < M; i++){
            int next = sc.nextInt();
            if(find(prev) != find(next)){
                answer = "NO";
                break;
            }
            prev = next; // 다음위치로 갱신
        }
        System.out.println(answer);

    }

    public static void union(int a, int b){
        a = find(a);
        b = find(b);

        if(a == b) return;

        if(a<b)
            parent[b] = a;
        else
            parent[a] = b;
    }

    public static int find(int a){
        if(a == parent[a]) return a; // 자기자신이 가장 꼭대기이면

        return find(parent[a]);
    }

}
