import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[] need = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n-1; i++) {
            // 2 3 1 0 이 저장된다.
            need[i] = Integer.parseInt(st.nextToken());
        }

        int[] charge = new int[n];
        int[] minCharge = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            charge[i] = Integer.parseInt(st.nextToken());
        }

        // 앞에서부터 가장 에너지가 적게 드는 충전소를 최신하하며 저장한다.
        minCharge[0] = charge[0];
        for(int i = 1; i < n; i++) {
            // 5 2 2 1 이런식임
            minCharge[i] = Math.min(charge[i], minCharge[i-1]);
        }

        // 이제 필요한 에너지를 구한다. need에서 필요한 에너지와 minCharge 를 곱한 값을 누적한다.
        long ans = 0;
        for(int i = 0; i < n-1; i++) {
            ans += minCharge[i] * need[i];
        }
        System.out.print(ans);
    }
}