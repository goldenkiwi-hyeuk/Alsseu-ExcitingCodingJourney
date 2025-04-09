package programmers.표병합;

import java.util.*;

class Solution {

    class Chart {
        int r;
        int c;
        String value;

        Chart(int r, int c, String value) {
            this.r = r;
            this.c = c;
            this.value = value;
        }

    }

    String EMPTY = "EMPTY";
    Map<String, Chart> map = new HashMap<>();
    Set<List<Chart>> merged = new HashSet<>();
    List<String> ans = new ArrayList<>();

    public List<String> solution(String[] commands) {
        for (String c : commands) {
            runCommand(c);
        }
        return ans;
    }

    void runCommand(String command) {
        String[] ops = command.split(" ");
        String op = ops[0];

        if (op.equals("UPDATE")) {
            if (ops.length == 4) {
                int r = Integer.parseInt(ops[1]);
                int c = Integer.parseInt(ops[2]);
                String value = ops[3];
                updateCell(r, c, value);
            } else {
                String v1 = ops[1];
                String v2 = ops[2];
                updateAll(v1, v2);
            }
        } else if (op.equals("MERGE")) {
            int r1 = Integer.parseInt(ops[1]);
            int c1 = Integer.parseInt(ops[2]);
            int r2 = Integer.parseInt(ops[3]);
            int c2 = Integer.parseInt(ops[4]);
            mergeCells(r1, c1, r2, c2);
        } else if (op.equals("UNMERGE")) {
            int r = Integer.parseInt(ops[1]);
            int c = Integer.parseInt(ops[2]);
            unmergeCell(r, c);
        } else if (op.equals("PRINT")) {
            int r = Integer.parseInt(ops[1]);
            int c = Integer.parseInt(ops[2]);
            printCell(r, c);
        }
    }

    void updateCell(int r, int c, String value) {
        Chart target = getOrCreateChart(r, c);
        List<Chart> group = findGroup(r, c);
        if (group == null) {
            target.value = value;
        } else {
            for (Chart ch : group) {
                ch.value = value;
            }
        }
    }

    void updateAll(String from, String to) {
        for (Chart ch : map.values()) {
            if (ch.value != null && ch.value.equals(from)) {
                ch.value = to;
            }
        }
    }

    void mergeCells(int r1, int c1, int r2, int c2) {
        if (r1 == r2 && c1 == c2) return;

        Chart ch1 = getOrCreateChart(r1, c1);
        Chart ch2 = getOrCreateChart(r2, c2);

        List<Chart> group1 = findGroup(r1, c1);
        List<Chart> group2 = findGroup(r2, c2);

        Set<Chart> set = new HashSet<>();

        if (group1 != null) set.addAll(group1);
        else set.add(ch1);

        if (group2 != null) set.addAll(group2);
        else set.add(ch2);

        merged.remove(group1);
        merged.remove(group2);

        List<Chart> newGroup = new ArrayList<>(set);
        String finalValue = ch1.value != null ? ch1.value : ch2.value;
        for (Chart ch : newGroup) ch.value = finalValue;

        merged.add(newGroup);
    }

    void unmergeCell(int r, int c) {
        List<Chart> group = findGroup(r, c);
        if (group == null) return;

        Chart main = getOrCreateChart(r, c);
        String mainValue = main.value;

        for (Chart ch : group) {
            ch.value = null;
        }

        main.value = mainValue;
        merged.remove(group);
    }

    void printCell(int r, int c) {
        Chart ch = getOrCreateChart(r, c);
        ans.add((ch.value == null || ch.value.isEmpty()) ? EMPTY : ch.value);
    }

    Chart getOrCreateChart(int r, int c) {
        String key = r + "," + c;
        if (!map.containsKey(key)) {
            map.put(key, new Chart(r, c, null));
        }
        return map.get(key);
    }

    List<Chart> findGroup(int r, int c) {
        Chart target = getOrCreateChart(r, c);
        for (List<Chart> group : merged) {
            if (group.contains(target)) return group;
        }
        return null;
    }
}
