package Array;

import java.lang.reflect.Array;
import java.util.*;

public class Solution {

    //my original solution is stupid!
    public int removeDuplicates(int[] nums) {
        int len = nums.length;
        if (len < 3) return len;
        int left = 0;
        int right = len - 1;
        int count = 0;
        while (left <= right) {
            if (left == 0 || nums[left - 1] == nums[left]) count++;
            else count = 1;
            if (count >= 3) {
                exchange(nums, left, right--);
                int p = left;
                while (p < right && nums[p] > nums[p + 1]) {
                    exchange(nums, p, p + 1);
                    p++;
                }
            } else left++;
        }
        return right + 1;
    }

    public int removeDuplicates2(int[] nums) {
        int i = 0;
        for (int num : nums) {
            if (i < 2 || num > nums[i - 2]) {
                nums[i] = num;
                i++;
            }
        }
        return i;
    }

    private void exchange(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    public int findMin(int[] nums) {
        int len = nums.length;
        if (nums[0] <= nums[len - 1]) return nums[0];
        int left = 0, right = len - 1;
        while (left < right) {
            int mid = (left + right) >> 1;
            if (nums[mid] >= nums[0]) {
                left = mid;
            } else if (nums[mid] < nums[0]) {
                if (nums[mid] < nums[mid - 1]) {
                    return nums[mid];
                } else {
                    right = mid;
                }
            }
        }
        return left;
    }

    public static int[][] kClosest(int[][] points, int K) {
        int n = points.length;
        if (K >= n) return points;
        int[][] res = new int[K][2];
        PriorityQueue<int[]> pq = new PriorityQueue<>((p1, p2) ->
                p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
        for (int[] point : points) {
            if (pq.size() < K) {
                pq.offer(point);
            } else if (pq.comparator().compare(point, pq.peek()) > 0) {
                pq.poll();
                pq.offer(point);
            }
        }
        for (int i = 0; i < K; i++) {
            res[i] = pq.poll();
        }
        return res;
    }

    public int[][] kClosest2(int[][] points, int K) {
        return quickSelect(points, 0, points.length - 1, K - 1);
    }

    private int[][] quickSelect(int[][] points, int lo, int hi, int idx) {
        if (lo > hi) return new int[0][0];
        int j = partition(points, lo, hi);
        if (j == idx) return Arrays.copyOf(points, j + 1);
        else return j > idx ? quickSelect(points, lo, j - 1, idx) : quickSelect(points, j + 1, hi, idx);
    }

    private int partition(int[][] points, int lo, int hi) {
        int[] v = points[lo];
        int cmp = v[0] * v[0] + v[1] * v[1];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && points[i][0] * points[i][0] + points[i][1] * points[i][1] <= cmp) ;
            while (--j > lo && points[j][0] * points[j][0] + points[j][1] * points[j][1] > cmp) ;
            if (i > j) break;
            int[] tmp = points[j];
            points[j] = points[i];
            points[i] = tmp;
        }
        points[lo] = points[j];
        points[j] = v;
        return j;
    }

