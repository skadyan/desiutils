package desi.mango.utils;

import java.beans.Introspector;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class StringUtils {
	private StringUtils() {
	}

	public static boolean isNullOrBlank(CharSequence value) {
		if (value == null)
			return true;

		int length = value.length();
		if (length == 0) {
			return true;
		}

		for (int i = 0; i < length; i++) {
			if (!Character.isWhitespace(value.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	public static Collection<String> parseCsv(String value) {
		return splitValue(value, ',', '"');
	}

	public static String unquote(String value) {
		int len;
		if ((len = value.length() - 1) > 0) {
			if (value.charAt(0) == '"' && value.charAt(len) == '"') {
				value = value.substring(1, len);
			} else if (value.charAt(0) == '\'' && value.charAt(len) == '\'') {
				value = value.substring(1, len);
			}
		}

		return value;
	}

	public static Collection<String> splitValue(String line, char separatorChar, char quoteCharacter) {
		line = trimToNull(line);
		List<String> tokenList = new ArrayList<String>();
		StringBuilder sb = new StringBuilder(30);
		if (line != null) {
			int len = line.length();
			char[] inputs = new char[len + 1];
			line.getChars(0, len, inputs, 0);
			inputs[len] = '\0';

			int index = 0;
			char ch = ' ';
			boolean inQuote = false;
			boolean wasInQuote = false;
			while (index < len) {
				ch = inputs[index];
				if (inQuote) {
					if (ch == quoteCharacter) {
						// look-a-head
						if (inputs[index + 1] == quoteCharacter) {
							sb.append(ch);
							index++;// consume next QUOTE as well.
						} else {
							inQuote = false;
							int next = index + 1;
							while (next < len) {
								char c = inputs[next];
								if (!Character.isWhitespace(c)) {
									if (c != separatorChar) {
										throw new IllegalArgumentException("separate char was expected.");
									}
									break;
								} else {
									++index;
								}
								next = index + 1;
							}
							wasInQuote = true;
						}
					} else {
						sb.append(ch);
					}
				} else {
					if (ch == quoteCharacter) {
						inQuote = true;
					} else {
						if (ch == separatorChar) {
							String token = sb.toString();
							sb.setLength(0);
							if (!wasInQuote) {
								token = token.trim();
							}
							tokenList.add(token);
							wasInQuote = false;
						} else {
							sb.append(ch);
						}
					}
				}

				index++;
			}
			if (inQuote) {
				throw new IllegalArgumentException("unclosed quote");
			}

			String token = sb.toString();
			// grab last token
			if (!wasInQuote) {
				token = token.trim();
			}

			tokenList.add(token);
		}

		return tokenList;
	}

	public static String trimToNull(String line) {
		return isNullOrBlank(line) ? null : line.trim();
	}

	public static String decapitalize(String name) {
		return Introspector.decapitalize(name);
	}
}
