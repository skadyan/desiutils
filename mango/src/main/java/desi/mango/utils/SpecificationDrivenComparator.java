package desi.mango.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import desi.mango.utils.ComparatorSpecification.Spec;

public class SpecificationDrivenComparator implements Comparator<Object> {
  private ComparatorSpecification specification;

	public SpecificationDrivenComparator(ComparatorSpecification specification) {
		this.specification = specification;
	}

	@Override
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public int compare(Object o1, Object o2) {
		if (o1 == o2) {
			return 0;
		}

		if (o1 == null) {
			return -1;
		}
		if (o2 == null) {
			return 1;
		}
		String typeName = o1.getClass().getName();

		int cmp = typeName.compareTo(o2.getClass().getName());
		if (cmp == 0) {
			Spec spec = specification.getSpec(typeName);
			if (spec != null) {
				cmp = compareUsingSpec(o1, o2, spec, o1.getClass());
			} else {
				if (o1 instanceof Collection) {
					cmp = compareCollection((Collection<?>) o1,
							(Collection<?>) o2);
				} else if (o1 instanceof Map) {
					cmp = compareMap((Map) o1, (Map) o2);
				} else if (o1 instanceof Comparable) {
					cmp = ((Comparable) o1).compareTo(o2);
				} else {
					throw new IllegalStateException(
							"do not know how to compare o1 :" + o1 + " ,o2 :"
									+ o2);
				}
			}
		}

		return cmp;
	}

	private int compareCollection(Collection<?> o1, Collection<?> o2) {
		int cmp = o1.size() - o2.size();
		if (cmp == 0) {
			o1 = toSortedList(o1);
			o2 = toSortedList(o2);

			for (Iterator<?> itr1 = o1.iterator(), itr2 = o2.iterator(); itr1
					.hasNext();) {
				Object value1 = itr1.next();
				Object value2 = itr2.next();
				if ((cmp = compare(value1, value2)) != 0) {
					break;
				}
			}

		}
		return cmp;
	}

	private Collection<?> toSortedList(Collection<?> o2) {
		List<Object> sortedList = new ArrayList<Object>(o2);
		Collections.sort(sortedList, this);
		return sortedList;
	}

	private int compareUsingSpec(Object o1, Object o2, Spec spec,
			Class<?> specClass) {
		/**
		 * <pre>
		 * int cmp = 0;
		 * try {
		 * 	if (spec.useSuperClassSpecification()) {
		 * 
		 * 		Class&lt;?&gt; superClazz = specClass.getSuperclass();
		 * 		Spec superClassSpec = specification.getSpec(superClazz.getName());
		 * 		if (superClassSpec != null) {
		 * 			cmp = compareUsingSpec(o1, o2, superClassSpec, superClazz);
		 * 		}
		 * 	}
		 * 	if (cmp == 0) {
		 * 		for (String property : spec.getProperties()) {
		 * 			Object value1 = OgnlUtils.getValue(property, o1);
		 * 			Object value2 = OgnlUtils.getValue(property, o2);
		 * 
		 * 			Comparator&lt;Object&gt; comparator = getComparator(property, spec);
		 * 
		 * 			if ((cmp = comparator.compare(value1, value2)) != 0) {
		 * 				break;
		 * 			}
		 * 		}
		 * 	}
		 * } catch (OgnlException e) {
		 * 	throw new IllegalStateException(
		 * 			&quot;Unable to read property value. Check your spec for type &quot;
		 * 					+ spec.getTypeName(), e);
		 * }
		 * 
		 * return cmp;
		 * </pre>
		 */

		throw new UnsupportedOperationException("method not implemented");
	}

	@SuppressWarnings({ "unchecked", "unused" })
	private Comparator<Object> getComparator(String propertyName, Spec spec) {
		Comparator<Object> comparator;
		try {
			String comparatorClass = spec.getPropertyComparator(propertyName);
			if (comparatorClass == null || "default".equals(comparatorClass))
				comparator = this;
			else {
				comparator = newInstance((Class<? extends Comparator<Object>>) Class
						.forName(comparatorClass));
			}
		} catch (ClassNotFoundException e) {
			throw new IllegalStateException(
					"Proeprty Comparator class not found. Check your spec for type "
							+ spec.getTypeName());
		}
		return comparator;
	}

	private Comparator<Object> newInstance(
			Class<? extends Comparator<Object>> forName) {
		try {
			return forName.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}

	private int compareMap(Map<Object, Object> lhsMap,
			Map<Object, Object> rhsMap) {
		int cmp = lhsMap.size() - rhsMap.size();
		if (cmp == 0) {
			if (!(lhsMap instanceof SortedMap)) {
				lhsMap = sortedMap(lhsMap);
				rhsMap = sortedMap(rhsMap);
			}

			for (Iterator<Map.Entry<Object, Object>> itr1 = lhsMap.entrySet()
					.iterator(), itr2 = rhsMap.entrySet().iterator(); itr1
					.hasNext();) {
				Map.Entry<Object, Object> e1 = itr1.next();
				Map.Entry<Object, Object> e2 = itr2.next();
				if (((cmp = compare(e1.getKey(), e2.getKey())) != 0)
						|| ((cmp = compare(e1.getValue(), e2.getValue())) != 0)) {
					break;
				}
			}
		}

		return cmp;
	}

	private Map<Object, Object> sortedMap(Map<Object, Object> map) {
		TreeMap<Object, Object> sortedMap = new TreeMap<Object, Object>(this);
		sortedMap.putAll(map);

		return sortedMap;
	}
}
