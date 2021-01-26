package String;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class Solution {
    public String simplifyPath(String path) {
        String[] raw_folders=path.split("/");
        Stack<String> folders=new Stack<>();
        for (String raw_folder : raw_folders) {
            if (raw_folder.equals("..")) {
                if (!folders.isEmpty()) {
                    folders.pop();
                }
            } else if ((!raw_folder.equals("."))&&(!raw_folder.equals(""))) {
                folders.push(raw_folder);
            }
        }
        if(folders.isEmpty())   return "/";
        StringBuilder res=new StringBuilder();
        for (String folder : folders) {
            res.append("/").append(folder);
        }
        return res.toString();
    }

}
