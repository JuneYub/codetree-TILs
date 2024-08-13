import java.io.*;
import java.util.*;
public class Main {
    static int n, m;
    static long[] arr;
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new long[m];
        
        for(int i = 0; i < m; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        long left = 1;
        long right = Long.MAX_VALUE;
        long midIdx = Long.MAX_VALUE;

        while(left <= right) {
            long mid = (left + right) / 2;
            if( isPossible(mid) ) {
                right = mid -1;
                midIdx = Math.min(midIdx, mid);
                
            } else {
                left = mid + 1;
                
            }
        }

        System.out.print(midIdx);
    }

    public static boolean isPossible(long target) {
        long cnt = 0;
        for(int i  = 0; i < m; i++) {
            cnt += (target / arr[i]);
        }
        
        if(cnt >= n) return true;
        return false;
    }
}