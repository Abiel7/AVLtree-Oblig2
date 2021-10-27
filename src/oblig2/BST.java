package oblig2;


import java.util.ArrayList;
import java.util.Iterator;

/**
 * @param <E>
 */

/**
 * @param <E>
 * Denne klassa implementerer to generiske grensesnitt. Det er Comparable og Tree.
 * Den har to konstruktør metoder, ein som er tom og den andre tar imot ein Array med generiske verdier.
 * Desse blir satt inn i treet ved hjelp av insert metoden som er putta inn i ein for-loop
 * Den har ein static class som heiter TreeNode som også extender Comparable grensesnittet og begge er generiske.
 * Denne klassa har Denne klassa implementerer Comparable og Tree-grensesnittet. Begge er generiske.
 * Den har to konstruktør metoder. Ein tom og ein som tar inn ein "
 */
public class BST<E extends  Comparable<E>> implements Tree<E> {
    protected  TreeNode<E> root ;
    protected  int size = 0;

    public BST() {
    }

    public BST(E[] e){
        for (int i = 0; i < e.length; i++) {
            insert(e[i]);
        }
    }

    public static  class TreeNode<E extends  Comparable<E>>{
        public E element;
       public   TreeNode<E> left ;
       public TreeNode<E> right;

        public  TreeNode(E e){
            element = e;
        }
     }

    /**
     * @param e
     * @return
     */
    @Override
    public boolean search(E e) {
        TreeNode<E> current = root ;
        while (current!=null){
            if(e.compareTo(current.element)<0){
                current =  current.left;
            }
            else if(e.compareTo(current.element)>0){
                current =  current.left;
            }
            else return true;
        }
        return false;
    }

    /**
     * @param e
     * @return
     */
    @Override
    public boolean insert(E e) {
        if(root == null ) root =  createNewNode(e);
        else{
            TreeNode<E> parent = null;
            TreeNode<E> current = root;

            while(current!=null){
                if (e.compareTo(current.element)<0){
                    parent= current;
                    current = current.left;
                }
                else if(e.compareTo(current.element)>0){
                    parent = current;
                    current = current.right;
                }
                else return   false;
            }
            if(e.compareTo(parent.element)<0)
                parent.left= createNewNode(e);
            else
                parent.right =  createNewNode(e);

        }
        size++;
        return true;
    }


    /**
     * sletting av elementer fra et tre må vi vurdere 2 tilfeller
     * case 1  den nåværende noden har ikke  venstre barn
     * case 2 den nåværende noden har venstre barn
     *
     * */
    @Override
    public boolean delete(E e) {
        TreeNode<E> parent =  null;
        TreeNode<E> current =  root;

        while(current!=null){
            if(e.compareTo(current.element)<0){
                parent =current;
                current =current.left;
            }
            else if( e.compareTo(current.element)>0){
                parent =  current;
                current = current.right;
            } else break;
        }
        if(current == null) return  false ;

        // case 1 current has no left child
        if (current.left ==null) {
            if(parent ==null){
                root =  current.right;
            }
            else{
                if(e.compareTo(parent.element)<0){
                    parent.left = current.right;
                }
                else parent.right =  current.right;
            }
        }
        else{

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

        }
        size--;

        return true;
    }

    /**
     * return antall noder i treet
     * @return
     */
    @Override
    public int getSize() {
        return size;
    }


    protected TreeNode<E> createNewNode(E e) {
        return  new TreeNode<>(e);
    }
    @Override
    public Iterator<E> iterator() {
        return new InorderIterator();
    }


    /**
     * return  path fra  angitte elementet og start fra root noden
     * @param e
     * @return
     */
    public ArrayList<TreeNode<E>> path(E e){
        ArrayList<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current =  root;

        while (current!=null){
            list.add(current);
            if (e.compareTo(current.element)<0){
                current = current.left;
            }
            else  if( e.compareTo(current.element)>0){
                current = current.right;
            }
            else break;
        }
        return list;
    }


    public TreeNode<E> getRoot() {
        return root;
    }

    /**
     * rydder tre
     */
    public void clear() {
        root = null;
        size = 0;
    }


    /** iterator class */
    private class InorderIterator implements Iterator<E>{
    private ArrayList<E>  list = new ArrayList<>();
    private int current  = 0;
        @Override
        public boolean hasNext() {
            if(current < list.size()) return true;
            return false;
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            delete(list.get(current)); // Delete the current element
            list.clear(); // Clear the list
           // inorder();
            Iterator.super.remove();
        }


    }
}
