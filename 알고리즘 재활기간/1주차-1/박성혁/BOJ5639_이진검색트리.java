import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static class Node {
        int num;

        Node left, right;

        public Node() {
        }

        public Node(int num) {
            this.num = num;
        }

        public Node(int num, Node left, Node right) {
            this.num = num;
            this.left = left;
            this.right = right;
        }
    }

    public static StringBuilder sb;
    public static List<Node> nodeList;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        nodeList = new ArrayList<Node>();
        String str = "";
        sb = new StringBuilder();
        while ( (str = br.readLine()) != null) {
            nodeList.add(new Node(Integer.parseInt(str)));
        }
        for (int i = 1; i < nodeList.size(); i++) {
            makeTree(i);
        }
        LRV(nodeList.get(0));
        System.out.println(sb);
    }

    private static void makeTree(int i) {
        Node node = nodeList.get(0);
        while(true){
            if (node.num > nodeList.get(i).num){
                if (node.left == null){
                    node.left = nodeList.get(i);
                    break;
                } else {
                    node = node.left;
                }
            } else {
                if (node.right == null){
                    node.right = nodeList.get(i);
                    break;
                } else {
                    node = node.right;
                }
            }
        }
    }

    public static void LRV(Node node) {
        if (node == null) {
            return;
        }
        LRV(node.left);
        LRV(node.right);
        sb.append(node.num).append("\n");
    }
}
