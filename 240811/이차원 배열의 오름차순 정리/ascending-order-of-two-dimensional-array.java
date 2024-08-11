import java.io.*;
import java.util.*;

public class Main {
    static long n;
    static long k;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());

        long left = 1;
        long right = n*n;
        long midIdx = n*n;
        while(left <= right) {
            long mid = (left + right) / 2;
            
            if(getCount(mid) >= k) {
                right = mid -1;
                midIdx = Math.min(midIdx, mid);
            } else {
                left = mid + 1;
            }
        }

        System.out.print(midIdx);
    }

    public static long getCount(long mid) {
        long sum = 0;
        // 행마다 돈다.
        for(int i = 1; i <= n; i++) {
            sum += Math.min(mid/i , n);
        }
        return sum;
    }       
}