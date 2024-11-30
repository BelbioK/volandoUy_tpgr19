
package publicar;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtListaVuelos complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtListaVuelos">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="vuelos" type="{http://publicar/}dtVuelo" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtListaVuelos", propOrder = {
    "vuelos"
})
public class DtListaVuelos {

    @XmlElement(nillable = true)
    protected List<DtVuelo> vuelos;

    /**
     * Gets the value of the vuelos property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the vuelos property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVuelos().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtVuelo }
     * 
     * 
     * @return
     *     The value of the vuelos property.
     */
    public List<DtVuelo> getVuelos() {
        if (vuelos == null) {
            vuelos = new ArrayList<>();
        }
        return this.vuelos;
    }

}
