import java.io.*;
import java.util.*;

class Solution {

    static class Point{
        int x,y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }


    static int N, M;
    static char[][] map;
    public int solution(String[] storage, String[] requests) {
        M = storage.length;
        N = storage[0].length();

        map = new char[M + 2][N + 2];

        for (int i = 0; i < M + 2; i++) {
            for (int j = 0; j < N + 2; j++) {
                if (i == 0 || j == 0 || i == M + 1 || j == N + 1) {
                    map[i][j] = ' ';
                }
            }
        }


        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) {
                map[i + 1][j + 1] = storage[i].charAt(j);
            }
        }
        // map 초기화 끝




        // 이제 컨테이너 꺼내기
        for(int i=0; i<requests.length; i++){
            if(requests[i].length()==1){
                checkMap(requests[i].charAt(0));
                // 바로 외부와 접한 컨테이너 탐색
            } else{
                // 2개이면 -> 해당 값 다 빼기
                char val = requests[i].charAt(0);
                for(int r = 0; r<M+2; r++){
                    for(int c=0; c<N+2; c++){
                        if(map[r][c]==val){
                            map[r][c]= ' ';
                        }
                    }
                }
            }
        }

        // map 디버깅 출력
        for (int i = 0; i < M + 2; i++) {
            for (int j = 0; j < N + 2; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }


        int result = 0;
        for(int i=1; i<M+1; i++){
            for(int j=1; j<N+1; j++){
                if(map[i][j] != ' '){
                    result++;
                }
            }
        }

        return result;
    }

    public static void checkMap(char val){
        int[] dr = {0,1,0,-1};
        int[] dc = {-1,0,1,0};

        Queue<Point> que = new ArrayDeque<>();
        boolean[][] outside = new boolean[M+2][N+2];
        boolean[][] isAccessible = new boolean[M+2][N+2];

        que.add(new Point(0,0));
        outside[0][0] = true;


        while(!que.isEmpty()){
            Point now = que.poll();

            for(int dir=0; dir<4; dir++){
                int nr = now.x + dr[dir];
                int nc = now.y + dc[dir];

                if(nr>=0 && nc>=0 && nr<M+2 && nc<N+2){
                    if(!outside[nr][nc] && (map[nr][nc]==' ')){
                        outside[nr][nc] = true;
                        que.add(new Point(nr,nc));
                    }
                }
            }
        }


        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (map[i][j]==val) {
                    for (int k = 0; k < 4; k++) {
                        int x = i + dr[k];
                        int y = j + dc[k];

                        if (x >= 0 && y >= 0 && x < M+2 && y < N+2) {
                            if (outside[x][y]) {
                                isAccessible[i][j] = true;
                                break;
                            }
                        }
                    }
                }
            }
        }

        for (int i = 1; i <= M; i++) {
            for (int j = 1; j <= N; j++) {
                if (isAccessible[i][j]) {
                    map[i][j] =' ';
                }
            }
        }

    }
}