package desi.coreservices.util;

import com.sapient.ipv.data.dictionary.RawField;

public interface SupportsRawData {

	<T> void putRawField(RawField<T> field, T value);
}
