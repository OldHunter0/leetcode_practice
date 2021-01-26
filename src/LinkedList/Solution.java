package LinkedList;

public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if(head==null)  return null;
        ListNode dummy= new ListNode(-1);
        dummy.next=head;
        ListNode prev=head;
        while(prev.next!=null){
            ListNode cur=prev.next;
            if(prev.val<=cur.val){
                prev=cur;
                continue;
            }
            prev.next=cur.next;
            ListNode p=dummy;
            while(p!=prev){
                if(p.next.val>=cur.val){
                    cur.next=p.next;
                    p.next=cur;
                    break;
                }
                p=p.next;
            }
        }
        return dummy.next;
    }


    public int findCircleNum(int[][] M) {
        int n=M.length;
        int cnt=0;
        boolean[] visit=new boolean[n];
        for (int i = 0; i < n; i++) {
            if(!visit[i]){
                cnt++;
                dfs(M,i,visit);
            }
        }
        return cnt;
    }

    private void dfs(int[][] M, int i, boolean[] visit) {
        visit[i]=true;
        for(int j=i+1;j<M.length;j++){
            if((!visit[j])&&M[i][j]==1){
                dfs(M,j, visit);
            }
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode resHead=new ListNode(-1);
        ListNode p=resHead;
        int ans=0;
        while(l1!=null||l2!=null){
            p.next=new ListNode(0);
            if(l1!=null) {
                ans += l1.val;
                l1=l1.next;
            }
            if(l2!=null) {
                ans += l2.val;
                l2=l2.next;
            }
            p.next.val+=ans%10;
            ans=ans/10;
            p=p.next;
        }
        if(ans!=0)  p.next=new ListNode(ans);
        return resHead.next;
    }


}
