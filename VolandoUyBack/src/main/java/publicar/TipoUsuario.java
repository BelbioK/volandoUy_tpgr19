
package publicar;

import jakarta.xml.bind.annotation.XmlEnum;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para tipoUsuario.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <pre>{@code
 * <simpleType name="tipoUsuario">
 *   <restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     <enumeration value="AEROLINEA"/>
 *     <enumeration value="CLIENTE"/>
 *   </restriction>
 * </simpleType>
 * }</pre>
 * 
 */
@XmlType(name = "tipoUsuario")
@XmlEnum
public enum TipoUsuario {

    AEROLINEA,
    CLIENTE;

    public String value() {
        return name();
    }

    public static TipoUsuario fromValue(String v) {
        return valueOf(v);
    }

}
