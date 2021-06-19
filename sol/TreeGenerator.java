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

    @Override
    public ITreeNode buildClassifier(String targetAttribute) {
        LinkedList<String> unusedAttributes = new LinkedList<>(this.trainingData.getAttributes());
        unusedAttributes.remove(targetAttribute);
        ITreeNode newNode = this.buildClassifierHelp(targetAttribute, unusedAttributes);
        this.rootNode = newNode;
        //update root node here
        return newNode;
        
    }

    @Override
    public Object lookupRecommendation(IAttributeDatum datum) {
      return rootNode.lookupDecision(datum);
    }

    @Override
    public void printTree() {
        // TODO: implement
    }
}
