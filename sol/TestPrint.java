package sol;

import com.sun.source.tree.Tree;
import src.IAttributeDataset;
import tester.Tester;

import java.util.LinkedList;

public class TestPrint {

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

    public void printTree(){
        setupDataTable();
        TreeGenerator<Vegetable> gen = new TreeGenerator<>(vegTable);
        TreeGenerator<Vegetable> gen2 = new TreeGenerator<>(vegTable);
        gen.buildClassifier("like to eat");
        gen2.buildClassifier("like to eat");
        gen.printTree();
        gen2.printTree();
    }

    public static void main(String[] args) {
        TestPrint t = new TestPrint();
        t.printTree();
    }
}
