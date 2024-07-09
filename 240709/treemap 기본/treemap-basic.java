import java.io.*;
import java.util.*;

public class Main {

    public static int n;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        TreeMap<Integer, Integer> m = new TreeMap<>();

        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            String oper = st.nextToken();

            if("add".equals(oper)) {
                Integer key = Integer.parseInt(st.nextToken());
                Integer value = Integer.parseInt(st.nextToken());
                m.put(key, value);
            } else if("find".equals(oper)) {
                int key = Integer.parseInt(st.nextToken());
                if(m.containsKey(key)) {
                    sb.append(m.get(key)).append("\n");
                } else {
                    sb.append("None").append("\n");
                }
                
            } else if("print_list".equals(oper)) {
                Iterator<Map.Entry<Integer, Integer>> it = m.entrySet().iterator();
                if(m.size() == 0) {
                    sb.append("None").append("\n");
                }
                while (it.hasNext()) {
                    Map.Entry<Integer, Integer> entry = it.next();
                    sb.append(entry.getValue() + " ");
                }
                sb.append("\n");
            } else if("remove".equals(oper)) {
                int key = Integer.parseInt(st.nextToken());
                m.remove(key);
            }
        }

        System.out.println(sb.toString());



    }
}