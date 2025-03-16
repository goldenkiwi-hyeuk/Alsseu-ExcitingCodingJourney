package boj_2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q1644 {
    static int N;
    static List<Integer> list = new ArrayList<>(); // N 이하의 소수 리스트


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        getList(N);

        int result = getResult();

        System.out.println(result);

    }

    static void getList(int limit) {
        boolean[] isPrime = new boolean[limit + 1];
        Arrays.fill(isPrime, true);
        isPrime[0] = isPrime[1] = false;

        for (int i = 2; i * i <= limit; i++) {
            if (isPrime[i]) {
                for (int j = i * i; j <= limit; j += i) {
                    isPrime[j] = false;
                }
            }
        }

        for (int i = 2; i < limit + 1; i++) {
            if (isPrime[i]) {
                list.add(i);
            }
        }

    }

    static int getResult() {
        int left = 0;
        int right = 0;
        int sum = 0;
        int count = 0;

        while(right<list.size() || sum>=N){
            if(sum<N && right<list.size()){
                sum+=list.get(right++);
            } else {
                if(sum==N) count++;
                sum -= list.get(left++);
            }
        }


        return count;
    }
}
