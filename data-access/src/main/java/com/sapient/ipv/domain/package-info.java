@org.hibernate.annotations.TypeDefs({
		@TypeDef(defaultForType = LocalDateTime.class,
				typeClass = PersistentLocalDateTime.class),
		@TypeDef(defaultForType = DateTime.class,
				typeClass = PersistentDateTime.class),
				@TypeDef(defaultForType = RawData.class,
				typeClass = RawDataType.class) }
)
package com.sapient.ipv.domain;

import org.hibernate.annotations.TypeDef;
import org.jadira.usertype.dateandtime.joda.PersistentDateTime;
import org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import com.sapient.ipv.domain.types.RawDataType;

