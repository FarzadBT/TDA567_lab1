import org.junit.Test;

import static org.junit.Assert.*;

public class AddWorkPeriodTest1 {

    @Test
    public void test_addWorkingPeriod_part1() {
        WorkSchedule schedule = new WorkSchedule(6);
        assertFalse(schedule.addWorkingPeriod("", -1, 5));
    }

    @Test
    public void test_addWorkingPeriod_part2() {
        WorkSchedule schedule = new WorkSchedule(4);
        assertFalse(schedule.addWorkingPeriod("", 0, 5));
    }

    // Bug
    @Test
    public void test_addWorkingPeriod_part3() {
        WorkSchedule schedule = new WorkSchedule(4);
        assertFalse(schedule.addWorkingPeriod("", 3, 2));
    }

    @Test
    public void test_addWorkingPeriod_part4() {
        WorkSchedule schedule = new WorkSchedule(5);
        schedule.setRequiredNumber(1, 0, 4);
        assertTrue(schedule.addWorkingPeriod("fdskijsdv", 1, 3));
    }

    private void testScheduled(WorkSchedule schedule, int start, int end, int required, int working) {
        for (int i = start; i <= end; i++) {
            WorkSchedule.Hour h = schedule.readSchedule(i);
            assertEquals(required, h.requiredNumber);
            assertEquals(working, h.workingEmployees.length);
        }
    }

    @Test
    public void test_addWorkingPeriod_border1() {
        WorkSchedule schedule = new WorkSchedule(5);
        // Verify that no one is scheduled
        testScheduled(schedule, 1, 3, 0, 0);

        //Try to add an employee with no schedule available
        assertFalse(schedule.addWorkingPeriod("dsgv", 1, 3));

        // Verify that no one is scheduled
        testScheduled(schedule, 1, 3, 0, 0);
    }

    @Test
    public void test_addWorkingPeriod_border2() {
        WorkSchedule schedule = new WorkSchedule(5);
        //Add 1 required amount of employees between 0-4
        schedule.setRequiredNumber(1, 0, 4);

        // Verify that no one is scheduled and one is required
        testScheduled(schedule, 1, 3, 1, 0);
        assertTrue(schedule.addWorkingPeriod("kfnv", 1, 3));

        // Verify that one is scheduled and one is required
        testScheduled(schedule, 1, 3, 1, 1);
        assertFalse(schedule.addWorkingPeriod("lksd", 1, 3));

        // Verify that one is still scheduled and one is required
        testScheduled(schedule, 1, 3, 1, 1);
    }

    @Test
    public void test_addWorkingPeriod_border3() {
        WorkSchedule schedule = new WorkSchedule(5);
        //Add 1 required amount of employees between 0-4
        schedule.setRequiredNumber(1, 0, 4);

        // Verify that no one is scheduled and one is required
        testScheduled(schedule, 1, 3, 1, 0);
        assertTrue(schedule.addWorkingPeriod("kfnv", 1, 3));

        // Verify that no one is scheduled and one is required
        testScheduled(schedule, 1, 3, 1, 1);
        assertFalse(schedule.addWorkingPeriod("kfnv", 3, 4));

        // Verify that no one is scheduled and one is required
        testScheduled(schedule, 1, 3, 1, 1);
    }
}
