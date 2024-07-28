import java.util.*;
import java.io.*;

class Segment {
    int x1, x2;

    public Segment(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }
}

class Point implements Comparable<Point> {
    int x, v;
    
    public Point(int x, int v) {
        this.x = x;
        this.v = v;
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

        // 주어진 좌표의 범위가 큰 경우에는
        // 각 선분을 두 지점으로 나눠서
        // +1, -1로 담은 뒤,
        // 정렬해줍니다.
        ArrayList<Point> points = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            points.add(new Point(x1, 1));
            points.add(new Point(x2, -1));
        }
        
        Collections.sort(points);

        int maxVal = 0;
        int sumVal = 0;

        for(int i = 0; i < 2 * n; i++) {
            int x = points.get(i).x;
            int v = points.get(i).v;
            sumVal += v;

            maxVal = Math.max(sumVal, maxVal);
        }

        System.out.print(maxVal);

    }
}