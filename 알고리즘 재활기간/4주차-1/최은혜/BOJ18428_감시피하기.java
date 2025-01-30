import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static String[][] map;
    static int N;
    static Queue<Point> que;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new String[N][N];

        que = new ArrayDeque<>();


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                String val = st.nextToken();
                map[i][j] = val;
                if(val.equals("T")){
                    que.add(new Point(i,j));
                }
            }
        }


        if(setObstacle(0)){
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }


    }

    static boolean setObstacle(int count){
        if(count ==3) {
            return check();
        }

        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                if(map[i][j].equals("X")){
                    map[i][j] = "O";
                    if(setObstacle(count+1)) return true;
                    map[i][j] = "X";
                }
            }
        }

        return false;

    }

    static boolean check(){
        int[] dr = {0,1,0,-1};
        int[] dc = {-1,0,1,0};

        Queue<Point> temp = new ArrayDeque<>(que);

        while(!temp.isEmpty()){
            Point now = temp.poll();

            for(int dir=0; dir<4; dir++){
                int nr = now.x;
                int nc = now.y;

                while(true){
                    nr += dr[dir];
                    nc += dc[dir];

                    if(nr<0 || nc<0 || nr>N-1 || nc>N-1) break;
                    if(map[nr][nc].equals("O")) break;
                    if(map[nr][nc].equals("S")) return false;
                }
            }
        }

        return true;

    }

}
