import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static char[][] a, b;
    static HashSet<String> aSet;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        a = new char[n][m];
        b = new char[n][m];

        aSet = new HashSet<>();


        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                a[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                b[i][j] = s.charAt(j);
            }
        }

        int count = 0;

        for (int i = 0; i < m; i++) {
            for (int j = i + 1; j < m; j++) {
                for (int k = j + 1; k < m; k++) {
                    aSet.clear();


                    // A그룹 문자열 생성
                    for (int idx = 0; idx < n; idx++) {
                        String s = makeAChars(idx, i, j, k);
                        aSet.add(s);
                    }


                    // B그룹 문자열 생성
                    boolean flag = true; 
                    for(int idx = 0; idx < n; idx++) {
                        if(aSet.contains(makeBChars(idx, i, j, k))) {
                            flag = false;
                            break;
                        }
                    }
                    
                    if(flag) {
                        // A그룹과 B그룹을 구분할 수 있다면? 개수 증가시키기
                        count++;
                    }

                }
            }
        }

        System.out.println(count);
    }


    // a 배열로 생성가능한 문자열 생성
    private static String makeAChars(int index, int i, int j, int k) {
        return a[index][i] + "" + a[index][j] + "" + a[index][k];
    }

    // b배열로 생성가능한 문자열 생성

    private static String makeBChars(int index, int i, int j, int k) {
        return b[index][i] + "" + b[index][j] + "" + b[index][k];
    }
}