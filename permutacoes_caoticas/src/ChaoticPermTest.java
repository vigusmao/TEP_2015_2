/**
 * Created with IntelliJ IDEA.
 * User: vigusmao
 * Date: 10/29/15
 * Time: 11:12 AM
 * To change this template use File | Settings | File Templates.
 */
public class ChaoticPermTest {

    public static void NegativeNumberTest() {
        try {
            ChaoticPermGenerator.obtainChaoticPerm(-5);
        } catch (Exception e) {
            // ok
            return;
        }
        System.out.println("Test failed -- should have raised an exception!");
    }

    public static void PositiveNumberTest() {
        for (int i = 1; i <= 6; i++) {
            long obtained = ChaoticPermGenerator.obtainChaoticPerm(i).size();
            long expected = ChaoticPermGenerator.countChaoticPerm(i);
            if (obtained != expected) {
                System.out.println(String.format(
                        "Test failed -- found %d permutations, expected was %d!",
                        obtained, expected));
                return;
            }
        }
    }

    public static void main(String[] args) {
        NegativeNumberTest();
        PositiveNumberTest();

    }
}
