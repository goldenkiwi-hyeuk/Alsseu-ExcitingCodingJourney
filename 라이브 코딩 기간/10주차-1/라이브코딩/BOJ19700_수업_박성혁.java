import java.io.*;
import java.util.*;


class Main {
    // 기본 아이디어 : 이분탐색
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] arr = new int[N][2]; // 입력값을 받아줄 배열
        int[] group = new int[N]; // 해당 팀에서 가장 작은 키를 받아줄 배열
        int[] groupnum = new int[N]; // 해당 팀의 인원수를 받아줄 배열
        Arrays.fill(group, 987654321);
        for(int i = 0; i<N; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int height = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            arr[i][0] = height;
            arr[i][1] = k;
        }
        // Arrays.sort(arr, (o1,o2)->{
        //     if(o1[1] == o2[1]){
        //         return o1[0]- o2[0];
        //     } else {
        //         return o1[1] - o2[1];
        //     }
        // });
        Arrays.sort(arr, (o1,o2)->{
            return o2[0]- o1[0];
        });
        for(int i = 0; i<N;++i){
            int height = arr[i][0];
            int k = arr[i][1];
            int left = 0;
            int right = N;
            while(left<=right){ // 이분탐색
                int mid = (left+right)/2;
                if((group[mid]>height)&&(groupnum[mid]<k)){ // 해당 팀에서 가장 작은 키가 height보다 크고 허용가능한 k보다 팀인원이 작다면 가능
                    right = mid-1;
                } else { // 아니라면 범위를 넓혀야함
                    left = mid + 1;
                }
            }
            group[left] = height;
            ++groupnum[left];
            // System.out.println("height : "+height + ", left : "+left);
            // System.out.println(Arrays.toString(group));
            // System.out.println(Arrays.toString(groupnum));
        }
        
        boolean isfull = true;
        for(int i = 0; i<N;++i){
            if(group[i] == 987654321){
                System.out.println(i);
                isfull = false;
                break;
            }
        }
        if(isfull){
            System.out.println(N);
        }
    }
}