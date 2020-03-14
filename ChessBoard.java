import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class ChessBoard extends GridPane {

    private Button[][] grid;
    private Board board;

    public ChessBoard(int size){
        super();

        this.grid = new Button[size][size];

        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                Button current = new Button();
                current.setDisable(true);

                current.setPrefSize(35, 35);
                current.setFont(Font.font("Arial", FontWeight.BOLD, 14));

                if(i % 2 == j % 2)
                    current.setStyle("-fx-color: white");
                else
                    current.setStyle("-fx-color: gray");

                this.grid[i][j] = current;
                this.add(current, i, j);
            }
        }
    }

    public long minConflictAlgorithm(){
        long time = System.currentTimeMillis();

        MinConflict conflict = new MinConflict(25, 10000);

        board = conflict.search();

        return System.currentTimeMillis() - time;
    }

    public void placeQueens(){
        int[] data = board.getData();

        for(int i = 0; i < data.length; i++){
            for(int j = 0; j < data.length; j++){
                if(j == data[i])
                    grid[i][j].setText("\u265B");
                else
                    grid[i][j].setText("");
            }
        }
    }

    public Board getPuzzle(){
        return this.board;
    }



}
