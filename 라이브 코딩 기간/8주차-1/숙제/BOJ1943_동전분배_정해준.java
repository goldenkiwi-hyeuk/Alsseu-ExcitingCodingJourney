import java.util.Scanner;

public class BOJ1943_동전분배_정해준 {
    static class Money {
        int cost;
        int amount;

        public Money(int cost, int amount) {
            this.cost = cost;
            this.amount = amount;
        }
    }
    static boolean[][] dp;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < 3; t++) {
            int N = sc.nextInt();
            int sum = 0;
            Money[] money = new Money[N];
            for (int i = 0; i < N; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                money[i] = new Money(a, b);
                sum += a * b;
            }
            if (sum % 2 == 1) { // 총합이 짝수일 경우 나눌 수 없음
                sb.append(0).append("\n");
            } else {
                int max = sum / 2;
                dp = new boolean[N + 1][max + 1]; // 동전으로 얼마를 만들 수 있는지
                dp[0][0] = true; // 0개로 0원을 만들 수 있다.
                for (int i = 1; i <= N; i++) {
                    Money now = money[i - 1];
                    for (int j = 0; j <= max; j++) {
                        if (dp[i - 1][j]) { // 현재의 동전을 사용하지 않고 이 금액을 완성했을 경우, 이 동전 만큼의 수를 또 만들 수 있음
                            for (int k = 0; k <= now.amount; k++) {
                                if (j + now.cost * k <= max) {
                                    dp[i][j + now.cost * k] = true;
                                }
                            }
                        }
                    }
                }
                if(dp[N][sum / 2]) { // N개의 동전 다 썼을 때 max값을 만들 수 있으면 성공
                    sb.append(1).append("\n");
                } else {
                    sb.append(0).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
