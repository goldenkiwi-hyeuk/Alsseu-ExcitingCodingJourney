import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for(int tc=0; tc<T; tc++){
            st = new StringTokenizer(br.readLine());

            int index = Integer.parseInt(st.nextToken());
            sb.append(index).append(" ");

            List<Integer> list = new ArrayList<>();
            int cnt = 0;
            for(int i=0; i<20; i++){

                int num = Integer.parseInt(st.nextToken());

                int pos = 0;
                while(pos<list.size() && list.get(pos) < num) {
                    pos++;
                }

                cnt+= list.size()-pos;

                list.add(pos, num);
            }
            sb.append(cnt).append("\n");
        }
        System.out.println(sb);
    }
}
