import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class BOJ16724_피리부는사나이_정해준 {
    static int[] parents;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        parents = new int[N * M];
        char[][] map = new char[N][M];

        for(int i = 0; i < N; i++) {
            String str = sc.next();
            for(int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j);
                parents[i * M + j] = i * M + j;
            }
        }

        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                int a = i;
                int b = j;
                char c = map[i][j];
                if(c == 'U') {
                    a--;
                } else if(c == 'D') {
                    a++;
                } else if(c == 'L') {
                    b--;
                }else {
                    b++;
                }
                union(i*M+j, a*M+b);
            }
        }

        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                set.add(find(i*M + j));
            }
        }

        System.out.println(set.size());
    }

    public static void union(int a, int b) {
        int A = find(a);
        int B = find(b);
        if( A < B)
            parents[B] = A;
        else
            parents[A] = B;
    }

    public static int find(int x) {
        if(x == parents[x])
            return parents[x];

        return parents[x] = find(parents[x]);
    }
}
