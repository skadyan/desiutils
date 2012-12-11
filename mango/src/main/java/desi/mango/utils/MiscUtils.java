package desi.mango.utils;

import java.util.UUID;

public class MiscUtils {

	public static String getCodeSourceLocation(Class<?> clazz) {
		return clazz.getProtectionDomain().getCodeSource().getLocation().toExternalForm();
	}

	public static String generateUUID() {
		return UUID.randomUUID().toString();
	}

	public static String generateUUID(String suffix) {
		return suffix + generateUUID();
	}

}
