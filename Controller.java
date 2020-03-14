import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.control.Label;

public class Controller {

    public Controller(){

    }


    @FXML
    private Pane boardPane;

    @FXML
    private Pane parentGrid;

    @FXML
    private Button minConflictAlgBtn;

    @FXML
    private Label attackingLbl;

    @FXML
    private Label timeLbl;

    @FXML
    private void initialize(){
        ChessBoard board = new ChessBoard(25);

        boardPane.getChildren().add(board);

        minConflictAlgBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
                timeLbl.setText(board.minConflictAlgorithm() + "");
                board.placeQueens();

                attackingLbl.setText(board.getPuzzle().getAttackingValue()+"");
            }
        });

    }

}
