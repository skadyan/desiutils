package desi.mango.xml2json.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "carrier")
@XmlAccessorType(XmlAccessType.FIELD)
public class Carrier {
	@XmlElement
	private String code;
	@XmlElement
	private String name;

	@XmlElement
	private String country;
}
