package com.kaley.app;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class MainView {

    private final BorderPane root = new BorderPane();

    public MainView() {
        root.setTop(buildMenuBar());
        root.setLeft(buildLeftSidebar());
        root.setCenter(buildTable());
        root.setRight(buildRightPanel());
    }

    public Parent getRoot() {
        return root;
    }

    private MenuBar buildMenuBar() {
        MenuItem exit = new MenuItem("Exit");
        exit.setOnAction(e -> System.exit(0));

        Menu file = new Menu("File", null,
                new MenuItem("New"),
                new MenuItem("Open"),
                new SeparatorMenuItem(),
                exit
        );

        Menu edit = new Menu("Edit", null,
                new MenuItem("Undo"),
                new MenuItem("Redo")
        );

        Menu theme = new Menu("Theme", null,
                new MenuItem("Default"),
                new MenuItem("Dark")
        );

        Menu help = new Menu("Help", null,
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
        left.setPrefWidth(170);

        // Avatar placeholder (we'll swap to ImageView later if you want)
        StackPane avatarBox = new StackPane(new Label("👤"));
        avatarBox.getStyleClass().add("avatar-box");
        avatarBox.setPrefSize(120, 120);

        left.getChildren().add(avatarBox);
        return left;
    }

    private TableView<Person> buildTable() {
        TableView<Person> table = new TableView<>();
        table.getStyleClass().add("main-table");

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
        right.setPadding(new Insets(18));
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

        btnClear.getStyleClass().add("action-btn");
        btnAdd.getStyleClass().add("action-btn");
        btnDelete.getStyleClass().add("action-btn");
        btnEdit.getStyleClass().add("action-btn");

        Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);

        right.getChildren().addAll(
                tfFirst, tfLast, tfDept, tfMajor, tfEmail, tfImage,
                spacer,
                btnClear, btnAdd, btnDelete, btnEdit
        );

        return right;
    }
}
