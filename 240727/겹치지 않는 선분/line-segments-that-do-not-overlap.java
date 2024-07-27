import java.util.*;
import java.io.*;

class Line implements Comparable<Line> {
    int x1, x2;

    public Line(int x1, int x2) {
        this.x1 = x1;
        this.x2 = x2;
    }

    public int compareTo(Line l) {
        return this.x1 - l.x1;
    }
}
public class Main {

    public static Line[] lines;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        lines = new Line[n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());

            lines[i] = new Line(x1, x2);
        }

        Arrays.sort(lines);

        int[] r = new int[n];
        int[] l = new int[n];

        l[0] = lines[0].x1;
        r[n-1] = lines[n-1].x2;

        // 좌측에서의 최대 x2보다 현재 x2가 커야함
        // 우측에서의의 최소 x2 보다 현재 x2가 작아야함

        for(int i = 1; i < n; i++) {
            // 시작점의 위치의 최대값을 갱신
            l[i] = Math.max(l[i-1], lines[i].x2);
        }

        for(int i = n-2; i >= 0; i--) {
            // 끝점 위치의 최소값을 갱신해준다.
            r[i] = Math.min(r[i+1], lines[i].x2);
        }

        // l의 경우 4 9 9 16
        // r의 경우 -3 3 7 10

        int ans = 0;
        for(int i = 0; i < n; i++) {
            //System.out.println(lines[i].x2 + " " + l[i] + " " + r[i]);
            if(lines[i].x2 >= l[i] && lines[i].x2 <= r[i]) {
                ans++;
            }
        }
        System.out.print(ans);

    }
}