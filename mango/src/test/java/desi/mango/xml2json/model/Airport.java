package desi.mango.xml2json.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Airport {
	@XmlElement
	private String country;
	@XmlElement
	private String city;

	@XmlElement(name="airport")
	private AirportId airport;

	@XmlElement
	private String time;
	@XmlElement(name = "service_company")
	private String serviceCompany;

	@XmlAccessorType(XmlAccessType.FIELD)
	public static class AirportId {
		@XmlAttribute(name = "code")
		private String code;
		@XmlElement
		private String airport;
	}

}
