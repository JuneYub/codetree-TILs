import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<Integer, Integer> map = new HashMap<>();
        
        int n = Integer.parseInt(br.readLine());
        
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String opper = st.nextToken();

            if("add".equals(opper)) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                map.put(a, b);
            } else if("find".equals(opper)) {
                int find = Integer.parseInt(st.nextToken());

                if(null == map.get(find)) {
                    System.out.println("None");
                } else {
                    System.out.println(map.get(find));
                }
            }
            else if("remove".equals(opper)) {
                int num = Integer.parseInt(st.nextToken());
                map.remove(num);
            }
        }

    }
}