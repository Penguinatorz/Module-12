import java.util.ArrayList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
/**
 * This PopBox is a class that assists the Filechooser.class
 * This Popbox displays the list of word occurrences once user presses the button in Filechooser
 *  
 * @author Jan
 *
 */
public class PopBox {
	
	
	public static void displayArray(String title, ArrayList<String> orderArray) {
		//stage
		Stage window = new Stage();
		//sets the window size as well as dissallowing user to go to the window while this one is in use
		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(500);
		//label 
		Text label = new Text();
		Text titleBox = new Text();
		label.setText(orderArray.toString() + "\n");
		label.setFont(new Font("Arial", 20));
		titleBox.setText("Top 20 Word Frequency [Word, Frequency Number]");
		titleBox.setFont(new Font("Arial", 28));
		//layout setup
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		label.setWrappingWidth(200);//since this is one label i used a widthwrap to go to the next row for readability sake
		grid.add(titleBox, 0, 0);
		grid.add(label, 0, 2);
		
		//Scene
		Scene scene = new Scene(grid);
		window.setScene(scene);
		window.showAndWait();//waits for the window to close before being able to interact with the previous window
	}
	
	

}
