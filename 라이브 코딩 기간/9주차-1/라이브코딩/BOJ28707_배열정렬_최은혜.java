import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static int M;
    static int[] arr;
    static int[][] lines;
    static String target;

    static public class Node {
        String str;
        int cost;

        Node(String str, int cost) {
            this.str = str;
            this.cost = cost;
        }
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int[] temp = arr.clone();
        Arrays.sort(temp);
        target = getString(temp);

        M = Integer.parseInt(br.readLine());
        lines = new int[M][3];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Integer.parseInt(st.nextToken())-1;
            lines[i][1] = Integer.parseInt(st.nextToken())-1;
            lines[i][2] = Integer.parseInt(st.nextToken());
        }

        System.out.println(getResult());
    }

    public static int getResult() {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
        HashMap<String, Integer> visited = new HashMap<>();

        String start = getString(arr);
        pq.add(new Node(start, 0));
        visited.put(start, 0);

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            String curStr = cur.str;
            int curCost = cur.cost;

            if (curStr.equals(target)) {
                return curCost;
            }

            int[] curArray = getArray(curStr);

            for (int[] line : lines) {
                int i = line[0];
                int j = line[1];
                int cost = line[2];

                int[] nextArray = curArray.clone();
                swap(nextArray, i, j);
                String nextStr = getString(nextArray);
                int nextCost = curCost + cost;

                if (!visited.containsKey(nextStr) || visited.get(nextStr) > nextCost) {
                    visited.put(nextStr, nextCost);
                    pq.add(new Node(nextStr, nextCost));
                }
            }
        }
        return -1;
    }

    public static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static String getString(int[] array) {
        StringBuilder sb = new StringBuilder();
        for (int num : array) {
            sb.append(num).append(",");
        }
        return sb.toString();
    }

    public static int[] getArray(String str) {
        String[] temp = str.split(",");
        int[] array = new int[temp.length];
        for (int i = 0; i < temp.length; i++) {
            array[i] = Integer.parseInt(temp[i]);
        }
        return array;
    }
}

