import java.io.*;
import java.util.*;

public class Main {
    public static int n, m;
    public static char[][] arr1;
    public static char[][] arr2;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr1 = new char[n][m];
        arr2 = new char[n][m];

        for(int y = 0; y < n; y++) {
            String str = br.readLine();
            for(int x = 0; x < m; x++) {
                arr1[y][x] = str.charAt(x);
            }
        }


        for(int y = 0; y < n; y++) {
            String str = br.readLine();
            for(int x = 0; x < m; x++) {
                arr2[y][x] = str.charAt(x);
            }
        }

        int result = 0;


        for(int y = 0; y < m; y++) {
            for(int x = y+1; x < m; x++) {
                for(int z = x+1; z < m; z++) {

                    HashSet<String> set = new HashSet<>();

                    for(int idx = 0; idx < n; idx++) {
                        set.add(String.valueOf(arr1[idx][y]) + arr1[idx][x] + arr1[idx][z]);
                    }

                    boolean flag = true;
                    for(int idx = 0; idx < n; idx++) {
                        if(set.contains(String.valueOf(arr2[idx][y]) + arr2[idx][x] + arr2[idx][z])) {
                            flag = false;
                            break;
                        }
                    }

                    if(flag) {
                        result++;
                    }

                }
            }
        }

        System.out.print(result);




    }
}