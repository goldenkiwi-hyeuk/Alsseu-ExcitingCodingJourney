import java.util.*;
import java.io.*;

class Main {
    
    // 기본 아이디어 : 구현
    // 1. 낚시왕의 이동 + 낚시
    // 2. 상어의 이동 + 상어 식사
    static int get;
    static int R, C, M;
    static int[][] delta = {{-1,1,0,0},{0,0,1,-1}};
    static int[][] map;
    private static class Shark{
        int row, col, speed, dir, size;
        
        public Shark (){}
        
        public Shark(int row,int col,int speed,int dir,int size){
            this.row = row;
            this.col = col;
            this.speed = speed;
            this.dir = dir;
            this.size = size;
        }
    }
    static List<Shark> sharkList;
    static Set<Integer> aliveShark;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        sharkList = new ArrayList<>();
        sharkList.add(new Shark());
        aliveShark = new HashSet<>();
        map = new int[R+1][C+1];
        for(int i = 1; i<=M;++i){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharkList.add(new Shark(r,c,s,d,z));
            aliveShark.add(i);
            map[r][c] = i;
        }
        get = 0;
        for(int i = 1; i<=C;++i){
            fishhook(i); // 낚시 행위를 정의한 함수
            sharkMove(); // 상어의 이동을 정의한 함수
        }
        System.out.println(get);
    }
    
    private static void fishhook(int col){
        for(int i = 1; i<=R; ++i){
            if(map[i][col] != 0){
                aliveShark.remove(map[i][col]);
                get += sharkList.get(map[i][col]).size;
                break;
            }
        }
        return;
    }
    
    private static void sharkMove(){
        int[][] temp = new int[R+1][C+1];
        Set<Integer> temp2 = new HashSet<>();
        for(int sharkNum : aliveShark){
            sharkMove2(sharkNum);
            Shark s = sharkList.get(sharkNum);
            temp2.add(sharkNum);
            if(temp[s.row][s.col] == 0){
                temp[s.row][s.col] = sharkNum;
            } else {
                if(s.size > sharkList.get(temp[s.row][s.col]).size){
                    temp2.remove(temp[s.row][s.col]);
                    temp[s.row][s.col] = sharkNum;
                } else {
                    temp2.remove(sharkNum);
                }
            }
        }
        map = temp;
        aliveShark = temp2;
        return;
    }
    
    private static void sharkMove2(int sharkNum){
        int row = sharkList.get(sharkNum).row;
        int col = sharkList.get(sharkNum).col;
        int speed = sharkList.get(sharkNum).speed;
        int dir = sharkList.get(sharkNum).dir;
        
        if (dir <= 2) {
            speed %= (R-1)*2;    
        } else {
            speed %= (C-1)*2;    
        }
        
        for(int i = 0; i<speed; ++i){
            // 아직 이동하지 않은 상태에서 미리 계산
            int nr = row + delta[0][dir-1];
            int nc = col + delta[1][dir-1];
            // 격자를 벗어나면 방향 전환 후 다시 계산
            if (nr < 1 || nr > R || nc < 1 || nc > C) {
                // 방향 뒤집기
                if (dir == 1)         dir = 2;
                else if (dir == 2)    dir = 1;
                else if (dir == 3)    dir = 4;
                else                  dir = 3;
                // 뒤집힌 방향으로 한 칸 더
                nr = row + delta[0][dir-1];
                nc = col + delta[1][dir-1];
            }
            row = nr;
            col = nc;
        }
        sharkList.get(sharkNum).row = row;
        sharkList.get(sharkNum).col = col;
        sharkList.get(sharkNum).dir = dir;
    }
}