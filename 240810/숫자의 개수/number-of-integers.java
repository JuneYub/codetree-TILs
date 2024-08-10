import java.io.*;
import java.util.*;

public class Main {

    static int n, m;
    static int[] arr;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < m; i++) {
            int num = Integer.parseInt(br.readLine());
            int ans = upperBound(num) - lowerBound(num);
            sb.append(ans + "\n");
        }        

        System.out.print(sb.toString());
    }

    public static int lowerBound(int target) {
        int min_idx = n;
        int left = 0;
        int right = n-1;

        while(left <= right) {

            int min = (left + right) / 2;

            if(arr[min] >= target) {
                right = min -1;
                min_idx = Math.min(min_idx, min);
            }
            else {
                left = min + 1;
            }
        }

        return min_idx;
    }

    public static int upperBound(int target) {
        int max_idx = n;
        int left = 0;
        int right = n-1;
        while(left <= right) {
            int min = (left + right) / 2;
            
            if(arr[min] > target) {
                right = min - 1;
                max_idx = Math.min(max_idx, min);
            }
            else {
                left = min + 1;
            }
        }

        return max_idx;
    }
}