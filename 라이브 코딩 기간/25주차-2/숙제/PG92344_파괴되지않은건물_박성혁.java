class Solution {
    public int solution(int[][] board, int[][] skill) {
        // 차분배열 생성
        int[][] changeArr = new int[board.length+1][board[0].length+1];
        for(int[] change : skill){
            int type = change[0];
            int r1 = change[1];
            int c1 = change[2];
            int r2 = change[3];
            int c2 = change[4];
            int degree = change[5];
            if(type == 1){
                changeArr[r1][c1] -= degree;
                changeArr[r1][c2+1] += degree;
                changeArr[r2+1][c1] += degree;
                changeArr[r2+1][c2+1] -= degree;
            } else {
                changeArr[r1][c1] += degree;
                changeArr[r1][c2+1] -= degree;
                changeArr[r2+1][c1] -= degree;
                changeArr[r2+1][c2+1] += degree;
            }
        }
        
        // 차분배열 최종 업데이트 (열)
        for(int i = 0; i<changeArr.length; ++i){
            for(int j = 1; j<changeArr[0].length; ++j){
                changeArr[i][j] = changeArr[i][j] + changeArr[i][j-1];
            }
        }
        
        // 차분배열 최종 업데이트 (행)
        for(int j = 0; j<changeArr[0].length; ++j){
            for(int i = 1; i<changeArr.length; ++i){
                changeArr[i][j] = changeArr[i][j] + changeArr[i-1][j];
            }
        }
        
        int answer = 0;
        for(int i = 0; i<board.length;++i){
            for(int j = 0; j<board[0].length;++j){
                if(board[i][j]+changeArr[i][j]>0){
                    ++answer;
                }
            }
        }
        
        return answer;
    }
}