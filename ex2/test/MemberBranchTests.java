import org.junit.Test;
import static org.junit.Assert.*;

public class MemberBranchTests {
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
