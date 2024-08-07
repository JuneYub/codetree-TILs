import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr, 1, n+1);

        int j = n;
        int minValue = Integer.MAX_VALUE;
        for(int i = 1; i <= n; i++) {
            
            while(j > i && Math.abs(arr[i] + arr[j]) <= minValue) {
                minValue = Math.min(Math.abs(arr[i] + arr[j]), minValue);
                j--;
            }
            
        }
        System.out.print(minValue);
    }
}