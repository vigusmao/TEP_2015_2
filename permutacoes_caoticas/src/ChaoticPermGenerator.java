import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: vigusmao
 * Date: 10/27/15
 * Time: 11:27 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChaoticPermGenerator {

    private static Map<Integer, Long> factorialMemo = new HashMap<Integer, Long>();

    public static Set<List<Integer>> obtainChaoticPerm(int n) {
        Set<List<Integer>> result = new HashSet<List<Integer>>();
        List<Integer> partialPerm = new ArrayList<Integer>(n);
        backtrack(n, partialPerm, result);
        return result;
    }

    private static void backtrack(final int n,
                           final List<Integer> partialPerm,
                           final Set<List<Integer>> perms) {
         // tests the current state
        if (partialPerm.size() == n) {
            List<Integer> perm = new ArrayList<Integer>(partialPerm);
            perms.add(perm);
            return;
        }

        for (int next = 0; next < n; next++) {
            if (next != partialPerm.size() &&
                    !partialPerm.contains(next)) {
                // creates the next state
                partialPerm.add(next);

                backtrack(n, partialPerm, perms);

                // goes back to the former state
                partialPerm.remove(partialPerm.size() - 1);
            }
        }
    }



    public static long factorial(int n) {
        if (factorialMemo.containsKey(n)) {
            return factorialMemo.get(n);
        }
        long result = 1;
        if (n > 1) {
            result = n * factorial(n - 1);
        }
        factorialMemo.put(n, result);
        return result;
    }

    public static int signal(int n) {
        return n % 2 == 0 ? 1 : -1;
    }

    public static long countChaoticPerm(int n) {
        // Dn = n!.[1 - 1/1! + 1/2! - 1/3! + 1/4! - ...+(-1)^n.1/n!]
        long n_factorial = factorial(n);
        long result = 0;
        for (int term = 0; term <= n; term++) {
            result += signal(term) * n_factorial / factorial(term);
        }
        return result;
    }

    public static void main(String[] args) {
//        Set<List<Integer>> permutations = obtainChaoticPerm(5);
//        for (List<Integer> permutation : permutations) {
//            System.out.println(permutation);
//        }
//        System.out.println(String.format("#permutations = %d", permutations.size()));

        long start = System.nanoTime();
        for (int n = 1; n <= 50; n++) {
           countChaoticPerm(n);
        }
        System.out.println((System.nanoTime() - start) / 1000);
    }
}
