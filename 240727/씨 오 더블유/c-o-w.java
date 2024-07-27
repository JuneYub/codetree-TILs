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

        // w의 우측 누적합
        wr[n-1] = w[n-1];
        for(int i = n-2; i>= 0; i--) {
            wr[i] = wr[i+1] + w[i];
        }
        
        // ow의 우측 누적합
        or[n-1] = o[n-1];
        for(int i = n-2; i>= 0; i--) {
            if(o[i] == 1) {
                or[i] = or[i+1] + wr[i];
            } else {
                or[i] = or[i+1];
            }
        }
        

        long ans = 0;
        for(int i = 0; i < n-2; i++) {
            if(c[i] == 1) {
                ans += or[i];
            }
        }
        System.out.print(ans);
    }
}