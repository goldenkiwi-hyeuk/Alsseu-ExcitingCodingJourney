import java.util.*;
import java.io.*;

class Main {
    // 기본아이디어 : 자료구조
    // 가장 작은수부터 중간까지의 PQ와
    // 중간부터 가장 큰수까지의 PQ를 두개 운용하며 중간값을 계산
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> minPq = new PriorityQueue<>((o1,o2) -> o1-o2); // 중간부터 가장 큰수까지의 PQ
        PriorityQueue<Integer> maxPq = new PriorityQueue<>((o1,o2) -> o2-o1); // 가장 작은수부터 중간까지의 PQ
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N ;++i){
            int num = Integer.parseInt(br.readLine());
            if(minPq.size()==maxPq.size()){ // 사이즈가 같다면 maxPQ에 넣고
                maxPq.add(num);
            } else { // 사이즈가 다르다면 minPq에 넣음
                minPq.add(num);
            }
            
            if(!minPq.isEmpty()&&!maxPq.isEmpty()){
                // minPQ와 maxPQ의 값을 비교하여 maxpq의 가장 큰 값이 minpq의 가장 작은 값보다 크다면 스위치
                if(maxPq.peek()>minPq.peek()){ 
                    int temp = minPq.poll();
                    int temp2 = maxPq.poll();
                    minPq.add(temp2);
                    maxPq.add(temp);
                }    
            }
            // maxpq에서 가장 큰값이 중간값
            sb.append(maxPq.peek()).append("\n");
        }
        System.out.println(sb.toString());
    }
}