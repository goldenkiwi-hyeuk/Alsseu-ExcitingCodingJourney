import java.util.Arrays;


public class PG389481_봉인된주문_정해준 {

    public class Solution {
        public String solution(long n, String[] bans) {
            Arrays.sort(bans, (a, b) -> {
                if (a.length() != b.length()) return a.length() - b.length();
                return a.compareTo(b);
            });

            // 금지 주문보다 앞서는 것들만큼 n 증가
            for (String ban : bans) {
                String cur = getString(n);
                if (ban.length() < cur.length() ||
                        (ban.length() == cur.length() && ban.compareTo(cur) <= 0)) {
                    n++;
                } else break;
            }

            return getString(n);
        }

        // 숫자를 주문 문자열로 변환
        private String getString(long n) {
            StringBuilder sb = new StringBuilder();
            while (n > 0) {
                long rem = n % 26;
                n /= 26;
                if (rem == 0) {
                    sb.append('z');
                    n--;
                } else {
                    sb.append((char)('a' + rem - 1));
                }
            }
            return sb.reverse().toString();
        }
    }

}
