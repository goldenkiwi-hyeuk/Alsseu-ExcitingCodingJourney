import java.io.*;
import java.util.*;

public class 로봇이지나간경로 {

    private static int[][] delta = {{-1,0,1,0},{0,1,0,-1}};
    private static int H,W, total;
    private static char[][] map;

    private static class Loc implements Comparable<Loc> {
        int sdir, row, col, dir, left;
        Set<Integer> set;
        List<Character> command;

        public Loc(int sdir, int row, int col, int dir, int left,Set<Integer> set, List<Character> command) {
            this.sdir = sdir;
            this.row = row;
            this.col = col;
            this.dir = dir;
            this.left = left;
            this.set = set;
            this.command = command;
        }

        @Override
        public int compareTo(Loc o){
            return this.command.size()-o.command.size();
        }

        @Override
        public String toString() {
            return "Loc{" +
                    "sdir=" + sdir +
                    ", row=" + row +
                    ", col=" + col +
                    ", dir=" + dir +
                    ", left=" + left +
                    ", set=" + set +
                    ", command=" + command +
                    '}';
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        map = new char[H][W];
        List<Integer> stList = new ArrayList();
        total = 0;
        for(int i = 0; i<H; ++i){
            str = br.readLine();
            for(int j = 0; j<W;++j){
                map[i][j] = str.charAt(j);
                if(map[i][j]=='#'){
                    ++total;
                }
            }
        }
        for(int i = 0; i<H; ++i){
            for(int j = 0; j<W;++j){
                if (map[i][j]=='#'){
                    boolean check = checkstart(i,j);
                    if (check){
                        stList.add(i*100+j);
                    }
                }
            }
        }
        int ans = 987654321;
        int sr = -1;
        int sc = -1;
        int sdir = -1;
        String ansCommand = "";
        for (int i = 0; i < stList.size(); ++i){
            int startrow = stList.get(i)/100;
            int startcol = stList.get(i)%100;
            List<Character> command = new ArrayList();
            Set<Integer> set = new HashSet();
            set.add(startrow*100+startcol);
            PriorityQueue<Loc> pq = new PriorityQueue<>();
            for (int dir = 0; dir<4;++dir){
                pq.add(new Loc(dir,startrow,startcol,dir,total-1, set, command));
            }
            while(!pq.isEmpty()){
                Loc now = pq.poll();
                if (now.left==0){
                    if (now.command.size()<ans){
                        ans = command.size();
                        sr = startrow;
                        sc = startcol;
                        sdir = now.sdir;
                        StringBuilder sb =  new StringBuilder();
                        for (int j = 0; j<now.command.size();++j){
                            sb.append(now.command.get(j));
                        }
                        ansCommand = sb.toString();
                    }
                    break;
                }
                for (int dir = 0; dir<4; ++dir){
                    int nr = now.row + delta[0][dir];
                    int nc = now.col + delta[1][dir];
                    int nr2 = now.row + delta[0][dir]*2;
                    int nc2 = now.col + delta[1][dir]*2;
                    boolean nrnc = inRange(nr,nc);
                    boolean nrnc2 = inRange(nr2,nc2);
                    if (!now.set.contains(nr*100+nc)&&!now.set.contains(nr2*100+nc2)&&nrnc&&nrnc2){
                        Set<Integer> newLocset = new HashSet();
                        newLocset.addAll(now.set);
                        newLocset.add(nr*100+nc);
                        newLocset.add(nr2*100+nc2);
                        List<Character> newLocCommand = new ArrayList();
                        newLocCommand.addAll(now.command);
                        if (now.dir == dir) {  // 같은 방향이면 A만 추가
                            newLocCommand.add('A');
                        } else {
                            int diff = (dir - now.dir + 4) % 4; // 현재 방향에서 목표 방향까지의 회전 수 (0~3)
                            if (diff == 1) { // 오른쪽으로 90도 회전
                                newLocCommand.add('R');
                            } else if (diff == 2) { // 180도 회전
                                newLocCommand.add('R');
                                newLocCommand.add('R');
                            } else if (diff == 3) { // 왼쪽으로 90도 회전
                                newLocCommand.add('L');
                            }
                            newLocCommand.add('A'); // 회전 후 전진
                        }
                        pq.add(new Loc(now.sdir, nr2 ,nc2, dir, now.left-2, newLocset ,newLocCommand));
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(sr+1).append(" ").append(sc+1).append("\n");
        if (sdir == 0){
            sb.append("^").append("\n");
        } else if (sdir == 1){
            sb.append(">").append("\n");
        } else if (sdir == 2){
            sb.append("v").append("\n");
        } else {
            sb.append("<").append("\n");
        }
        sb.append(ansCommand);
        System.out.println(sb);
    }

    private static boolean checkstart(int row, int col) { // 시작 가능 지점인지 체크하는 함수 <= 시작가능 지점은 사방위중 #이 하나여야 함
        int cnt = 0;
        for (int dir = 0 ; dir<4 ; ++ dir){
            int nr = row+delta[0][dir];
            int nc = col+delta[1][dir];
            boolean itCan = inRange(nr,nc);
            if (itCan){
                ++cnt;
            }
        }
        if (cnt == 1){
            return true;
        } else {
            return false;
        }
    }

    private static boolean inRange(int nr, int nc){ // 갈수 있는 지점인지 체크하는 함수
        if ((nr< H)&&(nr>=0)&&(nc<W)&&(nc>=0)&&(map[nr][nc]=='#')){
            return true;
        }
        return false;
    }
}