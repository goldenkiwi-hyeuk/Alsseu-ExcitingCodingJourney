public class PG250134_수레움직이기_정해준 {
    class Solution {
        static class Car{
            int x;
            int y;

            public Car() {
                this.x = 0;
                this.y = 0;
            }

            public Car(int x, int y){
                this.x = x;
                this.y = y;
            }
        }

        static boolean[][] rv; //빨간색 방문처리
        static boolean[][] bv; //파란색 방문처리

        static int[] dx = {-1,1,0,0};
        static int[] dy = {0,0,-1,1};
        static int[][] map;

        static int N;
        static int M;







        public int solution(int[][] maze) { //N * M
            N = maze.length;
            M = maze[0].length;
            map = new int[N][M];
            Car r = new Car();
            Car b = new Car();

            rv = new boolean[N][M];
            bv = new boolean[N][M];

            for(int i = 0; i < N; i++) {
                for(int j = 0; j < M; j++) {
                    map[i][j] = maze[i][j];
                    if(map[i][j] == 1) {// 빨강 시작칸
                        r = new Car(i,j);
                        rv[i][j] = true;
                    }
                    else if(map[i][j] == 2) { // 파랑 시작
                        b = new Car(i,j);
                        bv[i][j] = true;
                    }
                }
            } // 맵 저장 완료

            int answer = dfs(r, b, 0, false, false);
            return answer == 987654321 ? 0 : answer;
        }

        public static boolean check(Car r, Car b,int rnx, int rny, int bnx, int bny, boolean rflag, boolean bflag){ // 이동하게될 좌표가 들어옴
            //맵 밖인지 갈 수 없는 곳인지
            if(rnx < 0 || rnx >= N || rny < 0 || rny >= M
                    || bnx < 0 || bnx >= N || bny < 0 || bny >= M)
                return false;

            if(map[rnx][rny] == 5 || map[bnx][bny] == 5 )
                return false;

            // 위치가 스위치 된 경우
            if((r.x == bnx && r.y == bny) && (rnx == b.x && rny == b.y))
                return false;

            // 이동한 위치가 같을 경우
            if(rnx == bnx && rny == bny)
                return false;

            // 도착이 아닌경우에 이미 방문한 곳일 경우
            if((!rflag && rv[rnx][rny]) || (!bflag && bv[bnx][bny]))
                return false;

            return true;
        }

        public static int dfs(Car r, Car b, int idx, boolean rflag, boolean bflag){ // 이동하는 것 dfs로

            if(rflag && bflag)
                return idx; // 두 수레모두 종료 되었을 때

            int answer = 987654321;

            for(int i = 0; i < 4; i++) { // 4 * 4 의 경우의 수
                for(int j = 0; j < 4; j++) {

                    //만약 도착지점이라면 움직이지 않는다
                    int rnx = (!rflag) ? r.x + dx[i] : r.x;

                    int rny = (!rflag) ? r.y + dy[i] : r.y;
                    int bnx = (!bflag) ? b.x + dx[j] : b.x;
                    int bny = (!bflag) ? b.y + dy[j] : b.y; // 이동 확인

                    if(!check(r, b, rnx, rny, bnx, bny,rflag, bflag)) // 이동 가능한 경우인지 확인
                        continue;

                    // 가능한 경우
                    Car newr = new Car(rnx,rny); // 만약 도착 안한 상태라면 이동
                    Car newb = new Car(bnx,bny); // 만약 도착 안한 상태라면 이동

                    rv[rnx][rny] = true;
                    bv[bnx][bny] = true;
                    if(map[rnx][rny] == 3)
                        rflag = true;
                    if(map[bnx][bny] == 4)
                        bflag = true;

                    int tmp = dfs(newr,newb, idx + 1, rflag, bflag);
                    answer = Math.min(answer, tmp);


                    rflag = false;
                    bflag = false;
                    rv[rnx][rny] = false;
                    bv[bnx][bny] = false;

                }
            }


            return answer;
        }


    }
}
