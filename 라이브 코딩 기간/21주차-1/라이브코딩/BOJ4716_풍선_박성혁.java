import java.util.*;
import java.io.*;

class Main {
    // 기본아이디어 : 그리디

    private static class Team{
        int balloon, A, B, gap;
        Team(){}
        Team(int balloon,int A,int B){
            this.balloon = balloon;
            this.A = A;
            this.B = B;
            this.gap = Math.abs(A-B);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int INF = 987654321;
        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            
            if(N == 0 && A == 0 && B == 0){
                break;
            }
            // A와 B 방의 풍선의 갭차이 큰순서대로 꺼내는 PQ 생성
            PriorityQueue<Team> pq = new PriorityQueue<>((o1,o2)->{
                return o2.gap - o1.gap;
            });
            int ans = 0;
            for(int i = 1; i<=N;++i){
                st = new StringTokenizer(br.readLine());
                int K = Integer.parseInt(st.nextToken());
                int Da = Integer.parseInt(st.nextToken());
                int Db = Integer.parseInt(st.nextToken());
                pq.add(new Team(K, Da,Db));
            }
            while(!pq.isEmpty()){
                Team team = pq.poll();
                // 값이 싼 풍선을 가져오는게 원칙이나 풍선이 다 없어진 상황이라면 다른 방의 풍선을 가져온다.
                if(team.A > team.B){
                    if(team.balloon < B){
                        ans += (team.B*team.balloon);
                        B -= team.balloon;
                    } else {
                        ans += (team.B*B);
                        ans += team.A*(team.balloon-B);
                        A -= (team.balloon-B);
                        B = 0;
                    }
                } else {
                    if(team.balloon < A){
                        ans += (team.A*team.balloon);
                        A -= team.balloon;
                    } else {
                        ans += (team.A*A);
                        ans += team.B*(team.balloon-A);
                        B -= (team.balloon-A);
                        A = 0;
                    }
                }
            }
            sb.append(ans).append("\n");
        }
        System.out.println(sb);
    }
}