import java.util.*;
import java.io.*;

class Main {
    static int N, F;
    static int taxiR, taxiC;
    static int[][] map;
    static int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
    static List<Person> people;
    
    private static class Person {
        int nowR, nowC, targetR, targetC, moveCnt;
        
        public Person(){}
        
        public Person(int nowR, int nowC, int targetR, int targetC, int moveCnt){
            this.nowR = nowR;
            this.nowC = nowC;
            this.targetR = targetR;
            this.targetC = targetC;
            this.moveCnt = moveCnt;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for(int r = 0; r<N; ++r){
            st = new StringTokenizer(br.readLine());
            for(int c = 0; c<N; ++c){
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        taxiR = Integer.parseInt(st.nextToken()) - 1;
        taxiC = Integer.parseInt(st.nextToken()) - 1;
        
        people = new ArrayList<Person>();
        for(int i = 2; i < M+2 ; ++i){
            st = new StringTokenizer(br.readLine());
            int nowR = Integer.parseInt(st.nextToken()) -1;
            int nowC = Integer.parseInt(st.nextToken()) - 1;
            int targetR = Integer.parseInt(st.nextToken()) - 1;
            int targetC = Integer.parseInt(st.nextToken()) - 1;
            int moveCnt = howLong(nowR, nowC, targetR, targetC);
            people.add(new Person(nowR, nowC, targetR, targetC, moveCnt));
            map[nowR][nowC] = i;
        }
        int user = 0;
        while(F>0){
            int[] target = findPerson(taxiR, taxiC);
            // 더이상 태울 손님이 없음
            if(target[1] == 987654321){
                // 손님을 한번도 못태운 경우
                break;
            } else if (target[0] > F){ // 손님 태우러 가는길에 연료 오링
                F = -1;
                break;
            }
            F -= target[0];
            Person targetP = people.get(target[1]-2);
            if(targetP.moveCnt > F){ // 손님을 태우고 이동중에 연료 오링
                F = -1;
                break;
            } else {
                F += targetP.moveCnt;
                taxiR = targetP.targetR;
                taxiC = targetP.targetC;
                map[targetP.nowR][targetP.nowC] = 0;
                ++user;
            }
        }
        if (user < M) {
            System.out.println(-1);
        } else {
            System.out.println(F);
        }
    }
    
    // 현재 택시 위치 기준 가장 가깝고 번호가 작은 사람 찾기
    private static int[] findPerson(int taxiR, int tatxiC){
        int minDist = 987654321;
        List<int[]> candidates = new ArrayList<>(); // [거리, 행, 열, 승객번호]

        Deque<int[]> deq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        deq.addLast(new int[] {taxiR, tatxiC, 0});
        while(!deq.isEmpty()){
            int[] loc = deq.pollFirst();
            if(visited[loc[0]][loc[1]]){
                continue;
            } else {
                visited[loc[0]][loc[1]] = true;    
            }
            
            if(minDist< loc[2]){
                break;
            }
            
            if(map[loc[0]][loc[1]] > 1){
                // 더 가까운 승객을 찾았으면 기존 후보들 제거하고 새로운 후보로 시작
                if (loc[2] < minDist) { 
                    minDist = loc[2];
                    candidates.clear();
                    candidates.add(new int[]{loc[2], loc[0], loc[1], map[loc[0]][loc[1]]});
                } else if (loc[2] == minDist) { // 같은 거리의 승객이라면 추가
                    candidates.add(new int[]{loc[2], loc[0], loc[1], map[loc[0]][loc[1]]});
                }
            }
            
            for(int dir = 0; dir<4 ; ++dir){
                int nr = loc[0]+delta[0][dir];
                int nc = loc[1]+delta[1][dir];
                if(inRange(nr,nc)&&!visited[nr][nc]){
                    deq.addLast(new int[] {nr,nc,loc[2]+1});
                }
            }
        }
        
        if (candidates.isEmpty()) { // 찾은 승객이 없는 경우
            return new int[]{987654321, 987654321};
        }
        
        // 우선순위에 따라 승객 선택
        // 1. 행 번호가 가장 작은 승객
        // 2. 열 번호가 가장 작은 승객
        int selectedDist = 987654321;
        int selectedPersonId = 987654321;
        int minRow = 987654321;
        int minCol = 987654321;

        for (int[] candidate : candidates) {
            int dist = candidate[0];
            int r = candidate[1];
            int c = candidate[2];
            int personId = candidate[3];

            if (r < minRow) {
                minRow = r;
                minCol = c;
                selectedDist = dist;
                selectedPersonId = personId;
            } else if (r == minRow) {
                if (c < minCol) {
                    minCol = c;
                    selectedDist = dist;
                    selectedPersonId = personId;
                }
            }
        }

        return new int[]{selectedDist, selectedPersonId};
    }
    
    // 승객이 목적지까지 가는데 얼마나 걸리는지 찾기
    private static int howLong(int nowR, int nowC, int targetR, int targetC){
        int ans = 987654321;
        Deque<int[]> deq = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];
        deq.addLast(new int[] {nowR, nowC, 0});
        while(!deq.isEmpty()){
            int[] loc = deq.pollFirst();
            if(visited[loc[0]][loc[1]]){
                continue;
            } else {
                visited[loc[0]][loc[1]] = true;    
            }
            if((loc[0]== targetR)&&(loc[1]== targetC)){
                ans = loc[2];
                break;
            }
            for(int dir = 0; dir<4 ; ++dir){
                int nr = loc[0]+delta[0][dir];
                int nc = loc[1]+delta[1][dir];
                if(inRange(nr,nc)&&!visited[nr][nc]){
                    deq.addLast(new int[] {nr,nc,loc[2]+1});
                }
            }
        }
        return ans;
    }
    
    private static boolean inRange(int row, int col){
        if((row>=0)&&(row<N)&&(col>=0)&&(col<N)&&(map[row][col]!=1)){
            return true;
        }
        return false;
    }
}