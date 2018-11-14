import org.junit.Test;

import static org.junit.Assert.*;

public class WorkingEmployeesTest {

    private WorkSchedule schedule;
    private static final int HOURS = 12;
    private static final String[] EMPTY_LIST = new String[0];
    private static final String[] BOB = new String[] {"bob"};
    private static final String[] SAM = new String[] {"sam"};
    private static final String[] BOB_SAM = new String[] {"bob", "sam"};

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
     *
     */
    @Test
    public void test_workingEmployees_part1() {
        schedule = new WorkSchedule(HOURS);

        testScheduled(0, HOURS, 0, EMPTY_LIST);
        assertArrayEquals(new String[0], schedule.workingEmployees(4, 3));
        testScheduled(0, HOURS, 0, EMPTY_LIST);
    }

    /**
     *
     */
    @Test
    public void test_workingEmployees_part2() {
        schedule = new WorkSchedule(HOURS);
        schedule.setRequiredNumber(1, 1, 3);
        schedule.addWorkingPeriod("bob", 1, 3);

        testScheduled(0, 0, 0, EMPTY_LIST);
        testScheduled(1, 3, 1, BOB);
        testScheduled(4, HOURS, 0, EMPTY_LIST);
        assertArrayEquals(BOB, schedule.workingEmployees(1, 5));
        testScheduled(0, 0, 0, EMPTY_LIST);
        testScheduled(1, 3, 1, BOB);
        testScheduled(4, HOURS, 0, EMPTY_LIST);
    }

    /**
     *
     */
    @Test
    public void test_workingEmployees_part3() {
        schedule = new WorkSchedule(HOURS);
        schedule.setRequiredNumber(2, 1, 5);
        schedule.addWorkingPeriod("bob", 1, 3);
        schedule.addWorkingPeriod("sam", 2, 5);

        testScheduled(0, 0, 0, EMPTY_LIST);
        testScheduled(1, 1, 2, BOB);
        testScheduled(2, 3, 2, BOB_SAM);
        testScheduled(4, 5, 2, SAM);
        testScheduled(6, HOURS, 0, EMPTY_LIST);
        assertArrayEquals(BOB_SAM, schedule.workingEmployees(1, 5));
        testScheduled(0, 0, 0, EMPTY_LIST);
        testScheduled(1, 1, 2, BOB);
        testScheduled(2, 3, 2, BOB_SAM);
        testScheduled(4, 5, 2, SAM);
        testScheduled(6, HOURS, 0, EMPTY_LIST);
    }


    /**
     *
     */
    @Test
    public void test_workingEmployees_part4() {
        schedule = new WorkSchedule(HOURS);
        schedule.setRequiredNumber(2, 1, 5);
        schedule.addWorkingPeriod("bob", 1, 3);
        schedule.addWorkingPeriod("sam", 4, 5);

        testScheduled(0, 0, 0, EMPTY_LIST);
        testScheduled(1, 3, 2, BOB);
        testScheduled(4, 5, 2, SAM);
        testScheduled(6, HOURS, 0, EMPTY_LIST);
        assertArrayEquals(BOB, schedule.workingEmployees(1, 3));
        testScheduled(0, 0, 0, EMPTY_LIST);
        testScheduled(1, 3, 2, BOB);
        testScheduled(4, 5, 2, SAM);
        testScheduled(6, HOURS, 0, EMPTY_LIST);
    }



}
