package boj;

import java.util.*;

public class Main1700 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        int[] electronics = new int[k];
        for (int i = 0; i < k; i++) {
            electronics[i] = sc.nextInt();
        }
        sc.close();

        Set<Integer> set = new HashSet<>();
        int cnt = 0;

        for (int i = 0; i < k; i++) {
            int electronic = electronics[i];

            if (set.contains(electronic)) continue;

            if (set.size() < n) {
                set.add(electronic);
                continue;
            }

            int toUnplug = -1;
            int farthest = -1;

            for (int s : set) {
                int next = Integer.MAX_VALUE;

                for (int j = i + 1; j < k; j++) {
                    if (s == electronics[j]) {
                        next = j;
                        break;
                    }
                }

                if (next > farthest) {
                    farthest = next;
                    toUnplug = s;
                }
            }
            set.remove(toUnplug);
            set.add(electronic);
            cnt++;
        }

        System.out.println(cnt);
    }
}
