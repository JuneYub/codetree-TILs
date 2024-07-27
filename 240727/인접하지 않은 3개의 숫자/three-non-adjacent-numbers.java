import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] arr = new int[n];
        int[] l = new int[n];
        int[] r = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        

        l[0] = arr[0];
        r[n-1] = arr[n-1];

        for(int i = 1; i < n; i++) {
            l[i] = Math.max(arr[i], l[i-1]);
        }

        for(int i = n-2; i >= 0; i--) {
            r[i] = Math.max(arr[i], r[i+1]);
        }

        int ans = 0;
        for(int i = 2; i <= n-3; i++) {
            ans = Math.max(ans, l[i-2] + arr[i] + r[i+2] ); 
        }

        System.out.print(ans);


    }
}