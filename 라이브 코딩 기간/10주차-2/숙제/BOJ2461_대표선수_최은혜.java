import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N, M;

    static int[][] students;
    static class Info{
        int score, classIdx;

        public Info(int score, int classIdx){
            this.score = score;
            this.classIdx = classIdx;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        students = new int[N][M];

        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<M; j++) {
                students[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        List<Info> list = new ArrayList<>();
        for(int i=0; i<N; i++){
            for(int j=0; j<M; j++){
                list.add(new Info(students[i][j],i));
            }
        }

        list.sort(Comparator.comparingInt(o -> o.score)); // 점수 기준 정렬


        int[] count = new int[N];
        int left = 0, minScore = Integer.MAX_VALUE, kind = 0;

        for (int right = 0; right < list.size(); right++) {
            Info rInfo = list.get(right);
            if (count[rInfo.classIdx]++ == 0) {
                kind++;
            }


            while (kind == N) {
                int diff = list.get(right).score - list.get(left).score;
                minScore = Math.min(minScore, diff);

                Info lInfo = list.get(left);
                if (--count[lInfo.classIdx] == 0) {
                    kind--;
                }
                left++;
            }
        }

        System.out.println(minScore);
    }
}
