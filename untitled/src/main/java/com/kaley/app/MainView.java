package com.kaley.app;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

public class MainView {

    private final BorderPane root = new BorderPane();
    private final VBox outer = new VBox();

    /* Data */
    private final ObservableList<Person> people = FXCollections.observableArrayList();
    private int nextId = 1;

    /* UI controls */
    private TableView<Person> table;

    private TextField tfFirst;
    private TextField tfLast;
    private TextField tfDept;
    private TextField tfMajor;
    private TextField tfEmail;
    private TextField tfImage;

    private ImageView avatarView;

    public MainView() {
        root.setTop(buildMenuBar());
        root.setLeft(buildLeftSidebar());
        root.setCenter(buildTable());
        root.setRight(buildRightPanel());

        outer.setFillWidth(true);
        outer.getChildren().addAll(root, buildBottomBar());
        VBox.setVgrow(root, Priority.ALWAYS);
        root.setMinHeight(0);
    }

    public Parent getRoot() {
        return outer;
    }

    /* Menu */

    private MenuBar buildMenuBar() {
        MenuItem mNew = new MenuItem("New");
        mNew.setOnAction(e -> {
            people.clear();
            clearFields();
        });

        MenuItem mOpen = new MenuItem("Open");
        mOpen.setOnAction(e -> info("Open", "Extra credit idea: connect this to file/db later.\nFor now, UI demo only."));

        MenuItem mExit = new MenuItem("Exit");
        mExit.setOnAction(e -> System.exit(0));

        Menu file = new Menu("File");
        file.getItems().addAll(mNew, mOpen, new SeparatorMenuItem(), mExit);

        MenuItem mUndo = new MenuItem("Undo");
        mUndo.setOnAction(e -> info("Undo", "Undo not implemented (UI demo)."));

        MenuItem mRedo = new MenuItem("Redo");
        mRedo.setOnAction(e -> info("Redo", "Redo not implemented (UI demo)."));

        Menu edit = new Menu("Edit");
        edit.getItems().addAll(mUndo, mRedo);

        MenuItem mDefault = new MenuItem("Default");
        mDefault.setOnAction(e -> root.getStyleClass().remove("dark"));

        MenuItem mDark = new MenuItem("Dark");
        mDark.setOnAction(e -> {
            if (!root.getStyleClass().contains("dark")) root.getStyleClass().add("dark");
        });

        Menu theme = new Menu("Theme");
        theme.getItems().addAll(mDefault, mDark);

        MenuItem mAbout = new MenuItem("About");
        mAbout.setOnAction(e -> info("About", "CSC325 JavaFX UI + CSS replication.\nExtra credit: Table interaction + buttons."));

        Menu help = new Menu("Help");
        help.getItems().add(mAbout);

        MenuBar bar = new MenuBar(file, edit, theme, help);
        bar.getStyleClass().add("top-menu");
        return bar;
    }

    /* Left */

    private VBox buildLeftSidebar() {
        VBox left = new VBox();
        left.getStyleClass().add("left-sidebar");
        left.setPadding(new Insets(18));
        left.setPrefWidth(150);

        Label icon = new Label("👤");
        icon.setStyle("-fx-font-size: 40px;");

        StackPane avatarBox = new StackPane(icon);
        avatarBox.getStyleClass().add("avatar-box");
        avatarBox.setPrefSize(120, 120);

        left.getChildren().add(avatarBox);
        return left;
    }

    /* Table */

