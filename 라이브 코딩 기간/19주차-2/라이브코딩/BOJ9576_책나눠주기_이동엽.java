package boj;

import java.util.*;

class Main9576 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        while (t-- > 0) {
            int n = sc.nextInt();
            int m = sc.nextInt();

            List<Book> books = new ArrayList<>();
            for (int i = 0; i < m; i++) {
                int a = sc.nextInt();
                int b = sc.nextInt();
                books.add(new Book(a, b));
            }
            Collections.sort(books);

            int cnt = 0;
            boolean[] given = new boolean[n + 1];
            for (int i = 0; i < m; i++) {
                Book b = books.get(i);
                for (int j = b.start; j <= b.end; j++) {
                    if (!given[j]) {
                        cnt++;
                        given[j] = true;
                        break;
                    }
                }
            }

            sb.append(cnt).append("\n");
        }
        sc.close();
        System.out.println(sb);
    }


    static class Book implements Comparable<Book>{
        int start, end;

        Book(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override // 시작이 작은순 정렬
        public int compareTo(Book b) {
            if (this.end == b.end) return this.start - b.start;
            return this.end - b.end;
        }

    }

}