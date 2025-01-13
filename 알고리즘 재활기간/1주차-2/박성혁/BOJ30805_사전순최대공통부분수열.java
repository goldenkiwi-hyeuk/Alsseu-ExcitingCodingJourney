import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr1 = new int[N];
        String str = br.readLine();
        StringTokenizer st = new StringTokenizer(str);
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }
        int M = Integer.parseInt(br.readLine());
        int[] arr2 = new int[M];
        str = br.readLine();
        st = new StringTokenizer(str);
        for (int i = 0; i < M; i++) {
            arr2[i] = Integer.parseInt(st.nextToken());
        }
        Deque<Integer>[][] dp = new ArrayDeque[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (arr1[i] == arr2[j]) {
                    if (i == 0 || j == 0) {
                        dp[i][j] = new ArrayDeque<>();
                    } else {
                        dp[i][j] = new ArrayDeque<>();
                        for (int num : dp[i - 1][j - 1]) {
                            dp[i][j].add(num);
                        }
                    }
                    if (dp[i][j].isEmpty()) {
                        dp[i][j].add(arr1[i]);
                    } else {
                        if (dp[i][j].peekLast() >= arr1[i]) {
                            dp[i][j].addLast(arr1[i]);
                        } else {
                            while (!dp[i][j].isEmpty() && dp[i][j].peekLast() < arr1[i]) {
                                dp[i][j].pollLast();
                            }
                            dp[i][j].addLast(arr1[i]);
                        }
                    }
                } else {
                    if (i == 0 && j == 0) {
                        dp[i][j] = new ArrayDeque<>();
                    } else if(i==0) {
                        Deque<Integer> max = CheckMaxDeque(null, dp[i][j - 1]);
                        dp[i][j] = new ArrayDeque<>();
                        for (int num : max) {
                            dp[i][j].add(num);
                        }
                    } else if(j == 0) {
                        Deque<Integer> max = CheckMaxDeque(dp[i - 1][j], null);
                        dp[i][j] = new ArrayDeque<>();
                        for (int num : max) {
                            dp[i][j].add(num);
                        }
                    } else {
                        Deque<Integer> max = CheckMaxDeque(dp[i - 1][j], dp[i][j - 1]);
                        dp[i][j] = new ArrayDeque<>();
                        for (int num : max) {
                            dp[i][j].add(num);
                        }
                    }
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(dp[N - 1][M - 1].size()).append("\n");
        while (!dp[N - 1][M - 1].isEmpty()) {
            sb.append(dp[N - 1][M - 1].pollFirst()).append(" ");
        }
        System.out.println(sb);
    }

    private static Deque<Integer> CheckMaxDeque(Deque<Integer> integers1, Deque<Integer> integers2) {
        List<Integer> deq1;
        List<Integer> deq2;
        if (integers1==null && integers2==null) {
            return new ArrayDeque<>();
        } else if (integers2==null) {
            return integers1;
        } else if (integers1==null) {
            return integers2;
        } else {
            deq1 = new ArrayList<>(integers1);
            deq2 = new ArrayList<>(integers2);
        }
        for (int i = 0; i < Math.min(deq1.size(), deq2.size()); i++) {
            if (deq1.get(i) == deq2.get(i)) {
                continue;
            } else if (deq1.get(i) > deq2.get(i)) {
                return integers1;
            } else {
                return integers2;
            }
        }
        if (deq1.size() > deq2.size()) {
            return integers1;
        } else {
            return integers2;
        }
    }
}
