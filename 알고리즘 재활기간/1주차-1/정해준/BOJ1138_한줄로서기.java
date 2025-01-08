import java.util.Scanner;

public class BOJ1138_한줄로서기 {
    static int[] arr;
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); // 총 인원수
        arr = new int[N]; // 줄 서는 위치

        for(int i = 1; i <= N; i++){
            int num = sc.nextInt(); // i의 왼쪽에 본인보다 큰 키의 사람의 수 == 빈칸의 수
            checkPos(i, num);
        }

        for(int i : arr){
            System.out.print(i + " ");
        }

    }
    static void checkPos(int num, int pos) {
        int cnt = 0; //빈칸의 수
        for (int i = 0; i < arr.length; i++) {
            if(cnt == pos && arr[i] == 0 ){ //해당 칸이 비어있고 빈칸의 수가 pos와 일치할 때
                arr[i] = num;
                break;
            }

            if(arr[i] == 0){
                cnt++;
            }

        }
    }

}


