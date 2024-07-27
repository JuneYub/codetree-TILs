import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String str = br.readLine();

        int[] c = new int[n];
        int[] o = new int[n];
        int[] w = new int[n];

        int[] cr = new int[n];
        int[] or = new int[n];
        int[] wr = new int[n];


        for(int i = 0; i < n; i++) {
            char ch = str.charAt(i);
            if(ch == 'C') {
                c[i]++;
            } else if(ch == 'O' ) {
                o[i]++;
            } else if(ch == 'W') {
                w[i]++;
            }
        }

        cr[n-1] = c[n-1];
        or[n-1] = o[n-1];
        wr[n-1] = w[n-1];
        for(int i = n-2; i >= 0; i--) {
            cr[i] = cr[i+1] + c[i];
            or[i] = or[i+1] + o[i];
            wr[i] = wr[i+1] + w[i];
        }

        int p1 = 0; 
        int p2 = 0;
        int p3 = 0;
        int idx = 0;
        while(idx < n) {
            if(c[idx] > 0) {
                p1 = idx;
                idx++;
                break;
            }
            idx++;
        }
        while(idx < n) {
            if(o[idx] > 0) {
                p2 = idx;
                idx++;
                break;
            }
            idx++;
        }
        while(idx < n) {
            if(w[idx] > 0) {
                p3 = idx;
                idx++;
                break;
            }
            idx++;
        }

        if(p2 > 0 && p3 > 0) {
            System.out.print(cr[p1] * or[p2] * wr[p3]);
        } else {
            System.out.print(0);
        }
        
    }
}