import java.util.*;
import java.io.*;

public class BOJ16566_카드게임_정해준 {
    static int[] parent;
    static int[] cards;
    static Map<Integer, Integer> cardIndexMap = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        cards = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(cards); // 이진 탐색을 위한 정렬


        for (int i = 0; i < m; i++) {
            cardIndexMap.put(cards[i], i);
        }

        parent = new int[m];
        for (int i = 0; i < m; i++) {
            parent[i] = i;
        }


        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < k; i++) {
            int playerCard = Integer.parseInt(st.nextToken());

            // 이진 탐색으로 upper_bound 찾기
            int idx = upperBound(cards, playerCard);

            // 유효한 카드 인덱스 찾기
            int availableIdx = find(idx);


            sb.append(cards[availableIdx]).append("\n");

            // 이 카드는 이제 사용했으므로 다음 인덱스로 유니온
            union(availableIdx, availableIdx + 1);
        }

        System.out.print(sb);
    }


    static int upperBound(int[] arr, int key) {
        int low = 0, high = arr.length;
        while (low < high) {
            int mid = (low + high) / 2;
            if (arr[mid] <= key)
                low = mid + 1;
            else
                high = mid;
        }
        return low;
    }


    static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }


    static void union(int a, int b) {
        if (b >= parent.length) return; // 범위 초과 방지
        a = find(a);
        b = find(b);
        if (a != b)
            parent[a] = b;
    }
}
