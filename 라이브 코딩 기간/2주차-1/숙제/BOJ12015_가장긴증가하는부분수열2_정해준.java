import java.util.Scanner;

public class BOJ12015_가장긴증가하는부분수열2_정해준 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        int idx = 0;
        for (int i = 0; i < N; i++) {
            int n = sc.nextInt();

            if(i == 0) {
                arr[idx] = n;
                idx++;
            } else if(arr[idx-1] < n) {
                arr[idx] = n;
                idx++;
            }
            else { //들어올 값보다 한단계 큰 값을 찾기
                int low = 0;
                int high = idx;
                while(low < high) {
                    int mid = (low + high)/2;
                    if(arr[mid] < n) {
                        low = mid + 1;
                    } else {
                        high = mid;
                    }
                } // 기존의 값을 바꿔치기한다. 이것은 길이에 영향을 주지 않기 때문
                arr[low] = n;
            }

        }
        System.out.println(idx);
    }
}
