import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 구멍의 개수
        int K = Integer.parseInt(st.nextToken()); // 사용 횟수

        int count = 0;

        int[] order = new int[K];
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<K; i++){
            order[i] = Integer.parseInt(st.nextToken());
        }

        List<Integer> list = new ArrayList<>(); // 멀티탭


        for(int i=0; i<K; i++){
            int val = order[i];

            if(list.contains(val)) continue;

            if(list.size()<N){
                list.add(val);
                continue;
            }

            int removeIdx = 0; // 제거할 인덱스
            int lastUsedIdx = 0; // 가장 늦게 쓰일 인덱스

            for(int j=0; j<N; j++){
                int stuff = list.get(j);
                int nextUsedIdx = Integer.MAX_VALUE; // 다음에 사용할 인덱스

                for(int k=i; k<K; k++){
                    if(order[k] == stuff){
                        nextUsedIdx = k;
                        break;
                    }
                }

                // 가장 나중에 쓰이는 인덱스보다 크다면, 더 나중에 쓰여야함
                if(nextUsedIdx > lastUsedIdx){
                    lastUsedIdx = nextUsedIdx;
                    removeIdx = j;
                }
            }

            list.remove(removeIdx);
            list.add(val);
            count++;


        }

        System.out.println(count);
    }
}
