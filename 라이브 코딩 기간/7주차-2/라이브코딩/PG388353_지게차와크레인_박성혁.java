import java.util.*;

class Solution {
    int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
    boolean[][] visited;
    char[][] container;
    int R, C;
    
    // 기본 아이디어 : 구현 + bfs
    // 지게차는 bfs를 통하여 제거 가능 여부 탐색 , 크레인은 탐색후 제거;
    
    public int solution(String[] storage, String[] requests) {
        container = new char[storage.length][storage[0].length()];
        for(int r = 0; r<storage.length; ++r){
            for(int c = 0 ; c<storage[0].length();++c){
                container[r][c] = storage[r].charAt(c);
            }
        }
        R = storage.length;
        C = storage[0].length();
        int answer = storage.length * storage[0].length();
        for(String request : requests){
            if(request.length()==2){ // 크레인 사용
                char target = request.charAt(0);
                for(int r = 0; r<storage.length; ++r){
                    for(int c = 0 ; c<storage[0].length();++c){
                        if(container[r][c] == target){
                            --answer;
                            container[r][c] = '-'; // 꺼내고 빈칸처리
                        }
                    }
                }
            } else { // 지게차 사용
                char target = request.charAt(0);
                List<int[]> list = new ArrayList<>();
                for(int r = 0; r<storage.length; ++r){
                    for(int c = 0 ; c<storage[0].length();++c){
                        if(container[r][c]== target && canRemove(r,c)){ // 제거 가능 여부는 bfs로 체크
                            --answer;
                            list.add(new int[] {r,c}); // 제거할 목록 정리, 미리 제거해 버리면 연쇄적으로 제거되기에 목록에 넣었다가 한번에 제거
                        }
                    }
                }
                for(int[] nums : list){
                    container[nums[0]][nums[1]] = '-'; // 꺼내고 빈칸처리
                }
            }
        }
        return answer;
    }
    
    private boolean canRemove(int r, int c){
        if((r==0)||(r==R-1)||(c==0)||(c==C-1)){ // 가장 바깥에 존재하는 케이스
            return true;
        } else {
            visited = new boolean[R][C];
            boolean isok = false;
            Deque<int[]> deq = new ArrayDeque<>();
            deq.add(new int[]{r,c});
            while(!deq.isEmpty()){
                int[] loc = deq.pollFirst();
                if(visited[loc[0]][loc[1]]) continue;
                visited[loc[0]][loc[1]] = true;
                if((loc[0]==0)||(loc[0]==R-1)||(loc[1]==0)||(loc[1]==C-1)){ // 외부와 연결된 케이스 제거 가능!
                    isok = true;
                    break;
                }
                for(int dir = 0; dir<4;++dir){
                    int nr = loc[0] + delta[0][dir];
                    int nc = loc[1] + delta[1][dir];
                    if(inRange(nr,nc) && container[nr][nc]=='-'){
                        deq.addLast(new int[] {nr,nc});
                    }
                }    
            }
            if(isok){
                return true;
            }
            return false;
        }
    }
    
    private boolean inRange(int r, int c){
        if((r>=0)&&(r<R)&&(c>=0)&&(c<C)){
            return true;
        } 
        return false;
    }
}