import java.util.*;

class Solution {
    // 기본아이디어 : 다익스트라(Multi-source Dijkstra)
    private static class Edge implements Comparable<Edge>{
        int start, end, weight, intensity;
        
        Edge(){}
        
        Edge(int start, int end, int weight){
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.intensity = weight;
        }
        
        Edge(int start, int end, int weight, int max){
            this.start = start;
            this.end = end;
            this.weight = weight;
            this.intensity = max;
        }

        public int compareTo(Edge o){
            if(this.intensity == o.intensity)
                return this.end - o.end;
            return this.intensity - o.intensity;
        }
    }        
    
    static List<List<Edge>> edgeList;
    static Set<Integer> gatesSet, summitsSet;
    static int[] intensityList;
    
    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        edgeList = new ArrayList<>();
        for(int i = 0; i<=n; ++i){
            edgeList.add(new ArrayList<Edge>());
        }
        
        gatesSet = new HashSet<>();
        for(int gate : gates){
            gatesSet.add(gate);
        }
        
        summitsSet = new HashSet<>();
        for(int summit : summits){
            summitsSet.add(summit);
        }
        
        for(int[] path : paths){
            if(!gatesSet.contains(path[1]) && !summitsSet.contains(path[0])){
                edgeList.get(path[0]).add(new Edge(path[0], path[1], path[2]));
            }
            if(!gatesSet.contains(path[0]) && !summitsSet.contains(path[1])){
                edgeList.get(path[1]).add(new Edge(path[1], path[0], path[2]));
            }
        }
        
        int[] answer = searchMaxCourse(n);
        return answer;
    }
    
    private static int[] searchMaxCourse(int num){
        int intensity = 987654321;
        int target = 987654321;
        
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        intensityList = new int[num+1];
        Arrays.fill(intensityList, 987654321);
        for(int gate : gatesSet){
            intensityList[gate] = 0;
            pq.add(new Edge(gate, gate, 0, 0));
        }
        while(!pq.isEmpty()){
            Edge edge = pq.poll();
            if(intensityList[edge.end] < edge.intensity){
                continue;
            }

            if(summitsSet.contains(edge.end)){
                // 진짜 더 작은 intensity이거나,
                // intensity 같을 때 번호가 더 작은 summit이라면 갱신
                if (edge.intensity < intensity || (edge.intensity == intensity && edge.end < target)) {
                    intensity = edge.intensity;
                    target = edge.end;
                }
            }

            for(Edge edge2 : edgeList.get(edge.end)){
                if (intensityList[edge2.end] > Math.max(edge.intensity, edge2.weight)) {
                    intensityList[edge2.end] = Math.max(edge.intensity, edge2.weight);
                    pq.add(new Edge(edge2.start, edge2.end, edge2.weight, Math.max(edge.intensity, edge2.weight)));
                }
            }
        }

        return new int[] {target, intensity};
    }
}