@org.hibernate.annotations.TypeDefs({ 
	@TypeDef(defaultForType = LocalDateTime.class,
			typeClass = PersistentLocalDateTime.class)

})

package com.sapient.ipv.domain;

import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime;
import org.joda.time.LocalDateTime;

