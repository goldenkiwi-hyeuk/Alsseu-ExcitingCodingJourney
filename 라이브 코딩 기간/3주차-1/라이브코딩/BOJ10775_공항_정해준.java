import java.util.*;


class BOJ10775_공항_정해준 {
    static int[] arr;

    static int find(int x) {
        if(arr[x] == x)
            return x;

        return arr[x] = find(arr[x]);
    }

    static int union(int x) {
        int nx = find(x);
        return arr[nx] -= 1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int G = sc.nextInt();
        int P = sc.nextInt();
        arr = new int[G+1];
        //각 게이트마다 번호를 할당
        for(int i = 1; i <= G; i++)
            arr[i] = i;

        int cnt = 0;
        for(int i = 0; i < P; i++) {
            int g = sc.nextInt();

            //fing(g)가 0일 경우 들어갈 게이트가 없으므로 종료료
            if(find(g) == 0)
                break;

            cnt++;
            union(g);
        }

        System.out.println(cnt);
    }
}