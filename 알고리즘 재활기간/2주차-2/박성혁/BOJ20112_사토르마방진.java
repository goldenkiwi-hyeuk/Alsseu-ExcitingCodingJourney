import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] arr = new String[N];
        StringBuilder sb1 = new StringBuilder();
        for (int i = 0; i < N; i++) {
            arr[i] = br.readLine();
            sb1.append(arr[i]);
        }
        StringBuilder sb2 = new StringBuilder();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                sb2.append(arr[j].charAt(i));
            }
        }
        if (sb1.toString().equals(sb2.toString())) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }
}
