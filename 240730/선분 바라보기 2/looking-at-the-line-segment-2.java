import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    int y, x, v, idx;

    public Point(int y, int x, int v, int idx) {
        this.y = y;
        this.x = x;
        this.v = v;
        this.idx = idx;
    }

    public int compareTo(Point p) {
        return this.x - p.x;
    }
}

class Comp implements Comparable<Comp> {
    int y, idx;

    public Comp(int y, int idx) {
        this.y = y;
        this.idx = idx;
    }

    public int compareTo(Comp c) {
        return this.y - c.y;
    }
}


public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st;

        List<Point> points = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            points.add(new Point(y, x1, 1, i));
            points.add(new Point(y, x2, -1, i));
        }

        Collections.sort(points);
        TreeSet<Comp> tree = new TreeSet<>();

        boolean[] visiable = new boolean[n];

        for(int i = 0; i < points.size(); i++) {
            Point p = points.get(i); 
            //System.out.println(p.idx + " " + p.v);
            if(p.v == 1) {
                tree.add(new Comp(p.y, p.idx));
                
            } else if(p.v == -1) {
                tree.remove(new Comp(p.y, p.idx));
            }

            if(tree.size() == 0) continue;
            
            visiable[tree.first().idx] = true;

        }

        int ans = 0;
        for(int i = 0; i < n; i++) {
            if(visiable[i]) {
                ans++;
            }
        }

        System.out.print(ans);
    }
}