import org.junit.runner.RunWith;
import org.junit.runners.Suite;
@RunWith(Suite.class)
@Suite.SuiteClasses({
        InsertStatementTests.class,
        InsertBranchTests.class,
        MemberStatementTests.class,
        MemberBranchTests.class,
        SectionStatementBranchTests.class
        })
public class SetTestSuite {
    // nothing goes here
}