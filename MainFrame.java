package skylordtools;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.BufferedInputStream;

public class MainFrame extends Application{
    private Stage window;
    private final int sizeMap = 250;
    private final int windowWidth = 1349;
    private final int windowHeight = 1039;

    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        this.window = primaryStage;
        initUI();

    }

    public void initUI(){
        window.setTitle("Skylords Upgrades");
        window.setMinWidth(windowWidth);
        window.setMinHeight(windowHeight);
        window.setWidth(windowWidth);
        window.setHeight(windowHeight);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);

        ColumnConstraints column1 = new ColumnConstraints(sizeMap+15); //+15 because of scrollbar
        ColumnConstraints column2 = new ColumnConstraints();
        column2.setHgrow(Priority.ALWAYS);
        grid.getColumnConstraints().addAll(column1, column2);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(60);
        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(40);

        grid.getRowConstraints().addAll(row1, row2);

        //Maps on the left
        VBox maps = new VBox();
        maps.setSpacing(15);

        ScrollPane scroll = new ScrollPane(maps);
        scroll.setFitToHeight(true);
        scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);

        grid.add(scroll, 0, 0, 1, 2);

        //TESTING - Add upgrades to map - TESTING
        Image nightmare = FileOperations.getImage("./resources/maps/NightmareShard.jpg");

        for (int i = 0; i < 5; i++){
            VBox map = new VBox();
            map.setAlignment(Pos.CENTER);

            Label name = new Label("%mapName% %difficulty%");

            ImageView mapImg = new ImageView(nightmare);
            mapImg.setPreserveRatio(true);
            mapImg.setFitHeight(sizeMap);

            HBox bottom = new HBox();
            bottom.setAlignment(Pos.CENTER);
            bottom.setSpacing(30);
            Label numUpgr = new Label("Number of upgrades: %n%");
            Button btn = new Button("Show");

            bottom.getChildren().addAll(numUpgr, btn);

            map.getChildren().addAll(name, mapImg, bottom);

            maps.getChildren().addAll(map, new Separator());
        }

        //Upgrades on the top right
        Label upgradeLabel = new Label("Upgrades dropped on %mapName% - %difficulty% (U%difficultyNumber%):");
        upgradeLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        upgradeLabel.setPadding(new Insets(5, 0, 5, 0));

        FlowPane upgradeFlowPane = new FlowPane();
        upgradeFlowPane.setHgap(5);
        upgradeFlowPane.setVgap(5);

        upgradeFlowPane.setAlignment(Pos.BASELINE_LEFT);

        BorderPane upgradeBorderPane = new BorderPane();
        upgradeBorderPane.setTop(upgradeLabel);
        BorderPane.setAlignment(upgradeLabel, Pos.CENTER);
        upgradeBorderPane.setCenter(upgradeFlowPane);


        grid.add(upgradeBorderPane, 1, 0);

        //TESTING - Add upgrades to upgradeFlowPane - TESTING
        Image abomination = FileOperations.getImage("./resources/cards/Abomination_frost.jpg");

        for (int j = 0; j < 6; j++) {
            ImageView img = new ImageView(abomination);
            upgradeFlowPane.getChildren().add(img);
        }

        //Deck on the bottom right
        Label deckLabel = new Label("Current deck:");
        deckLabel.setFont(Font.font("Segoe UI", FontWeight.BOLD, 20));
        upgradeLabel.setPadding(new Insets(5, 0, 5, 0));


        GridPane deckGridPane = new GridPane();
        deckGridPane.setHgap(5);
        deckGridPane.setVgap(5);
        deckGridPane.setAlignment(Pos.CENTER);

        /*
        for (int i = 0; i < 10; i++){
            RowConstraints row = new RowConstraints(5);
            //row.setVgrow(Priority.ALWAYS);
            deckGridPane.getRowConstraints().add(row);

            ColumnConstraints col = new ColumnConstraints(5);
            //col.setHgrow(Priority.ALWAYS);
            deckGridPane.getColumnConstraints().add(col);
        }
        */

        BorderPane deckBorderPane = new BorderPane();
        deckBorderPane.setTop(deckLabel);
        BorderPane.setAlignment(deckLabel, Pos.CENTER);
        deckBorderPane.setCenter(deckGridPane);

        grid.add(deckBorderPane, 1, 1);

        //TESTING - Add deckslots to deckFlowPane - TESTING
        for (int i = 0; i < 2; i++){
            for (int j = 0; j < 10; j++){
                ImageView img = new ImageView(abomination);
                img.setPreserveRatio(true);
                img.setFitHeight(135);

                HBox eras = new HBox();
                eras.getChildren().addAll(new Button("U1"), new Button("U2"), new Button("U3"));
                eras.setAlignment(Pos.CENTER);

                VBox deckSlot = new VBox();
                deckSlot.getChildren().addAll(img, eras);

                deckSlot.setAlignment(Pos.CENTER);

                deckGridPane.add(deckSlot, j, i);
            }
        }

        //Toolbar
        ToolBar toolBar = new ToolBar();
        toolBar.getItems().addAll(
                new Button("Calculate upgrades"),
                new Separator(),
                new Button("Switch deck"),
                new Button("Edit deck"),
                new Button("New Deck"),
                new Separator(),
                new Button("Redownload cardbase data")
        );

        //Wrap everything in borderPane
        BorderPane mainBorderPane = new BorderPane();
        mainBorderPane.setTop(toolBar);
        mainBorderPane.setCenter(grid);

        Scene mainScene = new Scene(mainBorderPane);
        window.setScene(mainScene);
        window.show();

        System.out.println(Cardbase.getCards());
    }
}
