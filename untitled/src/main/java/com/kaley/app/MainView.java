package com.kaley.app;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainView {

    private final BorderPane root = new BorderPane();
    private final VBox outer = new VBox();

    public MainView() {
        root.setTop(buildMenuBar());
        root.setLeft(buildLeftSidebar());
        root.setCenter(buildTable());
        root.setRight(buildRightPanel());

        outer.getChildren().addAll(root, buildBottomBar());
        VBox.setVgrow(root, Priority.ALWAYS);
        root.setMinHeight(0);
    }

    public Parent getRoot() {
        return outer;
    }

    private MenuBar buildMenuBar() {

        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));

        Menu file = new Menu("File");
        file.getItems().addAll(
                new MenuItem("New"),
                new MenuItem("Open"),
                new SeparatorMenuItem(),
                exit
        );

        Menu edit = new Menu("Edit");
        edit.getItems().addAll(
                new MenuItem("Undo"),
                new MenuItem("Redo")
        );

        Menu theme = new Menu("Theme");
        theme.getItems().addAll(
                new MenuItem("Default"),
                new MenuItem("Dark")
        );

        Menu help = new Menu("Help");
        help.getItems().add(
                new MenuItem("About")
        );

        MenuBar bar = new MenuBar(file, edit, theme, help);
        bar.getStyleClass().add("top-menu");

        return bar;
    }

    private VBox buildLeftSidebar() {

        VBox left = new VBox();
        left.getStyleClass().add("left-sidebar");
        left.setPadding(new Insets(18));
        left.setPrefWidth(150);

        StackPane avatarBox = new StackPane(new Label("👤"));
        avatarBox.getStyleClass().add("avatar-box");
        avatarBox.setPrefSize(120, 120);

        left.getChildren().add(avatarBox);

        return left;
    }

    private TableView<Person> buildTable() {

        TableView<Person> table = new TableView<>();
        table.getStyleClass().add("main-table");
        table.setMinHeight(0);

        TableColumn<Person, Integer> id = new TableColumn<>("ID");
        TableColumn<Person, String> first = new TableColumn<>("First Name");
        TableColumn<Person, String> last = new TableColumn<>("Last Name");
        TableColumn<Person, String> dept = new TableColumn<>("Department");
        TableColumn<Person, String> major = new TableColumn<>("Major");
        TableColumn<Person, String> email = new TableColumn<>("Email");

        table.getColumns().addAll(id, first, last, dept, major, email);

        return table;
    }

    private VBox buildRightPanel() {

        VBox right = new VBox(10);
        right.getStyleClass().add("right-panel");
        right.setPadding(new Insets(18, 18, 10, 18));
        right.setPrefWidth(260);

        TextField tfFirst = new TextField();
        tfFirst.setPromptText("First Name");

        TextField tfLast = new TextField();
        tfLast.setPromptText("Last Name");

        TextField tfDept = new TextField();
        tfDept.setPromptText("Department");

        TextField tfMajor = new TextField();
        tfMajor.setPromptText("Major");

        TextField tfEmail = new TextField();
        tfEmail.setPromptText("Email");

        TextField tfImage = new TextField();
        tfImage.setPromptText("imageURL");

        Button btnClear = new Button("Clear");
        Button btnAdd = new Button("Add");
        Button btnDelete = new Button("Delete");
        Button btnEdit = new Button("Edit");

        btnClear.setMaxWidth(Double.MAX_VALUE);
        btnAdd.setMaxWidth(Double.MAX_VALUE);
        btnDelete.setMaxWidth(Double.MAX_VALUE);
        btnEdit.setMaxWidth(Double.MAX_VALUE);

        btnClear.getStyleClass().add("action-btn");
        btnAdd.getStyleClass().add("action-btn");
        btnDelete.getStyleClass().add("action-btn");
        btnEdit.getStyleClass().add("action-btn");

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.SOMETIMES);

        VBox.setMargin(btnClear, new Insets(12, 0, 0, 0));

        right.getChildren().addAll(
                tfFirst, tfLast, tfDept, tfMajor, tfEmail, tfImage,
                spacer,
                btnClear, btnAdd, btnDelete, btnEdit
        );

        return right;
    }

    private Region buildBottomBar() {
        Region bar = new Region();
        bar.getStyleClass().add("bottom-bar");
        bar.setMinHeight(22);
        bar.setPrefHeight(22);
        bar.setMaxHeight(22);
        return bar;
    }
}