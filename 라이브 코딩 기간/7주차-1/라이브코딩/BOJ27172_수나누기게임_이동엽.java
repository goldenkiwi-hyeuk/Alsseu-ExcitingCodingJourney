package boj;

import java.util.*;

class Main27172 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 플레이어 수 2~100_000

        int[] z = new int[n + 1];
        int[] score = new int[n + 1];
        Set<Integer> set = new HashSet<>();

        for (int i = 1; i <= n; i++) {
            z[i] = sc.nextInt();
            set.add(z[i]);
        }
        sc.close();

        for (int i = 1; i <= n; i++) {
            int card = z[i];

            // 배수가 있으면 점수 +1
            for (int j = 2 * card; j <= 1_000_000; j += card) {
                if (set.contains(j)) score[i]++;
            }

            // 12 = 3 * 4 이므로 3과 4를 둘 다 찾아야함.
            for (int j = 1; j * j <= card; j++) {
                if (card % j == 0) {
                    // 약수 a
                    if (j != card && set.contains(j)) score[i]--;
                    // 약수 b
                    int val = card / j;
                    if (val != card && val != j && set.contains(val)) score[i]--;
                }
            }

        }

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) sb.append(score[i]).append(" ");
        System.out.println(sb);
    }
}