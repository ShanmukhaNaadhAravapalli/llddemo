package behavioral;
import java.util.*;

interface Iterator<T>{
    boolean hasNext();
    T next();
}

class ArrayListIterator<T> implements Iterator<T>{
    private final List<T> list;
    private int cursor = 0;
    public ArrayListIterator( List<T> list){
        this.list = list;
    }
    @Override
    public boolean hasNext(){
        return cursor < list.size();
    }
    @Override
    public T next(){
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        T element = list.get(cursor);
        cursor++; // Increment the cursor to move to the next element
        return element;
    }
}

class TreePreorderIterator<T> implements Iterator<T>{
    public final TreeNode<T> root;
    public final Stack<TreeNode<T>> st;
    public TreePreorderIterator(TreeNode<T> root){
        st = new Stack<>();
        this.root = root;
        st.push(root);
    }
    @Override
    public boolean hasNext(){
        return !st.isEmpty();
    }
    @Override
    public T next(){
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        TreeNode<T> curr = st.pop();
        if(curr.left!= null)
            st.push(curr.left);
        if(curr.right!= null)
            st.push(curr.right);
        return curr.value;
    }
}

class TreeInorderIterator<T> implements Iterator<T>{
    public final TreeNode<T> root;
    public final Stack<TreeNode<T>> st;
    public TreeInorderIterator(TreeNode<T> root){
        this.root = root;
        st = new Stack<>();
        moveLeft(root);
    }
    public void moveLeft(TreeNode<T> curr){
        while(curr!= null){
            st.push(curr);
            curr = curr.left;
        }
    }
    @Override
    public boolean hasNext(){
        return !st.isEmpty();
    }
    @Override
    public T next(){
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        TreeNode<T> curr = st.pop();
        if(curr.right!= null)
            moveLeft(curr.right);
        return curr.value;
    }
}

class TreeNode<T> {
    T value;
    TreeNode<T> left;
    TreeNode<T> right;
    public TreeNode(T val){
        this.value = val;
    }
}
public class IteratorDemo {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        list.add("here");
        list.add("there");
        list.add("hero");

        TreeNode<Integer> root = new TreeNode<>(4);
        root.left = new TreeNode<>(2);
        root.right = new TreeNode<>(5);
        root.left.left = new TreeNode<>(1);
        root.left.right = new TreeNode<>(3);
        Iterator<String> arryListIteror = new ArrayListIterator<>(list);
        while (arryListIteror.hasNext()) {
            System.out.print(arryListIteror.next() + " ");
        }
        System.out.println();

        // Traverse tree using TreeNodeIterator
        Iterator<Integer> treeNodeIterator = new TreePreorderIterator<>(root);
        while (treeNodeIterator.hasNext()) {
            System.out.print(treeNodeIterator.next() + " ");
        }
        System.out.println();

        treeNodeIterator = new TreeInorderIterator<>(root);
        while (treeNodeIterator.hasNext()) {
            System.out.print(treeNodeIterator.next() + " ");
        }
        System.out.println();

//        Iterator<String> iter = list.iterator();
    }
}
