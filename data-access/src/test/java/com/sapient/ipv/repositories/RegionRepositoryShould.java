package com.sapient.ipv.repositories;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.sapient.ipv.domain.Region;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = { "classpath:applicationContext.xml" })
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class RegionRepositoryShould {

	@Autowired
	private RegionRepository regionRepository;

	@Test
	public void auditTheChanges() throws Exception {
		Region region = new Region();

		region.setName("EMEA");
		region.setTimeZone("Asia/Hong_Kong");

		region = regionRepository.save(region);

		assertThat(region.getId(), notNullValue());
		assertThat(region.getCreatedDate(), notNullValue());
	}
}
