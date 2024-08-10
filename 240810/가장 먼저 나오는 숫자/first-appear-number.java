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

        st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        for(int j = 0; j < m; j++) {
            int target = Integer.parseInt(st.nextToken());
            sb.append(lowerBound(target) + "\n");
        }

        System.out.print(sb);

    }

    public static int lowerBound(int target) {
        int minIdx = n;
        int left = 0;
        int right = n-1;
        //System.out.println("==================");
        while(left <= right) {
            
            int mid = (left + right) / 2;
            //System.out.println(left + " " + mid + " " + right);
            if(arr[mid] >= target) {
                right = mid - 1;
                minIdx = Math.min(minIdx, mid);
            } else {
                left = mid + 1;
            }

        }
        
        if(arr[minIdx] != target) return -1;
        return minIdx+1;
    }
}