package sol;

import src.IAttributeDatum;
import src.ITreeNode;
import java.util.LinkedList;

public class Node implements ITreeNode{
    public String attributeName;
    public LinkedList<Edge> edgeList;
    public Object mostCommonValue;

    /**
     * Node Constructor
     *
     * @param attributeName the attribute associated with the node.
     * @param edgeList the list of possible edges attached to the node on the tree.
     * @param mostCommonValue the most common value for all possible edges attached to the tree.
     */
    public Node(String attributeName, LinkedList<Edge> edgeList, Object mostCommonValue){
        this.attributeName = attributeName;
        this.edgeList = edgeList;
        this.mostCommonValue = mostCommonValue;
    }

    /**
     * Traverses the decision tree to get the predicted value of the tree's target attribute for a datum.
     * @param iAttributeDatum the datum for which to predict the value of the target attribute
     * @return the predicted value of the target attribute (the attribute the tree was trained to predict)
     */
    @Override
    public Object lookupDecision(IAttributeDatum iAttributeDatum) {
        for(Edge item : this.edgeList){ //Go through tree checking for equal to value of given datum
            if (item.decision == iAttributeDatum.getValueOf(item.attribute)){
                return item.nextNode.lookupDecision(iAttributeDatum);
            }
        }
        return this.mostCommonValue;
    }

    /**
     * Prints the tree rooted at this node, prepending a given string of lead space.
     * @param leadSpace the lead space to print before printing the tree (this is to help form
     *                  an indented tree structure)
     */
    @Override
    public void printNode(String leadSpace) {
        System.out.println(leadSpace + attributeName);
        for(Edge item : this.edgeList){
            item.printEdge(leadSpace + " ");
        }
    }
}
