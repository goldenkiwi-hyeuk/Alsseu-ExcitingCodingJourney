package boj;

import java.util.*;

class Main2457 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // n <= 100_000
        List<Flower> flowers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int bloomM = sc.nextInt();
            int bloomD = sc.nextInt();
            int endM = sc.nextInt();
            int endD = sc.nextInt();

            flowers.add(new Flower(bloomM * 100 + bloomD, endM * 100 + endD));
        }
        sc.close();
        Collections.sort(flowers);
        // for (Flower f : flowers) {
        //     System.out.println(f.bloom + " " + f.end);
        // }

        int start = 301;
        int latest = 0;
        int cnt = 0;
        int idx = 0;

        while (start <= 1130) {
            boolean canPlant = false;
            for (; idx < n; idx++) {
                Flower f = flowers.get(idx);
                if (f.bloom > start) break;
                if (f.end > latest) {
                    latest = f.end;
                    canPlant = true;
                }
                // System.out.println("start: " + start);
                // System.out.println("latest: " + latest);
            }

            // System.out.println(canPlant);
            if (!canPlant) {
                System.out.println(0);
                return;
            }

            start = latest;
            cnt++;
        }
        System.out.println(cnt);

    }

    static class Flower implements Comparable<Flower> {
        int bloom, end;

        public Flower(int bloom, int end) {
            this.bloom = bloom;
            this.end = end;
        }

        @Override
        public int compareTo(Flower o) {
            if (this.bloom == o.bloom) return o.end - this.end;
            return this.bloom - o.bloom;
        }

    }

}