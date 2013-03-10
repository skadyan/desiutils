package desi.mango.xml2json.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "flight")
@XmlAccessorType(XmlAccessType.FIELD)
public class Flight {
	@XmlElement(name = "carrier", required = true)
	private Carrier carrier;
	@XmlElement(name = "desination", required = true)
	private Airport desination;
	@XmlElement(name = "departure", required = true)
	private Airport departure;

}
