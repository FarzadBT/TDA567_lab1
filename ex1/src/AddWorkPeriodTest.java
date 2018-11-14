import org.junit.Test;

import static org.junit.Assert.*;

public class AddWorkPeriodTest {

    private WorkSchedule schedule;
    private static final int HOURS = 12;
    private static final String[] EMPTY_LIST = new String[0];

    /**
     * Helper method, used by other methods for set-up
     * @param start
     * @param end
     * @param required
     * @param workers
     */
    private void testScheduled(int start, int end, int required, String[] workers) {
        for (int i = start; i < end; i++) {
            WorkSchedule.Hour h = schedule.readSchedule(i);
            assertEquals(required, h.requiredNumber);
            assertArrayEquals(workers, h.workingEmployees);
        }
    }

    /**
     * Test partition #1, starttime < 0
     */
    @Test
    public void test_addWorkingPeriod_part1() {
        schedule = new WorkSchedule(HOURS);
        schedule.setRequiredNumber(1, 0, HOURS - 1);

        testScheduled(0, HOURS, 1, EMPTY_LIST);
        assertFalse(schedule.addWorkingPeriod("", -1, 5));
        testScheduled(0, HOURS, 1, EMPTY_LIST);
    }

    /**
     * Test partition #2, endtime > size
     */
    @Test
    public void test_addWorkingPeriod_part2a() {
        schedule = new WorkSchedule(HOURS);
        schedule.setRequiredNumber(1, 0, HOURS - 1);

        testScheduled(0, HOURS, 1, EMPTY_LIST);
        assertFalse(schedule.addWorkingPeriod("", 0, HOURS + 1));
        testScheduled(0, HOURS, 1, EMPTY_LIST);
    }

    /**
     * Test partition #2, endtime == size
     */
    @Test
    public void test_addWorkingPeriod_part2b() {
        schedule = new WorkSchedule(HOURS);
        schedule.setRequiredNumber(1, 0, HOURS - 1);

        testScheduled(0, HOURS, 1, EMPTY_LIST);
        assertFalse(schedule.addWorkingPeriod("", 0, HOURS));
        testScheduled(0, HOURS, 1, EMPTY_LIST);
    }

    /**
     * Test partition #3, starttime > endtime
     * Bug
     */
    @Test
    public void test_addWorkingPeriod_part3() {
        schedule = new WorkSchedule(HOURS);
        schedule.setRequiredNumber(1, 0, HOURS - 1);

        testScheduled(0, HOURS, 1, EMPTY_LIST);
        assertFalse(schedule.addWorkingPeriod("", 3, 2));
        testScheduled(0, HOURS, 1, EMPTY_LIST);
    }

    /**
     * Test Partition #4, starttime >= 0 && starttime < endtime && employee != ""
     */
    @Test
    public void test_addWorkingPeriod_part4() {
        //Setup
        schedule = new WorkSchedule(HOURS);
        schedule.setRequiredNumber(1, 0, 4);

        //Test
        testScheduled(0, 3, 1, EMPTY_LIST);
        testScheduled(5, HOURS, 0, EMPTY_LIST);
        assertTrue(schedule.addWorkingPeriod("bob", 0, 3));
        testScheduled(0, 3, 1, new String[] {"bob"});
        testScheduled(4, 4, 1, EMPTY_LIST);
        testScheduled(5, HOURS, 0, EMPTY_LIST);
    }

    /**
     * Test partition #5, two employees, same time slot with only one allowed
     */
    @Test
    public void test_addWorkingPeriod_part5() {
        schedule = new WorkSchedule(HOURS);
        //Add 1 required amount of employees between 0-4
        schedule.setRequiredNumber(1, 0, 3);

        // Verify that no one is scheduled and one is required
        testScheduled(0, 3, 1, EMPTY_LIST);
        testScheduled(4, HOURS, 0, EMPTY_LIST);
        assertTrue(schedule.addWorkingPeriod("bob", 1, 3));

        // Verify that one is scheduled and one is required
        testScheduled(0, 0, 1, EMPTY_LIST);
        testScheduled(1, 3, 1, new String[] {"bob"});
        testScheduled(4, HOURS, 0, EMPTY_LIST);
        assertFalse(schedule.addWorkingPeriod("sam", 1, 3));

        // Verify that one is still scheduled and one is required
        testScheduled(0, 0, 1, EMPTY_LIST);
        testScheduled(1, 3, 1, new String[] {"bob"});
        testScheduled(4, HOURS, 0, EMPTY_LIST);
    }

