import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        String str2 = br.readLine();
        int idx = 0;
        StringBuilder ans = new StringBuilder();
        StringBuilder check = new StringBuilder();
        Deque<String> stack = new ArrayDeque<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == str2.charAt(idx)) { // 검사하는데 같은 케이스
                check.append(str.charAt(i));
                idx++;
                if (check.toString().length()==str2.length()) { // str2와 완전히 같아짐
                    if (!stack.isEmpty()) { // stack이 완전히 비어있지 않다면?
                        check.setLength(0);
                        check.append(stack.pollLast());
                        idx = check.length();
                    } else { // stack이 완전히 비어있다면?
                        check.setLength(0);
                        idx = 0;
                    }
                }
            } else {
                if (check.length() > 0 && str.charAt(i) == str2.charAt(0)) { // 검사하는데 같지않은데 str2의 첫글자와 같은 케이스
                    stack.addLast(check.toString());
                    idx = 1;
                    check.setLength(0);
                    check.append(str.charAt(i));
                } else { // 검사하는데 같지않은데 str2의 첫글자와도 같지 않은 케이스
                    while (!stack.isEmpty()){
                        ans.append(stack.pollFirst());
                    }
                    ans.append(check);
                    ans.append(str.charAt(i));
                    check.setLength(0);
                    idx = 0;
                }
            }
        }
        while (!stack.isEmpty()){
            ans.append(stack.pollFirst());
        }
        if (check.length()>0) {
            ans.append(check);
        }
        if (ans.length()>0) {
            System.out.println(ans.toString());
        } else {
            System.out.println("FRULA");
        }
    }
}
