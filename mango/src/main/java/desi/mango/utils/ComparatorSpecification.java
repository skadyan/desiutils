package desi.mango.utils;

import java.io.IOException;
import java.io.Reader;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComparatorSpecification {

  public static class Spec {
		private String typeName;
		private List<String> properties;
		private boolean useSuperClassSpecification;
		private Map<String, String> propertyComparators;

		public Spec(String typeName, List<String> properties,
				boolean useSuperClassSpecification,
				Map<String, String> propertyComparators) {
			this.typeName = typeName;
			this.properties = properties;
			this.useSuperClassSpecification = useSuperClassSpecification;
			this.propertyComparators = propertyComparators;
		}

		public List<String> getProperties() {
			return properties;
		}

		public String getTypeName() {
			return typeName;
		}

		public boolean useSuperClassSpecification() {
			return useSuperClassSpecification;
		}

		public String getPropertyComparator(String propertyName) {
			return propertyComparators.get(propertyName);
		}
	}

	public static final ComparatorSpecification DEFAULT = new ComparatorSpecification(
			Collections.<String, Spec> emptyMap());

	private Map<String, Spec> specification;

	public ComparatorSpecification(Map<String, Spec> specs) {
		this.specification = specs;
	}

	@SuppressWarnings("unchecked")
	public static ComparatorSpecification from(Reader json) throws IOException {
		Map<String, Object> configuration = new HashMap<>(); // TODO PARSE JSON
		Map<String, Spec> specsMap = new HashMap<String, ComparatorSpecification.Spec>();
		for (Map.Entry<String, Object> config : configuration.entrySet()) {
			String typeName = config.getKey();
			Map<String, Object> specs = (Map<String, Object>) config.getValue();
			List<String> properties = (List<String>) specs.get("properties");
			String useSuperClassSpecification = String.valueOf(specs
					.get("useSuperClassSpecification"));
			Map<String, String> propertyComparators = (Map<String, String>) specs
					.get("comparators");
			if (propertyComparators == null) {
				propertyComparators = Collections.emptyMap();
			}
			specsMap.put(
					typeName,
					new Spec(typeName, properties, Boolean
							.valueOf(useSuperClassSpecification),
							propertyComparators));

		}
		return new ComparatorSpecification(specsMap);
	}

	public Comparator<Object> newComparator() {
		return new SpecificationDrivenComparator(this);
	}

	public Spec getSpec(String typeName) {
		return specification.get(typeName);
	}

}
