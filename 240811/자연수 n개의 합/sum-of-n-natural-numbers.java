import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long s  = Long.parseLong(br.readLine());

        long left = 0;
        long right = s + 1;
        long maxIdx = 0;
        while(left <= right) {
            long mid = (left + right) / 2;
            long sum = (mid*(mid+1))/2;
            if(sum <= s) {
                maxIdx = Math.max(maxIdx, mid);
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }

        System.out.print(maxIdx);
    }
}