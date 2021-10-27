package oblig2;

import java.util.ArrayList;
import java.util.List;

public class AvlTree<E extends Comparable<E>> extends BST<E> {


    public AvlTree(){}


    public AvlTree(E[] objects){
        super(objects);
    }

    protected  static class AvlTreeNode<E extends  Comparable<E>> extends BST.TreeNode<E>{

        int height  =0;
        int size =0;
        public AvlTreeNode(E e){
            super(e);
        }

        public int getSize() {
            return size;
        }

        public void setSize(int size) {
            this.size = size;
        }
    }

    @Override
    protected AvlTreeNode<E> createNewNode(E e) {
        return new AvlTreeNode<>(e);
    }

    @Override
    public boolean insert(E e) {
        boolean successful =  super.insert(e);

        if(!successful) return false;
        else {
            balancePath(e);
            updateSize();
        }



        return true;
    }
        /** balancere nodene  fra  den angitte noden ved å
         * utføre rotasjoner på nodene helt til de er balancert
         * */
    private void balancePath(E e) {
        ArrayList<TreeNode<E>> path =  path(e);

        for (int i = path.size() -1; i>=0;i--){
            AvlTreeNode<E>  A = (AvlTreeNode<E>)path.get(i);
            updateHeight(A);

            AvlTreeNode<E> parentOfA =  (A ==root) ?null:(AvlTreeNode<E>)(path.get(i-1));

            switch (balanceFactor(A)){
                case -2:
                    if(balanceFactor((AvlTreeNode<E>) A.left)<=0){
                        balanceLL(A,parentOfA);
                    }else{
                        balanceLR(A,parentOfA);
                    }
                    break;

                case +2:
                    if(balanceFactor((AvlTreeNode<E>) A.right)>=0){
                        balanceRR(A,parentOfA);
                    }
                    else{
                        balanceRL(A,parentOfA);
                    }
            }

        }
    }


/**
 * 1                    2
 *  \                  / \
 *   2  ----------->  1   3
 *    \
 *     3
 *når en node er ubalansert også at balance factoren ikke er -1 , 0 eller 1 da utfører vi høgre rotasjon
 *
 * */
    private void balanceRR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B= A.right;

        if(A==root){
            root =B;
        }
        else{
            if (parentOfA.left ==A){
                parentOfA.left =B;
            }
            else{
                parentOfA.right =B;
            }
        }

