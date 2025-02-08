import java.util.Scanner;

public class BOJ1300_K번째수_정해준 {
    /*
    *  1 2 3 4 -> 4, 4
    *  2 4 6 8  -> 2, 3
    *  3 6 9 12 -> 1, 2
    *  4 8 12 16 -> 1, 1
    *
    *  k 번째의 수가 4일 때 작거나 같은 수의 개수 = 8, 4 / i
    *  k 번째의 수가 6일 때 작거나 같은 수의 개수 = 10 6 / i, j의 갯수
    *
    *
    * */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        //K 번째 값에 들어올 수 있는 수의 범위는 1 ~ K;
        long low = 1;
        long high = K;

        while(low < high) {
            //이분 탐색을 통해 임의의 값이 몇번째 순서인지 확인, 갯수를 통해 어떤 숫자가 k 번째에 오는지 확인하는 방식
            long mid = (low + high) / 2;
            long cnt = 0;

            for(int i = 1; i <= N; i++) {
                cnt += Math.min(mid/i, N);
            }
            //cnt > K 일 경우 임의의 수가 찾아야 되는 수보다 큼
            if(cnt >= K) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(low);
    }
}
