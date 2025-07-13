// Online Java Compiler
// Use this editor to write, compile and run your Java code online
import java.util.*;

class BOJ2632_피자판매_정해준 {
    static int[] arrA; // A 피자의 크기
    static int[] arrB; // B 피자의 크기
    static int[] sumA; // A의 조각의 누적값
    static int[] sumB; // B의 조각의 누적값
    static int[] cntA; // A의 총 갯수
    static int[] cntB; // B의 총 갯수
    static int target; // 목표값 
    static int N, M;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        target = sc.nextInt();
        M = sc.nextInt();
        N = sc.nextInt();
        arrA = new int[2 * M + 1];
        sumA = new int[2 * M + 1];
        arrB = new int[2 * N + 1];
        sumB = new int[2 * N + 1];

        for(int i = 1; i <= M; i++) {
            int n = sc.nextInt();
            arrA[i] = n;
            arrA[i + M] = n;
        }

        for(int i = 1; i <= N; i++) {
            int n = sc.nextInt();
            arrB[i] = n;
            arrB[i + N] = n;
        }

        //A의 누적값 구하기
        for(int i = 1; i <= M * 2; i++){
            sumA[i] += arrA[i] + sumA[i - 1];
        }

        //B의 누적값 구하기
        for(int i = 1; i <= N * 2; i++){
            sumB[i] += arrB[i] + sumB[i - 1];
        }

        cntA = new int[target + 1];
        cntB = new int[target + 1];
        count(sumA, cntA, M);
        count(sumB, cntB, N);

        int answer = 0;
        answer += cntA[target]; // B는 0 조각만 쓸 때 
        answer += cntB[target]; // A 는 0 조각 

        for(int i = 1; i < target; i++){
            answer += cntA[i] * cntB[target - i];
        }

        System.out.println(answer);
    }
    // 경수의 수를 측정
    public static void count(int[] sumArr, int[] cntArr, int size){
        for(int i = 1; i < size; i++) { // 몇조각을 고르는지
            for(int j = 1; j <= size; j++) { // 몇번째 조각이 시작인지
                int pizza = sumArr[i + j - 1] - sumArr[j - 1];
                if(pizza <= target){ // 조합해서 피자를 만들 수 있는 수면 
                    cntArr[pizza]++;
                }
            }
        }
        if(sumArr[size] <= target)
            cntArr[sumArr[size]]++;
    }
}