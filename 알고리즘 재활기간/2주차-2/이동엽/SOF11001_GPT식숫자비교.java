import java.io.*;
import java.util.*;

public class SOF11001_GPT식숫자비교 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        List<int[]> nums = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            if (!str.contains(".")) {
                nums.add(new int[]{Integer.parseInt(str), -1}); // 입력 : 3
            } else {
                String[] part = str.split("\\.");
                nums.add(new int[]{Integer.parseInt(part[0]), Integer.parseInt(part[1])});
            }
        }
        br.close();

        nums.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        StringBuilder sb = new StringBuilder();
        for (int[] num : nums) {
            if (num[1] == -1) {
                sb.append(num[0]).append("\n");
            } else {
                sb.append(num[0] + "." + num[1]).append("\n");
            }
        }
        System.out.println(sb);
    }
}