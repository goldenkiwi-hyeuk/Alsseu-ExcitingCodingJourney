import java.util.*;
import java.io.*;

class Main {
    static int N, ans;
    static boolean flag;
    static int[][] board;
    static int[] card;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = 10;
        board = new int[N][N];
        card = new int[6];
        for(int i = 0; i<6; ++i){
            card[i] = 5;
        }
        
        for(int i = 0; i<N; ++i){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j <N; ++j){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        flag = true;
        ans = 987654321;
        
        fillBoard(0,0);
        
        // 완벽히 채우는게 불가능 하다면 -1 출력
        if(flag){
            System.out.println(-1);
        } else { 
            // 사용한 색종이 갯수 체크해서 출력
            System.out.println(ans);
        }
    }
    
    private static void fillBoard(int r, int c){
        // 현재 위치가 마지막열 마지막행이고 해당 칸이 채워진 상태라면 사용한 카드갯수 조사하기
        if(r==9 &&c==9 && board[r][c]==0){
            flag = false;
            int cnt = 0;
            for(int i = 1; i<6; ++i){
                cnt += 5 - card[i];
            }
            ans = Math.min(cnt,ans);
            return;
        }
        
        // 현재 행 탐색
        for(int j = c ; j<10;++j){
            if(board[r][j]==1){
                // 가능한 최대사이즈 탐색
                int size = checkSize(r,j);
                // 사이즈를 줄여가면서 색종이를 채워보기
                while(size > 0){
                    if(card[size]==0){
                        --size;
                        continue;
                    }
                    --card[size];
                    fill(r,j,size); // 해당 부분 채우기
                    fillBoard(r, j); // 다시 탐색
                    ++card[size];
                    fill(r,j,size); // 백트래킹,원복 코드
                    --size;
                }
                return; // 해당칸을 채울수 없다면 더이상 탐색할 이유가 없음
            }
            if(r== 9 && j==9 && board[r][j] == 0){
                fillBoard(9,9);
            }
        }
        
        // 다음 행부터 끝까지 탐색
        for(int i = r+1; i<10; ++i){
            for(int j = 0; j<10;++j){
                if(board[i][j]==1){
                    int size = checkSize(i,j);
                    while(size > 0){
                        if(card[size]==0){
                            --size;
                            continue;
                        }
                        --card[size];
                        fill(i,j,size);
                        fillBoard(i, j);
                        ++card[size];
                        fill(i,j,size);
                        --size;
                    }
                    return;
                }
                if(i== 9 && j==9 && board[i][j] == 0){
                    fillBoard(9,9);
                }
            }
        }
    }
    
    private static boolean inRange(int r, int c){
        if((r>=0)&&(r<N)&&(c>=0)&&(c<N)) return true;
        return false;
    }
    
    private static int checkSize(int r, int c){
        int size = 1;
        boolean flag = false;
        out : while(size<5){
            ++size;
            for(int i = r; i < r+size; ++i){
                if(inRange(i,c+size-1)&&board[i][c+size-1]==1){
                    continue;
                } else {
                    flag = true;
                    break out;
                }
            }
            
            for(int j = c; j < c+size; ++j){
                if(inRange(r+size-1,j)&&board[r+size-1][j] == 1){
                    continue;
                } else {
                    flag = true;
                    break out;
                }
            }
        }
        
        if(flag){
            return size-1;    
        } else {
            return size;
        }
    }
    
    
    // 색종이를 size만큼 채우는 작업 || 원복시키는 코드
    private static void fill(int r, int c, int size){
        for(int i = r; i<r+size; ++i){
            for(int j = c; j<c+size; ++j){
                board[i][j] = board[i][j]==0 ? 1:0;
            }
        }
    }
}