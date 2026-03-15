package com.example.miniproject2;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.time.LocalDate;

import static javafx.geometry.Pos.CENTER;
import static javafx.geometry.VPos.BASELINE;
import static javafx.scene.layout.GridPane.*;

public class Employee extends Application {

    private String name;

    public static void main(String[] args) {
        launch(args);
    }
    private ObservableList<EmployeeI> employees = FXCollections.observableArrayList();
    @Override
    public void start(Stage stage) {
        stage.setTitle("Employee Management System");
        stage.setResizable(true);
        GridPane grid = new GridPane();
        grid.setAlignment(CENTER);
        grid.setPadding(new Insets(20, 20, 20, 20));
        grid.setHgap(5);
        grid.setVgap(25);


        Text scenetitle = new Text("Welcome!");
        scenetitle.setFont(Font.font("Arial", FontWeight.NORMAL, 25));
        grid.add(scenetitle, 1, 0);

        Label name = new Label("User Name:");
        grid.add(name, 0, 1);
        setHalignment(name, HPos.RIGHT);
        TextField ust = new TextField();
        grid.add(ust, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);
        setHalignment(pw, HPos.RIGHT);
        setValignment(pw, VPos.TOP);
        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button btn = new Button("Sign in");
        grid.add(btn, 2, 4);

        Text error = new Text();
        error.setFill(Color.RED);


        grid.add(error, 0, 3, 2, 1);

        Text welcome = new Text("");
        StackPane wStack = new StackPane();
        wStack.getChildren().add(welcome);

        Scene wScene = new Scene(wStack, 300, 275);

        btn.setOnAction(e -> {
                    String un = ust.getText();
                    String password = pwBox.getText();
                    if (!un.isEmpty() && !password.isEmpty()) {

                        welcome.setText("Welcome " + un + "!");
                        stage.setScene(wScene);
                        showInformations();
                    } else
                        error.setText("Username and password are required");

                }

        );
        Scene loginScene = new Scene(grid, 500, 500);
        stage.setScene(loginScene);
        stage.show();


    }
        private void showInformations() {
            Stage informations = new Stage();
            Text title = new Text("Employee");
            GridPane grid = new GridPane();
            title.setFont(Font.font("Tahoma", FontWeight.NORMAL, 32));
            grid.add(title, 0, 0, 2, 1);

            Label userNameLbl = new Label("User Name:");
            grid.add(userNameLbl, 0, 1);

            TextField userFld = new TextField();
            userFld.setPromptText("Enter your username");
            grid.add(userFld, 1, 1);

            Label dobLbl = new Label("Date of Birth:");
            grid.add(dobLbl, 0, 2);

            DatePicker dobPck = new DatePicker();
            dobPck.setValue(LocalDate.now());
            grid.add(dobPck, 1, 2);


            Label genderLbl = new Label("Gender:");
            grid.add(genderLbl, 0, 3);

            RadioButton radioButton1 = new RadioButton("Male");
            radioButton1.setSelected(true);
            RadioButton radioButton2 = new RadioButton("Female");
            ToggleGroup radioGroup = new ToggleGroup();
            radioButton1.setToggleGroup(radioGroup);
            radioButton2.setToggleGroup(radioGroup);
            HBox radioBox = new HBox(radioButton1, radioButton2);
            radioBox.setSpacing(25);
            grid.add(radioBox, 1, 3);

            Label degreeLbl = new Label("Degree:");
            grid.add(degreeLbl, 0, 4);

            ChoiceBox<String> choiceBox = new ChoiceBox<>();
            choiceBox.getItems().add("Computer Science");
            choiceBox.getItems().add("Biomedical Engineering");
            choiceBox.getItems().add("Psychology");
            choiceBox.getItems().add("Biology");
            choiceBox.getSelectionModel().selectFirst();
            grid.add(choiceBox, 1, 4);

            Label addLbl = new Label("Address:");
            grid.add(addLbl, 0, 5);

            TextField addrFld = new TextField();
            addrFld.setPromptText("Enter your current address");
            grid.add(addrFld, 1, 5);

            Label emaillb = new Label("Email:");
            grid.add(emaillb, 0, 6);

            TextField lbemail= new TextField();
            lbemail.setPromptText("Enter your email");
            grid.add(lbemail, 1, 6);

            Label salarylb = new Label("Salary:");
            grid.add(salarylb, 0, 7);
            TextField salary = new TextField();
            salary.setPromptText("Enter your salary");
            grid.add(salary, 1, 7);

            Button submitBtn = new Button("Submit");
            Text errorMsg = new Text();
            HBox hbox = new HBox(submitBtn, errorMsg);
            hbox.setAlignment(CENTER);

            TableView<EmployeeI> table = new TableView<>();

            TableColumn<EmployeeI, String> nameCol = new TableColumn<>("Name");
            nameCol.setCellValueFactory(new PropertyValueFactory<>("getUserName"));
            nameCol.setMinWidth(100);

            TableColumn<EmployeeI, String> dobCol = new TableColumn<>("Date of Birth");
            dobCol.setCellValueFactory(new PropertyValueFactory<>("getDob"));
            dobCol.setMinWidth(100);

            TableColumn<EmployeeI, String> genderCol = new TableColumn<>("Gender");
            genderCol.setCellValueFactory(new PropertyValueFactory<>("getGender"));
            genderCol.setMinWidth(100);

            TableColumn<EmployeeI, String> degreeCol = new TableColumn<>("Degree");
            degreeCol.setCellValueFactory(new PropertyValueFactory<>("getDegree"));
            degreeCol.setMinWidth(100);

            TableColumn<EmployeeI, String> addressCol = new TableColumn<>("Address");
            addressCol.setCellValueFactory(new PropertyValueFactory<>("getAddress"));
            addressCol.setMinWidth(100);

            TableColumn<EmployeeI, String> emailCol = new TableColumn<>("Email");
            emailCol.setCellValueFactory(new PropertyValueFactory<>("getEmail"));
            emailCol.setMinWidth(100);

            TableColumn<EmployeeI, Double> colSalary = new TableColumn<>("Salary");
            colSalary.setCellValueFactory(new PropertyValueFactory<>("getSalary"));

            table.getColumns().addAll(addressCol, nameCol, genderCol, degreeCol, dobCol,colSalary,emailCol);


            employees.add(new EmployeeI("Beirut", "John", "Male","Psycho","33","6533","JohnPaw@gmail.com"));
            employees.add(new EmployeeI("Saida", "Nabil","Male","Finance","22","888","Nabilkabalan@gmail.com" ));
            table.setItems(employees);

            VBox vbox = new VBox(25);
            vbox.setPadding(new Insets(25));
            vbox.getChildren().addAll(grid, hbox, table );

            Scene scene = new Scene(vbox, 600, 600);
            informations.setScene(scene);

            informations.show();

            submitBtn.setOnAction(evt -> {
                String username = userFld.getText();
                String dob = dobPck.getValue().toString();
                String gender = ((RadioButton)radioGroup.getSelectedToggle()).getText();
                String degree = choiceBox.getValue();
                String address = addrFld.getText();
                String email= lbemail.getText();
                String sal = salary.getText();
                System.out.println("DEBUG: "+username+" "+sal);



                if(     !username.isEmpty()
                        && !dob.isEmpty()
                        && !gender.isEmpty()
                        && !degree.isEmpty()
                        && !address.isEmpty()
                        &&!sal.isEmpty()) {
                    employees.add(new EmployeeI (address,username,gender,degree,dob,sal, email));

                    userFld.setText("");
                    dobPck.setValue(LocalDate.now());
                    radioButton1.setSelected(true);
                    choiceBox.getSelectionModel().selectFirst();
                    addrFld.setText("");
                    lbemail.setText("");
                    salary.setText("");

                }
            });



            }
        }


