import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ14003_가장긴증가하는부분수열5_정해준 {
    //Scanner 334308KB	1772 ms
    //Buffered 282804KB	796ms
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        int[] index = new int[N]; // 해당 숫자가 어떤 위치에 들어가게 되는지 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list = new ArrayList<Integer>();
        list.add(arr[0]);
        for(int i = 1; i < N; i++) {
            int num = arr[i];
            if(num > list.get(list.size() - 1)) {
                list.add(num); // 가장 마지막 값보다 클 경우
                index[i] = list.size() - 1;
            } else { // 가장 인접한 수와 교체
                int start = 0;
                int end = list.size() - 1;
                while(start < end) {
                    int mid = (start + end) / 2;
                    if(list.get(mid) < num) {
                        start = mid + 1;
                    } else {
                        end = mid;
                    }
                }
                list.set(start, num);
                index[i] = start; // 해당 숫자의 위치를 저장
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(list.size()).append("\n");
        Stack<Integer> stack = new Stack<>();
        int idx = list.size() - 1;
        for(int i = N-1; i >= 0; i--) { //배열 뒤에서 부터 idx에 맞는 숫자를 저장
            if(index[i] == idx) { // 4 -> 3 -> 2 -> 1 순으로 push, 뒤에서 찾는 이유는 가장 마지막에 위치가 정해진 것을 찾기 위해
                stack.push(arr[i]);
                idx--;
            }
        }
        while(!stack.isEmpty()) {//1->2->3->4 순으로 pop
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }


}
