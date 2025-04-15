import java.io.*;
import java.util.*;

class Main {
    
    // 기본아이디어 : 그리디
    // 현재 만족해야 하는 날짜 보다 작은 날짜들을 전부 골라서
    // 그중에 꽃이 지는 날짜가 가장 큰 날짜로 갱신 하는 형태
    private static class Date{
        int startM, startD, endM, endD;
        
        Date(){}
        
        Date(int startM, int startD, int endM, int endD){
            this.startM = startM;
            this.startD = startD;
            this.endM = endM;
            this.endD = endD;
        }
    }
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Date[] flowers = new Date[N];
        for(int i = 0; i<N;++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int startM = Integer.parseInt(st.nextToken());
            int startD = Integer.parseInt(st.nextToken());
            int endM = Integer.parseInt(st.nextToken());
            int endD = Integer.parseInt(st.nextToken());
            flowers[i] = new Date(startM, startD, endM, endD);
        }
        
        Arrays.sort(flowers, (o1,o2)->{ // 피는 날짜 기준 정렬
            if(o1.startM == o2.startM){
                if(o1.startD == o2.startD){
                    if(o1.endM == o2.endM){
                        return o2.endD - o1.endD;
                    } else {
                        return o2.endM - o1.endM;
                    }
                } else {
                    return o1.startD-o2.startD;
                }
            } else {
                return o1.startM-o2.startM;
            }
        });

        int cnt = 0; // 필요한 꽃의 갯수
        int idx = 0; // 탐색할 idx
        int startM = 3; // 요구되는 월
        int startD = 1; // 요구되는 일
        while(true){
            if(startM>=12){ // 12월을 도달했다는 의미는 이미 11월 30일까지 꽃을 피운 케이스
                break;
            }
            int targetM = -1; // 지는 날이 가장 늦은 케이스를 담기 위한 변수
            int targetD = -1;
            for(int i = idx ; i<N ; ++i){
                if((flowers[i].startM < startM )|| (flowers[i].startM == startM&&flowers[i].startD <= startD)){ // 시작 날짜가 요구날짜보다 작으면 
                    ++idx; // 탐색할 idx를 높이고
                    if(flowers[i].endM>targetM){ // 지는 날 업데이트
                        targetM = flowers[i].endM;
                        targetD = flowers[i].endD;
                    } else if(flowers[i].endM == targetM){
                        if(flowers[i].endD>targetD){
                            targetD = flowers[i].endD;
                        }
                    }
                } else {
                    break;
                }
            }
            
            if(targetM == -1 && targetD == -1){ // 만약 지는 날이 업데이트 되지 않았다는 의미는 꽃을 연속해서 피울수 없다는 의미
                cnt = 0;
                break;
            } else {
                // 꽃의 개수 증가 및 요구날짜 업데이트
                ++cnt;
                startM = targetM;
                startD = targetD;
            }
        }
        System.out.println(cnt);
    }
}