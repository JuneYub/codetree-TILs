import java.io.*;
import java.util.*;

class Position implements Comparable<Position> {
    int start, end;

    Position(int start, int end) {
        this.start = start;
        this.end = end;
    }

    public int compareTo(Position p) {
        if(this.start == p.start) {
            return this.end - p.end;
        }

        return this.start - p.start;
    }
}

public class Main {

    public static int n;
    public static Position[] positions;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        positions = new Position[n];

        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            positions[i] = new Position(start,end);
        }

        Arrays.sort(positions);

        long left = 1;
        long right = 1_000_000_000;
        long ans = 0;

        while(left <= right) {
            long mid = (left + right) / 2;

            if(isPossiable(mid)) {
                left = mid + 1;
                ans = Math.max(ans, mid);
            } else {
                right = mid -1;
            }
        }

        System.out.print(ans);

    }

    public static boolean isPossiable(long dis) {
        long cur = positions[0].start;
        for(int i = 1; i < n; i++) {
            int start = positions[i].start;
            int end = positions[i].end;
            if(end < cur + dis) return false;

            cur = Math.max(cur+dis, start);
        }
        return true;
    }
}