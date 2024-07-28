import java.util.*;
import java.io.*;

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

        List<Point> points = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            points.add(new Point(x1, 1));
            points.add(new Point(x2, -1));

        }

        Collections.sort(points);

        // 현재 겹치는 선의 개수
        int cnt = 0;
        // 지금까지 최대로 겹친 선의 개수
        int maxCnt = 0;
        // 최대로 겹친 선의개수의 겹친 지역 개수
        int ans = 0;

        for(int i = 0; i < points.size(); i++) {
            Point p = points.get(i);

            if(p.v == 1) {
                // 겹치는 선의 개수 1개 증가시킴
                ++cnt;
            }
            if(p.v == -1) {
                // 겹치는 선의 개수를 1개 감소시킴
                --cnt;
            }

            // 겹치는 선의 개수의 최대값이 갱신된 경우
            if(cnt > maxCnt) {
                // 최대로 겹친 지역의 개수를 1로 초기화한다.
                ans = 1;
            } else if(cnt == maxCnt) {
                // 지금 겹친 선의 개수가 최대값과 같다면 지역 개수 증가시킨다.
                ans++;
            }
        }

        System.out.print(ans);
    }
}