import java.io.*;
import java.util.*;

class Main {
    
    // 기본아이디어 : Union Find
    
    static int N,M;
    static int[][] map;
    static int[][] parent;
    static int[][] delta = {{-1,-1,0,1,1,1,0,-1},{0,1,1,1,0,-1,-1,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M]; // 주어지는 Map의 상태를 저장할 2차원 배열
        parent = new int[N][M]; // 자신의 부모를 저장할 2차원 배열
        for(int i = 0; i<N;++i){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M;++j){
                map[i][j] = Integer.parseInt(st.nextToken()); 
                parent[i][j] = i*1000+j; // 최초의 부모는 자기 자신의 위치
            }
        }
        
        for(int i = 0; i<N;++i){
            for(int j = 0; j<M;++j){
                int min = map[i][j]; // 기본적으로 자기 자신을 부모로 생각하고
                int parentIdx = i*1000+j; // 해당 인덱스값
                for(int dir = 0; dir<8;++dir){
                    int nr = i + delta[0][dir];
                    int nc = j + delta[1][dir];
                    if(inRange(nr,nc)){ // 해당 새로운 위치가 범위 안이가 확인
                        if(min>map[nr][nc]){ // 만약 주변 8방위 안에 작은값 존재시 가장 작은값과 위치를 파악
                            min = map[nr][nc];
                            parentIdx = nr*1000 + nc;
                        }
                    }
                }
                parent[i][j] = parentIdx; // 나의 부모는 내주변 8방위중 가장 작은 값
            }
        }
        
        int[][] answer = new int[N][M]; // 최종적으로 내가 답변할 배열
        for(int i = 0; i<N;++i){
            for(int j = 0; j<M;++j){
                int finalLoc = findParent(i,j); // 조상의 Idx를 찾고
                ++answer[finalLoc/1000][finalLoc%1000]; // 해당 위치의 값을 1증가
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N;++i){
            for(int j = 0; j<M;++j){
                sb.append(answer[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
    private static int findParent(int row, int col){
        if(parent[row][col] == row*1000+col){ // 나의 부모 인덱스가 나를 가리키는 경우
            return parent[row][col];
        } else { // 나의 부모 인덱스가 나를 가리키지 않는 경우 부모의 부모를 조사
            return parent[row][col] = findParent(parent[row][col]/1000, parent[row][col]%1000);
        }
    }
    
    private static boolean inRange(int row, int col){
        if((row>=0)&&(row<N)&&(col>=0)&&(col<M)){
            return true;
        }
        return false;
    }
}