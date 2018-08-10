package sample;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javax.swing.*;
import java.sql.*;

public class Main extends Application {

    public static Statement statement;

    @Override
    public void start(Stage primaryStage) throws Exception{
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        //подключаемся к бд
        try {
            statement = DriverManager.getConnection("jdbc:mysql://localhost:3306/games_backlog?autoReconnect=true&useSSL=false&" +
                    "useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC", "root", "PolotencE123").createStatement();
        }
        catch (SQLException e){e.printStackTrace();}
        //создание окна аутентификации
        final TextField loginField = new TextField();
        loginField.setPromptText("Your login: ");
        final PasswordField passField = new PasswordField();
        passField.setPromptText("Your password: ");
        Button btnLogin = new Button();
        btnLogin.setText("Sign in");
        btnLogin.setStyle(
                "-fx-background-radius: 2;" +
                        "-fx-min-height: 30;" +
                        "-fx-min-width: 70;" +
                        "-fx-max-height: 30;" +
                        "-fx-max-width: 70;" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-size: 10px;"
        );
        Button btnRegister = new Button();
        btnRegister.setText("Sign up");
        btnRegister.setStyle(
                "-fx-background-radius: 2;" +
                        "-fx-min-height: 30;" +
                        "-fx-min-width: 70;" +
                        "-fx-max-height: 30;" +
                        "-fx-max-width: 70;" +
                        "-fx-font-family: Courier New;" +
                        "-fx-font-size: 10px;"
        );
        HBox hBox = new HBox();
        hBox.setLayoutX(75.0);
        hBox.setLayoutY(90.0);
        hBox.setSpacing(10);
        hBox.getChildren().addAll(btnRegister, btnLogin);

        VBox vBox = new VBox();
        vBox.setLayoutX(75.0);
        vBox.setLayoutY(20.0);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(loginField, passField, hBox);

        AnchorPane root = new AnchorPane();
        root.setPrefSize(300, 150);
        root.getChildren().addAll(hBox, vBox);

        Scene signinScene = new Scene(root, 300, 150);

        primaryStage.setTitle("Authentication");
        primaryStage.setScene(signinScene);
        primaryStage.setResizable(false);
        primaryStage.show();

        //обработка кнопки регистрации
        btnRegister.setOnAction(event -> {
            final TextField nameField = new TextField();
            nameField.setPromptText("Your name: ");
            final TextField loginField1 = new TextField();
            loginField1.setPromptText("Your login: ");
            final PasswordField passField1 = new PasswordField();
            passField1.setPromptText("Your password: ");
            final PasswordField passField2 = new PasswordField();
            passField2.setPromptText("Repeat password: ");
            Button btnRegister1 = new Button();
            btnRegister1.setText("Sign up");
            btnRegister1.setStyle(
                    "-fx-background-radius: 2;" +
                            "-fx-min-height: 30;" +
                            "-fx-min-width: 150;" +
                            "-fx-max-height: 30;" +
                            "-fx-max-width: 150;" +
                            "-fx-font-family: Courier New;" +
                            "-fx-font-size: 10px;"
            );
            VBox vBox1 = new VBox();
            vBox1.setLayoutX(75.0);
            vBox1.setLayoutY(15.0);
            vBox1.setSpacing(10);
            vBox1.getChildren().addAll(nameField, loginField1, passField1, passField2, btnRegister1);

            AnchorPane root1 = new AnchorPane();
            root1.setPrefSize(300, 200);
            root1.getChildren().addAll(vBox1);

            Scene registrationScene = new Scene(root1, 300, 200);

            primaryStage.close();
            primaryStage.setTitle("Registration");
            primaryStage.setScene(registrationScene);
            primaryStage.setResizable(false);
            primaryStage.show();
            //Обработка кнопки регистрации в окне регистрации
            btnRegister1.setOnAction(event1 -> {
                try {
                    ResultSet result = statement.executeQuery("SELECT * FROM users WHERE login=\'" + loginField1.getText() + "\'");
                    if (!result.next()) {
                        if (passField1.getText().equals(passField2.getText())) {
                            statement.executeUpdate("INSERT INTO users (name, login, password) VALUES (\'" + nameField.getText() + "\', \'" + loginField1.getText() + "\', \'" + passField1.getText() + "\')");
                            primaryStage.close();
                            primaryStage.setTitle("Authentication");
                            primaryStage.setScene(signinScene);
                            primaryStage.setResizable(false);
                            primaryStage.show();
                        }
                        else {
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("Passwords do not match.");
                            alert.showAndWait();
                        }
                    }
                    else {
                        ResultSet compareLogin = statement.executeQuery("SELECT * FROM users WHERE login=\'"
                                + loginField1.getText() + "\'");
                        if (compareLogin.next()) {
                            alert.setTitle("Warning");
                            alert.setHeaderText(null);
                            alert.setContentText("Login already exists.");
                            alert.showAndWait();
                        }
                    }
                } catch (SQLException e1) {e1.printStackTrace();}
            });
            }
        );
        //Обработка кнопки login
        btnLogin.setOnAction(event -> {
            try {
                ResultSet result = statement.executeQuery("SELECT * FROM users WHERE login=\'"
                        + loginField.getText() + "\' AND password=\'"
                        + passField.getText() + "\'");
                if (result.next()) {
                    //создание главного окна
                    final Label completedLabel = new Label("Completed Games");
                    completedLabel.setStyle("-fx-text-fill: #ffffff;" +
                            "-fx-font-size: 16px;");
                    completedLabel.setPrefSize(300, 40);
                    completedLabel.setAlignment(Pos.CENTER);
                    final Label currentLabel = new Label("Current Games");
                    currentLabel.setStyle("-fx-text-fill: #ffffff;" +
                            "-fx-font-size: 16px;");
                    currentLabel.setPrefSize(300, 40);
                    currentLabel.setAlignment(Pos.CENTER);
                    final Label mainLabel = new Label("Games Catalogue");
                    mainLabel.setStyle("-fx-text-fill: #ffffff;" +
                            "-fx-font-size: 16px;");
                    mainLabel.setPrefSize(900, 40);
                    mainLabel.setAlignment(Pos.CENTER);
                    ScrollPane completed_pane = new ScrollPane();
                    completed_pane.setPrefSize(300, 800);
                    completed_pane.setLayoutY(40);
                    completed_pane.setPadding(new Insets(40, 1200, 0, 0));
                    completed_pane.setStyle("-fx-background-color: #303030;");

                    ScrollPane current_pane = new ScrollPane();
                    current_pane.setPrefSize(300, 800);
                    current_pane.setLayoutY(40);
                    current_pane.setLayoutX(300);
                    current_pane.setPadding(new Insets(40, 900, 0, 300));
                    current_pane.setStyle("-fx-background-color: #b82121;");

                    ScrollPane main_pane = new ScrollPane();
                    main_pane.setPrefSize(900, 800);
                    main_pane.setLayoutY(40);
                    main_pane.setLayoutX(600);
                    main_pane.setPadding(new Insets(40,0, 0, 1200));
                    main_pane.setStyle("-fx-background-color: #2156b8;");

                    Pane completed_pane_head = new Pane();
                    completed_pane_head.setPrefSize(300, 40);
                    completed_pane_head.setLayoutY(0);
                    completed_pane_head.setLayoutX(0);
                    completed_pane_head.setPadding(new Insets(0, 1200, 840, 0));
                    completed_pane_head.setStyle("-fx-background-color: #403434;");
                    completed_pane_head.getChildren().add(completedLabel);

                    Pane current_pane_head = new Pane();
                    current_pane_head.setPrefSize(300, 40);
                    current_pane_head.setLayoutY(0);
                    current_pane_head.setLayoutX(300);
                    current_pane_head.setPadding(new Insets(0, 900, 840, 300));
                    current_pane_head.setStyle("-fx-background-color: #9e2f2f;");
                    current_pane_head.getChildren().add(currentLabel);

                    Pane main_pane_head = new Pane();
                    main_pane_head.setPrefSize(300, 40);
                    main_pane_head.setLayoutY(0);
                    main_pane_head.setLayoutX(600);
                    main_pane_head.setPadding(new Insets(0, 600, 840, 600));
                    main_pane_head.setStyle("-fx-background-color: #2f709e;");
                    main_pane_head.getChildren().add(mainLabel);

                    AnchorPane root_pane = new AnchorPane();
                    root_pane.setPrefHeight(880);
                    root_pane.setPrefWidth(1500);
                    root_pane.setMaxSize(20000, 20000);
                    root_pane.setMinSize(0, 0);
                    root_pane.getChildren().addAll(completed_pane_head, completed_pane, current_pane_head, current_pane, main_pane_head, main_pane);

                    Scene mainScene = new Scene(root_pane, 1500, 800);

                    primaryStage.close();
                    primaryStage.setTitle("Games Backlog");
                    primaryStage.setScene(mainScene);
                    primaryStage.setResizable(false);
                    primaryStage.show();
                }
                else {
                    alert.setTitle("Test");
                    alert.setHeaderText(null);
                    alert.setContentText("Please, register your account");
                    alert.showAndWait();
                }
            } catch (SQLException e) {e.printStackTrace();}
        });
    }


    public static void main(String[] args) throws SQLException {
        launch(args);
    }
}
