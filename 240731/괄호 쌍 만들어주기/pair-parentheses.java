import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        // 글자 수 만큼 배열을 생성한다.
        // 문자의 뒤에서부터 )) 으로 구성된 개수를 구한다.
        // )((()())()) 의 경우에는 22222221110 식이다. 그래서 앞에서부터 조사하면서 (( 끝나는 지점에 뒤에 몇개가 있는지 카운트한다.

        int n = str.length();
        int[] arr = new int[n];

        char beforeChar = str.charAt(n-1);
        
        for(int i = n-2; i > 0; i--) {
            // ) 로 연속하고 있다는 의미이다.
            if(beforeChar == str.charAt(i) && str.charAt(i) == ')') {
                arr[i] = arr[i+1] + 1;
            } else {
                arr[i] = arr[i+1];
            }
            // 이전 글자는 갱신해준다.
            beforeChar = str.charAt(i);
        }

        int result = 0;
        beforeChar = str.charAt(0);
        for(int i = 1; i < n; i++) {
            if(beforeChar == str.charAt(i) && str.charAt(i) == '(') {
                result += arr[i];
            }
            beforeChar = str.charAt(i);
        }
        System.out.print(result);


    }
}