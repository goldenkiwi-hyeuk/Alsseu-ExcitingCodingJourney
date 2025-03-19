import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Main {
    static class Trie{
        TreeMap<String, Trie> child = new TreeMap<>();
    }
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Trie root = new Trie();

        int N = Integer.parseInt(br.readLine());
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());

            int K = Integer.parseInt(st.nextToken());

            Trie cur = root;
            for(int j=0; j<K; j++){
                String str = st.nextToken();
                if(!cur.child.containsKey(str)){
                    cur.child.put(str,new Trie());
                }
                cur = cur.child.get(str);
            }
        }

        print(root,0);
        System.out.println(sb);
    }

    public static void print(Trie root, int depth){
        Set keys = root.child.keySet();

        for(Object key : keys){
            for(int i=0; i<depth; i++){
                sb.append("--");
            }
            sb.append(key).append("\n");
            print(root.child.get(key), depth+1);
        }
    }
}
