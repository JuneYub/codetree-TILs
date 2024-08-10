import java.io.*;
import java.util.*;

public class Main {
    static long m, a, b;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        m = Long.parseLong(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Long.parseLong(st.nextToken());
        b = Long.parseLong(st.nextToken());

        long minAns = m;
        long maxAns = 0;

        for(long i = a; i <= b; i++) {
            long cnt = find(i);
            minAns = Math.min(minAns, cnt);
            maxAns = Math.max(maxAns, cnt);
        }

        System.out.print(minAns + " " + maxAns);

    }

    public static long find(long target) {
        long left = 1;
        long right = m;
        
        long cnt = 0;
        while(left <= right) {
            long mid = (left + right) / 2;
            cnt++;

            if(mid > target) {
                right = mid - 1;
            } else if(mid < target) {
                left = mid + 1;
            } else {
                return cnt;
            }
        }
        return cnt;
    }
}