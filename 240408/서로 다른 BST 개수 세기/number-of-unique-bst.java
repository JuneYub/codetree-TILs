import java.util.*;
import java.io.*;

public class Main {

    public static final int MAX = 19;
    public static int[] dp = new int[MAX+1];
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        dp[0] = 1;
        dp[1] = 1;

        for(int i = 2; i <= n; i++) {
            dp[i] = findAns(i);
        }

        System.out.println(dp[n]);
    }

    public static int findAns(int num) {
        int ans = 0;

        for(int i = 0; i < num; i++) {
            ans += dp[i] * dp[num-i-1];
        }
        return ans;
    }
}