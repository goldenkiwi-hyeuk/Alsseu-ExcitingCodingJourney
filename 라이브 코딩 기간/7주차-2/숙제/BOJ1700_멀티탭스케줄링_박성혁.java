import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // 기본 아이디어 : 구현 + 그리디
    // 만약 비어있는 칸이 있거나, 이미 꼽혀 잇으면 넘어가고 뽑아야 할 상황이 오면 앞으로 안쓰이거나 가장 나중에 쓰일 전자기기를 제거

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = new int[K];
        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= K; i++) {
            list.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            list.get(arr[i]).add(i); // 각 전자기기가 몇번째 인덱스에 사용되는지 정리하는 리스트
        }
        Set<Integer> set = new HashSet<>();
        int ans = 0;
        for (int i = 0; i <K ; ++i) {
            int target = arr[i];
            if (set.contains(target)) { // 이미 꼽혀 있다면 통과
                continue;
            } else {
                if (set.size()<N){  // 비어 있는 칸이 있다면 추가하고 통과
                    set.add(target);
                } else {
                    int[][] arr2 = new int[N][2];
                    int idx = 0;
                    for (int device : set) {
                        arr2[idx][0] = device;
                        arr2[idx][1] = -1;
                        for (int j = 0; j<list.get(device).size(); j++) {
                            if (list.get(device).get(j)>i){
                                arr2[idx][1] = list.get(device).get(j);
                                break;
                            }
                        }
                        idx++;
                    }
                    int delete = -1;
                    int deleteIdx = -1;
                    for (int arr3[] : arr2){
                        if (arr3[1]==-1){ // 앞으로 사용되지 않는 경우
                            delete = arr3[0];
                            break;
                        } else {
                            if (arr3[1] > deleteIdx){ // 가장 늦게 사용될 기기를 선택
                                deleteIdx = arr3[1];
                                delete = arr3[0];
                            }
                        }
                    }
                    ++ans;
                    set.remove(delete);
                    set.add(target);
                }
            }
        }
        System.out.println(ans);
    }
}
