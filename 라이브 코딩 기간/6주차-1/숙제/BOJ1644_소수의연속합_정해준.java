import java.util.Scanner;

public class BOJ1644_소수의연속합_정해준 {
    static boolean[] check;
    static int cnt = 0;
    static int[] nums;
    static int N;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        nums = new int[N];
        check = new boolean[N + 1];
        elas(); // 소수를 구함
        int start = 0;
        int end = 0;
        int sum = 0;
        int answer = 0;

        while (start <= end && end <= cnt) {
            if(sum < N) { // 작으면 더해주고
                sum += nums[end++];
            } else {
                if(sum == N) { // 합이 N이 된 경우
                    answer++;
                }
                sum -= nums[start++]; // 같거나 작을 때는 앞의 있는 수를 빼준다.
            }
        }
        System.out.println(answer);
    }

    static void elas() { // 에라토스테네스의 체
        check[0] = true;
        check[1] = true;
        for(int i = 2; i*i <= N; i++) {
            if(!check[i]) {
                for(int j = i*i; j <= N; j+=i) {
                    check[j] = true;
                }
            }
        }

        for(int i = 1; i <= N; i++) {
            if(!check[i]) {
                nums[cnt++] = i;
            }
        }
    }
}
