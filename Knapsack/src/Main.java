import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        ArrayList<Bar> bars = new ArrayList<>();
        bars.add(new Bar(3, 1));
        bars.add(new Bar(4, 6));
        bars.add(new Bar(5, 4));
        bars.add(new Bar(8, 7));
        bars.add(new Bar(9, 6));

        Knapsack knapsack = new Knapsack(13);

        knapsack.push(bars);
        System.out.println(knapsack);
    }
}