import java.util.*;

class Solution {
    
    public class Loc{ // 현재 빨간 수레와 파란 수레의 위치, 몇회 움직였는지, 빨간 수레가 방문한 곳들, 파란 수레가 방문한곳들의 정보를 가지고 있는 객체
        int redR, redC, blueR, blueC, cnt;
        Set<Integer> redVisit;
        Set<Integer> blueVisit;
        
        public Loc(){}
        
        public Loc(int redR, int redC, int blueR, int blueC, int cnt, Set<Integer> redVisit, Set<Integer> blueVisit){
            this.redR = redR;
            this.redC = redC;
            this.blueR = blueR;
            this.blueC = blueC;
            this.cnt = cnt;
            this.redVisit = redVisit;
            this.blueVisit = blueVisit;
        }
    }
    
    public int solution(int[][] maze) {
        int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
        int min = 987654321;
        int answer = 0;
        int redStartR= -1; // 빨간 수레의 시작지점
        int redStartC= -1;
        int redTargetR= -1; // 빨간 수레의 타겟지점
        int redTargetC= -1;
        int blueStartR= -1; // 파란 수레의 시작지점
        int blueStartC= -1;
        int blueTargetR= -1; // 파란 수레의 타겟지점
        int blueTargetC= -1;
        for(int i = 0; i<maze.length; ++i){
            for(int j = 0 ; j < maze[0].length; ++j){
                switch(maze[i][j]){
                    case 1:
                        redStartR = i;
                        redStartC = j;
                        break;
                    case 2 :
                        blueStartR = i;
                        blueStartC = j;
                        break;
                    case 3 :
                        redTargetR = i;
                        redTargetC = j;
                        break;
                    case 4 :
                        blueTargetR = i;
                        blueTargetC = j;
                        break;
                }
            }
        }
        Deque<Loc> deq = new ArrayDeque<>(); // 어차피 cnt순대로 들어가고 완벽하게 들어맞으면 그게 최소 cnt이다.
        Set<Integer> redv = new HashSet<>();
        Set<Integer> bluev = new HashSet<>();
        redv.add(redStartR*10+redStartC);
        bluev.add(blueStartR*10+blueStartC);
        deq.addLast(new Loc(redStartR, redStartC, blueStartR, blueStartC, 0, redv,bluev));
        while(!deq.isEmpty()){
            Loc now = deq.pollFirst();
            if((now.redR == redTargetR)&&(now.redC == redTargetC)&&(now.blueR == blueTargetR)&&(now.blueC == blueTargetC)){ // 모든 수레가 타겟 위치에 들어간 케이스
                if(min > now.cnt){
                    min = now.cnt;
                    break;
                }
            }
            if((now.redR == redTargetR)&&(now.redC == redTargetC)){ // 빨간 수레만 타겟위치에 들어간 케이스
                for(int dir2 = 0 ; dir2<4 ; ++dir2){
                    int nBR = now.blueR + delta[0][dir2];
                    int nBC = now.blueC + delta[1][dir2];
                    if(inRange(nBR,nBC, maze)){
                        if((nBR == now.redR)&&(nBC == now.redC)){ // 빨간 수레가 있는 위치에 파란수레는 갈수없음
                            continue;
                        } else {
                            if(!now.blueVisit.contains(nBR*10+nBC)){ // 방문하지 않은곳이라면 deq에 추가
                                Set<Integer> blueV = new HashSet<>();
                                blueV.addAll(now.blueVisit);
                                blueV.add(nBR*10+nBC);
                                deq.addLast(new Loc(now.redR, now.redC, nBR, nBC, now.cnt+1,now.redVisit,blueV));
                            }
                        }
                    }
                }
            } else if((now.blueR == blueTargetR)&&(now.blueC == blueTargetC)){ // 파란 수레만 타겟위치에 들어간 케이스
                for(int dir1 = 0 ; dir1<4 ; ++dir1){
                    int nRR = now.redR + delta[0][dir1];
                    int nRC = now.redC + delta[1][dir1];
                    if(inRange(nRR,nRC, maze)){
                        if((nRR == now.blueR)&&(nRC == now.blueC)){ // 파란 수레가 있는 위치에 빨간 수레는 갈수없음
                            continue;
                        } else {
                            if(!now.redVisit.contains(nRR*10+nRC)){
                                Set<Integer> redV = new HashSet<>();
                                redV.addAll(now.redVisit);
                                redV.add(nRR*10+nRC);    
                                deq.addLast(new Loc(nRR,nRC,now.blueR,now.blueC,now.cnt+1, redV, now.blueVisit));
                            }
                        }
                    }
                }
            } else { // 둘다 타겟 위치에 도착못한 케이스
                for(int dir1 = 0 ; dir1<4 ; ++dir1){
                    for(int dir2 = 0 ; dir2<4 ; ++dir2){
                        int nRR = now.redR + delta[0][dir1];
                        int nRC = now.redC + delta[1][dir1];
                        int nBR = now.blueR + delta[0][dir2];
                        int nBC = now.blueC + delta[1][dir2];
                        if(inRange(nRR,nRC,maze)&&inRange(nBR,nBC,maze)){
                            if((nRR == now.blueR)&&(nRC == now.blueC)&&(nBR == now.redR)&&(nBC == now.redC)){ // 서로의 위치 교환은 불가
                                continue;
                            } else if((nRR == nBR)&&(nRC == nBC)){ // 서로 같은곳으로 이동 불가
                                continue;
                            } else {
                                if(!now.redVisit.contains(nRR*10+nRC)&&!now.blueVisit.contains(nBR*10+nBC)){
                                    Set<Integer> redV = new HashSet<>();
                                    Set<Integer> blueV = new HashSet<>();
                                    redV.addAll(now.redVisit);
                                    redV.add(nRR*10+nRC);    
                                    blueV.addAll(now.blueVisit);
                                    blueV.add(nBR*10+nBC);
                                    deq.addLast(new Loc(nRR,nRC,nBR,nBC,now.cnt+1,redV,blueV));
                                }
                            }
                        }
                    }
                }
            }
        }
        if(min != 987654321){ // 만약 최솟값이 변경되지 않았다면 불가능한 케이스 그 경우에는 0을 반환
            answer = min;
        }
        return answer;
    }
    
    private boolean inRange(int nr, int nc, int[][] maze){ // maze[nr][nc]가 벽이 아니고 범위 내에 있는지 판단해주는 함수
        if((nr>=0)&&(nr<maze.length)&&(nc>=0)&&(nc<maze[0].length)&&(maze[nr][nc]!=5)){
            return true;
        } else {
            return false;
        }
    }
}