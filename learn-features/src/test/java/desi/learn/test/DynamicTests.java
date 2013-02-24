package desi.learn.test;

import junit.framework.TestSuite;

import org.junit.Test;

import com.tocea.easycoverage.framework.checkers.ArrayIndexOutOfBoundExceptionChecker;
import com.tocea.easycoverage.framework.checkers.BijectiveCompareToChecker;
import com.tocea.easycoverage.framework.checkers.BijectiveEqualsChecker;
import com.tocea.easycoverage.framework.checkers.CloneChecker;
import com.tocea.easycoverage.framework.checkers.GetterNotNullChecker;
import com.tocea.easycoverage.framework.checkers.NPEConstructorChecker;
import com.tocea.easycoverage.framework.checkers.NPEMethodChecker;
import com.tocea.easycoverage.framework.checkers.NullValueEqualsChecker;
import com.tocea.easycoverage.framework.checkers.SetterChecker;
import com.tocea.easycoverage.framework.checkers.ToStringNotNullChecker;
import com.tocea.easycoverage.framework.junit.JUnitTestSuiteProvider;

public class DynamicTests {

	@Test
	public static TestSuite suite() {
		JUnitTestSuiteProvider testSuiteProvider = new JUnitTestSuiteProvider();

		testSuiteProvider.addClass(ClassUnderTest.class);
//		testSuiteProvider.addClass(AnotherClassUnderTest.class);

		testSuiteProvider.addClassChecker(ToStringNotNullChecker.class);
		testSuiteProvider.addClassChecker(BijectiveCompareToChecker.class);
		testSuiteProvider.addClassChecker(BijectiveEqualsChecker.class);
		testSuiteProvider.addClassChecker(CloneChecker.class);
		testSuiteProvider.addClassChecker(NPEConstructorChecker.class);
		testSuiteProvider.addClassChecker(NullValueEqualsChecker.class);

		testSuiteProvider.addMethodChecker(NPEMethodChecker.class);
		testSuiteProvider.addMethodChecker(GetterNotNullChecker.class);
		testSuiteProvider.addMethodChecker(SetterChecker.class);
		testSuiteProvider.addMethodChecker(ArrayIndexOutOfBoundExceptionChecker.class);

		return testSuiteProvider.getTestSuite("Dynamic Test Cases");
	}
}
