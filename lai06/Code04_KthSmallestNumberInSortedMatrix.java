package lai06;
/*
[Question]
    find kth smallest number in a row ascending order and column ascending order matrix
[Idea]
    use a min heap to collect available path, every time we poll the top, collect its right and down side element if it is not
    out of boundary and visited
[Notice]
    int[] use .length
    how to implement a heap
    don't forget "new" when create a class
[Complexity]
    Time:  KlogK, we put k element into a heap
    Space: mn + K, maintain a visited matrix and a k size heap
*/

import java.util.Comparator;
import java.util.PriorityQueue;

public class Code04_KthSmallestNumberInSortedMatrix {

    public static int kthSmallest(int[][] matrix, int k) {

        int rows = matrix.length;
        int cols = matrix[0].length;
        boolean[][] visited = new boolean[rows][cols];
        PriorityQueue<Cell> heap = new PriorityQueue<Cell>(new Comparator<Cell>() {
            @Override
            public int compare(Cell o1, Cell o2) {
                return o1.value - o2.value;
            }
        });
        heap.offer(new Cell(0, 0, matrix[0][0]));
        visited[0][0] = true;
        for(int i = 0; i < k - 1; i++) {
            Cell cur = heap.poll();
            if (cur.row + 1 < rows && visited[cur.row + 1][cur.col] == false) {
                heap.offer(new Cell(cur.row + 1, cur.col, matrix[cur.row + 1][cur.col]));
                visited[cur.row + 1][cur.col] = true;
            }
            if (cur.col + 1 < cols && visited[cur.row][cur.col + 1] == false) {
                heap.offer(new Cell(cur.row, cur.col + 1, matrix[cur.row][cur.col + 1]));
                visited[cur.row][cur.col + 1] = true;
            }
        }
        return heap.peek().value;
    }

    public static class Cell {
        int row;
        int col;
        int value;

        public Cell(int row, int col, int value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }
    }


    public static void main(String[] args) {

        int[][] matrix = {{1, 5, 8, 12},
                          {2, 6, 9, 13},
                          {4, 8, 11, 15}};
        int k = 5;

        // 6
        System.out.println(kthSmallest(matrix, k));
    }


}
