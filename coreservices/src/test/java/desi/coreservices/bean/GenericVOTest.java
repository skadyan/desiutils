package desi.coreservices.bean;

import org.junit.Ignore;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

public class GenericVOTest {

	@Test
	@Ignore("fix me")
	public void convertToStdJsonFormat() throws Exception {
		ObjectMapper mapper = new ObjectMapper();
		GenericVO vo = new GenericVO();
		vo.put("attr1", "SANDEP");
		vo.put("attr2", 10L);

		String s = mapper.writeValueAsString(vo);
		System.out.println("Json value : " + s);
	}
}
