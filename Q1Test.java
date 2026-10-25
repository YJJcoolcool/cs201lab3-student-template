
import java.util.Arrays;
import java.util.HashSet;

public class Q1Test {
     public static void main(String[] args) {

          String result, traversal1, traversal2, traversal3;

          traversal1 = "1-2";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "2-1-3";
          traversal3 = "1-3-2";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "1-2-3";
          traversal2 = "3-2-1";
          traversal3 = "2-3-1";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println("Expected Result : Invalid traversals");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "3-1-2-5-4";
          traversal2 = "2-1-4-5-3";
          traversal3 = "1-2-3-4-5";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Preorder, Traversal 2 - Postorder, Traversal 3 - Inorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();

          traversal1 = "10-20-30-40-50";
          traversal2 = "20-10-30-40-50";
          traversal3 = "10-50-40-30-20";
          result = verify(traversal1, traversal2, traversal3);
          System.out.println("Traversal 1 : " + traversal1);
          System.out.println("Traversal 2 : " + traversal2);
          System.out.println("Traversal 3 : " + traversal3);
          System.out.println(
                    "Expected Result : Traversal 1 - Inorder, Traversal 2 - Preorder, Traversal 3 - Postorder");
          System.out.println("  Actual Result : " + result);
          System.out.println();
     } 

      // write your codes here
     public static String verify(String traversal1, String traversal2, String traversal3){

          // Check if strings are of different lengths, which would be invalid
          if (!(traversal1.length() == traversal2.length() && traversal2.length() == traversal3.length())) {
               return "Invalid traversals";
          }

          // If valid, split the strings and get the common length
          String[] t1 = traversal1.split("-");
          String[] t2 = traversal2.split("-");
          String[] t3 = traversal3.split("-");
          int length = t1.length;
          // System.out.println("[DEBUG] Length: " + length);

          // Get the root node, which will also determine which string is which traversal
          // Preorder: first element is the root node; Postorder: last element is root node
          // So if there isn't a common root node amongst the 3 strings, they are invalid
          String root = "";
          int preorder = -1;
          int postorder = -1;
          String[] firstElements = new String[3];
          String[] lastElements = new String[3];
          firstElements[0] = t1[0];
          firstElements[1] = t2[0];
          firstElements[2] = t3[0];
          lastElements[0] = t1[length - 1];
          lastElements[1] = t2[length - 1];
          lastElements[2] = t3[length - 1];
          for (int f = 0; f < 3; f++) {
               for (int l = 0; l < 3; l++) {
                    if (f == l) continue; // Don't check the same string

                    // System.out.printf("[DEBUG] f[%d]: %s; l[%d]: %s\n", f, firstElements[f], l, lastElements[l]);
                    if (firstElements[f].equals(lastElements[l])) {
                         if (!root.equals("") && length > 2) { // Not possible for more than one pair of matching first/last elements
                              return "Invalid traversals";
                         }
                         root = firstElements[f];
                         preorder = f;
                         postorder = l;
                    }
               }
          }
          int inorder = 3 - preorder - postorder;
          String[] inorderElements;
          switch(inorder) {
               default:
               case 0:
                    inorderElements = t1;
                    break;
               case 1:
                    inorderElements = t2;
                    break;
               case 2:
                    inorderElements = t3;
                    break;
          }
          String[] preorderElements;
          switch(preorder) {
               default:
               case 0:
                    preorderElements = t1;
                    break;
               case 1:
                    preorderElements = t2;
                    break;
               case 2:
                    preorderElements = t3;
                    break;
          }
          String[] postorderElements;
          switch(postorder) {
               default:
               case 0:
                    postorderElements = t1;
                    break;
               case 1:
                    postorderElements = t2;
                    break;
               case 2:
                    postorderElements = t3;
                    break;
          }

          // Recursively check the subtrees
          if (!validSubtrees(preorderElements, inorderElements, postorderElements, root)) {
               return "Invalid traversals";
          }

          // Format and return the result string
          String[] strToSet = {"t1", "t2", "t3"};
          String result = "Traversal 1 - t1, Traversal 2 - t2, Traversal 3 - t3";
          result = result.replace(strToSet[preorder], "Preorder");
          result = result.replace(strToSet[inorder], "Inorder");
          result = result.replace(strToSet[postorder], "Postorder");
          
          return result;
     }

     public static boolean validSubtrees(String[] preorder, String[] inorder, String[] postorder, String root) {
          int n = preorder.length;

          // All three traversals must have the same number of nodes
          if (inorder.length != n || postorder.length != n) {
               return false;
          }

          // Empty subtrees are valid
          if (n == 0) return true;

          // A single-node subtree must contain the same node
          if (n == 1) {
               return preorder[0].equals(inorder[0]) && inorder[0].equals(postorder[0]) && inorder[0].equals(root);
          }

          // Find the root's position in inorder
          // If it cannot be found, invalid
          int rootIndex = -1;
          for (int i = 0; i < n; i++) {
               if (inorder[i].equals(root)) {
                    rootIndex = i;
                    break;
               }
          }
          if (rootIndex == -1) return false;

          // Check that inorder elements are in increasing order (which must be true for BST)
          for (int i = 0; i < n - 1; i++) {
               if (Integer.parseInt(inorder[i]) >= Integer.parseInt(inorder[i + 1])) {
                    return false;
               }
          }

          int leftSubtreeSize = rootIndex;

          // Collect the nodes belonging to the left subtree
          HashSet<String> leftSubtree = new HashSet<>();

          for (int i = 0; i < rootIndex; i++) {
               leftSubtree.add(inorder[i]);
          }

          // The root must be first in preorder and last in postorder
          if (!preorder[0].equals(root)
                    || !postorder[n - 1].equals(root)) {
               return false;
          }

          // Check that preorder's left-subtree segment contains
          // exactly the nodes belonging to the left subtree
          for (int i = 1; i <= leftSubtreeSize; i++) {
               if (!leftSubtree.contains(preorder[i])) {
                    return false;
               }
          }

          // Check that postorder's left-subtree segment contains
          // exactly the nodes belonging to the left subtree
          for (int i = 0; i < leftSubtreeSize; i++) {
               if (!leftSubtree.contains(postorder[i])) {
                    return false;
               }
          }

          // Determine the sizes of both subtrees
          int rightSubtreeSize = n - leftSubtreeSize - 1;

          // Recursively validate the left subtree
          boolean leftValid = validSubtrees(
                    Arrays.copyOfRange(preorder, 1, leftSubtreeSize + 1),
                    Arrays.copyOfRange(inorder, 0, rootIndex),
                    Arrays.copyOfRange(postorder, 0, leftSubtreeSize),
                    leftSubtreeSize > 0 ? preorder[1] : ""
          );

          if (!leftValid) {
               return false;
          }

          // Recursively validate the right subtree
          boolean rightValid = validSubtrees(
                    Arrays.copyOfRange(preorder, leftSubtreeSize + 1, n),
                    Arrays.copyOfRange(inorder, rootIndex + 1, n),
                    Arrays.copyOfRange(postorder, leftSubtreeSize, n - 1),
                    rightSubtreeSize > 0
                              ? preorder[leftSubtreeSize + 1] : ""
          );

          return rightValid;
     }
}
