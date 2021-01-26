package Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*n 皇后问题 研究的是如何将 n 个皇后放置在 n×n 的棋盘上，
并且使皇后彼此之间不能相互攻击。*/
public class NQueen {
    //personal solution,backtrace + path + parse path
    public List<List<String>> solveNQueens(int n) {
        int[][] chessboard = new int[n][n];
        List<List<String>> res = new ArrayList<>();
        List<Integer> solution = new ArrayList<>();
        backtrace(chessboard, res,solution, 0, n);
        return res;
    }

    private void backtrace(int[][] chessboard,
                           List<List<String>> res,
                           List<Integer> solution,
                           int depth, int n) {
        if (depth == n) {
            res.add(parseSolution(solution,n));
            return;
        }
        int[] line=chessboard[depth];
        for (int i = 0; i < n; i++) {
            if(line[i]==0){
                solution.add(i);
                choose(chessboard,depth,i,n);
                backtrace(chessboard,res,solution,depth+1,n);
                solution.remove(solution.size()-1);
                undo(chessboard,depth,i,n);
            }
        }
    }

    private void choose(int[][] chessboard, int x, int y,int n) {
        for(int i=1;i+x<n;i++){
            chessboard[i+x][y]++;
            if(y+i<n)   chessboard[i+x][y+i]++;
            if(y-i>=0)  chessboard[i+x][y-i]++;
        }
    }

    private void undo(int[][] chessboard, int x, int y, int n) {
        for(int i=1;i+x<n;i++){
            chessboard[i+x][y]--;
            if(y+i<n)   chessboard[i+x][y+i]--;
            if(y-i>=0)  chessboard[i+x][y-i]--;
        }
    }

    private List<String> parseSolution(List<Integer> solution, int n) {
        List<String> res = new ArrayList<>();
        for (Integer index : solution) {
            StringBuilder tmp=new StringBuilder();
            tmp.append(".".repeat(index)).append("Q")
                    .append(".".repeat(n-1-index));
            res.add(tmp.toString());
        }
        return res;
    }

    //better solution, use chessboard itself to record solution
    public List<List<String>> solveNQueens2(int n){
        char[][] chessboard=new char[n][n];
        List<List<String>> solutions=new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                chessboard[i][j]='.';
            }
        }
        backtrace2(chessboard,0,solutions);
        return solutions;
    }

    private void backtrace2(char[][] chessboard, int row, List<List<String>> solutions) {
        if(row==chessboard.length){
            solutions.add(board2Solution(chessboard));
        }
        for (int i = 0; i < chessboard.length; i++) {
            if(valid(chessboard,row,i)){
                chessboard[row][i]='Q';
                backtrace2(chessboard, row+1, solutions);
                chessboard[row][i]='.';
            }
        }
    }

    //valid if (row,line) can be placed with queen,and the method is very straightforward
    private boolean valid(char[][] chessboard, int row, int line) {
        //check above
        for (int i = 0; i < row; i++) {
            if(chessboard[i][line]=='Q')  return false;
        }
        //check left diagonal
        for(int i=row-1,j=line-1;i>=0&&j>=0;i--,j--){
            if(chessboard[i][j]=='Q')   return false;
        }
        //check right diagonal
        for(int i=row-1,j=line+1;i>=0&&j<chessboard.length;i--,j++){
            if(chessboard[i][j]=='Q')   return false;
        }
        return true;
    }

    private List<String> board2Solution(char[][] chessboard) {
        List<String> solution=new ArrayList<>();
        for (char[] chars : chessboard) {
            solution.add(new String(chars));
        }
        return solution;
    }
}
