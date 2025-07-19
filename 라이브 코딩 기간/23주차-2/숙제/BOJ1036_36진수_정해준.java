import java.util.*;
import java.math.BigInteger;

public class BOJ1036_36진수_정해준 {
    static int N, K;
    static List<String> numbers = new ArrayList<>();
    static Map<Character, BigInteger> gainMap = new HashMap<>();
    static final BigInteger BASE = BigInteger.valueOf(36);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = Integer.parseInt(sc.nextLine().trim());

        for (int i = 0; i < N; i++) {
            numbers.add(sc.nextLine().trim());
        }

        K = Integer.parseInt(sc.nextLine().trim());

        for (String s : numbers) {
            calculateGain(s);
        }

        List<Character> nums = new ArrayList<>(gainMap.keySet());
        nums.sort((a, b) -> gainMap.get(b).compareTo(gainMap.get(a)));

        Set<Character> changeSet = new HashSet<>();
        for (int i = 0; i < Math.min(K, nums.size()); i++) {
            changeSet.add(nums.get(i));
        }

        BigInteger total = BigInteger.ZERO;
        for (String s : numbers) {
            total = total.add(toDecimal(convert(s, changeSet)));
        }

        System.out.println(total.toString(36).toUpperCase());
    }

    // 이득 계산
    static void calculateGain(String s) {
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(len - 1 - i);
            int val = getValue(c);
            BigInteger power = BASE.pow(i);
            BigInteger diff = BigInteger.valueOf(35 - val).multiply(power);
            gainMap.put(c, gainMap.getOrDefault(c, BigInteger.ZERO).add(diff));
        }
    }

    static int getValue(char c) {
        if (Character.isDigit(c)) return c - '0';
        return c - 'A' + 10;
    }

    static String convert(String s, Set<Character> changeSet) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (changeSet.contains(c)) sb.append('Z');
            else sb.append(c);
        }
        return sb.toString();
    }

    static BigInteger toDecimal(String s) {
        BigInteger res = BigInteger.ZERO;
        for (char c : s.toCharArray()) {
            res = res.multiply(BASE).add(BigInteger.valueOf(getValue(c)));
        }
        return res;
    }
}
