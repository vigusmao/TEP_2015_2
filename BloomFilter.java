import sun.nio.cs.StandardCharsets;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vigusmao
 * Date: 1/7/16
 * Time: 10:56 AM
 * To change this template use File | Settings | File Templates.
 */
public class BloomFilter {
    private boolean[] filter;
    private int filterSize;
    private int hashCount;

    public BloomFilter(Collection<Long> elements, int filterSize) {
        this.filterSize = filterSize;
        this.hashCount = (int) Math.ceil(Math.log(2) * filterSize / elements.size());
        this.filter = new boolean[filterSize];

        System.out.println("hashCount = " + hashCount);

        for (Long element : elements) {
            int[] signature = obtainSignature(element);
            for (int pos : signature) {
                this.filter[pos] = true;
            }
        }
    }

    public boolean accepts(long element) {
        int[] signature = obtainSignature(element);
        for (int pos : signature) {
            if (!this.filter[pos]) {
                return false;
            }
        }
        return true;
    }

    private int hash(long x, int index) {
        return Math.abs(Long.valueOf(x * index).hashCode()) % this.filterSize;
    }

    private int[] obtainSignature(long element) {
        int[] result = new int[hashCount];
        for (int i = 0; i < hashCount; i++) {
            int pos = hash(element, i);
            result[i] = pos;
        }
        return result;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean bit : this.filter) {
            sb.append(bit ? "1" : "0");
        }
        return sb.toString();
    }



    public static void main(String[] args) {
        Random random = new Random(1234);
        Set<Long> myElements = new HashSet<Long>();
        int n_elements = 2000;
        int maxNumber = 10000;

        for (int i = 0; i < n_elements; i++) {
            myElements.add((long) random.nextInt(maxNumber));
        }

        BloomFilter filter = new BloomFilter(myElements, 4 * n_elements);
        System.out.println("\nmyElements:\n" + myElements);
        System.out.println(filter.toString());

        int acceptsCount = 0;
        int falsePositives = 0;

        for (int n = 0; n < maxNumber; n++) {
            boolean passesFilter = filter.accepts(n);
            if (passesFilter) {
                acceptsCount++;
            }

            if (myElements.contains((long) n)) {
                if (!passesFilter) {
                    System.out.println("FALSE NEGATIVE!!!!!");
                }
            } else {
                if (filter.accepts(n)) {
                    falsePositives++;
                }
            }
        }

        System.out.println(String.format("Acceptance ratio = %.4f",
                1.0 * acceptsCount / maxNumber));
        System.out.println(String.format("False positives ratio = %.4f",
                1.0 * falsePositives / acceptsCount));
    }


}
