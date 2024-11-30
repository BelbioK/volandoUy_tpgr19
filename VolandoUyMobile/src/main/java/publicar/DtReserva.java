
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
 * <p>Clase Java para dtReserva complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtReserva">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="fechaReserva" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="tipoAsiento" type="{http://publicar/}asiento" minOccurs="0"/>
 *         <element name="costo" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="cantidadPasajes" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="equipajeExtra" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="pasajes" type="{http://publicar/}dtPasajero" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="cliente" type="{http://publicar/}dtCliente" minOccurs="0"/>
 *         <element name="vuelo" type="{http://publicar/}dtVuelo" minOccurs="0"/>
 *         <element name="paquete" type="{http://publicar/}dtPaquete" minOccurs="0"/>
 *         <element name="fechaCheckIn" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtReserva", propOrder = {
    "fechaReserva",
    "tipoAsiento",
    "costo",
    "cantidadPasajes",
    "equipajeExtra",
    "pasajes",
    "cliente",
    "vuelo",
    "paquete",
    "fechaCheckIn"
})
public class DtReserva {

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaReserva;
    @XmlSchemaType(name = "string")
    protected Asiento tipoAsiento;
    protected Integer costo;
    protected Integer cantidadPasajes;
    protected Integer equipajeExtra;
    @XmlElement(nillable = true)
    protected List<DtPasajero> pasajes;
    protected DtCliente cliente;
    protected DtVuelo vuelo;
    protected DtPaquete paquete;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaCheckIn;

    /**
     * Obtiene el valor de la propiedad fechaReserva.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaReserva() {
        return fechaReserva;
    }

    /**
     * Define el valor de la propiedad fechaReserva.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaReserva(XMLGregorianCalendar value) {
        this.fechaReserva = value;
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
     * Obtiene el valor de la propiedad costo.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCosto() {
        return costo;
    }

    /**
     * Define el valor de la propiedad costo.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCosto(Integer value) {
        this.costo = value;
    }

    /**
     * Obtiene el valor de la propiedad cantidadPasajes.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCantidadPasajes() {
        return cantidadPasajes;
    }

    /**
     * Define el valor de la propiedad cantidadPasajes.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCantidadPasajes(Integer value) {
        this.cantidadPasajes = value;
    }

    /**
     * Obtiene el valor de la propiedad equipajeExtra.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getEquipajeExtra() {
        return equipajeExtra;
    }

    /**
     * Define el valor de la propiedad equipajeExtra.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setEquipajeExtra(Integer value) {
        this.equipajeExtra = value;
    }

    /**
     * Gets the value of the pasajes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the pasajes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPasajes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtPasajero }
     * 
     * 
     * @return
     *     The value of the pasajes property.
     */
    public List<DtPasajero> getPasajes() {
        if (pasajes == null) {
            pasajes = new ArrayList<>();
        }
        return this.pasajes;
    }

    /**
     * Obtiene el valor de la propiedad cliente.
     * 
     * @return
     *     possible object is
     *     {@link DtCliente }
     *     
     */
    public DtCliente getCliente() {
        return cliente;
    }

    /**
     * Define el valor de la propiedad cliente.
     * 
     * @param value
     *     allowed object is
     *     {@link DtCliente }
     *     
     */
    public void setCliente(DtCliente value) {
        this.cliente = value;
    }

    /**
     * Obtiene el valor de la propiedad vuelo.
     * 
     * @return
     *     possible object is
     *     {@link DtVuelo }
     *     
     */
    public DtVuelo getVuelo() {
        return vuelo;
    }

    /**
     * Define el valor de la propiedad vuelo.
     * 
     * @param value
     *     allowed object is
     *     {@link DtVuelo }
     *     
     */
    public void setVuelo(DtVuelo value) {
        this.vuelo = value;
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

    /**
     * Obtiene el valor de la propiedad fechaCheckIn.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFechaCheckIn() {
        return fechaCheckIn;
    }

    /**
     * Define el valor de la propiedad fechaCheckIn.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFechaCheckIn(XMLGregorianCalendar value) {
        this.fechaCheckIn = value;
    }

}
