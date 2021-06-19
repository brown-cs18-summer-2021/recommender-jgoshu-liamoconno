package sol;

import src.ITreeNode;

public class Edge {
    public Object decision;
    public ITreeNode nextNode;
    public String attribute;

    /**
     * Edge Constructor
     *
     * @param decision, determined value associated with edge.
     * @param nextNode, the next node the edge points to going down the tree.
     */
    public Edge(Object decision, ITreeNode nextNode, String attribute){
        this.decision = decision;
        this.nextNode = nextNode;
        this.attribute = attribute;
    }

    /**
     * Helps print the tree with this edge, prepending a given string of lead space, and calling printNode on its
     * nextNode with sufficient lead space.
     * @param leadSpace the lead space to print before printing the tree (this is to help form
     *                  an indented tree structure)
     */
    public void printEdge(String leadSpace){
        //TODO: Implement
        System.out.println(leadSpace + attribute + ": " + decision);
        nextNode.printNode(leadSpace + " ");
    }
}
