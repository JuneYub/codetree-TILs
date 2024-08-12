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
        arr = new long[n];

        for(int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }        
        

        int left = 1;
        int right = 1_000_000_000;
        int maxIdx = 0; 

        while(left <= right) {
            int mid = (left + right) / 2;

            if(isPossible(mid)) {
                left = mid + 1;
                maxIdx = Math.max(maxIdx, mid);
            } else {
                right = mid -1;
            }
        }

        System.out.print(maxIdx);



    }

    public static boolean isPossible(int dist) {
        int cnt = 1;
        int lastIdx = 0; 
        for(int i = 1; i < n; i++) {
            if(arr[i] - arr[lastIdx] >= dist) {
                cnt++;
                lastIdx = i;
            }
        }

        return cnt >= m;
    }
}