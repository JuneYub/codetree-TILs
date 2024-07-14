import java.io.*;
import java.util.*;

class Node {
    String data;
    Node prev, next;

    public Node(String data) {
        this.data = data;
        this.prev = null;
        this.next = null;
    }

    public String getPrev() {
        if(this.prev == null) {
            return "(Null)";
        } else {
            return this.prev.data;
        }
    }

    public String getNext() {
        if(this.next == null) {
            return "(Null)";
        } else {
            return this.next.data;
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        String value = br.readLine();
        Node curr = new Node(value);

        int n = Integer.parseInt(br.readLine());

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if(num == 1) {
                String str = st.nextToken();
                Node newNode = new Node(str);
                Node tmp = curr.prev;

                if(tmp != null) {
                    tmp.next = newNode;
                    newNode.prev = tmp;
                    newNode.next = curr;
                    curr.prev = newNode;
                } else {
                    curr.prev = newNode;
                }

            } else if(num == 2) {
                String str = st.nextToken();
                Node newNode = new Node(str);
                Node tmp = curr.next;

                if(tmp != null) {
                    tmp.prev = newNode;
                    newNode.next = tmp;
                    curr.next = newNode;
                    newNode.prev = curr;
                } else {
                    curr.next = newNode;
                }



            } else if(num == 3) {
                if(curr.prev != null) {
                    curr = curr.prev;
                }

            } else if(num == 4) {
                if(curr.next != null) {
                    curr = curr.next;
                }

            }

            sb.append(curr.getPrev() + " " + curr.data + " " + curr.getNext() + "\n");

        }
        System.out.print(sb);
    }
}