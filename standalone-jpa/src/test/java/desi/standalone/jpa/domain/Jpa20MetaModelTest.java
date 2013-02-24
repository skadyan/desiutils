package desi.standalone.jpa.domain;

import java.util.Set;

import javax.persistence.metamodel.Attribute;
import javax.persistence.metamodel.EntityType;
import javax.persistence.metamodel.Metamodel;

import org.junit.Test;

public class Jpa20MetaModelTest extends StandaloneJapTestSupport {

	@Test
	public void introspectMetaModel() throws Exception {
		Metamodel metamodel = emf.getMetamodel();

		Set<EntityType<?>> entities = metamodel.getEntities();
		for (EntityType<?> entityType : entities) {
			System.out.println("-------------------------\n Entity : " + entityType.getName());

			for (Attribute<?, ?> attr : entityType.getAttributes()) {
				attr.getPersistentAttributeType();
				System.out.println(" attr " + attr.getName()+ " " + attr.getJavaType());
			
			}
			
			
			
			System.out.println("-------------------------\n"); 

		}

	}
}
