package desi.rnp.jdbc.proxy.util;

import static org.hamcrest.CoreMatchers.instanceOf;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.mockito.Matchers;

import desi.rnp.jdbc.proxy.ProxyObject;

public class ProxyObjectMatchers {

	public static Matcher<? super ResultSet> isAProxyObjectHavingProxyStatement() {
		return new BaseMatcher<ResultSet>() {
	
			@Override
			public boolean matches(Object item) {
				try {
					return instanceOf(ProxyObject.class).matches(item)
							&& instanceOf(ProxyObject.class).matches(((ResultSet) item).getStatement());
				} catch (SQLException e) {
					throw new IllegalStateException("not able to match");
				}
			}
	
			@Override
			public void describeTo(Description description) {
				description.appendText("isAProxyObjectHavingProxyStatement");
			}
		};
	}

	public static <T extends Statement> Matcher<T> isAProxyObjectHavingProxyConnection() {
		return new BaseMatcher<T>() {
	
			@Override
			public boolean matches(Object item) {
				try {
					return instanceOf(ProxyObject.class).matches(item)
							&& instanceOf(ProxyObject.class).matches(((Statement) item).getConnection());
				} catch (SQLException e) {
					throw new IllegalStateException("not able to match");
				}
			}
	
			@Override
			public void describeTo(Description description) {
				description.appendText("isAProxyObjectHavingProxyConnection()");
			}
		};
	
	}

	public static String[] anyArrayOfSting() {
		return (String[]) Matchers.any();
	}

	public static int[] anyArrayOfInt() {
		return (int[]) Matchers.any();
	}

}
