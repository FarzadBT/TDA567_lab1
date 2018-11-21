import org.junit.Test;
import static org.junit.Assert.*;


public class MemberStatementTests {

    @Test
    public void member_statement_test1() {
        Set s = new Set();
        s.insert(2);
        assertFalse(s.member(1));
    }

    @Test
    public void member_statement_test2() {
        Set s = new Set();
        s.insert(2);
        s.insert(1);
        s.insert(3);
        assertTrue(s.member(2));
    }

    @Test
    public void member_statement_test3() {
        Set s = new Set();
        assertFalse(s.member(3));
    }
}
