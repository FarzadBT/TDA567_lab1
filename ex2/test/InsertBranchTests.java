import org.junit.Assert;
import org.junit.Test;

public class InsertBranchTests {
    /**
     * Contains bug: Insert method faulty
     */
    @Test
    public void insert_branch_test1() {
        Set s = new Set();
        s.insert(2);
        Assert.assertArrayEquals(s.toArray(), new int[]{2});
        s.insert(1);
        Assert.assertArrayEquals(s.toArray(), new int[]{1,2});
        s.insert(2);
        Assert.assertArrayEquals(s.toArray(), new int[]{1,2,2});
    }
}
