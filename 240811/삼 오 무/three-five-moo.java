import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        int left = 1;
        int right = Integer.MAX_VALUE;
        int minIdx = Integer.MAX_VALUE;


        while(left <= right) {
            int mid = (left + right) /2;

            if(excludeMooCnt(mid) >= n) {
                right = mid - 1;
                minIdx = Math.min(minIdx, mid);
            } else {
                left = mid + 1;
            }
        }
        System.out.print(minIdx);
    }

    public static long excludeMooCnt(long mid) {
        long mooCnt = mid / 3 + mid / 5 - mid / 15;

        return mid - mooCnt;
    }
}