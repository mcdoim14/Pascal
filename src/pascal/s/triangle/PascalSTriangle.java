/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pascal.s.triangle;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author ian
 */
public class PascalSTriangle extends Application {
    
    @Override
    public void start(Stage primaryStage) {
                
        PascalGUI root = new PascalGUI();
        
        Scene scene = new Scene(root, 1600, 900);
        
        primaryStage.setTitle("Pascal's Triangle");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
