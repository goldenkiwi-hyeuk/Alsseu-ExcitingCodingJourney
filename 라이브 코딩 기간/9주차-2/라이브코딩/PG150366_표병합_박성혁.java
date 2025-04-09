import java.util.*;

class Solution {
    // 기본 아이디어 : 구현
    public String[] solution(String[] commands) {
        String[][] map = new String[51][51]; // 각 표의 값을 저장하는 배열
        int parent[][] = new int[51][51]; // 특정 a,b를 접근했을때 최종적으로 접근되는 배열의 index를 저장하는 배열
        for(int r = 1; r<51;++r){
            for(int c = 1; c<51;++c){
                map[r][c] = "";
                parent[r][c] = r*100+c;
            }
        }
        List<String> answer = new ArrayList<>();
        for(String command : commands){
            String[] cs = command.split(" ");
            if(cs[0].equals("UPDATE")){ // UPDATE 구문 
                if(cs.length == 4){ // r,c를 value로 바꾸는 경우
                    int row = Integer.parseInt(cs[1]);
                    int col = Integer.parseInt(cs[2]);
                    String value = cs[3];
                    int parentNum = parent[row][col]; // row,col이 가리키는 값의 index 찾기
                    map[parentNum/100][parentNum%100] = value; // 해당 인덱스의 값 업데이트
                } else { // value1를 value2로 바꾸는 경우
                    String value1 = cs[1];
                    String value2 = cs[2];
                    for(int r = 1; r<51;++r){ // 완탐하며 값 변경
                        for(int c = 1; c<51;++c){
                            if(map[r][c].equals(value1)){
                                map[r][c] = value2;
                            }
                        }
                    }
                }
            } else if (cs[0].equals("MERGE")){ // MERGE 구문
                int r1 = Integer.parseInt(cs[1]);
                int c1 = Integer.parseInt(cs[2]);
                int r2 = Integer.parseInt(cs[3]);
                int c2 = Integer.parseInt(cs[4]);
                if(map[r1][c1].equals("")){ // 만약 1번이 비어있다면 2번 값을 할당
                    map[r1][c1] = map[parent[r2][c2]/100][parent[r2][c2]%100];
                }
                int parentNum = parent[r2][c2]; // 2번의 index값을 찾고
                for(int r = 1; r<51;++r){
                    for(int c = 1; c<51;++c){
                        if(parent[r][c] == parentNum){ // 2번의 index값과 동일한 모든 값들을 1번의 최종 인덱스 값으로 변경
                            parent[r][c] = parent[r1][c1];
                        }
                    }
                }
            } else if (cs[0].equals("UNMERGE")){ // UNMERGE 구문
                int row = Integer.parseInt(cs[1]);
                int col = Integer.parseInt(cs[2]);
                int parentNum = parent[row][col];
                map[row][col] = map[parentNum/100][parentNum%100]; // 분해할 번호가 가리키는 값을 분해할 번호에 할당
                parent[row][col] = row*100+col; // 이후에는 모든 인덱스값을 자기자신으로 돌리기
                for(int r = 1; r<51;++r){
                    for(int c = 1; c<51;++c){
                        if(parent[r][c] == parentNum){
                            if((r != row)||(c!=col)){
                                map[r][c] = "";
                                parent[r][c] = r*100+c;
                            }
                        }
                    }
                }
            } else if (cs[0].equals("PRINT")){ // PRINT 구문
                int row = Integer.parseInt(cs[1]);
                int col = Integer.parseInt(cs[2]);
                int parentNum = parent[row][col];
                String ans = map[parentNum/100][parentNum%100]; // PRINT할 위치가 최종적으로 가리키는 값을 찾아서 비어있다면 EMPTY를 아니라면 그 값을 STRINGBUILDER에 저장
                if(ans.equals("")){
                    answer.add("EMPTY");
                } else {
                    answer.add(ans);
                }
            }
        }
        String[] ans = new String[answer.size()];
        for(int i = 0; i<answer.size();++i){
            ans[i] = answer.get(i);
        }
        return ans;
    }
}