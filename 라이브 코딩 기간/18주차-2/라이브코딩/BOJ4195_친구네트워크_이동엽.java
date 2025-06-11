package boj;

import java.util.*;

class Main4195 {

    static int parent[], size[], idx;
    static Map<String, Integer> map;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();

        while(t-- > 0) {
            int f = sc.nextInt();
            parent = new int[2 * f];
            size = new int[2 * f];
            map = new HashMap<>();
            idx = 0;

            for (int i = 0; i < 2 * f; i++) {
                parent[i] = i; // 루트 = 나
                size[i] = 1; // 혼자
            }

            for (int i = 0; i < f; i++) {
                String name1 = sc.next();
                String name2 = sc.next();
                int id1 = getId(name1);
                int id2 = getId(name2);

                sb.append(union(id1, id2)).append("\n");
            }

        }
        sc.close();

        System.out.println(sb);
    }

    private static int find(int x)  {
        if (x == parent[x]) return x;
        return parent[x] = find(parent[x]);
    }

    private static int union(int x, int y) {
        x = find(x);
        y = find(y);

        if (x != y) {
            parent[y] = x;
            size[x] += size[y];
        }

        return size[x];
    }

    private static int getId(String str) {
        if (!map.containsKey(str)) map.put(str, idx++);
        return map.get(str);
    }

    // static class Friend {
    //     String name;
    //     int id;

    //     public Friend(String name, int id) {
    //         this.name = name;
    //         this.id = id;
    //     }

    // }

    // private static int getId(String str) {
    //     int i = 0;
    //     boolean isIn = false;
    //     for (Friend l : list) {
    //         if (l.name.equals(str)) {
    //             isIn = true;
    //             i = l.id;
    //             break;
    //         }
    //     }

    //     if (!isIn) {
    //         Friend f = new Friend(str, idx++);
    //         list.add(f);
    //         i = f.id;
    //     }

    //     return i;
    // }

}