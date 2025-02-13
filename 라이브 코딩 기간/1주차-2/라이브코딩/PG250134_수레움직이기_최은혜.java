import java.io.*;
import java.util.*;

class Solution {

    static boolean[][] redVisited, blueVisited;

    static int n, m;

    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};

    static int answer;
    static int redEndR, redEndC, blueEndR, blueEndC;

    public static int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;

        redVisited = new boolean[n][m];
        blueVisited = new boolean[n][m];

        int redStartR = 0, redStartC = 0;
        int blueStartR = 0, blueStartC = 0;

        answer = Integer.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (maze[i][j] == 1) {
                    redVisited[i][j] = true;
                    redStartR = i;
                    redStartC = j;
                } else if (maze[i][j] == 2) {
                    blueVisited[i][j] = true;
                    blueStartR = i;
                    blueStartC = j;
                } else if (maze[i][j] == 3) {
                    redEndR = i;
                    redEndC = j;
                } else if (maze[i][j] == 4) {
                    blueEndR = i;
                    blueEndC = j;
                }
            }
        }

        getMove(maze, redStartR, redStartC, blueStartR, blueStartC, 0, false, false);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public static void getMove(int[][] maze, int redR, int redC, int blueR, int blueC, int cnt, boolean redReached, boolean blueReached) {

        if (!redReached && redR == redEndR && redC == redEndC) redReached = true;
        if (!blueReached && blueR == blueEndR && blueC == blueEndC) blueReached = true;

        if (redReached && blueReached) {
            answer = Math.min(answer, cnt);
            return;
        }

        ArrayList<int[]> redMoves = new ArrayList<>();
        ArrayList<int[]> blueMoves = new ArrayList<>();


        if (!redReached) {
            for (int i = 0; i < 4; i++) {
                int nextRedR = redR + dr[i];
                int nextRedC = redC + dc[i];
                if (isValid(nextRedR, nextRedC, maze) && !redVisited[nextRedR][nextRedC]) {
                    redMoves.add(new int[]{nextRedR, nextRedC});
                }
            }
        } else {
            redMoves.add(new int[]{redR, redC});
        }


        if (!blueReached) {
            for (int i = 0; i < 4; i++) {
                int nextBlueR = blueR + dr[i];
                int nextBlueC = blueC + dc[i];
                if (isValid(nextBlueR, nextBlueC, maze) && !blueVisited[nextBlueR][nextBlueC]) {
                    blueMoves.add(new int[]{nextBlueR, nextBlueC});
                }
            }
        } else {
            blueMoves.add(new int[]{blueR, blueC});
        }

        for (int[] redPos : redMoves) {
            for (int[] bluePos : blueMoves) {
                int nextRedR = redPos[0], nextRedC = redPos[1];
                int nextBlueR = bluePos[0], nextBlueC = bluePos[1];

                // 같은 위치로 이동
                if (nextRedR == nextBlueR && nextRedC == nextBlueC) continue;
                // 위치를 서로 바꾸는 경우
                if (redR == nextBlueR && redC == nextBlueC && blueR == nextRedR && blueC == nextRedC) continue;


                redVisited[nextRedR][nextRedC] = true;
                blueVisited[nextBlueR][nextBlueC] = true;
                getMove(maze, nextRedR, nextRedC, nextBlueR, nextBlueC, cnt + 1, redReached, blueReached);
                redVisited[nextRedR][nextRedC] = false;
                blueVisited[nextBlueR][nextBlueC] = false;
            }
        }
    }

    public static boolean isValid(int r, int c, int[][] maze) {
        return (0 <= r && r < n && 0 <= c && c < m && maze[r][c] != 5);
    }
}
