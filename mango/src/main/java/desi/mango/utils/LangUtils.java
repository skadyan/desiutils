package desi.mango.utils;

public class LangUtils {

	public static boolean notEmpty(Object[] array) {
		return array != null && array.length > 0;
	}

	public static boolean isNullOrBlank(String text) {
		return text == null || text.length() == 0;
	}
}
