package logica.datatypes;

import java.util.Set;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DTListaString {
	
	private Set<String> items;

	public DTListaString() {
		
	}
	
	public Set<String> getArray() {
		return items;
	}

	public void setArray(Set<String> items) {
		this.items = items;
	}
}