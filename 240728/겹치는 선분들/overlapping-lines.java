import java.io.*;
import java.util.*;

class Point implements Comparable<Point> {
    int x, v, idx;

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
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        List<Point> points = new ArrayList<>();

        int startPoint = 0;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            String str = st.nextToken();
            char oper = str.charAt(0);

            if(oper == 'R') {
                int end = startPoint + num;
                points.add(new Point(startPoint, 1));
                points.add(new Point(end, -1));

                startPoint = end;

            } else {
                int start = startPoint - num;
                points.add(new Point(start, 1));
                points.add(new Point(startPoint, -1));

                startPoint = start;
            }
        }

        Collections.sort(points);

        int result = 0;
        int cnt = 0;
        int startIdx = 0;
        for(int i = 0; i < points.size(); i++) {
            

            Point p = points.get(i);
            if(p.v == 1) {
                cnt++;
                if(cnt >= k) {
                    startIdx = p.x;
                }
            } else {

                if(cnt >= k) {

                    
                    if(i+1 < points.size()) {
                        if(points.get(i+1).x == p.x && points.get(i+1).v == -1) {
                            continue;
                        }
                    }

                    //System.out.println(p.x + " " + startIdx);
                    result += Math.abs(p.x - startIdx);
                }
                cnt--;
            }
        }
        System.out.print(result);

    }
}