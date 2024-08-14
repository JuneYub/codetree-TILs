import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    long start, end;

    Point(long start, long end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Point p) {
        return (int)(this.start - p.start);
    }
}

public class Main {

    public static int n, m;
    public static int[] arr;
    public static Point[] points;

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
            //System.out.println(left + " " + mid + " " + right);
            
            if(isPossible(mid)) {
                left = mid + 1;
                maxIdx = Math.max(maxIdx, mid);
                //System.out.println("maxIdx : " + maxIdx);
            } else {
                right = mid - 1;
                //System.out.println("right : " + right);
            }
        }

        System.out.print(maxIdx);
    }

    public static boolean isPossible(long dis) {

        int cnt = 0;
        long lastPoint = -1000000;

        for(int i = 0; i < m; i++) {

            long j = points[i].start;
            //System.out.println(points[i].start + " " + points[i].end);
            // 다음 구간의 시작점을 찾습니다.
            // 구간의 첫번째 점부터 구간의 끝까지 돕니다.
            while(j < points[i].end) {
                // 만약 현재 점- 이전 점이 거리보다 크거나 같다면 마지막 점의 위치는 현재 점이
                // 됩니다. 
                if((j - lastPoint) >= dis) {
                    lastPoint = j;
                    cnt++;
                   
                    break;
                }
                
            }

            // 마지막 점의 위치가 현재 구간의 끝보다 작다면 진행한다.
            while(lastPoint + dis <= points[i].end) {
                lastPoint += dis;
                
                cnt++;
            }
            if(cnt >= n) return true;
        }
        
        return false;

    }
}