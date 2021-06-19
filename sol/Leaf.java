package sol;

import src.IAttributeDatum;
import src.ITreeNode;

public class  Leaf implements ITreeNode {
    public String attributeName;
    public Object finalDecision;


    /**
     * Leaf Constructor
     *
     * @param attributeName the attribute associated with the node.
     * @param finalDecision the final decision deduced
     */
    public Leaf(String attributeName, Object finalDecision){
        this.attributeName = attributeName;
        this.finalDecision = finalDecision;
    }

    @Override
    public Object lookupDecision(IAttributeDatum iAttributeDatum) {
        return this.finalDecision;
    }

    /**
     * A method for printing the leaf of a decision tree
     * @param leadSpace the lead space to print before printing the tree (this is to help form
     */
    @Override
    public void printNode(String leadSpace) {
        System.out.println(leadSpace + finalDecision + " LEAF"); //print attribute
    }
}
