package desi.coreservices.bean;

import java.util.HashMap;
import java.util.Map;

public class GenericVO {

	private Map<String, Object> rawData;

	public GenericVO() {
		rawData = new HashMap<>();
	}
	
	public void put(String name, Object v) {
		this.rawData.put(name, v);
	}
}
