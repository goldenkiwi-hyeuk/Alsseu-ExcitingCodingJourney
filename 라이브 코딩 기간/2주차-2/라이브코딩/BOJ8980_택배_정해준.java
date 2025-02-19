import java.util.Arrays;
import java.util.Scanner;

public class BOJ8980_택배_정해준 {
    static class Item implements Comparable<Item>{
        int start; //배송시작 마을
        int end; //배송 완료 마을
        int weight; // 무게

        public Item(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Item o) {
            if(this.end == o.end)
                return this.start - o.start;
            return this.end - o.end; //배송이 끝나는 순대로 배열
        }

    }

    public static void main(String[] args) {;
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 마을의 개수
        int C = sc.nextInt(); // 트럭의 한계무게
        int[] capacity = new int[N+1]; // 각 마을에 도착했을 때 넣을 수 있는 한계치
        for(int i = 1; i <= N; i++) { // 트럭의 최대 용량으로 초기화
            capacity[i] = C;
        }

        int K = sc.nextInt(); //박스의 개수
        Item[] items = new Item[K];
        for(int i = 0; i < K; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();

            items[i] = new Item(start, end, weight);
        }
        Arrays.sort(items);
        int sum = 0;
        for(int i = 0; i < K; i++) {
            int max = Integer.MAX_VALUE;
            for(int j = items[i].start; j < items[i].end; j++) {
                max = Math.min(max, capacity[j]); // 이동간에 옮길 수 있는 최대치
            }

            if(max >= items[i].weight) { //옮길 수 있는 한계치가 여유로울 때
                for(int j = items[i].start; j < items[i].end; j++) { // 짐 다 넣기
                    capacity[j] -= items[i].weight;
                }
                sum += items[i].weight;
            } else { // 부족할 때
                for(int j = items[i].start; j < items[i].end; j++) { // 한계치를 더하기
                    capacity[j] -= max;
                }
                sum += max;
            }
        }


        System.out.println(sum);
    }

}
