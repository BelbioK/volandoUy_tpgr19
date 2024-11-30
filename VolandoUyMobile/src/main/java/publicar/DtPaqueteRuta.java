
package publicar;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPaqueteRuta complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPaqueteRuta">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="cantidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="tipoAsiento" type="{http://publicar/}asiento" minOccurs="0"/>
 *         <element name="ruta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagenRuta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPaqueteRuta", propOrder = {
    "cantidad",
    "tipoAsiento",
    "ruta",
    "imagenRuta"
})
public class DtPaqueteRuta {

    protected int cantidad;
    @XmlSchemaType(name = "string")
    protected Asiento tipoAsiento;
    protected String ruta;
    protected String imagenRuta;

    /**
     * Obtiene el valor de la propiedad cantidad.
     * 
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Define el valor de la propiedad cantidad.
     * 
     */
    public void setCantidad(int value) {
        this.cantidad = value;
    }

    /**
     * Obtiene el valor de la propiedad tipoAsiento.
     * 
     * @return
     *     possible object is
     *     {@link Asiento }
     *     
     */
    public Asiento getTipoAsiento() {
        return tipoAsiento;
    }

    /**
     * Define el valor de la propiedad tipoAsiento.
     * 
     * @param value
     *     allowed object is
     *     {@link Asiento }
     *     
     */
    public void setTipoAsiento(Asiento value) {
        this.tipoAsiento = value;
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
     * Obtiene el valor de la propiedad imagenRuta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagenRuta() {
        return imagenRuta;
    }

    /**
     * Define el valor de la propiedad imagenRuta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagenRuta(String value) {
        this.imagenRuta = value;
    }

}
