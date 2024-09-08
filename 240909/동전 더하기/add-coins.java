import java.io.*;
import java.util.*;

public class Main {
    static int n,k;
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        int arr[] = new int[n];
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        int ans = 0;
        int idx = n-1;
        while(k > 0) {
            ans +=(k / arr[idx]);
            k = (k % arr[idx--]); 
        }

        System.out.print(ans);

    }
}