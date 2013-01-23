package desi.mango.utils;

import static org.junit.Assert.assertThat;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.junit.Before;
import org.junit.Test;

public class SpecificationDrivenComparatorTest {

  private abstract class CompareResultMatcher extends BaseMatcher<Integer> {

		abstract CompareResultMatcher reverse();
	}

	private SpecificationDrivenComparator comparator;

	@Before
	public void setUp() throws Exception {
		comparator = new SpecificationDrivenComparator(
				ComparatorSpecification.DEFAULT);
	}

	@Test
	public void compareTwoComparableObjectLessThan() throws Exception {
		String o1 = "Y";
		String o2 = "Z";

		asserrThat(o1, isLessThan(), o2);
	}

	@Test
	public void compareTwoComparableObjectEquals() throws Exception {
		String o1 = "Y";
		String o2 = "Y";

		asserrThat(o1, isEquals(), o2);
	}

	private void asserrThat(Object o1, CompareResultMatcher matcher, Object o2) {
		assertThat("unexpected comparison result", comparator.compare(o1, o2),
				matcher);
		assertThat("Comparison method violates its general contract!",
				comparator.compare(o2, o1), matcher.reverse());
	}

	private CompareResultMatcher isEquals() {
		return new CompareResultMatcher() {

			@Override
			public boolean matches(Object item) {
				return ((Integer) item).intValue() == 0;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText(" isEquals");
			}

			@Override
			CompareResultMatcher reverse() {
				return isEquals();
			}
		};
	}

	private CompareResultMatcher isLessThan() {
		return new CompareResultMatcher() {
			@Override
			public boolean matches(Object item) {
				return ((Integer) item).intValue() < 0;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText(" isLessThan");
			}

			@Override
			CompareResultMatcher reverse() {
				return isGreaterThan();
			}
		};
	}

	private CompareResultMatcher isGreaterThan() {
		return new CompareResultMatcher() {

			@Override
			public boolean matches(Object item) {
				return ((Integer) item).intValue() > 0;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText(" isLessThan");
			}

			@Override
			CompareResultMatcher reverse() {
				return isLessThan();
			}
		};
	}
}
