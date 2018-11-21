import org.junit.Test;
import static org.junit.Assert.*;

public class ContainsArithTripleTests {
    @Test
    public void containsArithTriple_branch_statement_test1() {
        Set s = new Set();

        s.insert(2);
        s.insert(5);
        s.insert(8);

        assertTrue(s.containsArithTriple());
    }

    @Test
    public void containsArithTriple_branch_statement_test2() {
        Set s = new Set();

        s.insert(1);
        s.insert(3);
        s.insert(9);

        assertFalse(s.containsArithTriple());
    }
}
