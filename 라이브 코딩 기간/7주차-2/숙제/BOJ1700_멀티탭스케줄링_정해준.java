import java.util.ArrayList;
import java.util.Scanner;

public class BOJ1700_멀티탭스케줄링_정해준 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 콘센트 수
        int K = sc.nextInt(); // 요구 수
        boolean[] check = new boolean[101]; // 모든 전자기기의 사용확인
        int[] req = new int[K];
        for(int i = 0; i < K; i++) {
            req[i] = sc.nextInt();
        }
        int ans = 0; // 교체 횟수
        int cnt = 0; // 사용하고 있는 콘센트 수
        for(int i = 0; i < K; i++) {
            if(check[req[i]]) { // 이번 이미 꽃혀있는 경우
                continue;
            }
            else if(cnt < N) { //아직 콘센트가 남아있을 경우
                cnt++;
                check[req[i]] = true;
            }
            else { // 콘센트가 다 차 있을 경우
                ArrayList<Integer> list = new ArrayList<>(); // 뒤에 쓸 번호 확인용
                for(int j = i; j < K; j++) {
                    if(check[req[j]] && !list.contains(req[j])) { // 이미 꽃혀있는데 나중에 쓰는 경우 확인
                        list.add(req[j]);
                    }
                }
                if(list.size() < N) { // 꽃혀있는 것 중 몇 개만 다시 쓸 경우
                    // 안쓰는 것을 교체
                    for(int j = 1; j < check.length; j++) {
                        if(!list.contains(j)) {
                            check[j] = false;
                            break;
                        }
                    }

                } else { // 다 쓰는 경우 가장 늦게 쓰는 것을 교체
                    check[list.get(list.size()-1)] = false;
                }
                ans++;
                check[req[i]] = true;
            }
        }
        System.out.println(ans);
    }
}
