import java.util.*;
class Node{
    int data;
    Node next;
    public Node(int data){
        this.data=data;
        this.next=null;
    }
}

public class LinkedListPractice
{
    Node head;
    public void display(){
        Node curr = head;
        while(curr!=null){
            System.out.print(curr.data+"->");
            curr = curr.next;
        }
        System.out.println("null");
    }
    public void insert(int data){
        Node newNode = new Node(data);
        if(head==null){
            head=newNode;
        }
        else{
            Node curr = head;
            while(curr.next!=null){
                curr = curr.next;
            }
            curr.next = newNode;
        }
    }
    public void deleteAtFirst(){
        if(head!=null){
            head = head.next;
        }
    }
    public void deleteAtLast(){
        Node curr = head;
        while(curr.next.next!=null){
            curr=curr.next;
        }
        curr.next=null;
    }
	public static void main(String[] args) {
	    LinkedListPractice list = new LinkedListPractice();
	    
	    list.insert(10);
	    list.insert(20);
	    list.insert(30);
	    
	    list.display();
	    list.deleteAtFirst();
	    
	    
	    System.out.println("After deleting First Element");
	   
	    list.display();
	    
	     list.deleteAtLast();
	     System.out.println("After deleting Last Element");
	     list.display();
	   
	}
}