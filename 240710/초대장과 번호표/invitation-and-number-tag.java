import java.io.*;
import java.util.*;


public class Main {
    public static final int MAX_N = 250000;

    public static int n, g;

    public static HashSet<Integer> total = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());

        HashSet<Integer>[] array = new HashSet[g];

        for(int i = 0; i < g; i++) {
            st = new StringTokenizer(br.readLine());
            array[i] = new HashSet<>();
            int cnt = Integer.parseInt(st.nextToken());

            for(int j = 0; j < cnt; j++) {
                int num = Integer.parseInt(st.nextToken());
                array[i].add(num);
            }
        }
        
        total.add(1);

        while(true) {

            Arrays.sort(array, new Comparator<HashSet<Integer>>(){
                public int compare(HashSet<Integer> a, HashSet<Integer> b) {
                    if(a == null && b == null) return 0;
                    if(a == null) return -1;
                    if(b == null) return 1;
                    return a.size() - b.size();
                }
            });

            int orgSize = total.size();
            for(HashSet<Integer> set : array) {
                if(set != null) {
                    set.removeAll(total);
                    if (set.size() == 1) {
                        total.addAll(set);
                        set.clear();
                    }
                }
            }

            if(orgSize == total.size()) {
                break;
            }
        }
        System.out.println(total.size());

    }
}