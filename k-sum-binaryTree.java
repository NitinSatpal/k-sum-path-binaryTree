import java.util.ArrayList;
import java.util.List;

public class BinaryTree {
    class Node{ 
        int data;
        Node left, right;
        Node(){}
        Node(int value) {
            this.data = value;
        }
        Node(int value, Node left, Node right) {
            this.data = value;
            this.left = left;
            this.right = right;
        }
        
        @Override
        public String toString(){
            return Integer.toString(data);
        } 
    }
    
    Node root;
    
    void helper(List<String> result, String currStr, Node node, int sum, int value){
        if(node == null)
            return;
        sum = sum + node.data;
        if(sum == value) {
            String probableRes = currStr;
            probableRes = probableRes + " " + node.toString();
            if(result.size() == 0)
                result.add(probableRes);
            else {
                String savedStr = result.get(0);
                int currentStrLength = probableRes.split(" ").length -1;
                int savedStrLength = savedStr.split(" ").length - 1;
                if(currentStrLength < savedStrLength)
                    result.set(0, probableRes);
            }
        }
        if(currStr.length() != 0)
            currStr = currStr + " ";
        helper(result, currStr + node.toString(), node.left, sum, value);
        helper(result, currStr + node.toString(), node.right, sum ,value);  
    }
    
    void checkPaths(Node root, List<String> result, int value){ 
        if(root == null)
            return;
        helper(result,"", root, 0, value);   
        checkPaths(root.left, result, value);
        checkPaths(root.right, result, value); 
    }
    
    public void printPathsForValue(int value){
        List<String> result = new ArrayList<>();
        checkPaths(root, result, value);
        System.out.println("Path: \n"+result);
    }
    
    public void buildCustomTree(){
        /*root = new Node(1);
        setChildren(root, 2, 3);
        setChildren(root.left, 1, 2);
        setChildren(root.left.right, null, 3);   
        setChildren(root.right, 1, 3); 
        setChildren(root.right.left, null, 4);
        setChildren(root.right.right, null, 2);
        setChildren(root.right.right.right, 1, null);*/
        
       /* root = new Node(1);
        setChildren(root, 2, 3);
        setChildren(root.left, 1, 0);
        setChildren(root.right, 1, 0);
        setChildren(root.left.right, 0, 1);
        setChildren(root.right, 1, 0);
        setChildren(root.left.right, 0, 1);
        setChildren(root.left.right.left, 1, null);
        setChildren(root.right.left, 3, 2);
        setChildren(root.right.left.right, 1, null);*/
        
        //with -ve values as well
        root = new Node(1);
        setChildren(root, 2, 3);
        setChildren(root.left, 1, 0);
        setChildren(root.left.left, -1, null);
        setChildren(root.left.left.left, 1, 2);
        setChildren(root.left.left.left.right, -1, null); 
        setChildren(root.left.right, 0, 1);
        setChildren(root.left.right.left, null, 1);
        setChildren(root.right, 1, 0);
        setChildren(root.right.left, 3, 2);
        setChildren(root.right.left.right, 1, null);
    }
    
    private void setChildren(Node node, Integer left, Integer right){
        if(left!=null)node.left = new Node(left);
        if(right!=null)node.right = new Node(right);
    }
    
    public static void main(String[] args){
        BinaryTree tree = new BinaryTree(); 
        tree.buildCustomTree();
        tree.printPathsForValue(4);
    }
}