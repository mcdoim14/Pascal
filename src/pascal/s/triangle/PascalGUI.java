/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pascal.s.triangle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 *
 * @author ian
 */
public class PascalGUI extends Pane {
    public PascalGUI(){
        final HBox container = new HBox();
        StackPane holder = new StackPane();
        final Canvas canvas = new Canvas(1420, 930);
        holder.getChildren().add(canvas);
        
        holder.setStyle("-fx-background-color: #e0e5c5;");
        final GraphicsContext gc = canvas.getGraphicsContext2D();
        
        final VBox inputBox = new VBox(80);
        
        
        inputBox.setAlignment(Pos.TOP_CENTER);
        inputBox.setPrefWidth(120);
        inputBox.setPadding(new Insets(100, 10, 10, 10));
        inputBox.setStyle("-fx-background-color: #80846e;");
        
        final Text inputLabel = new Text();
        inputLabel.setText("Input number of rows:");
        inputLabel.setFont(Font.font ("Verdana", 14));
        final TextField input = new TextField();
        
        Button startButton = new Button("GO");
        startButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
                inputRows = Integer.parseInt(input.getText());
                calculateTriangle(inputRows);
                printTriangle(gc);
            }
        });
        
        final VBox ncrBox = new VBox(15);
        ncrBox.setAlignment(Pos.TOP_CENTER);
        
        final Text ncrLabel = new Text();
        ncrLabel.setFont(Font.font ("Verdana", 14));
        ncrLabel.setText("nCr:");
        
        final HBox ncrInput = new HBox(3);
        
        final Text nVar = new Text();
        nVar.setText("n:");
        nVar.setFont(Font.font ("Verdana", 14));
        final TextField nInput = new TextField();
        
        final Text rVar = new Text();
        rVar.setText("r:");
        rVar.setFont(Font.font ("Verdana", 14));
        final TextField rInput = new TextField();
        
        final TextField answer = new TextField();
        
        Button ncrButton = new Button("CALCULATE");
        ncrButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                n = Integer.parseInt(nInput.getText());
                r = Integer.parseInt(rInput.getText());
                answer.setText(Long.toString(pascal[n + 1][r + 1]));
            }
        });
        
        Button pow2Button = new Button("Pow2");
        pow2Button.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                gc.clearRect(0, 0, CANVAS_WIDTH, CANVAS_HEIGHT);
                inputRows = Integer.parseInt(input.getText());
                calculateTriangle(inputRows);
                printTrianglePow2(gc);
            }
        });
        
        ncrInput.getChildren().addAll(nVar, nInput, rVar, rInput);
        ncrBox.getChildren().addAll(ncrLabel, ncrInput, ncrButton, answer, pow2Button);
        
        final VBox triangleBox = new VBox(15);
        triangleBox.setAlignment(Pos.TOP_CENTER);
        triangleBox.getChildren().addAll(inputLabel, input, startButton);
        
        inputBox.getChildren().addAll(triangleBox, ncrBox);
        container.getChildren().addAll(holder, inputBox);
        
        super.getChildren().add(container);        
 
    }
        
    
    public void calculateTriangle(int rows){
        this.rows = rows;
        pascal  = new long[rows +1][];
        pascal[1] = new long[3];
        pascal[1][1] = 1;
        for (int i = 2; i <= rows; i++) {
            pascal[i] = new long[i + 2];
            for (int j = 1; j < pascal[i].length - 1; j++) {
                pascal[i][j] = pascal[i-1][j-1] + pascal[i-1][j];
            }
        }
    }
    
    public void printTriangle(GraphicsContext gc) {
        diameter = CANVAS_WIDTH / (2 * rows);
        center = (CANVAS_WIDTH / 2) - (diameter / 2);
        yPos = ((CANVAS_HEIGHT - rows * diameter) / 2) - diameter;
        
        for (int i = 1; i <= rows; i++) {
            xPos = (center - ((i - 1) * (diameter / 2)));
            yPos += diameter;
            for (int j = 1; j < pascal[i].length - 1; j++) { 
                if(pascal[i][j] % 2 == 0) gc.setFill(Color.RED);
                else gc.setFill(Color.BLACK);
                gc.fillOval(xPos, yPos, diameter, diameter);
                xPos += diameter;
            }
        }   
    }
    
    public void printTrianglePow2(GraphicsContext gc) {
        diameter = CANVAS_WIDTH / (2 *rows);
        center = (CANVAS_WIDTH / 2) - (diameter / 2);
        yPos = ((CANVAS_HEIGHT - rows * diameter) / 2) - diameter;
        
        for (int i = 1; i <= rows; i++) {
            xPos = (center - ((i - 1) * (diameter / 2)));
            yPos += diameter;
            for (int j = 1; j < pascal[i].length - 1; j++) {
                if((i & (i - 1)) == 0){
                    gc.setFill(Color.RED);
                    gc.fillOval(xPos, yPos, diameter, diameter);
                    xPos += diameter;
                }
                else{
                if(pascal[i][j] % 2 == 0) gc.setFill(Color.GREY);
                else gc.setFill(Color.BLACK);
                gc.fillOval(xPos, yPos, diameter, diameter);
                xPos += diameter;
                }
            }
        }   
    }
    
    
    
    
    private int n;
    private int r;
    private long[][] pascal;
    private int rows;
    private double diameter;
    private double center;
    private double yPos;
    private double xPos;
    private int inputRows;
    private static final double CANVAS_WIDTH = 1410;
    private static final double CANVAS_HEIGHT = 900;
    
}

