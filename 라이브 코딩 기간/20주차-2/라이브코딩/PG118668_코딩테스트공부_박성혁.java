import java.util.*;

class Solution {
    // 기본아이디어 : DP
    // 특정 알고력, 코딩력을 올리는데 필요한 최소 시간을 저장하는 배열을 DP로 저장
    public int solution(int alp, int cop, int[][] problems) {
        int tar_alp = 0;
        int tar_cop = 0;

        // 목표 알고력, 코딩력 찾기
        for(int[] problem : problems){
            tar_alp =  Math.max(tar_alp, problem[0]);
            tar_cop =  Math.max(tar_cop, problem[1]);
        }

        // 이미 알고력이 완성된 케이스
        if(alp>= tar_alp){
            alp = tar_alp;
        }

        // 이미 코딩력이 완성된 케이스
        if(cop>= tar_cop){
            cop = tar_cop;
        }

        int[][] visited = new int[tar_alp+2][tar_cop+2];
        for(int i = 0; i<=tar_alp;++i){
            Arrays.fill(visited[i], 987654321);
        }
        
        // 시작점은 0으로
        visited[alp][cop]=0;
        for(int i = alp; i<=tar_alp;++i){
            for(int j = cop; j<= tar_cop ; ++j){
                // 알고력 올리기
                visited[i+1][j] = Math.min(visited[i][j]+1,visited[i+1][j]);
                // 코딩력 올리기
                visited[i][j+1] = Math.min(visited[i][j]+1,visited[i][j+1]);
                // 문제 풀기
                for(int[] problem : problems){
                    int alp_req = problem[0];
                    int cop_req = problem[1];
                    int alp_rwd = problem[2];
                    int cop_rwd = problem[3];
                    int cost = problem[4];
                    if(i>= alp_req && j>= cop_req){
                        int nextAlp = Math.min(i+alp_rwd,tar_alp);
                        int nextCop = Math.min(j+cop_rwd,tar_cop);
                        visited[nextAlp][nextCop] = Math.min(visited[nextAlp][nextCop], visited[i][j]+cost);
                    }
                }
            }
        }
        return visited[tar_alp][tar_cop];
    }
}