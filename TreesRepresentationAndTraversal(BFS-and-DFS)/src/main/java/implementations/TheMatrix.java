package implementations;

import java.util.ArrayDeque;
import java.util.Deque;

public class TheMatrix {
    private char[][] matrix;
    private char fillChar;
    private char starChar;
    private int startRow;
    private int startCol;

    public TheMatrix(char[][] matrix, char fillChar, int startRow, int startCol) {
        this.matrix = matrix;
        this.fillChar = fillChar;
        this.startRow = startRow;
        this.startCol = startCol;
        this.starChar = this.matrix[this.startRow][this.startCol];
    }

    public void solve() {
//      DFS - Depth-First Search
        fillMatrix(this.startRow, this.startCol);
//      ----- END -----

//      BFS - Breadth-First Search
//        Deque<int[]> coordenates = new ArrayDeque<>();
//
//        coordenates.offer(new int[]{startRow, startCol});
//
//        while (!coordenates.isEmpty()) {
//            int[] position = coordenates.poll();
//
//            int row = position[0];
//            int col = position[1];
//
//            this.matrix[row][col] = this.fillChar;
//
//            if (isInBounds(row + 1, col) && this.matrix[row + 1][col] == this.starChar){
//                coordenates.offer(new int[]{row + 1, col});
//            }
//            if (isInBounds(row - 1, col) && this.matrix[row - 1][col] == this.starChar){
//                coordenates.offer(new int[]{row - 1, col});
//            }
//            if (isInBounds(row, col + 1) && this.matrix[row][col + 1] == this.starChar){
//                coordenates.offer(new int[]{row, col + 1});
//            }
//            if (isInBounds(row, col - 1) && this.matrix[row][col - 1] == this.starChar){
//                coordenates.offer(new int[]{row, col - 1});
//            }
//        }
    }

    private boolean isInBounds(int row, int col) {
        return !isOutOFBounds(row, col);
    }

    public String toOutputString() {
        StringBuilder builder = new StringBuilder();

        for (char[] rows : this.matrix) {
            for (char cols : rows) {
                builder.append(cols);
            }
            builder.append(System.lineSeparator());
        }

        return builder.toString().trim();
    }

    private void fillMatrix(int row, int col) {
        if (isOutOFBounds(row, col) || this.matrix[row][col] != this.starChar) {
            return;
        }

        this.matrix[row][col] = fillChar;

        this.fillMatrix(row + 1, col);
        this.fillMatrix(row - 1, col);
        this.fillMatrix(row, col + 1);
        this.fillMatrix(row, col - 1);


    }

    private boolean isOutOFBounds(int row, int col) {
        return row < 0 || row >= this.matrix.length || col < 0 || col >= this.matrix[row].length;
    }
}
