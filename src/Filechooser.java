import javafx.application.Application; 
import javafx.scene.Scene; 
import javafx.scene.control.*; 
import javafx.scene.layout.*; 
import javafx.stage.Stage; 
import javafx.geometry.*; 
import javafx.scene.paint.*; 
import javafx.scene.canvas.*; 
import javafx.scene.text.*; 
import javafx.scene.Group; 
import javafx.scene.shape.*; 
import javafx.event.ActionEvent; 
import javafx.event.EventHandler; 
import javafx.collections.*; 
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.io.File;
import javafx.stage.FileChooser; 
/**
 * This class is the Filechooser - This is a class containing gui controls.
 * Displays a UI for the user to choose a text file.
 * 
 * @author Jan
 *
 */
public class Filechooser extends Application{

	@Override
	public void start(Stage stage) throws Exception {
		Map<String, Integer> fileWords = new HashMap<String, Integer>();
		ArrayList<String> orderArray = new ArrayList<String>();
		// TODO Auto-generated method stub
			// set title for the stage 
			stage.setTitle("FileChooser"); 

			// create a File chooser 
			FileChooser fil_chooser = new FileChooser(); 
			sendPara str = new sendPara();
			
			// create a Label 
			Label title = new Label("Top 20 Word Frequency Checker");
			title.setFont(new Font("Arial", 28));
			Label label = new Label("No Text File has been selected"); 
			//label config
			label.setFont(new Font("Arial", 20));
			// create a Button 
			Button selectButton = new Button("Select a Text File"); 
			
			Button readButton = new Button("Read the Text File");
			
			readButton.setDisable(true);
			// create an Event Handler 
			EventHandler<ActionEvent> fileChooser = new EventHandler<ActionEvent>() 
			{ 

				public void handle(ActionEvent e) 
				{ 
					FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
					fil_chooser.getExtensionFilters().add(extFilter);
						
					// get the file selected 
					File file = fil_chooser.showOpenDialog(stage);

					if (file != null) { 
						label.setText(file.getAbsolutePath());
						str.setSelectedFile(file.getAbsolutePath());
						readButton.setDisable(false);
					} 
					 
				} 
			}; 
			
			EventHandler<ActionEvent> readFile = new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent e) {
					
					
					//System.out.println(str.getSelectedFile());
					PopBox.displayArray("Reader", ReadFile.fileReader(str.getSelectedFile(), fileWords, orderArray));
				}
				
			};
			
			
			//setActions
			selectButton.setOnAction(fileChooser); 
			readButton.setOnAction(readFile); 
			
			// create a VBox 
			VBox vbox = new VBox(30,title, label, selectButton, readButton); 

			// set Alignment 
			vbox.setAlignment(Pos.CENTER); 

			// create a scene 
			Scene scene = new Scene(vbox, 800, 400); 

			// set the scene 
			stage.setScene(scene); 

			stage.show(); 
	}

}
