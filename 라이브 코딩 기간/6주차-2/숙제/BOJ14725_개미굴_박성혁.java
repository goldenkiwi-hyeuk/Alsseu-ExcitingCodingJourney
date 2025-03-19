import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    // 기본 아이디어 : 트리를 구현한다음 탐색하며 StringBuilder에 추가하기(DFS)
    static StringBuilder sb;

    private static class Node {
        int depth; // depth 만큼 -- 추가
        String word; // 단어
        Node parent; // 부모
        List<Node> childList  = new ArrayList<>(); // 자식 리스트

        public Node() {}
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Node root = new Node();
        root.childList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            Node prev = root;
            String str = br.readLine();
            StringTokenizer st = new StringTokenizer(str);
            int k = Integer.parseInt(st.nextToken());
            for (int j = 0; j < k; j++) {
                String word = st.nextToken();
                boolean In = false;
                List<Node> childList = prev.childList;
                for (Node child : childList) { // 이전 노드의 자식 리스트에 현재 단어가 있는지 확인
                    if (child.word.equals(word)) { // 있다면 이전 노드 갱신후 다음 단어 탐색
                        In = true;
                        prev = child;
                        break;
                    }
                }
                if (!In){  // 없다면 새로운 노드 생성이후에 이전 노드 자식 리스트에 넣어주고 이전 노드 갱신
                    Node node = new Node();
                    node.depth = j;
                    node.word = word;
                    node.parent = prev;
                    prev.childList.add(node);
                    prev = node;
                }
            }
        }
        sb = new StringBuilder();
        findchild(root);

        System.out.println(sb);
    }

    private static void findchild(Node root) { // 자식 탐색

        List<Node> childList = root.childList;
        Collections.sort(childList , (o1, o2) -> o1.word.compareTo(o2.word)); // 사전순 정렬
        for (Node child : childList) { // 자식을 하나씩 꺼내서
            for (int i = 0 ; i < child.depth ; i++) { // StringBuilder에 추가하고
                sb.append("--");
            }
            sb.append(child.word).append("\n");
            findchild(child); // 하위 자식 탐색
        }
    }
}
