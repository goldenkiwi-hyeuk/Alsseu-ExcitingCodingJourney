import java.util.Arrays;
import java.util.Scanner;

public class BOJ2138_전구와스위치 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        char[] o1 = sc.next().toCharArray();
        char[] o2 = Arrays.copyOf(o1, o1.length);
        o2[0] = o1[0] == '0' ? '1' : '0';
        o2[1] = o1[1] == '0' ? '1' : '0';
        char[] target = sc.next().toCharArray();

        int o1Cnt = 0;
        int o2Cnt = 1;

        for(int i = 1; i < o1.length; i++) {
            if(o1[i-1] != target[i-1]){
                o1Cnt++;
                o1[i-1] = o1[i-1] == '0' ? '1' : '0';
                o1[i] = o1[i] == '0' ? '1' : '0';
                if(i+1 < o1.length){
                    o1[i+1] = o1[i+1] == '0' ? '1' : '0';
                }
            }

            if(o2[i-1] != target[i-1]){
                o2Cnt++;
                o2[i-1] = o2[i-1] == '0' ? '1' : '0';
                o2[i] = o2[i] == '0' ? '1' : '0';
                if(i+1 < o2.length){
                    o2[i+1] = o2[i+1] == '0' ? '1' : '0';
                }
            }
        }
        boolean flag1 = checkArrya(o1, target);
        boolean flag2 = checkArrya(o2, target);

        if(flag1 && flag2){
            System.out.println(Math.min(o1Cnt, o2Cnt));
        }
        else if(flag1) {
            System.out.println(o1Cnt);
        } else if(flag2) {
            System.out.println(o2Cnt);
        } else {
            System.out.println(-1);
        }

    }

    static boolean checkArrya(char[] o, char[] target){
        for(int i = 0; i < o.length; i++){
            if(o[i] != target[i])
                return false;
        }
        return true;
    }
}
