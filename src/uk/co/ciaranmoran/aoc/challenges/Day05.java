package uk.co.ciaranmoran.aoc.challenges;
import java.util.Map;
import java.util.HashMap;

public class Day05 extends Challenge {
    public Day05() {
        super("05");
    }
    private class Node <T> {
        T value;
        Node<T> next;
        Node(T value) {
            this.value = value;
            this.next = null;
        }
    }
    private class LinkedList<T> {
        int size;
        Node<T> head;
        Node<T> tail;
        LinkedList () {
            this.size = 0;
            this.head = null;
            this.tail = null;
        }

         void add(T value){
            size++;
            if(head == null) {
                this.head = new Node<>(value);
                this.tail = this.head;
            } else {
                this.tail.next = new Node<>(value);
                this.tail = this.tail.next;
            }
        }

        @Override
        public String toString(){
            StringBuilder s = new StringBuilder();
            Node<T> current = this.head;
            while(current.next != null){
                s.append(current.value);
                current = current.next;
            }
            s.append(current.value);
            return s.toString();
        }
    }

    @Override
    String partA() {
        LinkedList<Character> list = new LinkedList<>();
        for(int i = 0; i < input.get(0).length(); i++){
            list.add(input.get(0).charAt(i));
        }
        return "" + reduce(list).size;
    }

    @Override
    String partB() {
        Map<Character,Integer> sizes = new HashMap<>();

        for(char alphabet = 'a'; alphabet <='z'; alphabet++ ) {
            System.out.println(alphabet);
            LinkedList<Character> list = new LinkedList<>();
            for(int i = 0; i < input.get(0).length(); i++){
                char c = input.get(0).charAt(i);
                if (Character.toLowerCase(c) != alphabet){
                    list.add(c);
                }
            }
            sizes.put(alphabet, reduce(list).size);
        }
        int min = Integer.MAX_VALUE;
        for(char key : sizes.keySet()){
            if (sizes.get(key) < min) {
                min = sizes.get(key);
            }
        }
        return "" + min;
    }

    private LinkedList<Character> reduce(LinkedList<Character> list) {
        Node<Character> current;
        int lastSize;
        do{
            lastSize = list.size;
            current = list.head;
            while (current.next != null && current.next.next != null) {
                char a = current.next.value;
                char b = current.next.next.value;
                if (Character.toUpperCase(a) == Character.toUpperCase(b) && a != b) {
                    current.next = current.next.next.next;
                    list.size -= 2;
                    break;
                }else{
                    current = current.next;
                }

            }
        }while(list.size != lastSize);
        if (Character.toUpperCase(list.head.value) == Character.toUpperCase(list.head.next.value) && list.head.value != list.head.next.value) {
            list.head = list.head.next.next;
            list.size -= 2;
        }
        return list;
    }

}
