import java.io.*;
import java.util.*;

public class Main {
    public static int n, m;
    public static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int left = 1;
        int right = 10000;
        int maxK = 0;

        while(left <= right) {
            int mid = (left + right) / 2;
            if(isPossible(mid)) {
                maxK = Math.max(maxK, mid);
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        System.out.print(maxK);

    }
    public static boolean isPossible(int num) {
        
        int cnt  = 0;
        for(int i = 0; i < n; i++) {
            cnt += (arr[i]/num);
        }
        if(cnt >= m) return true;
        return false;
    }
}