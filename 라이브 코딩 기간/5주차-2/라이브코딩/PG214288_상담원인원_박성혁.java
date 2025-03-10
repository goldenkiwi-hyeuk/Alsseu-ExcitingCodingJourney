import java.util.*;

class Solution {

    // 기본 아이디어 그리디 알고리즘
    // 1 ... K중 각각의 인원을 한명씩 늘렸을때 가장 크게 대기 시간이 줄어드는지 체크하여 대기 시간이 가장 크게 줄어드는 유형에 상담원을 투입
    public int solution(int k, int n, int[][] reqs) {
        int[] arrk = new int[k+1];
        Arrays.fill(arrk,1); // 모든 유형에 1명이상은 있어야 하니까 먼저 1명씩만 투입
        Map<Integer, int[]> map;
        int now = k;
        while(now!=n) { // 더이상 투입할수 있는 인원이 없다면 반복문 종료
            int[] cnt = new int[k+1]; // 각 인원을 늘렸을때 어느곳이 가장 크게 줄어드는지 확인하는 배열
            for(int cntnum = 1 ; cntnum<=k;++cntnum){ // k만큼 반복하며
                map = new HashMap<>();
                for(int i = 1; i<=k;++i){ // k를 제외한 나머지는 arrk배열의 수만큼 상담원을 할당하고 k는 arrk+1의 인원을 할당
                    if(i == cntnum){
                        map.put(i, new int[arrk[i]+1]);
                    } else {
                        map.put(i, new int[arrk[i]]);    
                    }
                }
                int count[] = new int[k+1];
                for(int[] customer : reqs){ // 유저 대기시간 계산
                    int[] kind = map.get(customer[2]);
                    int min = 987654321;
                    int minIdx = -1;
                    for(int i = 0; i<kind.length;++i){
                        if(min > kind[i]){
                            minIdx = i;
                            min = kind[i];
                        }
                    }
                    if( min - customer[0] <= 0 ){
                        kind[minIdx] = customer[0]+customer[1];
                        map.put(customer[2], kind);
                    } else {
                        count[customer[2]] += min - customer[0];
                        kind[minIdx] = min + customer[1];
                        map.put(customer[2], kind);
                    }
                }
                for(int i = 1; i<=k;++i){ // 유저 대기시간 총합을 cnt배열에 넣기
                    cnt[cntnum] += count[i];    
                }
            }
            int min = 987654321;
            int minIdx = -1;
            for(int i = 1; i<=k;++i){ // 가장 크게 줄어든 유형을 찾고 그 유형에 상담원을 추가 배치
                if(min > cnt[i]){
                    min = cnt[i];
                    minIdx = i;
                }
            }

            arrk[minIdx]++;                      
            ++now;
        }
        map = new HashMap<>(); // 최종적으로 가장 적게 대기하는 시간 계산
        for(int i = 1; i<=k;++i){
            map.put(i, new int[arrk[i]]);
        }
        int count[] = new int[k+1];
        for(int[] customer : reqs){
            int[] kind = map.get(customer[2]);
            int min = 987654321;
            int minIdx = -1;
            for(int i = 0; i<kind.length;++i){
                if(min > kind[i]){
                    minIdx = i;
                    min = kind[i];
                }
            }
            if( min - customer[0] <= 0 ){
                kind[minIdx] = customer[0]+customer[1];
                map.put(customer[2], kind);
            } else {
                count[customer[2]] += min - customer[0];
                kind[minIdx] = min + customer[1];
                map.put(customer[2], kind);
            }
        }
        int answer = 0;
        for(int i = 1; i<= k;++i){
            answer += count[i];
        }
        return answer;
    }
}