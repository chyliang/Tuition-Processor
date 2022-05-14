package com.example.tuition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The runner for project3.
 * Used for running the program.
 *
 * @author Xinyu Meng, Chengyu Liang
 */
public class Runproject3 extends Application {

    /**
     * The runner for project3.
     *
     * @param stage The interface.
     * @throws IOException The unexpected exceptions.
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Controller.class.getResource("TuitionView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 602, 542);
        stage.setTitle("Payment_Interface");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Main method to run the program.
     *
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }
}