import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17143_낚시왕_정해준{
    static class Shark{
        int r;
        int c;
        int s;
        int d;
        int z;
        public Shark(int r, int c, int s, int d, int z) {
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
    static int R, C, M;
    static int[] dr = {0,-1,1,0,0};
    static int[] dc = {0,0,0,1,-1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        List<Shark> sharks = new ArrayList<>();
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            sharks.add(new Shark(r, c, s, d, z));
        }
        int answer = 0;
        for (int t = 1; t <= C; t++) { // 낚시왕은 열을 이동
            // 잡기
            int minPos = R + 1;
            int idx = -1;
            for(int i = 0; i < sharks.size(); i++) {
                Shark s = sharks.get(i);
                if(s.c == t) {
                    if(minPos > s.r) {
                        minPos = s.r;
                        idx = i;
                    }
                }
            }
            if(idx != -1) { // 찾았을 경우
                Shark s = sharks.get(idx);
                answer += s.z;
                sharks.remove(idx); // 지우기
            }


            //  이동
            Map<String, Shark> map = new HashMap<>();

            for (Shark s : sharks) {
                moveShark(s);

                String key = s.r + "," + s.c;
                if (map.containsKey(key)) {
                    if (map.get(key).z < s.z) {
                        map.put(key, s); // 더 큰 상어가 먹음
                    }
                } else {
                    map.put(key, s);
                }
            }

            //남은 상어만 리스트에 넣기
            sharks = new ArrayList<>(map.values());
        }

        System.out.println(answer);
    }


    static void moveShark(Shark s) {
        if (s.d == 1 || s.d == 2) { // 상하
            int mod = (R - 1) * 2;
            int move = s.s % mod;
            for (int i = 0; i < move; i++) {
                if (s.d == 1 && s.r == 1) s.d = 2;
                else if (s.d == 2 && s.r == R) s.d = 1;
                s.r += dr[s.d];
            }
        } else { // 좌우
            int mod = (C - 1) * 2;
            int move = s.s % mod;
            for (int i = 0; i < move; i++) {
                if (s.d == 4 && s.c == 1) s.d = 3;
                else if (s.d == 3 && s.c == C) s.d = 4;
                s.c += dc[s.d];
            }
        }
    }
}
