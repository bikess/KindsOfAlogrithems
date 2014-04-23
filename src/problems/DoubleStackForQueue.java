package problems;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/***
 * 本类是用两个栈来实现队列，队列是先进先出，后进后出，而栈是先进后出
 * 
 * 1. 对于push方法的实现，我们只压入到栈1中去
 * @author bike
 *
 */
class Stack2Queue{
	private Stack<Object> stack1 = new Stack<>();
	private Stack<Object> stack2 = new Stack<>();
	private Lock lock1 = new ReentrantLock();
	private Lock lock2 = new ReentrantLock();
//	记录当前队列中的元素数
	private int num=0;
	
//	因为如队列的操作只在栈1上进行，因此只要对栈1进行加锁就可以
	public void push(Object o){
		lock1.lock();
		stack1.push(o);
		num++;
		lock1.unlock();
	}
	public Object get(){
		lock2.lock();
		if(stack2==null){
			lock1.lock();
			while(!stack1.isEmpty()){
				stack2.push(stack1.pop());
			}
			lock1.unlock();
		}
		if(stack2!=null){
			num--;
			return stack2.pop();
		}
		lock2.unlock();
		return null;
	}
	
	public Object front(){
		if(stack2==null){
			while(!stack1.isEmpty()){
				stack2.push(stack1.pop());
			}
		}
		if(stack2!=null){
			return stack2.peek();
		}
		return null;
	}
	public boolean isEmpty(){
		if(stack1.isEmpty()&&stack2.isEmpty()){
			return true;
		}
		else{
			return false;
		}
	}
}
public class DoubleStackForQueue {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Queue<Object> q = new ArrayDeque<Object>();
		
	}

}
