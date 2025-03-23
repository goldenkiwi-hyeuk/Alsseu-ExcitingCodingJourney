// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class BOJ27172_수나누기게임_정해준{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int MAX = 1000001;
        int[] players = new int[N]; // 각 플레이어가 가지는 수
        int[] cards = new int[MAX]; // 모든 수에 대한 정보
        boolean[] check = new boolean[MAX]; //그 수가 등장하는지
        for(int i = 0; i < N; i++) {
            players[i] = sc.nextInt();
            check[players[i]] = true; // 게임에 등장
        }

        for(int i : players) {
            //나누어 떨어질 수 있도록  i를 더해가면서 확인 
            for(int j = i * 2; j < MAX; j += i) {
                // j는 i로 나누어 떨어짐, 만약 다른 카드들이 있다면 i의 승리
                if(check[j]){ // 게임에 등장한 수일 경우우
                    cards[i]++;
                    cards[j]--;
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for(int i : players) {
            sb.append(cards[i]).append(" ");
        }
        System.out.println(sb);
    }



}