    /**
     * Test partition #6, one employee on overlapping schedules
     */
    @Test
    public void test_addWorkingPeriod_part6() {
        schedule = new WorkSchedule(HOURS);
        //Add 1 required amount of employees between 0-4
        schedule.setRequiredNumber(1, 0, 3);

        // Verify that no one is scheduled and one is required
        testScheduled(0, 3, 1, EMPTY_LIST);
        testScheduled(4, HOURS, 0, EMPTY_LIST);
        assertTrue(schedule.addWorkingPeriod("bob", 1, 3));

        // Verify that no one is scheduled and one is required
        testScheduled(0, 0, 1, EMPTY_LIST);
        testScheduled(1, 3, 1, new String[] {"bob"});
        testScheduled(4, HOURS, 0, EMPTY_LIST);
        assertFalse(schedule.addWorkingPeriod("bob", 3, 4));

        // Verify that no one is scheduled and one is required
        testScheduled(0, 0, 0, EMPTY_LIST);
        testScheduled(1, 3, 1, new String[] {"bob"});
        testScheduled(4, HOURS, 0, EMPTY_LIST);
    }

    /**
     * Test partition #7, two employee on overlapping schedules with two required
     */
    @Test
    public void test_addWorkingPeriod_part7() {
        schedule = new WorkSchedule(HOURS);
        //Add 1 required amount of employees between 0-4
        schedule.setRequiredNumber(2, 0, 4);

        // Verify that no one is scheduled and two is required
        testScheduled(0, 4, 2, EMPTY_LIST);
        testScheduled(5, HOURS, 0, EMPTY_LIST);
        assertTrue(schedule.addWorkingPeriod("bob", 1, 4));

        // Verify that one is scheduled and two is required
        testScheduled(0, 0, 2, EMPTY_LIST);
        testScheduled(1, 4, 2, new String[] {"bob"});
        testScheduled(5, HOURS, 0, EMPTY_LIST);
        assertTrue(schedule.addWorkingPeriod("sam", 2, 3));

        // Verify that no one is scheduled and one is required
        testScheduled(0, 0, 0, EMPTY_LIST);
        testScheduled(1, 1, 2, new String[] {"bob"});
        testScheduled(2, 3, 2, new String[] {"bob", "sam"});
        testScheduled(4, 4, 2, new String[] {"bob"});
        testScheduled(5, HOURS, 0, EMPTY_LIST);
    }

    /**
     * Test partition #8, add an employee when none is required
     */
    @Test
    public void test_addWorkingPeriod_part8() {
        schedule = new WorkSchedule(HOURS);

        // Verify that no one is scheduled
        testScheduled(0, HOURS, 0, EMPTY_LIST);

        //Try to add an employee with no name
        assertFalse(schedule.addWorkingPeriod("bob", 1, 3));

        // Verify that one is scheduled
        testScheduled(0, HOURS, 0, EMPTY_LIST);
    }

    /**
     * Test partition #6, two employee on overlapping schedules with one required
     */
    @Test
    public void test_addWorkingPeriod_part9() {
        schedule = new WorkSchedule(HOURS);
        //Add 1 required amount of employees between 0-4
        schedule.setRequiredNumber(1, 0, 3);

        // Verify that no one is scheduled and one is required
        testScheduled(0, 3, 1, EMPTY_LIST);
        testScheduled(4, HOURS, 0, EMPTY_LIST);
        assertTrue(schedule.addWorkingPeriod("bob", 1, 3));

        // Verify that no one is scheduled and one is required
        testScheduled(0, 0, 1, EMPTY_LIST);
        testScheduled(1, 3, 1, new String[] {"bob"});
        testScheduled(4, HOURS, 0, EMPTY_LIST);
        assertFalse(schedule.addWorkingPeriod("sam", 3, 4));

        // Verify that no one is scheduled and one is required
        testScheduled(0, 0, 0, EMPTY_LIST);
        testScheduled(1, 3, 1, new String[] {"bob"});
        testScheduled(4, HOURS, 0, EMPTY_LIST);
    }

    /**
     * Test border case #1, employee = ""
     */
    @Test
    public void test_addWorkingPeriod_border1() {
        schedule = new WorkSchedule(HOURS);
        schedule.setRequiredNumber(1, 0, 4);

        // Verify that no one is scheduled
        testScheduled(0, 4, 1, EMPTY_LIST);
        testScheduled(5, HOURS, 0, EMPTY_LIST);

        //Try to add an employee with no name
        assertTrue(schedule.addWorkingPeriod("", 1, 3));

        // Verify that one is scheduled
        testScheduled(0, 0, 1, EMPTY_LIST);
        testScheduled(1, 3, 1, new String[] {""});
        testScheduled(4, 4, 1, EMPTY_LIST);
        testScheduled(5, HOURS, 0, EMPTY_LIST);
        // Bug? that you can register empty name
    }
}
