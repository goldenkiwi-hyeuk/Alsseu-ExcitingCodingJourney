import java.util.Scanner;

public class BOJ4659_비밀번호_발음하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = sc.next();
            if (str.equals("end")) break;

            if (genPw(str)) {
                sb.append(String.format("<%s> is acceptable.\n", str));
            } else {
                sb.append(String.format("<%s> is not acceptable.\n", str));
            }
        }

        System.out.print(sb);
    }

    private static boolean genPw(String pw) {
        String vowels = "aeiou";
        boolean hasVowel = false;
        int vowelCnt = 0, consonantCnt = 0;
        char last = 0;

        for (int i = 0; i < pw.length(); i++) {
            char curr = pw.charAt(i);

            if (vowels.indexOf(curr) != -1) {
                hasVowel = true;
                vowelCnt++;
                consonantCnt = 0;
            } else {
                consonantCnt++;
                vowelCnt = 0;
            }

            if (vowelCnt == 3 || consonantCnt == 3) {
                return false;
            }

            if (i > 0 && curr == last && curr != 'e' && curr != 'o') {
                return false;
            }

            last = curr;
        }

        return hasVowel;
    }
}
