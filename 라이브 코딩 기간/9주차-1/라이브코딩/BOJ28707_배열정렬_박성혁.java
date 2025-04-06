import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 기본 아이디어 : 다익스트라 + dp?(메모이제이션)
    
    private static class Edge implements Comparable<Edge> {
        int end, cost;

        public Edge() {
        }

        public Edge(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }

        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    // State 클래스: 배열의 현재 상태(문자열로 표현)와 해당 상태까지의 누적 비용을 저장
    private static class State implements Comparable<State> {
        String arrState;
        int cost;

        public State(String arrState, int cost) {
            this.arrState = arrState;
            this.cost = cost;
        }

        public int compareTo(State o) {
            return this.cost - o.cost;
        }
    }

    // 문자열 상태를 int 배열로 변환하는 헬퍼 함수
    // 상태 문자열의 포맷은 Arrays.toString()의 결과 ("[a, b, c, ...]")입니다.
    private static int[] parseState(String state) {
        // 양쪽 대괄호 제거 후, ", " 기준으로 분리
        state = state.substring(1, state.length() - 1);
        String[] tokens = state.split(", ");
        int[] arr = new int[tokens.length];
        for (int i = 0; i < tokens.length; i++) {
            arr[i] = Integer.parseInt(tokens[i]);
        }
        return arr;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        int[] targetArr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            targetArr[i] = arr[i];
        }
        Arrays.sort(targetArr);
        String target = Arrays.toString(targetArr);

        List<List<Edge>> edgeList = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            edgeList.add(new ArrayList<>());
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; ++i) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            int cost = Integer.parseInt(st.nextToken());
            edgeList.get(start).add(new Edge(end, cost));
            edgeList.get(end).add(new Edge(start, cost));
        }

        // 다익스트라 알고리즘을 위한 PriorityQueue와 방문 기록을 위한 HashMap 사용
        PriorityQueue<State> pq = new PriorityQueue<>();
        Map<String, Integer> dist = new HashMap<>(); // 상태 문자열 -> 최소 누적 비용

        // 초기 상태 설정
        String initState = Arrays.toString(arr);
        pq.offer(new State(initState, 0));
        dist.put(initState, 0);

        // 상태 공간 탐색 시작
        while (!pq.isEmpty()) {
            State current = pq.poll();

            // 이미 더 낮은 비용으로 방문한 상태라면 넘어감
            if (current.cost > dist.get(current.arrState)) continue;

            // 현재 상태가 목표 상태와 같다면 최소 비용을 출력 후 종료
            if (current.arrState.equals(target)) {
                System.out.println(current.cost);
                return;
            }

            // 현재 상태 문자열을 배열로 변환
            int[] currentArr = parseState(current.arrState);

            // 각 인덱스 i에 대해, 해당 인덱스에서 가능한 모든 스왑 연산을 시도
            for (int i = 0; i < N; i++) {
                // 인덱스 i에서 갈 수 있는 모든 스왑 대상
                for (Edge edge : edgeList.get(i)) {
                    // 새로운 상태 생성을 위해 현재 배열 복사
                    int[] newArr = currentArr.clone();
                    // 인덱스 i와 edge.end의 값을 교환 (스왑 연산 적용)
                    int temp = newArr[i];
                    newArr[i] = newArr[edge.end];
                    newArr[edge.end] = temp;

                    // 새 상태를 문자열로 변환
                    String newState = Arrays.toString(newArr);
                    // 새로운 누적 비용: 현재까지 비용 + 해당 스왑의 비용
                    int newCost = current.cost + edge.cost;

                    // 새 상태를 방문한 적이 없거나, 더 낮은 비용으로 도달한 경우
                    if (!dist.containsKey(newState) || newCost < dist.get(newState)) {
                        dist.put(newState, newCost);
                        pq.offer(new State(newState, newCost));
                    }
                }
            }
        }

        // 모든 가능한 상태를 탐색했으나 목표 상태에 도달하지 못한 경우 -1 출력
        System.out.println(-1);
    }
}
