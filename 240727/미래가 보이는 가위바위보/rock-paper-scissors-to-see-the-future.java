import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] p = new int[n];
        int[] h = new int[n];
        int[] s = new int[n];

        int[] lp = new int[n];
        int[] lh = new int[n];
        int[] ls = new int[n];

        int[] rp = new int[n];
        int[] rh = new int[n];
        int[] rs = new int[n];


        for(int i = 0; i < n; i++) {
            String str = br.readLine();

            if(str.charAt(0) == 'H') {
                p[i] = 1;
            } else if(str.charAt(0) == 'S') {
                h[i] = 1;
            } else {
                s[i] = 1;
            }
        }

        ls[0] = s[0];
        lp[0] = p[0];
        lh[0] = h[0];
        rs[n-1] = s[n-1];
        rp[n-1] = p[n-1];
        rh[n-1] = h[n-1];
        
        for(int i = 1; i < n; i++) {
            ls[i] = ls[i-1] + s[i];
            lp[i] = lp[i-1] + p[i];
            lh[i] = lh[i-1] + h[i];
        }

        for(int i = n-2; i >= 0; i--) {
            rs[i] = rs[i+1] + s[i];
            rp[i] = rp[i+1] + p[i];
            rh[i] = rh[i+1] + h[i];
        }
    
        int max = Integer.MIN_VALUE;
        for(int i = 1; i <= n-2; i++) {
            max = Math.max(max, ls[i-1] + rs[i+1] + p[i]);
            max = Math.max(max, ls[i-1] + rs[i+1] + h[i]);
            
            max = Math.max(max, lp[i-1] + rp[i+1] + s[i]);
            max = Math.max(max, lp[i-1] + rp[i+1] + h[i]);

            max = Math.max(max, lh[i-1] + rh[i+1] + s[i]);
            max = Math.max(max, lh[i-1] + rh[i+1] + p[i]);
        }
        
        max = Math.max(max, ls[n-1]);
        max = Math.max(max, lp[n-1]);
        max = Math.max(max, lh[n-1]);

        System.out.print(max);
    }
}