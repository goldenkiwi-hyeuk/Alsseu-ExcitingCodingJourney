package boj;

import java.util.Scanner;
import java.util.Stack;

public class Main6549 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        while (true) {
            int n = sc.nextInt();
            if (n == 0) break;

            int[] h = new int[n];
            for (int i = 0; i < n; i++) {
                h[i] = sc.nextInt();
            }

            sb.append(findMax(h)).append("\n");
        }
        sc.close();
        System.out.println(sb);
    }

    private static long findMax(int[] h) {
        Stack<Integer> stack = new Stack<>();
        long max = 0;
        int n = h.length;

        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && h[stack.peek()] >= h[i]) {
                int idx = stack.pop();
                long height = h[idx];
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;
                max = Math.max(max, height * width);
            }
            stack.push(i);
        }

        while (!stack.isEmpty()) {
            int idx = stack.pop();
            long height = h[idx];
            int width = stack.isEmpty() ? n : n - stack.peek() - 1;
            max = Math.max(max, height * width);
        }

        return max;
    }

}
