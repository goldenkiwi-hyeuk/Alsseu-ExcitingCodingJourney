import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class BOJ1082_방번호 {
    static class Number implements Comparable<Number> { // 비용이 낮지만 값이 큰 것으로 정렬

        int number;
        int cost;

        Number(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Number o) {
            if(this.cost == o.cost)
                return o.number - this.number;
            return o.cost - this.cost;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        int[] weights = new int[N];
        List<Number> list = new ArrayList<Number>();

        for(int i = 0; i < N; i++) {
            int w = sc.nextInt();
            weights[i] = w;
            list.add(new Number(i, w));
        } // 번호화 가격을 추가

        Collections.sort(list);

        int money = sc.nextInt();
        String str = "";

        if(N == 1) { // 한방밖에 없는 경우
            System.out.println(0);
            return;
        }

        int size;
        // 만약 가장 비용이 작은 값이 0일 경우 (숫자의 크기도 내림차로 했기 때문에 0이 가장 마지막에 위치
        if(list.get(N-1).number == 0) {
            Number n = list.get(N-2);
            // 두번째로 가격이 작은 것을 선택
            if(money - n.cost < 0){
                System.out.println(0);
                return; // 두번째로 싼 숫자를 살 수 없기 때문에 0이됨
            }
            money -= n.cost;
            str += Integer.toString(n.number); // 첫번째 숫자만 2번째로 낮은 코스트 나머지는 0으로 채움

            size = money / list.get(N-1).cost;
            for(int i = 0; i < size; i++) {
                str += list.get(N-1).number;
            }
            money -= list.get(N-1).cost * size;
        } else {
            Number n = list.get(N-1); // 가장 비용이 낮은 거
            size = money / n.cost;
            for(int i = 0; i < size; i++) {
                str += n.number;
            }
            money -= n.cost * size;
        }
        char[] arr = str.toCharArray(); // 숫자를 갱신
        for(int i = 0; i < arr.length; i++) {
            int tmpCost = weights[arr[i] - '0']; // 가장 앞자리부터 비교

            for(int j = N - 1; j >= 0; j--) {
                if(money + tmpCost - weights[j] >=  0) { // 숫자를 갱신해도 돈이 남을 경우
                        money += tmpCost - weights[j];
                        arr[i] = (char)(j + '0');
                        break; // 갱신
                }
            }


        }

        System.out.println(arr);


    }
}
