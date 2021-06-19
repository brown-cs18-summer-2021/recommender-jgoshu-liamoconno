package sol;

import src.IAttributeDataset;
import src.IAttributeDatum;
import java.util.List;
import java.util.LinkedList;

/**
 * Class for a table (collection of rows) of data.
 * @param <T> the type of the data objects
 */
public class DataTable<T extends IAttributeDatum> implements IAttributeDataset<T> {
    public List<String> attributes;
    public List<T> dataObjects;

    public DataTable(List<String> attributes, List<T> dataObjects) {
        this.attributes = attributes;
        this.dataObjects = dataObjects;
    }

    /**
     * Gets the attributes of the dataset.
     * @return the list of attributes
     */
    @Override
    public List<String> getAttributes() {
        return this.attributes;
    }

    /**
     * Gets the data of the dataset.
     * @return the list of data objects
     */
    @Override
    public List<T> getDataObjects() {
        return this.dataObjects;
    }

    /**
     * Gets the size of the dataset.
     * @return the number of data/rows in the dataset
     */
    @Override
    public int size() {
        return this.dataObjects.size();
    }

    /**
     * Determines whether every datum in the dataset has the same value of a
     * given attribute.
     * @param ofAttribute the given attribute
     * @return true if all data in the dataset have the same value for
     * ofAttribute,false otherwise
     */
    @Override
    public boolean allSameValue(String ofAttribute) {
        if (this.dataObjects.isEmpty()){
            throw new RuntimeException("The Dataset is empty.");
        }
        Object value = this.dataObjects.get(0).getValueOf(ofAttribute);
        for(T item : this.dataObjects){
            if(!item.getValueOf(ofAttribute).equals(value)){
                return false;
            }
        }
        return true;
    }

    /**
     * Gets the shared value of a given attribute, assuming all data in the
     * dataset have the same value
     * of that attribute.
     * @param ofAttribute the given attribute
     * @return the common value for the given attribute
     */
    @Override
    public Object getSharedValue(String ofAttribute) {
        if (this.dataObjects.isEmpty()) {
            throw new RuntimeException("The Dataset is empty.");
        } else {
            return this.dataObjects.get(0).getValueOf(ofAttribute);
        }
    }

    /**
     * Gets the most common value of a given attribute across the dataset.
     * @param ofAttribute the given attribute
     * @return the most common value for an attribute across every datum
     * in the dataset
     */
    @Override
    public Object mostCommonValue(String ofAttribute) {
        //find a more efficient way?
        //ask about if multiple same occurrence
        if(!this.dataObjects.isEmpty()){
            int maxLength = 0;
            Object currentMCV = null;
            List<IAttributeDataset<T>> partitioned = this.partition(ofAttribute);
            for(IAttributeDataset<T> dataset : partitioned){
                if(dataset.size() > maxLength){
                    maxLength = dataset.size();
                    currentMCV = dataset.getSharedValue(ofAttribute);
                }
            }
            return currentMCV;
        } else {
            throw new RuntimeException("mostCommonValue error: DataObjects is empty");
        }
    }

    /**
     * Partitions the dataset into subsets such that each subset contains
     * only data/rows with the same values
     * of the given attribute.
     * @param onAttribute the attribute on which to split the dataset
     * @return new datasets, where each dataset contains only data with the
     * same value of onAttribute
     */
    @Override
    public List<IAttributeDataset<T>> partition(String onAttribute) {
        if (!this.dataObjects.isEmpty()) {
            //go through, put in dataset if match any getSharedValue, if not create new dataset
            List<IAttributeDataset<T>> partitionedDatasets = new LinkedList<>(); //return List
            for (T item : this.dataObjects) {
                boolean foundMatch = false;
                for (IAttributeDataset<T> dataset : partitionedDatasets) {
                    if (item.getValueOf(onAttribute).equals(dataset.getSharedValue(onAttribute))) {
                        dataset.getDataObjects().add(item);
                        foundMatch = true;
                    }
                }
                if (!foundMatch) {
                    IAttributeDataset<T> newDataSet = new DataTable<T>(this.attributes, new LinkedList<T>());
                    newDataSet.getDataObjects().add(item); //use methods not field
                    partitionedDatasets.add(newDataSet);
                }
            }
            return partitionedDatasets;
        } else {
            throw new RuntimeException("partition error: DataObjects is empty");
        }
    }

}

