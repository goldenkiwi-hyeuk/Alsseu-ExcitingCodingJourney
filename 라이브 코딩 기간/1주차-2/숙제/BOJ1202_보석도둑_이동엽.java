package boj;

import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main1202 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();

        Jewelry[] jewelry = new Jewelry[n];
        int[] bags = new int[k];

        for (int i = 0; i < n; i++) {
            int m = sc.nextInt();
            int v = sc.nextInt();
            jewelry[i] = new Jewelry(m, v);
        }

        for (int i = 0; i < k; i++) {
            bags[i] = sc.nextInt();
        }
        sc.close();

        Arrays.sort(bags);
        Arrays.sort(jewelry);

        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0, j = 0; i < k; i++) {
            while (j < n && jewelry[j].weight <= bags[i]) {
                pq.add(jewelry[j++].value);
            }
            if (!pq.isEmpty()) answer += pq.poll();
        }

        System.out.println(answer);
    }
}

class Jewelry implements Comparable<Jewelry> {
    int weight;
    int value;

    public Jewelry(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    @Override
    public int compareTo(Jewelry j) {
        if (this.weight == j.weight) return j.value - this.value;
        return Integer.compare(this.weight, j.weight);
    }
}
