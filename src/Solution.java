import javax.print.DocFlavor;
import java.lang.reflect.Array;
import java.util.*;

public class Solution {
    boolean fail=false;
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        boolean[] visited=new boolean[numCourses];
        for (int[] prerequisite : prerequisites) {
            if(!map.containsKey(prerequisite[0]))   map.put(prerequisite[0],new ArrayList<>());
            map.get(prerequisite[0]).add(prerequisite[1]);
        }
        for (int i = 0; i < numCourses; i++) {
            if((!visited[i])&&map.containsKey(i)){
                HashSet<Integer> path = new HashSet<>();
                dfs(i,map,path,visited);
                if(fail)    return false;
                for (Integer course : path) {
                    visited[course]=true;
                }
            }
        }
        return true;
    }

    private void dfs(int cur, HashMap<Integer, ArrayList<Integer>> map, HashSet<Integer> path, boolean[] visited) {
        if(fail)    return;
        if(path.contains(cur))    {
            fail=true;
            return;
        }
        path.add(cur);
        if(map.containsKey(cur)){
            ArrayList<Integer> preCourses = map.get(cur);
            for (Integer preCourse : preCourses) {
                if(!visited[preCourse]){
                    dfs(preCourse,map,path,visited);
                }
            }
        }
    }

    public int searchInsert(int[] nums, int target) {
        int n=nums.length;
        for(int i=0;i<n;i++){
            if(nums[i]>=target) return i;
        }
        return n;
    }

    public List<String> restoreIpAddresses(String s) {
        int len = s.length();
        List<String> res=new ArrayList<>();
        if(len<4||len>12)   return res;
        backtrace(s,0,4,new ArrayList<>(),res);
        return res;
    }

    private void backtrace(String s, int begin, int remain, List<Integer> path, List<String> res){
        if(begin>s.length()-1)  return;
        if(remain==1){
            if(s.charAt(begin)=='0'&&s.length()-begin>1){
                return;
            }
            int cur = Integer.parseInt(s.substring(begin));
            if(cur <256){
               path.add(cur);
                StringBuffer stringBuffer=new StringBuffer();
                for (Integer num : path) {
                    stringBuffer.append(num).append('.');
                }
                stringBuffer.deleteCharAt(stringBuffer.length()-1);
               res.add(stringBuffer.toString());
               path.remove(path.size()-1);
            }else {
                return;
            }
        }
        if(s.charAt(begin)=='0'){
            path.add(0);
            backtrace(s,begin+1,remain-1,path,res);
            path.remove(path.size()-1);
        }else {
            for (int i = 1; i <= 3; i++) {
                if(s.length()-begin>=i){
                    int nextNum = Integer.parseInt(s.substring(begin, begin + i));
                    if(nextNum<256){
                        path.add(nextNum);
                        backtrace(s,begin+i,remain-1,path,res);
                        path.remove(path.size()-1);
                    }
                }
            }
        }
    }


}
