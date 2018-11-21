import static org.junit.Assert.*;
import org.junit.Test;

public class InsertTests {
    int[] EMPTY = new int[]{};

    @Test
    public void insert_statement_test1() {
        Set s = new Set();
        assertArrayEquals(s.toArray(), EMPTY);
        s.insert(1);
        assertArrayEquals(s.toArray(), new int[]{1});
    }

    /**
     * Contains bug: Insert method is faulty
     */
    @Test
    public void insert_statement_test2() {
        Set s = new Set();
        assertArrayEquals(s.toArray(), EMPTY);
        s.insert(2);
        s.insert(1);
        assertArrayEquals(s.toArray(), new int[]{1,2});
    }

    /**
     * Contains bug: Insert method is faulty
     */
    @Test
    public void insert_statement_test3() {
        Set s = new Set();
        assertArrayEquals(s.toArray(), EMPTY);
        s.insert(1);
        s.insert(2);
        s.insert(3);
        s.insert(1);
        assertArrayEquals(s.toArray(), new int[]{1,1,2,3});
    }

    /**
     * Contains bug: Insert method faulty
     */
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
