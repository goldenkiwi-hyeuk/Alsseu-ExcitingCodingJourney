import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static int N, M, min;
    static int[][] map;
    static List<Integer> houseList;
    static List<Integer> storeList;
    static List<Integer> candidateList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        houseList = new ArrayList<>();
        storeList = new ArrayList<>();
        candidateList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            str = br.readLine();
            st = new StringTokenizer(str);
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    storeList.add(i*100+j);
                } else if (map[i][j] == 1) {
                    houseList.add(i*100+j);
                }
            }
        }
        min = Integer.MAX_VALUE;
        checkmap(0,0);
        System.out.println(min);
    }

    private static void checkmap(int index, int check) {
        if(check == M){
            int cnt = 0;
            for (int i = 0; i < houseList.size(); i++) {
                int mini = Integer.MAX_VALUE;
                for (int j = 0; j < candidateList.size(); j++) {
                    int dist = Math.abs(houseList.get(i)/100-candidateList.get(j)/100)+Math.abs(houseList.get(i)%100-candidateList.get(j)%100);
                    if (dist < mini) {
                        mini = dist;
                    }
                }
                cnt += mini;
            }
            if (cnt < min) {
                min = cnt;
            }
        }
        for (int i = index; i < storeList.size(); i++) {
            candidateList.add(storeList.get(i));
            checkmap(i+1,check+1);
            candidateList.remove(candidateList.size()-1);
        }
    }
}
