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
        int j = n;
        for(int i = 1; i <= n; i++) {
            
            while(j != 1&& arr[i] + arr[j] > k) {
                j--;
            }

            if(j <= i) break;

            ans += j - i;
        }

        System.out.print(ans);

    }
}