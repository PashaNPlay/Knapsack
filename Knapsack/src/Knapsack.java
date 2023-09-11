import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Knapsack {
    private final int capacity;
    private final ArrayList<Bar> bars = new ArrayList<>();
    public Knapsack(int capacity) {
        this.capacity = Math.max(0, capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getFreeSpace() {
        return capacity - getTotalWeight();
    }

    public ArrayList<Bar> getBarsList() {
        return bars;
    }

    public int getTotalWeight() {
        int totalWeight = 0;
        for (Bar bar : bars) {
            totalWeight += bar.getWeight();
        }
        return totalWeight;
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Bar bar : bars) {
            totalPrice += bar.getPrice();
        }
        return totalPrice;
    }

    public void addBar(Bar bar) {
        if (bar.getWeight() + getTotalWeight() > capacity) {
            System.out.println("Not enough space in the briefcase");
            return;
        }
        bars.add(bar);
    }

    public void addBars(List<Bar> bars) {
        int totalBarsWeight = 0;
        for (Bar bar : bars) {
            totalBarsWeight += bar.getWeight();
        }
        if (totalBarsWeight > capacity - getTotalWeight()) {
            System.out.println("Not enough space in the briefcase");
            return;
        }
        this.bars.addAll(bars);
    }

    public void addBars(Bar[] bars) {
        addBars(Arrays.asList(bars));
    }

    public void push(List<Bar> bars) {
        int count = bars.size();
        int maxWeight = getFreeSpace();

        int[][] A;
        A = new int[count + 1][];
        for (int i = 0; i < count + 1; i++) {
            A[i] = new int[maxWeight + 1];
        }

        for (int k = 0; k <= count; k++) {
            for (int s = 0; s <= maxWeight; s++) {
                if (k == 0 || s == 0) {
                    A[k][s] = 0;
                } else {
                    if (s >= bars.get(k - 1).getWeight()) {
                        A[k][s] = Math.max(
                                A[k - 1][s], A[k - 1][s - bars.get(k - 1).getWeight()] + bars.get(k - 1).getPrice());
                    } else {
                        A[k][s] = A[k - 1][s];
                    }
                }
            }
        }

        traceBars(A, bars, count, maxWeight);
    }

    private void traceBars(int[][] A, List<Bar> bars, int k, int s) {
        if (A[k][s] == 0) {
            return;
        }
        if (A[k - 1][s] == A[k][s]) {
            traceBars(A, bars, k - 1, s);
        } else {
            traceBars(A, bars, k - 1, s - bars.get(k - 1).getWeight());
            this.bars.add(0, bars.get(k - 1));
        }
    }

    public void push(Bar[] bars) {
        push(Arrays.asList(bars));
    }

    public Bar getBar(int index) {
        return bars.get(index);
    }

    public ArrayList<Bar> getBars(int startInd, int endInd) {
        if(startInd > endInd) throw new IllegalArgumentException();
        ArrayList<Bar> bars = new ArrayList<>();
        for (int i = 0; i <= endInd - startInd; i++) {
            bars.add(getBar(startInd));
        }
        return bars;
    }

    public Bar removeBar(int index) {
        return bars.remove(index);
    }

    public ArrayList<Bar> removeBars(int startInd, int endInd) {
        if(startInd > endInd) throw new IllegalArgumentException();
        ArrayList<Bar> removedBars = new ArrayList<>();
        for (int i = 0; i <= endInd - startInd; i++) {
            removedBars.add(removeBar(startInd));
        }
        return removedBars;
    }

    public boolean isEmpty() {
        return bars.isEmpty();
    }

    public void clear() {
        bars.clear();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("Knapsack content:\n");
        for (Bar bar : bars) {
            stringBuilder.append(bar).append("\n");
        }

        return String.format("%sTotal weight - %d\nFree space - %d\nTotal price - %d",
                stringBuilder, getTotalWeight(), getFreeSpace(), getTotalPrice());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Knapsack)) return false;

        Knapsack knapsack = (Knapsack) o;

        if (capacity != knapsack.capacity) return false;
        return bars.equals(knapsack.bars);
    }

    @Override
    public int hashCode() {
        int result = capacity;
        result = 31 * result + bars.hashCode();
        return result;
    }
}
