package programmers.등산코스정하기;

import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        ArrayList<Node> list[] = new ArrayList[n + 1];
        for (int i = 0; i < n + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < paths.length; i++) {
            int start = paths[i][0];
            int end = paths[i][1];
            int w = paths[i][2];

            list[start].add(new Node(end, w));
            list[end].add(new Node(start, w));
        }

        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);

        boolean[] isSummit = new boolean[n + 1];
        for (int i = 0; i < summits.length; i++) {
            isSummit[summits[i]] = true;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int i = 0; i < gates.length; i++) {
            pq.offer(new Node(gates[i], 0));
            dist[gates[i]] = 0;
        }

        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (isSummit[node.next] || node.time > dist[node.next]) continue;

            for (int i = 0; i < list[node.next].size(); i++) {
                int next = list[node.next].get(i).next;
                int time = list[node.next].get(i).time;

                int intensity = Math.max(node.time, time);
                if (intensity < dist[next]) {
                    dist[next] = intensity;
                    pq.offer(new Node(next, dist[next]));
                }
            }
        }

        Arrays.sort(summits);

        int[] answer = new int[]{0, Integer.MAX_VALUE};

        for (int s : summits) {
            if (dist[s] < answer[1]) {
                answer[1] = Math.min(answer[1], dist[s]);
                answer[0] = s;
            }
        }
        return answer;
    }

    static class Node implements Comparable<Node> {
        int next, time;

        Node(int next, int time) {
            this.next = next;
            this.time = time;
        }

        @Override
        public int compareTo(Node o) {
            return this.time - o.time;
        }
    }
}