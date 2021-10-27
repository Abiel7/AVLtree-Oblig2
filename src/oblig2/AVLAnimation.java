package oblig2;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.security.Key;
import java.security.cert.Extension;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class AVLAnimation extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    RadioButton radioInt;
    RadioButton radioString;
    TextField tfkey;
    @Override
    public void start(Stage primaryStage) {

        BorderPane pane  = new BorderPane() ;

        AvlTree  tree =  new AvlTree ();

        BstView view =  new BstView(tree);
        pane.setCenter(view);
        Button btnInsert  = new Button("Insert");
        Button btnDelete  = new Button("Delete");
        Button btnSearch = new Button("Search");
        Button btnInsertRand =  new Button("Random");
        Button btnFindElement =  new Button("Find Element");

        Button btnRestart =  new Button("Restart");

        ToggleGroup group =  new ToggleGroup();

        radioInt = new RadioButton("Integer");
        radioInt.setToggleGroup(group);
        radioInt.setSelected(true);
        radioString= new RadioButton("String");
        radioString.setToggleGroup(group);

        HBox v = new HBox(20);

         tfkey =  new TextField();
        tfkey.setPrefColumnCount(5);
        tfkey.setAlignment(Pos.BASELINE_RIGHT);

        HBox hBox =  new HBox(5);
        hBox.getChildren().addAll(new Label("Tall"),tfkey,btnInsert,btnDelete,btnSearch,btnInsertRand,btnFindElement);
        v.getChildren().addAll(radioInt,radioString,btnRestart);
        v.setAlignment(Pos.CENTER);
        hBox.setAlignment(Pos.CENTER);
        pane.setBottom(hBox);
        pane.setTop(v);

        btnInsert.setOnAction(e ->{

            try {
                if (radioInt.isSelected()) {
                    int key=  Integer.parseInt(tfkey.getText());
                    if (!tree.search(key)) {
                        tree.insert(key);
                        tfkey.clear();
                    }
                    view.displayTree();
                }
                else if (radioString.isSelected()){
                    String key=  tfkey.getText();
                    if(view.isNumeric(key)){
                        view.displayTree();
                        view.setStatus("String is  Chosen  cant insert  Integer");
                    }
                    else {
                        if (!tree.search(key)) {
                            tree.insert(key);
                            tfkey.clear();
                        }

                    }
                    view.displayTree();
                }
            }
            catch (NumberFormatException ee) {

                if(radioInt.isSelected()){
                    view.displayTree();
                    view.setStatus("Cant Insert String, Integer is Chosen");
                }
                else if(radioString.isSelected()){
                    view.displayTree();
                    view.setStatus("String is Selected");
                }
                else{
                    view.displayTree();
                    view.setStatus("cant do that  ");
                }


            }
        });


        btnDelete.setOnAction(e ->{
         int key = Integer.parseInt(tfkey.getText());
            if (!tree.search(key)){
                view.displayTree();
                view.setStatus(key + " is not in the tree");


            }
            else{
                tree.delete(key);
                view.displayTree();
                view.setStatus(key  + " is deleted from th tree");


            }
            tfkey.clear();
        });

        btnSearch.setOnAction(e-> {
                    int key =  Integer.parseInt(tfkey.getText());

                    if(tree.search(key)){
                        view.displayTree();
                        view.setStatus( key + " is in the tree ");

                    }else{
                        view.displayTree();
                        view.setStatus(key +" is not in the tree ");

                    }
        });

        btnRestart.setOnAction( e-> {
            tree.clear();
            view.restart();
        });

        btnFindElement.setOnAction(e->{
            int key =  Integer.parseInt(tfkey.getText());
            view.setStatus("The " + key + " Smallest  Element is " + tree.find(key));
            view.displayTree();
        });

        btnInsertRand.setOnAction(e -> {
            if(radioInt.isSelected()){
                int key  = Integer.parseInt(tfkey.getText());
                for (int i = 0; i <key ; i++){
                    tree.insert(tree.randNumb(1,key));
                }
                view.displayTree();
            }
            else  {
                int key  = Integer.parseInt(tfkey.getText());
                for(int i = 0 ;  i<key; i++){
                    tree.insert(String.valueOf(tree.rndChar()));
                }
                view.displayTree();
            }
        });


        Scene scene = new Scene(pane,500,500);
        primaryStage.setTitle("AVl Tree ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


}
