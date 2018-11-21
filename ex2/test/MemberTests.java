import org.junit.Test;
import static org.junit.Assert.*;


public class MemberTests {

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

    @Test
    public void member_branch_test1() {
        Set s = new Set();
        s.insert(2);
        s.insert(3);
        s.insert(5);

        assertFalse(s.member(1));
        assertTrue(s.member(3));
        assertFalse(s.member(99));
    }
}
