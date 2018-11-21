import org.junit.Test;
import static org.junit.Assert.*;

public class insertBranchTests {
    @Test
    public void insert_branch_test1() {
        Set s = new Set();
        s.insert(2);
        assertArrayEquals(s.toArray(), new int[]{2});
        s.insert(1);
        assertArrayEquals(s.toArray(), new int[]{1,2});
        s.insert(2);
        assertArrayEquals(s.toArray(), new int[]{1,2,2});
    }
}
