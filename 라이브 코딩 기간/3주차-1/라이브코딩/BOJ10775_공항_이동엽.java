package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

class Main10775 {

    static int parents[];

    public static void main(String[] args) throws IOException {
        /*
        문제 이해를 못함;
        순서대로 그냥 하나씩 도킹 시켜보면 될 줄 알았음.
        근데 바로 틀리고 힌트보니까
        i) 입력 받은 게이트에 넣음
        ii) 이미 있으면 입력게이트--에 넣음
        iii) 못넣으면 break
        인 것 같다고 유추..
        */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int g = Integer.parseInt(br.readLine()); // 1 <= G <= 100_000
        int p = Integer.parseInt(br.readLine()); // 1 <= P <= 100_000

        parents = new int[g + 1];
        for (int i = 1; i <= g; i++) parents[i] = i;

        int cnt = 0;
        for (int i = 0; i < p; i++) {
            int plane = Integer.parseInt(br.readLine());
            int gate = find(plane);

            if (gate == 0) break;

            cnt++;
            union(gate, gate - 1);
        }

        System.out.println(cnt);

    }

    private static void union(int a, int b) {
        a = find(a);
        b = find(b);
        parents[a] = b;
    }

    private static int find(int x) {
        if (parents[x] == x) return x;
        return parents[x] = find(parents[x]);
    }

}

