package boj;

import java.util.*;

public class Main2143 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        int n = sc.nextInt();
        int[] A = new int[n];
        for (int i = 0; i < n; i++) {
            A[i] = sc.nextInt();
        }

        int m = sc.nextInt();
        int[] B = new int[m];
        for (int i = 0; i < m; i++) {
            B[i] = sc.nextInt();
        }
        sc.close();

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += A[j];
                list.add(sum);
            }
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                sum += B[j];
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        long cnt = 0;

        for (int l : list) {
            int target = t - l;
            if (map.containsKey(target)) {
                cnt += map.get(target);
            }
        }

        System.out.println(cnt);
    }
}
