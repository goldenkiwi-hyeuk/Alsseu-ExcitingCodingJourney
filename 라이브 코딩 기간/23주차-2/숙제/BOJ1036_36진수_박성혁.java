import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.*;

public class Main {
    
    // 기본 아이디어 : 그리디
    
    private static class Mapping implements Comparable<Mapping> {
        char number;
        BigInteger number2;

        Mapping(){}

        Mapping(char number, BigInteger number2){
            this.number = number;
            this.number2 = number2;
        }

        public int compareTo(Mapping o) {
            return o.number2.compareTo(this.number2);
        }
    }
    
    // 36진수를 10진수에 매칭시키기 위한 map
    static Map<Character, Integer> mapping;
    // 10진수를 36진수에 매칭시키기 위한 map
    static Map<BigInteger, Character> reverseMapping;
    // Z로 바꿨을때 값의 변화를 저장하는 map
    static Map<Character, BigInteger> greed;
    static char[] alpabet = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        mapping = new HashMap<>();
        reverseMapping = new HashMap<>();
        greed = new HashMap<>();
        for (int i = 0; i<10; ++i){
            mapping.put((char) (i +'0'), i);
            reverseMapping.put(BigInteger.valueOf(i), (char)(i +'0'));
            greed.put((char) (i +'0'), BigInteger.ZERO);
        }
        for (int i = 10; i<36; ++i){
            mapping.put(alpabet[i-10], i);
            reverseMapping.put(BigInteger.valueOf(i), alpabet[i-10]);
            greed.put(alpabet[i-10], BigInteger.ZERO);
        }

        int N = Integer.parseInt(br.readLine());
        String[] strArr = new String[N];
        for (int i = 0; i < N; ++i){
            strArr[i] = br.readLine();
            BigInteger num = BigInteger.ONE;
            // 입력받은 36진수를 하나씩 돌면서 각 값을 Z로 변경했을때의 변화량을 greed map에 넣기
            for (int j = strArr[i].length()-1; j>=0 ; --j){
                char ch = strArr[i].charAt(j);
                int greed2 = mapping.get('Z') - mapping.get(ch);
                greed.put(ch, greed.get(ch).add(num.multiply(BigInteger.valueOf(greed2))));
                num = num.multiply(BigInteger.valueOf(36L));
            }
        }

        // 변화가 큰 K를 찾아내기 위해 객체 선언 및 PQ사용
        PriorityQueue<Mapping> pq = new PriorityQueue<>();
        for (char ch : greed.keySet()){
            pq.add(new Mapping(ch, greed.get(ch)));
        }

        int K = Integer.parseInt(br.readLine());

        // 변화시킬 글자를 저장할 set
        Set<Character> changeSet = new HashSet<>();
        for (int i =0; i<K; ++i){
            changeSet.add(pq.poll().number);
        }

        // 최종적으로 합을 저장
        BigInteger total = BigInteger.ZERO;
        for (int i = 0; i < N; ++i){
            BigInteger sum = BigInteger.ZERO;
            // num은 자릿수를 의미
            BigInteger num = BigInteger.ONE;
            for (int j = strArr[i].length()-1; j>=0 ; --j){
                char ch;
                // 변화여부 확인후 숫자 합하기
                if (changeSet.contains(strArr[i].charAt(j))){
                    ch = 'Z';
                } else {
                    ch = strArr[i].charAt(j);
                }
                sum = sum.add(num.multiply(BigInteger.valueOf(mapping.get(ch))));
                num = num.multiply(BigInteger.valueOf(36L));
            }
            // 최종적으로 변화한 수를 total에 더하기
            total = total.add(sum);
        }

        // 0인지 먼저 확인
        if (total.equals(BigInteger.ZERO)){
            System.out.println(0);
        } else {
            // 10진수를 36진수로 변환하는 과정
            Deque<BigInteger> deq = new ArrayDeque<>();
            while(true){
                deq.addFirst(total.mod(BigInteger.valueOf(36L)));
                total = total.divide(BigInteger.valueOf(36L));
                if (total.compareTo(BigInteger.valueOf(36L))<0){
                    if (!total.equals(BigInteger.ZERO)){
                        deq.addFirst(total);
                    }
                    break;
                }
            }
            StringBuilder sb = new StringBuilder();
            while (!deq.isEmpty()){
                sb.append(reverseMapping.get(deq.pollFirst()));
            }
            System.out.println(sb.toString());
        }
    }
}
