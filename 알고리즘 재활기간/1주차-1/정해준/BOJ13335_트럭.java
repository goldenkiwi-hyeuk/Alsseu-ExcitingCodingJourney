import java.util.Scanner;

public class BOJ13335_트럭 {
    static class Truck{
        int weight;
        int pos;

        Truck(int weight, int pos){
            this.weight = weight;
            this.pos = pos;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int L = sc.nextInt();

        Truck[] trucks = new Truck[n];
        for(int i = 0; i < n; i++){
            int weight = sc.nextInt();
            trucks[i] = new Truck(weight,0);
        } // 트럭의 정보를 저장(무게와 위치

        int answer = 0;

        int cnt = 0; //건너기가 종료한 트럭의 수
        int first = 0;

        while(cnt < n){
            answer++; // 1초 증가

            int sum = 0; // 현재 올라가 있는 트록의 무게의 수
            int sum2 = 0; //현재 올라가 있는 트럭의 대 수
            int prevPos = -1;

            for(int i = first; i < n; i++){
                if(sum + 1 > w || sum2 + trucks[i].weight > L || prevPos == trucks[i].pos + 1) // 지금 확인하는 순번를 추가 못하는 경우
                    break;
                trucks[i].pos++;
                prevPos = trucks[i].pos;

                if(trucks[i].pos == w + 1) { // 해당 차량이 다리를 다 건넜을 경우
                    cnt++;
                    continue;
                }

                sum++; // 진행중인 트럭 추가
                sum2 += trucks[i].weight;
            }
            first = cnt;

        }
        System.out.println(answer);
    }
}
