import java.io.*;
import java.util.*;

class Pair {
    int x, y;
    public Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
public class Main {
    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Pair[] pairs = new Pair[n];

        int[] r = new int[n];
        int[] l = new int[n];

        StringTokenizer st;

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            pairs[i] = new Pair(x, y);
        }

        for(int i = 1; i < n; i++) {
            l[i] = l[i-1]  + (Math.abs(pairs[i].x - pairs[i-1].x) + Math.abs(pairs[i].y - pairs[i-1].y));
        }

        for(int i = n-2; i >= 0; i--) {
            r[i] = r[i+1] + (Math.abs(pairs[i].x - pairs[i+1].x) + Math.abs(pairs[i].y - pairs[i+1].y));
        }

        int min = Integer.MAX_VALUE;

        for(int i = 1; i <= n-2; i++) {
            min  = Math.min(min, l[i-1] + r[i+1] + ( Math.abs(pairs[i-1].x - pairs[i+1].x) + Math.abs(pairs[i-1].y - pairs[i+1].y)  ) );
        }

        System.out.println(min);
    }
}