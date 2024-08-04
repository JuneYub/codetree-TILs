import java.io.*;
import java.util.*;

class Candy implements Comparable<Candy> {
    int x, cnt;

    public Candy(int x, int cnt) {
        this.x = x;
        this.cnt = cnt;
    }

    public int compareTo(Candy c) {
        return this.x - c.x;
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Candy[] candies = new Candy[n+1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());

            candies[i] = new Candy(x, cnt);
        }

        Arrays.sort(candies, 1, n+1);

        int j = 0;
        int totalNums = 0;
        int ans = 0;
        for(int i = 1; i <= n; i++) {

            while(j+1 <= n && (candies[j+1].x - candies[i].x) <= 2*k) {
                totalNums += candies[j+1].cnt;
                j++;
            }

            ans = Math.max(ans, totalNums);

            totalNums -= candies[i].cnt;


        }

        System.out.print(ans);



    }
}