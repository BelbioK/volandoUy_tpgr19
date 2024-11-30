
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
 * <p>Clase Java para dtVuelo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtVuelo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fecha" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="horasDur" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="minutosDur" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         <element name="asientosMaxTurista" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="asientosMaxEjecutivo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="asientosResTurista" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="asientosResEjecutivo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="reservas" type="{http://publicar/}dtReserva" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="aerolinea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="ruta" type="{http://publicar/}dtRutasDeVuelo" minOccurs="0"/>
 *         <element name="horaString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="minutoString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtVuelo", propOrder = {
    "nombre",
    "fecha",
    "horasDur",
    "minutosDur",
    "asientosMaxTurista",
    "asientosMaxEjecutivo",
    "asientosResTurista",
    "asientosResEjecutivo",
    "fechaAlta",
    "reservas",
    "imagen",
    "aerolinea",
    "ruta",
    "horaString",
    "minutoString"
})
public class DtVuelo {

    protected String nombre;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fecha;
    protected Integer horasDur;
    protected Integer minutosDur;
    protected int asientosMaxTurista;
    protected int asientosMaxEjecutivo;
    protected int asientosResTurista;
    protected int asientosResEjecutivo;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAlta;
    @XmlElement(nillable = true)
    protected List<DtReserva> reservas;
    protected String imagen;
    protected String aerolinea;
    protected DtRutasDeVuelo ruta;
    protected String horaString;
    protected String minutoString;

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
     * Obtiene el valor de la propiedad fecha.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFecha() {
        return fecha;
    }

    /**
     * Define el valor de la propiedad fecha.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFecha(XMLGregorianCalendar value) {
        this.fecha = value;
    }

    /**
     * Obtiene el valor de la propiedad horasDur.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getHorasDur() {
        return horasDur;
    }

    /**
     * Define el valor de la propiedad horasDur.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setHorasDur(Integer value) {
        this.horasDur = value;
    }

    /**
     * Obtiene el valor de la propiedad minutosDur.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getMinutosDur() {
        return minutosDur;
    }

    /**
     * Define el valor de la propiedad minutosDur.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setMinutosDur(Integer value) {
        this.minutosDur = value;
    }

    /**
     * Obtiene el valor de la propiedad asientosMaxTurista.
     * 
     */
    public int getAsientosMaxTurista() {
        return asientosMaxTurista;
    }

    /**
     * Define el valor de la propiedad asientosMaxTurista.
     * 
     */
    public void setAsientosMaxTurista(int value) {
        this.asientosMaxTurista = value;
    }

    /**
     * Obtiene el valor de la propiedad asientosMaxEjecutivo.
     * 
     */
    public int getAsientosMaxEjecutivo() {
        return asientosMaxEjecutivo;
    }

    /**
     * Define el valor de la propiedad asientosMaxEjecutivo.
     * 
     */
    public void setAsientosMaxEjecutivo(int value) {
        this.asientosMaxEjecutivo = value;
    }

    /**
     * Obtiene el valor de la propiedad asientosResTurista.
     * 
     */
    public int getAsientosResTurista() {
        return asientosResTurista;
    }

    /**
     * Define el valor de la propiedad asientosResTurista.
     * 
     */
    public void setAsientosResTurista(int value) {
        this.asientosResTurista = value;
    }

    /**
     * Obtiene el valor de la propiedad asientosResEjecutivo.
     * 
     */
    public int getAsientosResEjecutivo() {
        return asientosResEjecutivo;
    }

    /**
     * Define el valor de la propiedad asientosResEjecutivo.
     * 
     */
    public void setAsientosResEjecutivo(int value) {
        this.asientosResEjecutivo = value;
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
     * Gets the value of the reservas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the reservas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtReserva }
     * 
     * 
     * @return
     *     The value of the reservas property.
     */
    public List<DtReserva> getReservas() {
        if (reservas == null) {
            reservas = new ArrayList<>();
        }
        return this.reservas;
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

    /**
     * Obtiene el valor de la propiedad aerolinea.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAerolinea() {
        return aerolinea;
    }

    /**
     * Define el valor de la propiedad aerolinea.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAerolinea(String value) {
        this.aerolinea = value;
    }

    /**
     * Obtiene el valor de la propiedad ruta.
     * 
     * @return
     *     possible object is
     *     {@link DtRutasDeVuelo }
     *     
     */
    public DtRutasDeVuelo getRuta() {
        return ruta;
    }

    /**
     * Define el valor de la propiedad ruta.
     * 
     * @param value
     *     allowed object is
     *     {@link DtRutasDeVuelo }
     *     
     */
    public void setRuta(DtRutasDeVuelo value) {
        this.ruta = value;
    }

    /**
     * Obtiene el valor de la propiedad horaString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHoraString() {
        return horaString;
    }

    /**
     * Define el valor de la propiedad horaString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHoraString(String value) {
        this.horaString = value;
    }

    /**
     * Obtiene el valor de la propiedad minutoString.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMinutoString() {
        return minutoString;
    }

    /**
     * Define el valor de la propiedad minutoString.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMinutoString(String value) {
        this.minutoString = value;
    }

}