        A.right =B.left;
        B.left = A;
        updateHeight((AvlTreeNode<E>)A);
        updateHeight((AvlTreeNode<E>)B);
    }

    /**
     *     3               2
     *    /               / \
     *   2      --->    1    3
     *  /
     * 1
     *   balancere venstre
     * @param A
     * @param parentOfA
     */
    private void balanceLL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B =  A.left;
        if(A == root) {
            root = B;
        }
        else{
            if (parentOfA.left == A){
                parentOfA.left = B;
            }
            else {
                parentOfA.right = B;
            }
        }
        A.left = B.right;
        B.right  =A;
        updateHeight((AvlTreeNode<E>) A);
        updateHeight((AvlTreeNode<E>) B);
    }

    /**
     *    5
     *   /       --->    4
     *  3               / \
     *   \             3   5
     *    4
     *
     *    balancere venstre høyre
     * */
    private void balanceLR(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B= A.left;
        TreeNode<E> C =B.right;

        if(A == root ){
            root =C;
        }
        else{
            if (parentOfA.left ==A){
                parentOfA.left= C;
            }
            else {
                parentOfA.right = C;
            }
        }
        A.left= C.right;
        B.right = C.left;
        C.left =B;
        C.right = A;
        updateHeight((AvlTreeNode<E>)A);
        updateHeight((AvlTreeNode<E>)B);
        updateHeight((AvlTreeNode<E>)C);

    }

    /**
     *                            4
     *    3                      / \
     *     \                    3   5
     *      5          --->
     *     /
     *    4
     *
     *    balancere høyre sin venstre
     * */
    private void balanceRL(TreeNode<E> A, TreeNode<E> parentOfA) {
        TreeNode<E> B =  A.right;
        TreeNode<E> C =  B.left;

        if(A ==  root){
            root = C;
        }
        else{
            if (parentOfA.left ==A){
                parentOfA.left= C;
            }
            else {
                parentOfA.right = C;
            }
        }

        A.right = C.left;
        B.left = C.right;
        C.left = A;
        C.right = B;

        updateHeight((AvlTreeNode<E>)A);
        updateHeight((AvlTreeNode<E>)B);
        updateHeight((AvlTreeNode<E>)C);

    }


    /**
     * balance faktoren hjelper med å se hvilket node i treet er ubanalcert
     * BF = høyre - venstre  ={-1,0,1}
     * @param node
     * @return
     */
    private int balanceFactor(AvlTreeNode<E> node) {
        if(node.right == null) return -node.height;
        else if(node.left ==  null)  return +node.height;
        else return ((AvlTreeNode<E>)node.right).height - ((AvlTreeNode<E>)node.left).height;
    }

    /**
     * Oppdater høyden til en spesifisert node
     * */
    private void updateHeight(AvlTreeNode<E> node) {
        if (node.left ==  null && node.right== null) { // the node is a leaf node
            node.height =0;
        }
        else if(node.left ==  null) { // when the node has no left subtree
        node.height =  1 +   ((AvlTreeNode<E>)node.right).height;
        }
        else if (node.right == null) {// when the node has no right tree
            node.height =  1 + ((AvlTreeNode<E>)node.left).height;
        }
        else
            node.height =  1  + Math.max(((AvlTreeNode<E>)node.right).height,((AvlTreeNode<E>)node.left).height);
    }

    /**
     * */

    public E find(int k){

        if ((k < 0) || (k > size)) {
            return null;
        } else {
            return find(k, (AvlTreeNode<E>) root);
        }
    }

    public E find(int k,AvlTreeNode<E> node ) {
        AvlTreeNode<E> A=  (AvlTreeNode<E>)node.left;
        AvlTreeNode<E> B=  (AvlTreeNode<E>)node.right;

        if ((A == null)&&(k == 1)) {
            return node.element;
        } else if ((A == null)&&(k == 2)) {
            return B.element;
        } else if(k <= A.size) {
            return find(k, A);
        } else if(k == A.size + 1) {
            return node.element;
        } else {
            return find(k - A.size - 1, B);
        }
    }

    /**
     *
     * */
    public int updateSize(AvlTreeNode<E> node){

        if(node ==  null) return 0;
        else
            node.size = 1 + updateSize((AvlTreeNode<E>)node.left) + updateSize((AvlTreeNode<E>)node.right);
            return node.size;
    }

    /**
     *
     * */

    public void updateSize(){
        updateSize((AvlTreeNode<E>)root);
    }

    /**
     * sletting av elementer fra et tre må vi vurdere 2 tilfeller
     * case 1  den nåværende noden har ikke  venstre barn
     * case 2 den nåværende noden har venstre barn
     * og oppdater størrelse
     * */
    @Override
    public boolean delete(E e) {
        if (root == null)
            return false;
        TreeNode<E> parent =  null;
        TreeNode<E> current =  root;

        while(current!= null) {
            if(e.compareTo(current.element)<0){
                parent =  current;
                current =  current.left;

            } else if (e.compareTo(current.element)>0){
                parent =  current;
                current =  current.right;
            }
            else
                break;
        }
        if(current == null)  return false;

        if (current.left ==  null){
            if(parent ==  null) {
                root =  current.right;
            }
            else{
                if(e.compareTo(parent.element) >0){
                    parent.left =  current.right;
                }
                else
                    parent.right =  current.right;

                balancePath(parent.element);
            }
        }

        else {
            TreeNode<E> parentOfRightMost =current;
            TreeNode<E> rightMost =  current.left;

            while (rightMost.right!=null){
                parentOfRightMost =rightMost;
                rightMost = rightMost.right;
            }

            current.element =  rightMost.element;

            if(parentOfRightMost.right == rightMost)
                parentOfRightMost.right =  rightMost.left;
            else
                parentOfRightMost.left = rightMost.left;

            balancePath(parentOfRightMost.element);
        }


        size--;
        updateSize();
        return true;
    }
   /**
    * caller supper clear i bst
    * */
    public  void clear(){
        super.clear();
    }
    /**
     * generere tilfeldig tall
     * */
    public   int randNumb(int min, int max){
        return (int) ((Math.random() * (max - min))+ min);
    }

    /**
     * generere tilfeldige char
     * **/
    public   char rndChar() {
        int rnd = (int) (Math.random() * 11); // or use Random or whatever
        char base = (rnd < 26) ? 'A' : 'a';
        return (char) (base + rnd % 26);

    }
}
