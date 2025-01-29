import java.util.*;
class Node{
    int data;
    Node next;
    public Node(int data){
        this.data=data;
        this.next=null;
    }
}
 class Stack{
    private Node top;
    
    public Stack(){
        top = null;
    }
    public void push(int data){
        Node newNode = new Node(data);
        newNode.next=top;
        top = newNode;
        System.out.print(data+" ");
    }
    public boolean isEmpty(){
        return top==null;
    }
    public int peek(){
        if(isEmpty()){
            System.out.println("Stack is Empty");
            return -1;
        }
        return top.data;
    }
    public int pop(){
        if(isEmpty()){
            System.out.println("Stack is Empty");
            return -1;
        }
        int popElement = top.data;
        top = top.next;
        return popElement;
    }
    public void display(){
        if(isEmpty()){
            System.out.println("Stack is Empty");
        }
        else{
            Node curr = top;
            System.out.println("Stack elements:");
            while(curr!=null){
                System.out.print(curr.data+ "->");
                curr = curr.next;
            }
            System.out.print("null");
        }
    }
}
public class StackPractice
{
	public static void main(String[] args) {
	    Stack stack = new Stack();
	    stack.push(10);
	    stack.push(20);
	    stack.push(30);
	    
	    System.out.println("Top Element is "+ stack.peek());
	    stack.pop();
	    stack.display();
	    
	    stack.pop();
	    stack.pop();
	}
}