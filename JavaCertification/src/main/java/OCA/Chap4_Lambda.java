package OCA;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Chap4_Lambda {

}

/**
 * @see page 209
 */

class Animal {
    private String species;
    private boolean canHop;
    private boolean canSwim;

    public Animal(String speciesName, boolean hopper, boolean swimmer) {
        species = speciesName;
        canHop = hopper;
        canSwim = swimmer;
    }

    public boolean canHop() { return canHop; }
    public boolean canSwim() { return canSwim; }
    public String toString() { return species; }
}

// funtionnal interface, interface with one method
interface CheckTrait {
    boolean test(Animal a);
}

class CheckIfHopper implements CheckTrait {
    public boolean test(Animal a) {
        return a.canHop();
    }
}

class TraditionalSearch {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<Animal>(); // list of animals
        animals.add(new Animal("fish", false, true));
        animals.add(new Animal("kangaroo", true, false));
        animals.add(new Animal("rabbit", true, false));
        animals.add(new Animal("turtle", false, true));

        print(animals, new CheckIfHopper()); // pass class that does check
    }

    private static void print(List<Animal> animals, CheckTrait checker) {
        for (Animal animal : animals) {
            if (checker.test(animal)) // the general check
                System.out.print(animal + " ");
        }
        System.out.println();
    }
}

class LambdaSearch {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<Animal>(); // list of animals
        animals.add(new Animal("fish", false, true));
        animals.add(new Animal("kangaroo", true, false));
        animals.add(new Animal("rabbit", true, false));
        animals.add(new Animal("turtle", false, true));

        print(animals, a -> a.canHop());
        // Animals that can swim.
        print(animals, a -> a.canSwim());
        // Animals that cannot swim?
        print(animals, a -> !a.canSwim());
    }

    private static void print(List<Animal> animals, CheckTrait checker) {
        for (Animal animal : animals) {
            if (checker.test(animal)) // the general check
                System.out.print(animal + " ");
        }
        System.out.println();
    }
}

class PredicateSearch {
    public static void main(String[] args) {
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("fish", false, true));

        print(animals, a -> a.canHop());
        
        print(new Animal("fish", false, true), a -> a.canHop());
        print(new Animal("kangaroo", true, false), a -> a.canHop());
    }

    private static void print(List<Animal> animals, Predicate<Animal> checker) {
        for (Animal animal : animals) {
            if (checker.test(animal))
                System.out.print(animal + " ");
        }
        System.out.println();
    }
    
    private static void print(Animal animal, CheckTrait trait) {
        if(trait.test(animal))
        System.out.println(animal);
    }
}

/** Create instance of runnable type */
interface Runnable {
    public void run();
}

class TestRunnable {
    public static void main(String[] args) {
            Runnable run = () -> System.out.println("Run");
            // DOES not compile
            // Runnable run = () -> { System.out.println("Run"); };
            // Runnable run = () > System.out.println("Run");
            // Runnable run = -> System.out.println("Run");
    }
}
