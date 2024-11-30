
package publicar;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlEnumValue;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para asiento.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="asiento">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="Turista"/>
 *     <enumeration value="Ejecutivo"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "asiento")
@XmlEnum
public enum Asiento {

    @XmlEnumValue("Turista")
    TURISTA("Turista"),
    @XmlEnumValue("Ejecutivo")
    EJECUTIVO("Ejecutivo");
    private final String value;

    Asiento(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static Asiento fromValue(String v) {
        for (Asiento c: Asiento.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
