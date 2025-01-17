import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static List<Integer>[] tree;
    static int deleteNode;
    static int result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int root = 0; // 루트노드

        tree = new ArrayList[N];
        for(int i=0; i<N; i++){
            tree[i] = new ArrayList<>();
        }



        st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++){
            int parent = Integer.parseInt(st.nextToken());
            if(parent==-1){
                root = i;
                continue;
            }
            tree[parent].add(i);
        }

//        for(int i=0; i<N; i++){
//            System.out.println("Node " + i + " children: " + tree[i]);
//        }


        deleteNode = Integer.parseInt(br.readLine()); // 삭제될 노드
        if(deleteNode == root){
            System.out.println(0);
            return;
        }

        countResult(root);

        System.out.println(result);

    }

    static void countResult(int node){

        if(node==deleteNode){
            return;
        }

        if(tree[node].isEmpty() || (tree[node].size() == 1 && tree[node].get(0) == deleteNode)){
            result++;
            return;
        }

        for(int child : tree[node]){
            countResult(child);
        }

    }

}
