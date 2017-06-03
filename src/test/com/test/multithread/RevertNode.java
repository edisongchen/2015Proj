package com.test.multithread;

/**
 * Created by ctg on 2017/2/16.
 */
public class RevertNode {
    public static Node init(int num){
        Node node = new Node(0,null);
        Node cur = null;//当前节点
        Node tmp = null;//临时节点
        for(int i=1;i<num;i++){
            tmp = new Node(i,null);
            if(i == 1){
                node.next=tmp;
            }else{
                cur.next=tmp;
            }
            cur=tmp;

        }
        return node;
    }

    public static void out(Node head){
        Node tmp = head;
        while (tmp !=null){
            System.out.print(tmp.value);
            tmp=tmp.next;
        }
    }

    public static Node revert(Node node){
        if(node==null){
            return node;
        }
        Node pre = node;
        Node cur=node.next;
        Node next=null;//a,b,c
        while(cur !=null){
            next = cur.next;

            cur.next=pre;
            pre=cur;
            cur=next;
        }
        node.next=null;
        node=pre;
        return node;
    }

    public static Node reverseByRecur(Node current){
        if (current == null || current.next == null)
            return current;
        Node reverseRest = reverseByRecur(current.next);
        current.next.next=current;
        current.next=null;

        return reverseRest;
    }

    public static void main(String args[]){
        Node node = init(10);
        out(node);
        System.out.println("////");
        Node reverR=reverseByRecur(node);
        Node revert = revert(node);
        out(revert);
    }
}

class Node{
    int value;
    Node next;

    public Node(int value, Node next) {
        this.value = value;
        this.next = next;
    }
}