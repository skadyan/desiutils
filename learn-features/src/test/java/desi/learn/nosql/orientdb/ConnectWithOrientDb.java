package desi.learn.nosql.orientdb;

import static org.junit.Assert.assertThat;

import org.hamcrest.CoreMatchers;
import org.junit.Test;

import com.orientechnologies.orient.core.db.document.ODatabaseDocumentTx;

public class ConnectWithOrientDb {

	@Test
	public void openConnectionAndCreateNewDatabase() throws Exception {
		ODatabaseDocumentTx db = new ODatabaseDocumentTx("local:/tmp/db/scratchpad");
		if (db.exists()) {
			db.open("admin", "admin");
			db.drop();
		}

		db.create();

		assertThat(db.exists(), CoreMatchers.is(true));

	}
}
