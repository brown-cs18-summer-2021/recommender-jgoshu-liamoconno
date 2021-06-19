package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import src.ITreeGenerator;
import src.ITreeNode;

import java.util.List;
import java.util.LinkedList;
import java.util.Random;

public class TreeGenerator<T extends IAttributeDatum> implements ITreeGenerator {
    ITreeNode rootNode;
    IAttributeDataset<T> trainingData;

    /**
     * Constructor for a tree generator.
     * @param trainingData the training data on which to train the
     *                     decision tree
     */
    public TreeGenerator(IAttributeDataset<T> trainingData) {
          this.trainingData = trainingData;
    }

    /**
     * Trains a decision tree to predict the value of a target attribute.
     * @param targetAttribute the attribute that the decision tree will predict the value of
     * @return a trained decision tree that will predict the value of the target attribute for a given datum
     */
    @Override
    public ITreeNode buildClassifier(String targetAttribute) {
        LinkedList<String> unusedAttributes = new LinkedList<>(this.trainingData.getAttributes());
        unusedAttributes.remove(targetAttribute);
        ITreeNode newNode = this.buildClassifierHelp(targetAttribute, unusedAttributes);
        this.rootNode = newNode;
        //update root node here
        return newNode;
        
    }

    /**
     * A helper function for build classifier. Trains a decision tree to predict the value of a target attribute
     * @param targetAttribute - the attribute that the decision tree will predict the value of
     * @param unusedAttributes - the list of attributes that have yet to be used
     * @return - a trained decision tree that will predict the value of the target attribute for a given datum
     */
    public ITreeNode buildClassifierHelp(String targetAttribute, LinkedList<String> unusedAttributes) {
        //LOOK FOR MUTABILITY ISSUES
        //BUILD CLASSIFIER NEEDS TO WORK IN MULTIPLE RUNS, NOT CHANGING DATASET
        if (this.trainingData.allSameValue(targetAttribute)){ //if all rows in subset have same value for targetAttribute:
            return new Leaf(targetAttribute, trainingData.getSharedValue(targetAttribute));
        } else if (unusedAttributes.isEmpty()) {
//            return decision node (leaf) with most common value of targetAttribute across current subset
            return new Leaf(targetAttribute, this.trainingData.mostCommonValue(targetAttribute));

        } else {
//            choose a previously unused attribute (RANDOMLY)
            Random random = new Random();
            int randomNum = random.nextInt(unusedAttributes.size());

            LinkedList<Edge> edgeList = new LinkedList<>();
            String partAttribute = unusedAttributes.get(randomNum);
            LinkedList<String> copyUnusedAttributes = new LinkedList<>(unusedAttributes);
            copyUnusedAttributes.remove(unusedAttributes.get(randomNum));

            for(IAttributeDataset<T> item : this.trainingData.partition(partAttribute)){
                TreeGenerator<T> newGenerator = new TreeGenerator<>(item);
                Edge newEdge = new Edge(item.getSharedValue(partAttribute),
                        newGenerator.buildClassifierHelp(targetAttribute, copyUnusedAttributes), partAttribute);
                edgeList.add(newEdge);
            }
            Node newNode = new Node(partAttribute, edgeList, trainingData.mostCommonValue(targetAttribute));
            //printTree();
            return newNode;
//            return new node

        }
        
    }

    /**
     * Uses the decision tree trained by buildClassifier to get the predicted value of the target attribute for
     * a given datum.
     * @param datum the datum to classify
     * @return the predicted value (recommendation) of the target attribute for the given datum
     */
    @Override
    public Object lookupRecommendation(IAttributeDatum datum) {
      return rootNode.lookupDecision(datum);
    }

    /**
     * Prints the decision tree trained by buildClassifier.
     */
    @Override
    public void printTree() {
        rootNode.printNode("");
    }
}
