import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int[] arr = new int[n+1];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int j = 0;
        int sumValue = 0;
        int ans = 0;
        for(int i = 1; i <= n; i++) {
            
            while(j+1 <= n && (sumValue + arr[j+1])  < m) {
                
                sumValue += arr[j+1];
                j++;
            }

            if(j+1 <= n) {
                
                //System.out.println(sumValue + " " + arr[j+1]);
                if(sumValue + arr[j+1]==m) {
                    ans++;
                }
            }
            sumValue -= arr[i];

        }

        System.out.print(ans);
    }
}