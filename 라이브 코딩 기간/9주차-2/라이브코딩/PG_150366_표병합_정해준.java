import java.util.ArrayList;
import java.util.List;

public class PG_150366_표병합_정해준 {
    class Solution {
        static String[] csv = new String[2500];
        static int[] parent = new int[2500];
        static List<String> list = new ArrayList<>();
        public String[] solution(String[] commands) {
            for(int i = 0; i < 2500; i++) {
                parent[i] = i; //초기화
            }
            for(String command : commands) {
                String[] req = command.split(" ");
                // for(int i = 0; i < 2; i++) {
                //     for(int j = 0; j < 2; j++) {
                //         System.out.print(csv[i * 50 + j] + " ");
                //     }
                //     System.out.println();
                // }
                switch(req[0]) {
                    case "UPDATE" :
                        if(req.length > 3)
                            update1(req);
                        else
                            update2(req);
                        break;
                    case "MERGE" :
                        merge(req);
                        break;
                    case "UNMERGE" :
                        unmerge(req);
                        break;
                    default:
                        print(req);
                }
            }
            String[] answer = new String[list.size()];
            for(int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
            return answer;
        }

        static int Find(int a) {
            if(a == parent[a])
                return a;

            return parent[a] = Find(parent[a]);
        }

        static void Union(int a, int b) {
            int A = Find(a);
            int B = Find(b);
            if(A == B)
                return;
            csv[B] = null;
            parent[B] = A;
        }

        static void update1(String[] req) { //UPDATE r c value
            int r = Integer.parseInt(req[1]) - 1;
            int c = Integer.parseInt(req[2]) - 1;
            csv[Find(r * 50 + c)] = req[3];
        }

        static void update2(String[] req) { //UPDATE value1 value2
            for(int i = 0; i < 2500; i++) {
                int idx = Find(i);
                if(csv[idx] != null && csv[idx].equals(req[1])) {
                    csv[idx] = req[2];
                }
            }
        }

        static void merge(String[] req) { //MERGE r1 c1 r2 c2
            int a = (Integer.parseInt(req[1]) - 1) * 50 + (Integer.parseInt(req[2]) - 1);
            int b = (Integer.parseInt(req[3]) - 1) * 50 + (Integer.parseInt(req[4]) - 1);

            if(csv[Find(a)] == null && csv[Find(b)] != null) { // 값이 있는 쪽을 a
                int tmp = a;
                a = b;
                b = tmp;
            }

            Union(a, b);
        }

        static void unmerge(String[] req) { //UNMERGE r c
            int r = Integer.parseInt(req[1]) - 1;
            int c = Integer.parseInt(req[2]) - 1;

            for (int i = 0; i < 2500; i++) {
                Find(i); // 경로 압축
            }
            int n = Find(r * 50 + c); // 그룹을 찾아냄
            String value = csv[n]; // 그 그룹이 가지고 있는 값

            for(int i = 0; i < 2500; i++) {
                if(Find(i) == n) {
                    parent[i] = i;

                    if(i == r * 50 + c) { // 행 위치 일 경우 병합 칸이 가지고 있는 값을 계승
                        csv[i] = value;
                    } else {
                        csv[i] = null; // 아닐 경우 초기화
                    }
                }
            }

        }

        static void print(String[] req) { //"PRINT r c
            int r = Integer.parseInt(req[1]) - 1;
            int c = Integer.parseInt(req[2]) - 1;
            String value = csv[Find(r * 50 + c)];
            list.add(value == null ? "EMPTY" : value);

        }
    }
}
