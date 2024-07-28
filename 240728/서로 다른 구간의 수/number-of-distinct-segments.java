import java.io.*;
import java.util.*;


class Point implements Comparable<Point> {
    int x, v, idx;

    public Point(int x, int v, int idx) {
        this.x = x;
        this.v = v;
        this.idx = idx;
    }

    public int compareTo(Point p) {
        return this.x - p.x;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        HashSet<Integer> set = new HashSet<>();
        List<Point> list = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            
            list.add(new Point(x1, 1, i));
            list.add(new Point(x2, -1, i));
                
        }

        Collections.sort(list);

        int ans = 0;
        for(int i = 0; i < list.size(); i++) {
            Point p = list.get(i);

            if(set.size() == 0) {
                ans++;
            }

            if(p.v == 1) {
                set.add(p.idx);
            }

            if(p.v == -1) {
                set.remove(p.idx);
            }
        }

        System.out.print(ans);
    }
}