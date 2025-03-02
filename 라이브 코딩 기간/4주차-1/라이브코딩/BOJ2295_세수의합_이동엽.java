package boj;

import java.util.*;

public class Main2295 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 5 <= N <= 1,000
      /*
      N^3 = 10억
      2개 더하고 하나 찾으면 N^2
      x + y + z = k
      x + y = k - z
      */

        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = sc.nextInt();
        sc.close();

        Arrays.sort(nums);

        Set<Integer> sum = new HashSet<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sum.add(nums[i] + nums[j]);
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int minus = nums[i] - nums[j];
                if (sum.contains(minus)) {
                    max = Math.max(max, nums[i]);
                }
            }
        }

        System.out.println(max);
    }
}