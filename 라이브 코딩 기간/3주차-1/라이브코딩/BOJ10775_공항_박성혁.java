import java.io.*;
import java.util.*;

class Main {

    // 기본 아이디어 : 유니온 파인드

    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(br.readLine());
        parent = new int[G+1];
        for (int i = 1; i <= G; i++) {
            parent[i] = i; // 초기에는 각 게이트 번호에는 자기 숫자만큼의 비행기를 받을수 있음 Ex) 4번 게이트에는 4,3,2,1 4개의 비행기를 받는것이 가능
        }
        int P = Integer.parseInt(br.readLine());
        int ans = 0;
        boolean isok = true; // 중간에 더이상 게이트에 비행기를 넣을수 없는지 확인하는 용도
        for (int i = 1; i <= P; i++) {
            int plane = Integer.parseInt(br.readLine());
            if(isok){
                if (plane == parent[plane]){
                    ans++;
                    parent[plane]--;
                } else {
                    int planeparent = findParent(plane); // 부모를 찾음
                    if (parent[plane] != 0 ){ // 넣을수있다면 넣고 아니라면 isok를 false로 만들기
                        ans++;
                        parent[planeparent]--;
                    } else {
                        isok = false;
                    }
                }
            }
        }
        System.out.println(ans);
    }
    public static int findParent(int num){
        if (parent[num] == num){
            return num;
        }
        return parent[num] = findParent(parent[num]);
    }
}