    private TableView<Person> buildTable() {
        table = new TableView<>();
        table.getStyleClass().add("main-table");
        table.setItems(people);
        table.setMinHeight(0);
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        TableColumn<Person, Number> id = new TableColumn<>("ID");
        id.setCellValueFactory(data -> data.getValue().idProperty());

        TableColumn<Person, String> first = new TableColumn<>("First Name");
        first.setCellValueFactory(data -> data.getValue().firstNameProperty());

        TableColumn<Person, String> last = new TableColumn<>("Last Name");
        last.setCellValueFactory(data -> data.getValue().lastNameProperty());

        TableColumn<Person, String> dept = new TableColumn<>("Department");
        dept.setCellValueFactory(data -> data.getValue().departmentProperty());

        TableColumn<Person, String> major = new TableColumn<>("Major");
        major.setCellValueFactory(data -> data.getValue().majorProperty());

        TableColumn<Person, String> email = new TableColumn<>("Email");
        email.setCellValueFactory(data -> data.getValue().emailProperty());

        table.getColumns().addAll(id, first, last, dept, major, email);

        // Click row
        table.getSelectionModel().selectedItemProperty().addListener((obs, oldV, p) -> {
            if (p == null) return;
            tfFirst.setText(p.getFirstName());
            tfLast.setText(p.getLastName());
            tfDept.setText(p.getDepartment());
            tfMajor.setText(p.getMajor());
            tfEmail.setText(p.getEmail());
            tfImage.setText(p.getImageUrl());
        });

        return table;
    }

    /* Right */

    private VBox buildRightPanel() {
        VBox right = new VBox(10);
        right.getStyleClass().add("right-panel");
        right.setPadding(new Insets(18));
        right.setPrefWidth(260);

        tfFirst = new TextField();
        tfFirst.setPromptText("First Name");
        tfLast = new TextField();
        tfLast.setPromptText("Last Name");
        tfDept = new TextField();
        tfDept.setPromptText("Department");
        tfMajor = new TextField();
        tfMajor.setPromptText("Major");
        tfEmail = new TextField();
        tfEmail.setPromptText("Email");
        tfImage = new TextField();
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

        // Spacing
        VBox.setMargin(btnClear, new Insets(12, 0, 0, 0));

        /* Button actions */

        btnClear.setOnAction(e -> clearFields());

        btnAdd.setOnAction(e -> {
            Person p = new Person(
                    nextId++,
                    tfFirst.getText().trim(),
                    tfLast.getText().trim(),
                    tfDept.getText().trim(),
                    tfMajor.getText().trim(),
                    tfEmail.getText().trim(),
                    tfImage.getText().trim()
            );
            people.add(p);
            table.getSelectionModel().select(p);
        });

        btnDelete.setOnAction(e -> {
            Person selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                info("Delete", "Select a row to delete.");
                return;
            }
            people.remove(selected);
            clearFields();
        });

        btnEdit.setOnAction(e -> {
            Person selected = table.getSelectionModel().getSelectedItem();
            if (selected == null) {
                info("Edit", "Select a row to edit.");
                return;
            }
            selected.setFirstName(tfFirst.getText().trim());
            selected.setLastName(tfLast.getText().trim());
            selected.setDepartment(tfDept.getText().trim());
            selected.setMajor(tfMajor.getText().trim());
            selected.setEmail(tfEmail.getText().trim());
            selected.setImageUrl(tfImage.getText().trim());

            /* refresh selection + avatar */
            table.refresh();
        });

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.SOMETIMES);

        right.getChildren().addAll(
                tfFirst, tfLast, tfDept, tfMajor, tfEmail, tfImage,
                spacer,
                btnClear, btnAdd, btnDelete, btnEdit
        );

        return right;
    }

    /* Bottom */

    private Region buildBottomBar() {
        Region bar = new Region();
        bar.getStyleClass().add("bottom-bar");
        bar.setMinHeight(22);
        bar.setPrefHeight(22);
        bar.setMaxHeight(22);
        bar.setMouseTransparent(true);
        return bar;
    }

    /* Helpers */

    private void clearFields() {
        tfFirst.clear();
        tfLast.clear();
        tfDept.clear();
        tfMajor.clear();
        tfEmail.clear();
        tfImage.clear();
        table.getSelectionModel().clearSelection();
    }

    private void info(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title);
        a.setHeaderText(null);
        a.setContentText(msg);
        a.showAndWait();
    }
}

