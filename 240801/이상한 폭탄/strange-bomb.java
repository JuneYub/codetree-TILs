import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        TreeMap<Integer, List<Integer>> map = new TreeMap<>(new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i = 0; i < n; i++) {
            int num = Integer.parseInt(br.readLine());

            List<Integer> list = map.get(num);
            if(list == null) {
                list = new ArrayList<Integer>();
                list.add(i);
                map.put(num, list);
            } else {
                list.add(i);
                map.put(num, list);
            }
        }

        boolean finish = false;
        for(Integer num : map.keySet() ) {

            List<Integer> list = map.get(num);
            for(int i = 0; i < list.size()-1; i++) {
                if ((list.get(i+1) - list.get(i)) <= k ) {
                    System.out.print(num);
                    finish = true;
                    break;
                } 
            }
            if(finish) break;

        }
    }
}