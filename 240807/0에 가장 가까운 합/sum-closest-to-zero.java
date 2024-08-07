import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr, 1, n+1);

        int j = n;
        long minValue = Integer.MAX_VALUE;
        
        for(int i = 1; i <= n; i++) {

            //System.out.println(arr[i] + " " + arr[j]);
            
            if(i < j) {
                minValue = Math.min(Math.abs(arr[i] + arr[j]), minValue);
            }

            while(i < j-1 && arr[i] + arr[j] > 0) {
                j--;
                minValue = Math.min(minValue, Math.abs(arr[i] + arr[j]));
            }
        }
        System.out.print(minValue);
    }
}