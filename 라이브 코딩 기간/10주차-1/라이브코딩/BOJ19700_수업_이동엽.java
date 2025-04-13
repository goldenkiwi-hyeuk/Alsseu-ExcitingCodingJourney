package boj;

import java.util.*;

class Main19700 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Student> students = new ArrayList<>();
        int n = sc.nextInt(); // 1 ~ 500_000
        while (n-- > 0) {
            int h = sc.nextInt();
            int k = sc.nextInt();
            students.add(new Student(h, k));
        }
        sc.close();

        Collections.sort(students);
        // for (Student s : students) System.out.println(s.h);

        List<Integer> team = new ArrayList<>();
        for (Student s : students) {
            int l = 0;
            int r = team.size();
            while (l < r) {
                int m = (l + r) / 2;
                if (team.get(m) <= s.k - 1) {
                    r = m;
                } else {
                    l = m + 1;
                }
            }
            if (l < team.size()) {
                team.set(l, team.get(l) + 1);
            } else {
                team.add(1);
            }
        }
        System.out.println(team.size());
    }

    static class Student implements Comparable<Student> {
        int h, k;

        public Student(int h, int k) {
            this.h = h;
            this.k = k;
        }

        @Override
        public int compareTo(Student s) {
            return Integer.compare(s.h, this.h);
        }

    }

}