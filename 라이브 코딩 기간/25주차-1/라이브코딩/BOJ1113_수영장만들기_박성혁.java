import java.util.*;
import java.io.*;

class Main {
    static int N,M;
    static int[][] map;
    static Set<Integer> candidate;
    static int[][] delta = {{-1,1,0,0},{0,0,-1,1}};
    
    private static class Cell implements Comparable<Cell>{
        int row, col, height;
        
        Cell(){}
        
        Cell(int row, int col, int height){
            this.row = row;
            this.col = col;
            this.height = height;
        }
        
        @Override
        public int compareTo(Cell o){
            return this.height - o.height;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        int total = 0;
        for(int i = 0; i<N; ++i){
            String str = br.readLine();
            for(int j = 0; j<M ; ++j){
                map[i][j] = (int) (str.charAt(j) - '0');
            }
        }
        
        PriorityQueue<Cell> pq = new PriorityQueue<>();
        boolean[][] visited = new boolean[N][M];
        for(int i = 0; i<N ; ++i){
            pq.add(new Cell(i,0,map[i][0]));
            pq.add(new Cell(i,M-1,map[i][M-1]));
            visited[i][0] = true;
            visited[i][M-1] = true;
        }
        for(int i = 0; i<M ; ++i){
            pq.add(new Cell(0,i,map[0][i]));
            pq.add(new Cell(N-1,i,map[N-1][i]));
            visited[0][i] = true;
            visited[N-1][i] = true;
        }

        int curr = 0;
        while(!pq.isEmpty()){
            Cell cell = pq.poll();
            curr = Math.max(curr, cell.height);
            
            for(int dir = 0; dir<4; ++dir){
                int nr = cell.row + delta[0][dir];
                int nc = cell.col + delta[1][dir];
                
                if(inRange(nr,nc)&& !visited[nr][nc]){
                    visited[nr][nc] = true;
                    int nh = map[nr][nc];
                    if(nh < curr){
                        total += (curr-nh);
                    }
                    pq.add(new Cell(nr,nc, Math.max(nh,curr)));
                }
            }
        }
        
        System.out.println(total);
    }
    
    private static boolean inRange(int row, int col){
        if((row>=0)&&(row<N)&&(col>=0)&&(col<M)){
            return true;
        }
        return false;
    }
}
