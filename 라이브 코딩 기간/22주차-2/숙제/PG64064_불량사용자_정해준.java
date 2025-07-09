import java.util.*;

public class PG64064_불량사용자_정해준 {
    class Solution {
        static boolean[] check;
        static HashSet<String> set;
        public int solution(String[] user_id, String[] banned_id) {
            set = new HashSet<>();
            check = new boolean[user_id.length];
            for(int i=0; i<banned_id.length; i++) {
                banned_id[i] = banned_id[i].replace('*', '.');
            }

            backtrack(0, "", banned_id, user_id);
            return set.size();
        }

        public static void backtrack(int depth, String res, String[] ban_id, String[] user_id) {
            if(depth==ban_id.length) { // banid 만큼 만들어지면 종료
                String[] arr = res.split(" ");
                Arrays.sort(arr);

                String str="";
                for(String s : arr) {
                    str += s;
                }
                set.add(str);

                return;
            }

            for(int i=0; i < user_id.length; i++) {//유저id 조합
                if(check[i] || !user_id[i].matches(ban_id[depth]))
                    continue;
                check[i]=true;
                backtrack(depth+1,user_id[i]+" "+res,ban_id,user_id);
                check[i]=false;
            }
        }
    }
}
