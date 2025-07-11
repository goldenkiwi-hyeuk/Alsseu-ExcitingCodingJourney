import java.util.*;
import java.io.*;

class Main {
    // 기본아이디어 : 누적합
    
    static int pizza;
    static List<Integer> ListA, ListB;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        pizza = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        int n = Integer.parseInt(st.nextToken());
        int[] A = new int[m];
        for(int i = 0; i<m ; ++i){
            A[i] = Integer.parseInt(br.readLine());
        }
        int[] B = new int[n];
        for(int i = 0; i<n ; ++i){
            B[i] = Integer.parseInt(br.readLine());
        }
        
        // 만들수 있는 피자의 크기를 key, 나올수 있는 횟수를 value 값으로 하는 map 생성
        Map<Integer,Integer> mapA = new HashMap<>();
        // 피자를 사용 안할수도 있으므로 0도 추가
        mapA.put(0,1);
        for(int len = 1;  len <= m; ++len){
            for(int i = 0; i< m; ++i){
                int sum = 0;
                for(int j = 0; j<len; ++j){
                    sum += A[(i+j)%m];
                }
                if(mapA.containsKey(sum)){
                    mapA.put(sum, mapA.get(sum)+1);
                } else {
                    mapA.put(sum, 1);
                }
                if(len == m){
                    break;
                }
            }
        }
        
        // 만들수 있는 피자의 크기를 key, 나올수 있는 횟수를 value 값으로 하는 map 생성
        Map<Integer,Integer> mapB = new HashMap<>();
        // 피자를 사용 안할수도 있으므로 0도 추가
        mapB.put(0,1);
        for(int len = 1;  len <= n; ++len){
            for(int i = 0; i< n; ++i){
                int sum = 0;
                for(int j = 0; j<len; ++j){
                    sum += B[(i+j)%n];
                }
                if(mapB.containsKey(sum)){
                    mapB.put(sum, mapB.get(sum)+1);
                } else {
                    mapB.put(sum, 1);
                }
                if(len == n){
                    break;
                }
            }
        }
        
        int ans = 0;
        
        // keySet을 통해서 A의 값을 정하고 B에 target값이 있는지 확인
        // 있다면 ans에 (mapA.get(num)* mapB.get(target)) 더하기
        for(int num : mapA.keySet()){
            int target = pizza - num;
            if(mapB.containsKey(target)){
                // System.out.println("num :" + num +", mapA.get(num) : "+mapA.get(num) +", target : "+target + ", mapB.get(target) : "+ mapB.get(target));
                ans += (mapA.get(num)* mapB.get(target));
            }
        }
        
        System.out.println(ans);
    }
}