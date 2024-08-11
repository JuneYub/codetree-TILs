import java.io.*;
import java.util.*;

public class Main {
    static int n;
    static int[] arr;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        
        arr = new int[n+1];

        int idx = 1;
        for(int i = 1; i <= n; i++) {
            
            while(idx % 3 == 0 || idx % 5 == 0) {
                idx++;
            }
            arr[i] = idx++;
        }


        int left = 0;
        int right = n;
        int minIdx = n+1;

        while(left <= right) {
            int mid = (left + right) /2;

            if(arr[mid] >= n) {
                minIdx = Math.min(minIdx, mid);
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }

        System.out.print(arr[n]);
        

    }
}