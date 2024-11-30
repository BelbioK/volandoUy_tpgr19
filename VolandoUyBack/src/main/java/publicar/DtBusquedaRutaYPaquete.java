
package publicar;

import java.util.ArrayList;
import java.util.List;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtBusquedaRutaYPaquete complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtBusquedaRutaYPaquete">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="rutas" type="{http://publicar/}dtRutasDeVuelo" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="paquete" type="{http://publicar/}dtPaquete" maxOccurs="unbounded" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtBusquedaRutaYPaquete", propOrder = {
    "rutas",
    "paquete"
})
public class DtBusquedaRutaYPaquete {

    @XmlElement(nillable = true)
    protected List<DtRutasDeVuelo> rutas;
    @XmlElement(nillable = true)
    protected List<DtPaquete> paquete;

    /**
     * Gets the value of the rutas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the rutas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRutas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtRutasDeVuelo }
     * 
     * 
     * @return
     *     The value of the rutas property.
     */
    public List<DtRutasDeVuelo> getRutas() {
        if (rutas == null) {
            rutas = new ArrayList<>();
        }
        return this.rutas;
    }

    /**
     * Gets the value of the paquete property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the paquete property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPaquete().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPaquete }
     * 
     * 
     * @return
     *     The value of the paquete property.
     */
    public List<DtPaquete> getPaquete() {
        if (paquete == null) {
            paquete = new ArrayList<>();
        }
        return this.paquete;
    }

}
