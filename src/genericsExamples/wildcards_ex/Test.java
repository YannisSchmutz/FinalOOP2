package genericsExamples.wildcards_ex;

public class Test {


    public static void main(String[] args){
        Apple apple = new Apple();
        Banana banana = new Banana();
        Fruit fruit = apple;

        Box<Fruit> fruitBox = new Box<>(fruit);
        Box<Apple> appleBox = new Box<>(apple);
        Box<Banana> bananaBox = new Box<>(banana);

        //fruitBox = appleBox;      //todo: Error boxes are invariant
        //appleBox = fruitBox;      //todo: Error boxes are invariant
        //appleBox = bananaBox;     //todo: Error boxes are invariant

        Box rawBox = new Box(apple);    // You should NOT use raw-types!
        rawBox = fruitBox;          // You can assign everything to a RAWTYPE
        rawBox = appleBox;

        Box<? extends Fruit> covariantFruitBox = new Box<>(fruit);  // Upperbound
        covariantFruitBox = appleBox;
        covariantFruitBox = bananaBox;
        covariantFruitBox = fruitBox;
        covariantFruitBox = rawBox;     // Todo: Warning
        rawBox = covariantFruitBox;

        Box<? super Apple> contravariantFruitBox = new Box<>(apple);    // todo: IT is APPLE here
        contravariantFruitBox = fruitBox;
        contravariantFruitBox = appleBox;
        //contravariantFruitBox = bananaBox;  // todo: error
        contravariantFruitBox = rawBox;     // Todo: Warning
        rawBox = contravariantFruitBox;
    }

}
