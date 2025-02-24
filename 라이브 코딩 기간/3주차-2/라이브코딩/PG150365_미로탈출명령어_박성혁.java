import java.util.*;

class Solution {
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        // 출발지와 도착지 간 최소 이동 횟수 (맨해튼 거리)
        int required = Math.abs(r - x) + Math.abs(c - y);
        // 최소 이동 횟수가 k보다 많거나, 남은 이동 횟수와 최소 거리의 차가 홀수이면 불가능
        if(required > k || (k - required) % 2 != 0) return "impossible";
        
        StringBuilder sb = new StringBuilder();
        int curX = x, curY = y;
        int remaining = k;
        
        // 사전 순: 'd'(아래) < 'l'(왼쪽) < 'r'(오른쪽) < 'u'(위)
        // 매 이동마다 가능한 방향 중, 이동 후 남은 이동횟수로 도착 가능(맨해튼 거리 + 짝수 조건)한지 판단
        while(remaining > 0) {
            boolean moved = false;
            // 아래로 이동(d)
            if(curX < n) {
                int nx = curX + 1, ny = curY;
                int need = Math.abs(r - nx) + Math.abs(c - ny);
                if(need <= remaining - 1 && ((remaining - 1 - need) % 2 == 0)) {
                    sb.append('d');
                    curX = nx;
                    remaining--;
                    moved = true;
                    continue;
                }
            }
            // 왼쪽으로 이동(l)
            if(curY > 1) {
                int nx = curX, ny = curY - 1;
                int need = Math.abs(r - nx) + Math.abs(c - ny);
                if(need <= remaining - 1 && ((remaining - 1 - need) % 2 == 0)) {
                    sb.append('l');
                    curY = ny;
                    remaining--;
                    moved = true;
                    continue;
                }
            }
            // 오른쪽으로 이동(r)
            if(curY < m) {
                int nx = curX, ny = curY + 1;
                int need = Math.abs(r - nx) + Math.abs(c - ny);
                if(need <= remaining - 1 && ((remaining - 1 - need) % 2 == 0)) {
                    sb.append('r');
                    curY = ny;
                    remaining--;
                    moved = true;
                    continue;
                }
            }
            // 위로 이동(u)
            if(curX > 1) {
                int nx = curX - 1, ny = curY;
                int need = Math.abs(r - nx) + Math.abs(c - ny);
                if(need <= remaining - 1 && ((remaining - 1 - need) % 2 == 0)) {
                    sb.append('u');
                    curX = nx;
                    remaining--;
                    moved = true;
                    continue;
                }
            }
            // 어떤 방향으로도 이동이 불가능하다면 경로 구성 실패
            if(!moved) return "impossible";
        }
        return sb.toString();
    }
}
