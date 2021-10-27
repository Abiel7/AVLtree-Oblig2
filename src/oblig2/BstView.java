package oblig2;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;

import java.util.ArrayList;


public class BstView<E extends Comparable<E>> extends Pane {

    private BST<E> tree =  new BST<>();
    private double radius = 15;
    private double vGap = 50;


        /**
         * kunstruktør for bstview  som tar imot bstview
         * **/
    BstView(BST<E> tree){
        this.tree =  tree;

    }

    /**
     * setter tekst i pane
     * */
    public void setStatus(String msg){
        Text text =  new Text(20,20,msg);
        getChildren().addAll(text);


    }

    /**
     * når root ikke er null set treet på pane
     * */
    public  void displayTree(){
        this.getChildren().clear();

        if(tree.getRoot() !=null){
            displayTree( tree.getRoot(),getWidth() / 2,vGap,getWidth() /4);
        }
    }


    /**
     * tegnet treet i pane
     * */

    private void displayTree(BST.TreeNode<E> current,double x ,double y,double hGap){
        if (current.left!=null){
            getChildren().add(new Line(x-hGap,y+vGap,x,y));
            displayTree(current.left,x-hGap,y + vGap,hGap/2);
        }
        if (current.right != null){
            getChildren().add(new Line(x + hGap,y + vGap,x,y));
            displayTree(current.right,x+hGap,y + vGap,hGap/2);
        }

        Circle circle =  new Circle(x,y,radius);
        circle.setFill(Color.WHITE);
        circle.setStroke(Color.BLACK);
        getChildren().addAll(circle,new Text(x-4,y+4,current.element +""));
    }

    /**
     * rydder pane
    **/
    public void restart( ){
        getChildren().clear();
    }


    /**
     * sjekk om input er tall eller  ikke
     * **/

    public  boolean isNumeric(String string) {
        int intValue;
        if(string == null || string.equals("")) {
            return false;
        }
        try {
            intValue = Integer.parseInt(string);
            return true;
        } catch (NumberFormatException e) {
                e.printStackTrace();
        }
        return false;
    }

}
