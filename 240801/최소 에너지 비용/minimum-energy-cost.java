import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] need = new int[n-1];  // 크기를 n-1로 변경
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n-1; i++) {
            need[i] = Integer.parseInt(st.nextToken());
        }

        int[] charge = new int[n];
        int[] minCharge = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            charge[i] = Integer.parseInt(st.nextToken());
        }

        minCharge[0] = charge[0];
        for(int i = 1; i < n; i++) {
            minCharge[i] = Math.min(charge[i], minCharge[i-1]);
        }

        long ans = 0;  // ans만 long으로 선언
        for(int i = 0; i < n-1; i++) {
            ans += (long)minCharge[i] * need[i];  // 곱셈 결과를 long으로 캐스팅
        }
        System.out.print(ans);
    }
}