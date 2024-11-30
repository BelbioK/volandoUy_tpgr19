
package publicar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtCompraRuta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtCompraRuta">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cantidadRestante" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="ruta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="asiento" type="{http://publicar/}asiento" minOccurs="0"/>
 *         <element name="paquete" type="{http://publicar/}dtPaquete" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtCompraRuta", propOrder = {
    "cantidadRestante",
    "ruta",
    "asiento",
    "paquete"
})
public class DtCompraRuta {

    protected int cantidadRestante;
    protected String ruta;
    @XmlSchemaType(name = "string")
    protected Asiento asiento;
    protected DtPaquete paquete;

    /**
     * Obtiene el valor de la propiedad cantidadRestante.
     * 
     */
    public int getCantidadRestante() {
        return cantidadRestante;
    }

    /**
     * Define el valor de la propiedad cantidadRestante.
     * 
     */
    public void setCantidadRestante(int value) {
        this.cantidadRestante = value;
    }

    /**
     * Obtiene el valor de la propiedad ruta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuta() {
        return ruta;
    }

    /**
     * Define el valor de la propiedad ruta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuta(String value) {
        this.ruta = value;
    }

    /**
     * Obtiene el valor de la propiedad asiento.
     * 
     * @return
     *     possible object is
     *     {@link Asiento }
     *     
     */
    public Asiento getAsiento() {
        return asiento;
    }

    /**
     * Define el valor de la propiedad asiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Asiento }
     *     
     */
    public void setAsiento(Asiento value) {
        this.asiento = value;
    }

    /**
     * Obtiene el valor de la propiedad paquete.
     * 
     * @return
     *     possible object is
     *     {@link DtPaquete }
     *     
     */
    public DtPaquete getPaquete() {
        return paquete;
    }

    /**
     * Define el valor de la propiedad paquete.
     * 
     * @param value
     *     allowed object is
     *     {@link DtPaquete }
     *     
     */
    public void setPaquete(DtPaquete value) {
        this.paquete = value;
    }

}
