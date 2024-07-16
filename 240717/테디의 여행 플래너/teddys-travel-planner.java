import java.io.*;
import java.util.*;

class Node {
    String name;
    Node next;
    Node prev;

    public Node(String name) {
        this.name = name;
    }

    public String printName() {
        if((next == null && prev == null) || (next.name == prev.name)) {
            return "-1";
        } else {
            return prev.name + " " + next.name;
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());

        
        st = new StringTokenizer(br.readLine());

        Node pin = new Node(st.nextToken());
        Node curr = pin;
        for(int i = 1; i < n; i++) {
            String name = st.nextToken();
            Node newNode = new Node(name);
            curr.next = newNode;
            newNode.prev = curr;

            curr = newNode;
        }

        pin.prev = curr;
        curr.next = pin;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());

            if(num == 1) {
                if(pin.next != null) {
                    pin = pin.next;
                }
            } else if(num == 2) {
                if(pin.prev != null) {
                    pin = pin.prev;
                }
            } else if(num == 3) {
                Node pinNext = pin.next;
                if(pinNext != null) {
                    pin.next = pinNext.next;
                    pinNext.prev = pin;

                    pinNext.prev = null;
                    pinNext.next = null;
                }

            } else {
                String newName = st.nextToken();

                Node pinNext = pin.next;
                Node newNode = new Node(newName);
                if(pinNext != null) {
                    pinNext.prev= newNode;
                    newNode.next = pinNext;
                    newNode.prev = pin;
                    pin.next = newNode;

                } else {
                    pin.next = newNode;
                    newNode.prev = pin;
                }
            }

            sb.append(pin.printName()+"\n");
            
        }

        System.out.println(sb);

    }
}