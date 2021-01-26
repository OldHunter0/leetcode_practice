package Array;

import java.util.LinkedList;
import java.util.Queue;

class MyStack {
    Queue<Integer> queue;
    /** Initialize your data structure here. */
    public MyStack() {
        queue=new LinkedList<>();
    }

    /** Push element x onto stack. */
    public void push(int x) {
        queue.offer(x);
    }

    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        Queue<Integer> newQueue=new LinkedList<>();
        while (queue.size()>1){
            newQueue.offer(queue.poll());
        }
        int res=queue.poll();
        queue=newQueue;
        return res;
    }

    /** Get the top element. */
    public int top() {
        int res = pop();
        queue.add(res);
        return res;
    }

    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.isEmpty();
    }
}
