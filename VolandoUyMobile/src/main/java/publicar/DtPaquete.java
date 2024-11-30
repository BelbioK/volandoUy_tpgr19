
package publicar;

import java.util.ArrayList;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlSchemaType;
import jakarta.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para dtPaquete complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtPaquete">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="tipoAsiento" type="{http://publicar/}asiento" minOccurs="0"/>
 *         <element name="periodoValidez" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="descuento" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costoAsociado" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="comprado" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         <element name="rutas" type="{http://publicar/}dtPaqueteRuta" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtPaquete", propOrder = {
    "nombre",
    "descripcion",
    "tipoAsiento",
    "periodoValidez",
    "descuento",
    "costoAsociado",
    "comprado",
    "rutas",
    "fechaAlta",
    "imagen"
})
public class DtPaquete {

    protected String nombre;
    protected String descripcion;
    @XmlSchemaType(name = "string")
    protected Asiento tipoAsiento;
    protected int periodoValidez;
    protected int descuento;
    protected int costoAsociado;
    protected Boolean comprado;
    @XmlElement(nillable = true)
    protected List<DtPaqueteRuta> rutas;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAlta;
    protected String imagen;

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad descripcion.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Define el valor de la propiedad descripcion.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcion(String value) {
        this.descripcion = value;
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
     * Obtiene el valor de la propiedad periodoValidez.
     * 
     */
    public int getPeriodoValidez() {
        return periodoValidez;
    }

    /**
     * Define el valor de la propiedad periodoValidez.
     * 
     */
    public void setPeriodoValidez(int value) {
        this.periodoValidez = value;
    }

    /**
     * Obtiene el valor de la propiedad descuento.
     * 
     */
    public int getDescuento() {
        return descuento;
    }

    /**
     * Define el valor de la propiedad descuento.
     * 
     */
    public void setDescuento(int value) {
        this.descuento = value;
    }

    /**
     * Obtiene el valor de la propiedad costoAsociado.
     * 
     */
    public int getCostoAsociado() {
        return costoAsociado;
    }

    /**
     * Define el valor de la propiedad costoAsociado.
     * 
     */
    public void setCostoAsociado(int value) {
        this.costoAsociado = value;
    }

    /**
     * Obtiene el valor de la propiedad comprado.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isComprado() {
        return comprado;
    }

    /**
     * Define el valor de la propiedad comprado.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setComprado(Boolean value) {
        this.comprado = value;
    }

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
     * {@link DtPaqueteRuta }
     * 
     * 
     * @return
     *     The value of the rutas property.
     */
    public List<DtPaqueteRuta> getRutas() {
        if (rutas == null) {
            rutas = new ArrayList<>();
        }
        return this.rutas;
    }

    /**
     * Obtiene el valor de la propiedad fechaAlta.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaAlta() {
        return fechaAlta;
    }

    /**
     * Define el valor de la propiedad fechaAlta.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaAlta(XMLGregorianCalendar value) {
        this.fechaAlta = value;
    }

    /**
     * Obtiene el valor de la propiedad imagen.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getImagen() {
        return imagen;
    }

    /**
     * Define el valor de la propiedad imagen.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setImagen(String value) {
        this.imagen = value;
    }

}
