import java.util.*;

public class BOJ14725_개미굴_정해준 {
    static class Node {
        HashMap<String, Node> children = new HashMap<>();

    }
    static int N;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        Node parent = new Node();
        for(int i = 0; i < N; i++) {
            int K = sc.nextInt();
            Node root = parent;
            for(int j = 0; j < K; j++) {
                String str = sc.next();
                if(!root.children.containsKey(str)) { //만약 자식중에 해당 값이 없으면 새로 추가
                    root.children.put(str, new Node());
                }
                root = root.children.get(str);
            }
        }

        dfs(parent, "");
    }

    public static void dfs(Node now, String str) {
        Object[] keys = now.children.keySet().toArray();
        Arrays.sort(keys);
        for(Object key : keys) {
            System.out.println(str + key);
            dfs(now.children.get(key), str + "--");
        }
    }
}
