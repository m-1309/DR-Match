package ischemic_filter;

import java.io.*;


import java.util.Scanner;

public class LinkedList
{
	public int info;
	public LinkedList front;
	public LinkedList rear;
	public LinkedList link;
	public LinkedList()
	{
		//front=null;
	}
	public LinkedList(int data)
	{
		info=data;
		link=null;
	}
public void QInsert(int item)  //insertion at end
{
	LinkedList temp = new LinkedList(item);
	
	
	if(temp==null)
		System.out.println("Overflow");
	else
		{
		if (front==null)
			front=rear=temp;
		else
		{
			
			rear.link=temp;
			rear=temp;
		}
		
		}
}
public void st_insert(int value) //insertion at front
{
	LinkedList temp = new LinkedList(value);
	if (temp == null)
		System.out.println("Insertion not possible");
	else
	{
		temp.link = front;
		front = temp;
		
	}
}
public  void delete_v(int v1) //delete the given value
{
	LinkedList pt = front;
	LinkedList save = null;
	if(v1==front.info)
	{
		front=front.link;
		//count--;
		return;
	}
	while(pt!=null)
	{
		if(v1==pt.info)
		{
			//count--;
			save.link=pt.link;
			break;
		}
		save=pt;
		pt=pt.link;
	}
	if(pt==null)
		System.out.println("Given value is not present in the list");
	
	
}
public void QDelete()
{
	if(front==null)
		System.out.println("Underflow");
	else
	{
		front = front.link;
		
		
	}
	
	
	
}
public  void display()
{
	
		if(front==null)
		{
			System.out.println("no element");
		}
		else
		{
		LinkedList ptr = front;
		while(ptr!=null)
		{
			System.out.println(ptr.info);
			ptr = ptr.link;
		}
		}
	
}
public static void main(String args[])
{
	LinkedList ob = new LinkedList();
	ob.QInsert(40);
	ob.QInsert(80);
	ob.display();
	//ob.QDelete();
	ob.st_insert(20);
	ob.display();
}
}

/*
class Node {

    String Rec_id;
    Node next;

    // Constructor
    Node(String rec_id)
    {
        Rec_id = rec_id;
        next = null;
    }
}

public class LinkedList {

 Node head; 

 
 public static void insert(LinkedList list, String rec_id)
 {
    
     Node new_node = new Node(rec_id);
     
     if (list.head == null) {
         list.head = new_node;
     }
     else {
         
         Node last = list.head;
         while (last.next != null) {
             last = last.next;
         }

    
         last.next = new_node;
     }

     System.out.println("LinkedList");
     //return list;
 } 
 
 public static LinkedList deleteByKey(LinkedList list,
         String key)
{
// Store head node
Node currNode = list.head, prev = null;



if (currNode != null && currNode.Rec_id.equals(key)) {
list.head = currNode.next; // Changed head
System.out.println(key + " found and deleted !");
return list;
}

while (currNode != null && !(currNode.Rec_id.equals(key))) {
prev = currNode;
currNode = currNode.next;
}

if (currNode != null) {
prev.next = currNode.next;
System.out.println(key + " found and deleted");
}
if (currNode == null) {
System.out.println(key + " not found");
}
return list;
}
 }
*/