    public static int wiggleMaxLength(int[] nums) {
        int n = nums.length;
        if (n < 2) return n;
        int[] dp = new int[n];
        boolean[] isPositive = new boolean[n];
        dp[0] = 1;
        dp[1] = nums[0] == nums[1] ? 1 : 2;
        int max = dp[1];
        isPositive[1] = (nums[1] - nums[0] > 0);
        for (int i = 2; i < n; i++) {
            int j;
            for (j = i - 1; j > 0; j--) {
                if (((nums[i] - nums[j] > 0) ^ isPositive[j]) && nums[i] != nums[j]) break;
            }
            if (j > 0) {
                dp[i] = dp[j] + 1;
            } else {
                dp[i] = nums[i] == nums[0] ? 1 : 2;
            }
            isPositive[i] = (nums[i] - nums[j] > 0);
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    //o(n)的动态规划解法，十分巧妙
    public static int wiggleMaxLength2(int[] nums) {
        int n = nums.length;
        int up = 1;
        int down = 1;
        for (int i = 2; i < n; i++) {
            if (nums[i] > nums[i - 1]) {
                up = down + 1;
            } else if (nums[i] < nums[i - 1]) {
                down = up + 1;
            }
        }
        return Math.max(up, down);
    }


    public int lengthOfLongestSubstring(String s) {
        int len = s.length();
        int left = 0;
        int right = 0;
        int max = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        while (right < len) {
            char c = s.charAt(right);
            if (map.containsKey(c)) {
                left = Math.max(left, map.get(c) + 1);
            }
            map.put(c, right);
            max = Math.max(max, right - left + 1);
            right++;
        }
        return max;
    }

    public boolean isValid(String s) {
        int len = s.length();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(')');
            else if (c == '[') stack.push(']');
            else if (c == '{') stack.push('}');
            else if (stack.isEmpty() || stack.pop() != c) return false;
        }
        return stack.isEmpty();
    }

    public int eraseOverlapIntervals(int[][] intervals) {
        int n = intervals.length;
        if (n <= 1) return 0;
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int remain = 1;
        int end = intervals[0][1];
        int i = 1;
        while (i < n) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                remain++;
            }
            i++;
        }
        return n - remain;
    }


    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int len = s.length();
        UnionFind uf = new UnionFind(len);
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        char[] res = new char[len];
        HashMap<Integer, List<Integer>> groups = new HashMap<>(uf.size);
        for (int i = 0; i < len; i++) {
            int root = uf.find(i);
            if (groups.containsKey(root)) {
                groups.get(root).add(i);
            } else {
                ArrayList<Integer> list = new ArrayList<>();
                list.add(i);
                groups.put(root, list);
            }
        }
        for (List<Integer> indexes : groups.values()) {
            indexes.sort((a, b) -> a - b);
            int size = indexes.size();
            char[] chars = new char[size];
            for (int i = 0; i < size; i++) {
                chars[i] = s.charAt(indexes.get(i));
            }
            Arrays.sort(chars);
            for (int i = 0; i < size; i++) {
                res[indexes.get(i)] = chars[i];
            }
        }
        return new String(res);
    }

    public static class UnionFind {
        int size;
        int[] root;

        public UnionFind(int size) {
            this.size = size;
            root = new int[size];
            for (int i = 0; i < size; i++) {
                root[i] = i;
            }
        }

        public int find(int i) {
            if (root[i] == i) return i;
            else return root[i] = find(root[i]);
        }

        public void union(int i, int j) {
            int root_i = find(i);
            int root_j = find(j);
            if (root_i != root_j) {
                size--;
                root[root_i] = root_j;
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        int len = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len < 3) return new ArrayList<>(res);
        for (int i = 0; i < len - 2 && nums[i] <= 0; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int left = i + 1;
            int right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) left++;
                else if (sum > 0) right--;
                else {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[++left] == nums[left - 1]) ;
                    right--;
                }
            }
            ;
        }
        return res;
    }


    public void nextPermutation(int[] nums) {
        int len = nums.length;
        int start = len - 2;
        while (start >= 0 && nums[start] >= nums[start + 1]) start--;
        if (start >= 0) {
            int j = len - 1;
            while (j > start + 1 && nums[j] <= nums[start]) j--;
            swap(nums, start, j);
        }
        reverse(nums, start + 1);
    }

    //exchange two numbers in an array
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    // reverse an array from start index(inclusive)
    private void reverse(int[] nums, int start) {
        int left = start;
        int right = nums.length - 1;
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }

    public int maxValue(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        for (int i = 1; i < n; i++) {
            grid[0][i] += grid[0][i - 1];
        }
        for (int i = 1; i < m; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[m - 1][n - 1];
    }

    //原地哈希
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        int i = 0;
        while (i < n) {
            if (nums[i] != i + 1) {
                if (nums[i] > 0 && nums[i] <= n) {
                    if (nums[nums[i] - 1] == nums[i]) i++;
                    else swap(nums, i, nums[i] - 1);
                } else i++;
            } else {
                i++;
            }
        }
        for (int j = 0; j < n; j++) {
            if (nums[j] != j + 1) return j + 1;
        }
        return n + 1;
    }




    public static void main(String[] args) {
        wiggleMaxLength(new int[]{486, 431, 132, 46, 441, 383, 199, 476, 87, 225, 491, 3, 315, 32, 441, 195, 188,
                72, 299, 404, 224, 473, 124, 279, 301, 145, 429, 77, 423, 472, 388, 387, 29, 348, 22, 327, 276, 448,
                396, 269, 382, 436, 382, 160, 156, 34, 303, 264, 271, 409});
    }
}
