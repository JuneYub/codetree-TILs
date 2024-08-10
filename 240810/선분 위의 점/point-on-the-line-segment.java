import java.util.*;
import java.io.*;

public class Main {

    public static int n, m;
    public static int[] arr;

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

        Arrays.sort(arr);
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            int startIdx = lowerBound(start);
            int endIdx = upperBound(end);

            sb.append((endIdx - startIdx) + "\n");

        }

        System.out.print(sb);

    }

    public static int lowerBound(int target) {
        int left = 0;
        int right = n-1;

        int minIdx = n;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(arr[mid] >= target) {
                right = mid -1;
                minIdx = Math.min(minIdx, mid);
            } else {
                left = mid + 1;
            }
        }

        return minIdx;
    }

    public static int upperBound(int target) {
        int left = 0;
        int right = n -1;

        int maxIdx = n;

        while(left <= right) {
            int mid = (left + right) / 2;

            if(arr[mid] > target) {
                right = mid -1;
                maxIdx = Math.min(maxIdx, mid);
            } else {
                left = mid + 1;
            }
        }

        return maxIdx;
    }
}