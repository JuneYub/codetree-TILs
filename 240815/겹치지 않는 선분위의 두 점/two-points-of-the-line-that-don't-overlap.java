import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    long start, end;

    Point(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Point p) {
        if(this.start == p.start) {
            if(this.end < p.end) return -1;
            return 1;
        }
        if(this.start > p.start) return 1;
        return -1;
    }
}

public class Main {

    public static int n, m;
    public static int[] arr;
    public static Point[] points;

    public static final long MAX_NUM = (long)1e18;
    public static final int MAX_M = 100000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        points = new Point[m];
        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            long start = Long.parseLong(st.nextToken());
            long end = Long.parseLong(st.nextToken());

            Point p = new Point(start, end);
            points[i] = p;
            
        }

        Arrays.sort(points);

        long left = points[0].start;
        long right = points[m-1].end;   
        long maxIdx = 0;

        while(left <= right) {
            long mid = (left + right) / 2;
            if(isPossible(mid)) {
                left = mid + 1;
                maxIdx = Math.max(maxIdx, mid);
            } else {
                right = mid - 1;
            }
        }
        System.out.print(maxIdx);
    }

    public static boolean isPossible(long dis) {

        int cnt = 0;
        long lastPoint = -MAX_NUM;

        for(int i = 0; i < m; i++) {

            long start = points[i].start;
            long end = points[i].end;

            // 마지막 점의 위치가 현재 구간의 끝보다 작다면 진행한다.
            while(lastPoint + dis <= end) {
                lastPoint = Math.max(start, lastPoint + dis);
                cnt++;
                if(cnt >= n) break;
            }
            
        }
        
        return cnt >= n;

    }
}