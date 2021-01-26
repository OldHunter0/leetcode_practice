import java.util.ArrayDeque;
import java.util.Deque;

class MaxQueue {
    Deque<Integer> queue;
    Deque<Integer> assist;
    public MaxQueue() {
        queue=new ArrayDeque<>();
        assist=new ArrayDeque<>();
    }

    public int max_value() {
        if(assist.isEmpty()) return -1;
        else return assist.peek();
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!assist.isEmpty()&&assist.peekLast()<value){
            assist.pollLast();
        }
        assist.offerLast(value);
    }

    public int pop_front() {
        if(queue.isEmpty()) return -1;
        Integer cur = queue.poll();
        if(cur.equals(assist.peek())){
            assist.pollFirst();
        }
        return cur;
    }
}

/**
 * Your MaxQueue object will be instantiated and called as such:
 * MaxQueue obj = new MaxQueue();
 * int param_1 = obj.max_value();
 * obj.push_back(value);
 * int param_3 = obj.pop_front();
 */
