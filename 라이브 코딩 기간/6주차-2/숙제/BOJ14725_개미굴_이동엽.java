package boj;

import java.util.Scanner;
import java.util.TreeMap;

public class Main14725 {
    static class Node {
        String name;
        TreeMap<String, Node> children = new TreeMap<>();

        Node(String name) {
            this.name = name;
        }
    }

    static class Trie {
        Node root = new Node("");

        void insert(String[] str) {
            Node curr = root;
            for (String s : str) {
                curr.children.putIfAbsent(s, new Node(s));
                curr = curr.children.get(s);
            }
        }

        void print(Node node, int depth) {
            for (Node child : node.children.values()) {
                System.out.println("--".repeat(depth) + child.name);
                print(child, depth + 1);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        Trie trie = new Trie();

        for (int i = 0; i < n; i++) {
            int k = sc.nextInt();
            String[] food = new String[k];
            for (int j = 0; j < k; j++) {
                food[j] = sc.next();
            }
            trie.insert(food);
        }
        trie.print(trie.root, 0);
        sc.close();
    }
}