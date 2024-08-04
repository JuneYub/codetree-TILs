import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];
        for(int i = 0; i < n; i++) {
            arr[i+1] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);
        // 1 2 3 4 5
        
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            int j = i; 
            while(j+1 <= n && (arr[i] + arr[j+1]) <= k) {
                ans++;
                j++;
            }
        }

        System.out.print(ans);

    }
}