package sol;

import src.IAttributeDataset;
import sol.Vegetable;
import src.ITreeGenerator;
import src.ITreeNode;
import tester.Tester;

import java.util.LinkedList;
import java.util.List;

public class RecommenderTestSuite {

    // TODO: write unit tests for your implementations of IAttributeDatum,
    //  IAttributeDataset, and ITreeNode
    public Vegetable spinach;
    public Vegetable kale;
    public Vegetable peas;
    public Vegetable carrot;
    public Vegetable lettuce;
    public IAttributeDataset<Vegetable> vegTable;
    public LinkedList<Vegetable> vegDataObjects;
    public LinkedList<String> vegAttributes;
    public IAttributeDataset<Vegetable> emptyVegTable;
    public LinkedList<Vegetable> part1VegTableObj;
    public IAttributeDataset<Vegetable> part1VegTable;
    public IAttributeDataset<Vegetable> part2VegTable;
    public LinkedList<Vegetable> part2VegTableObj;
    public LinkedList<IAttributeDataset<Vegetable>> partVegTable;

    // TODO: write unit tests for your implementations of IAttributeDatum,
    //  IAttributeDataset, and ITreeNode

    /**
     * A method for setting up tests for methods in the Vegetable class
     */
    public void setupVegetable(){
        this.spinach = new Vegetable("green", true, true, false);
        this.kale = new Vegetable("green", true, true, true);
        this.peas = new Vegetable("green", false, true, true);
        this.carrot = new Vegetable("orange", false, false, false);
        this.lettuce = new Vegetable("green", true, false, true);
    }
    /**
     * A method for setting up tests for methods in the DataTable class
     */
    public void setupDataTable(){
        setupVegetable();
        this.vegDataObjects = new LinkedList<>(); //check what type of list is best to use
        this.vegDataObjects.add(spinach);
        this.vegDataObjects.add(kale);
        this.vegDataObjects.add(peas);
        this.vegDataObjects.add(carrot);
        this.vegDataObjects.add(lettuce);
        this.vegAttributes = new LinkedList<>();
        this.vegAttributes.add("color");
        this.vegAttributes.add("low carb");
        this.vegAttributes.add("high fiber");
        this.vegAttributes.add("like to eat");
        this.vegTable = new DataTable<Vegetable>(vegAttributes, vegDataObjects);
        this.emptyVegTable = new DataTable<Vegetable>(new LinkedList<>(), new LinkedList<>());

        this.part1VegTableObj = new LinkedList<Vegetable>();
        this.part1VegTableObj.add(spinach);
        this.part1VegTableObj.add(kale);
        this.part1VegTableObj.add(lettuce);
        this.part1VegTable = new DataTable<>(vegAttributes, part1VegTableObj);

        this.part2VegTableObj = new LinkedList<Vegetable>();
        this.part2VegTableObj.add(peas);
        this.part2VegTableObj.add(carrot);
        this.part2VegTable = new DataTable<>(vegAttributes, part2VegTableObj);

        this.partVegTable = new LinkedList<>();
        this.partVegTable.add(part1VegTable);
        this.partVegTable.add(part2VegTable);
    };

    /**
     * A method for testing the getValueOf method in the Vegetable class
     * @param t - a tester
     */
    public void testGetValueOfVeg(Tester t){
        setupVegetable();
        t.checkExpect(peas.getValueOf("color"), "green");
        t.checkExpect(kale.getValueOf("low carb"), true);
        t.checkExpect(spinach.getValueOf("high fiber"), true);
        t.checkExpect(carrot.getValueOf("like to eat"), false);
        t.checkException(new IllegalArgumentException("invalid value type"), lettuce, "getValueOf", "shoe size");
    }

    /**
     * A method for testing the getAttributes method in the DataTable class
     * @param t - a tester
     */
    public void testGetAttributesDataTable(Tester t){
        setupDataTable();
        t.checkExpect(this.vegTable.getAttributes(), this.vegAttributes);
    };

    /**
     * A method for testing the getDataObjects method in the DataTable class
     * @param t - a tester
     */
    public void testGetDataObjectsDataTable(Tester t){
        setupDataTable();
        t.checkExpect(this.vegTable.getDataObjects(), this.vegDataObjects);
    };

    /**
     * A method for testing the size method in the DataTable class
     * @param t - a tester
     */
    public void testSizeDataTable(Tester t){
        setupDataTable();
        t.checkExpect(this.vegTable.size(), 5);
    };

    /**
     * A method for testing the allSameValue method in the DataTable class
     * @param t - a tester
     */
    public void testAllSameValueDataTable(Tester t){
        setupDataTable();
        t.checkExpect(!this.vegTable.allSameValue("like to eat"));
        this.vegTable.getDataObjects().remove(carrot);
        t.checkExpect(this.vegTable.allSameValue("color"));
        t.checkException(new RuntimeException("The Dataset is empty."), emptyVegTable, "allSameValue", "color");
    };

    /**
     * A method for testing the getSharedValue method in the DataTable class
     * @param t - a tester
     */
    public void testGetSharedValueDataTable(Tester t){
        setupDataTable();
        t.checkExpect(vegTable.partition("color").get(0).getSharedValue("color"), "green");
        t.checkExpect(vegTable.partition("low carb").get(0).getSharedValue("low carb"), true);
        t.checkException(new RuntimeException("The Dataset is empty."), emptyVegTable, "getSharedValue", "color");
    };

    /**
     * A method for testing the mostCommonValue method in the DataTable class
     * @param t - a tester
     */
    public void testMostCommonValueDataTable(Tester t){
        setupDataTable();
        t.checkExpect(vegTable.mostCommonValue("color"), "green");
        t.checkExpect(vegTable.mostCommonValue("like to eat"), true);
        t.checkExpect(vegTable.mostCommonValue("high fiber"), true);
        t.checkException(new RuntimeException("mostCommonValue error: DataObjects is empty"),
                emptyVegTable, "mostCommonValue", "color");

    };

    /**
     * A method for testing the partition method in the DataTable class
     * @param t - a tester
     */
    public void testPartitionDataTable(Tester t){
        setupDataTable();
        t.checkException(new RuntimeException("partition error: DataObjects is empty"),
                emptyVegTable, "partition", "color");
        t.checkExpect(vegTable.partition("color").get(0).allSameValue("color"));
        t.checkExpect(vegTable.partition("high fiber").get(0).allSameValue("high fiber"));
        t.checkExpect(vegTable.partition("like to eat").get(0).allSameValue("like to eat"));
        //t.checkExpect(vegTable.partition("low carb"), partVegTable);
    };

    /**
     * A method for testing the lookupDecision method in the Node class
     * @param t - a tester
     */
    public void testLookupDecisionNode(Tester t){};

    /**
     * A method for testing the lookupDecision method in the Leaf class
     * @param t - a tester
     */
    public void testLookupDecisionLeaf(Tester t){};

    public static void main(String[] args) {
        Tester.run(new RecommenderTestSuite());
    }
}
