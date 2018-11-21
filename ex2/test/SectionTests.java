import org.junit.Test;
import static org.junit.Assert.*;

public class SectionTests {
    /**
     * Contains bug: faulty statement method
     */
    @Test
    public void section_statement_branch_test1() {
        Set s = new Set();
        Set a = new Set();

        a.insert(1);
        a.insert(2);
        a.insert(5);

        s.insert(2);
        s.insert(3);
        s.insert(5);

        a.section(s);

        assertArrayEquals(a.toArray(), new int[]{1});
    }
}
