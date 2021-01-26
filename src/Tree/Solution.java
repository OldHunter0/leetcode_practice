package Tree;

import LinkedList.ListNode;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        int left = countDepth(root.left);
        int right = countDepth(root.right);
        if (left == right) return (int) Math.pow(2, left) + countNodes(root.right);
        else return (int) Math.pow(2, right) + countNodes(root.left);
    }

    private int countDepth(TreeNode root) {
        if (root == null) return 0;
        else return countDepth(root.left) + 1;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        ArrayList<TreeNode> level = new ArrayList<>();
        level.add(root);
        boolean left2right = true;
        while (!level.isEmpty()) {
            List<Integer> cur = new ArrayList<>();
            if (left2right) {
                for (int i = 0; i < level.size(); i++) {
                    cur.add(level.get(i).val);
                }
            } else {
                for (int i = level.size() - 1; i >= 0; i--) {
                    cur.add(level.get(i).val);
                }
            }
            res.add(cur);
            ArrayList<TreeNode> nextLevel = new ArrayList<>();
            for (TreeNode node : level) {
                if (node.left != null) nextLevel.add(node.left);
                if (node.right != null) nextLevel.add(node.right);
            }
            level = nextLevel;
            left2right = !left2right;
        }
        return res;
    }

    public static TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;
        if (head.next == null) return new TreeNode(head.val);
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.next.val);
        root.right = sortedListToBST(slow.next.next);
        slow.next = null;
        root.right = sortedListToBST(head);
        return root;
    }

    ListNode globalHead;

    public TreeNode sortedListToBST2(ListNode head) {
        globalHead = head;
        ListNode p = head;
        int n = 0;    //length of linked list
        while (p != null) {
            n++;
            p = p.next;
        }
        return buildBST(0, n - 1);
    }

    private TreeNode buildBST(int start, int end) {
        if (start > end) return null;
        int mid = (start + end + 1) >> 2;
        TreeNode root = new TreeNode(0);
        root.left = buildBST(start, mid - 1);
        root.val = globalHead.val;
        globalHead = globalHead.next;
        root.right = buildBST(mid + 1, end);
        return root;
    }

    int K = 0;
    int num = -1;

    public int kthSmallest(TreeNode root, int k) {
        dfs(root, k);
        return num;
    }

    private void dfs(TreeNode root, int k) {
        if (root == null) {
            return;
        }
        dfs(root.left, k);
        K++;
        if (K == k) {
            num = root.val;
            return;
        } else if (K > k) return;
        dfs(root.right, k);
    }

    public int monotoneIncreasingDigits(int N) {
        if(N<10) return N;
        int originN=N;
        List<Integer> digits = new ArrayList<>();
        while (N > 0) {
            digits.add(N % 10);
            N /= 10;
        }
        int i;
        for (i = digits.size() - 1; i >= 1; i--) {
            if (digits.get(i) > digits.get(i - 1)) break;
        }
        if(i==0)  return originN;
        int res=0;
        for(int j=digits.size()-1;j>i;j--){
            res*=10;
            res+=digits.get(j);
        }
        res=res*10+digits.get(i)-1;
        for(int j=i-1;j>=0;j--){
            res*=10;
            res+=9;
        }
        return res;
    }


    public static void main(String[] args) {
        int[] list = {-10, -3, 0, 5, 9};
        ListNode dummy = new ListNode(0);
        ListNode p = dummy;
        for (int j : list) {
            p.next = new ListNode(j);
            p = p.next;
        }
        sortedListToBST(dummy.next);
    }
}
