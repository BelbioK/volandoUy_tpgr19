
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
 * <p>Clase Java para dtRutasDeVuelo complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>{@code
 * <complexType name="dtRutasDeVuelo">
 *   <complexContent>
 *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       <sequence>
 *         <element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="descripcionCorta" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="hora" type="{http://publicar/}localTime" minOccurs="0"/>
 *         <element name="horaString" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="fechaAlta" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         <element name="origen" type="{http://publicar/}dtCiudad" minOccurs="0"/>
 *         <element name="destino" type="{http://publicar/}dtCiudad" minOccurs="0"/>
 *         <element name="costoTurista" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costoEjecutivo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="costoEquipaje" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         <element name="vuelos">
 *           <complexType>
 *             <complexContent>
 *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 <sequence>
 *                   <element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     <complexType>
 *                       <complexContent>
 *                         <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           <sequence>
 *                             <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             <element name="value" type="{http://publicar/}dtVuelo" minOccurs="0"/>
 *                           </sequence>
 *                         </restriction>
 *                       </complexContent>
 *                     </complexType>
 *                   </element>
 *                 </sequence>
 *               </restriction>
 *             </complexContent>
 *           </complexType>
 *         </element>
 *         <element name="vuelosSet" type="{http://publicar/}dtVuelo" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="categorias" type="{http://publicar/}dtCategoria" maxOccurs="unbounded" minOccurs="0"/>
 *         <element name="estado" type="{http://publicar/}estado" minOccurs="0"/>
 *         <element name="aerolinea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="imagen" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="video" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         <element name="visitas" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       </sequence>
 *     </restriction>
 *   </complexContent>
 * </complexType>
 * }</pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dtRutasDeVuelo", propOrder = {
    "nombre",
    "descripcion",
    "descripcionCorta",
    "hora",
    "horaString",
    "fechaAlta",
    "origen",
    "destino",
    "costoTurista",
    "costoEjecutivo",
    "costoEquipaje",
    "vuelos",
    "vuelosSet",
    "categorias",
    "estado",
    "aerolinea",
    "imagen",
    "video",
    "visitas"
})
public class DtRutasDeVuelo {

    protected String nombre;
    protected String descripcion;
    protected String descripcionCorta;
    protected LocalTime hora;
    protected String horaString;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar fechaAlta;
    protected DtCiudad origen;
    protected DtCiudad destino;
    protected int costoTurista;
    protected int costoEjecutivo;
    protected int costoEquipaje;
    @XmlElement(required = true)
    protected DtRutasDeVuelo.Vuelos vuelos;
    @XmlElement(nillable = true)
    protected List<DtVuelo> vuelosSet;
    @XmlElement(nillable = true)
    protected List<DtCategoria> categorias;
    @XmlSchemaType(name = "string")
    protected Estado estado;
    protected String aerolinea;
    protected String imagen;
    protected String video;
    protected int visitas;

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
     * Obtiene el valor de la propiedad descripcionCorta.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescripcionCorta() {
        return descripcionCorta;
    }

    /**
     * Define el valor de la propiedad descripcionCorta.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescripcionCorta(String value) {
        this.descripcionCorta = value;
    }

    /**
     * Obtiene el valor de la propiedad hora.
     * 
     * @return
     *     possible object is
     *     {@link LocalTime }
     *     
     */
    public LocalTime getHora() {
        return hora;
    }

    /**
     * Define el valor de la propiedad hora.
     * 
     * @param value
     *     allowed object is
     *     {@link LocalTime }
     *     
     */
    public void setHora(LocalTime value) {
        this.hora = value;
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
     * Obtiene el valor de la propiedad origen.
     * 
     * @return
     *     possible object is
     *     {@link DtCiudad }
     *     
     */
    public DtCiudad getOrigen() {
        return origen;
    }

    /**
     * Define el valor de la propiedad origen.
     * 
     * @param value
     *     allowed object is
     *     {@link DtCiudad }
     *     
     */
    public void setOrigen(DtCiudad value) {
        this.origen = value;
    }

    /**
     * Obtiene el valor de la propiedad destino.
     * 
     * @return
     *     possible object is
     *     {@link DtCiudad }
     *     
     */
    public DtCiudad getDestino() {
        return destino;
    }

    /**
     * Define el valor de la propiedad destino.
     * 
     * @param value
     *     allowed object is
     *     {@link DtCiudad }
     *     
     */
    public void setDestino(DtCiudad value) {
        this.destino = value;
    }

    /**
     * Obtiene el valor de la propiedad costoTurista.
     * 
     */
    public int getCostoTurista() {
        return costoTurista;
    }

    /**
     * Define el valor de la propiedad costoTurista.
     * 
     */
    public void setCostoTurista(int value) {
        this.costoTurista = value;
    }

    /**
     * Obtiene el valor de la propiedad costoEjecutivo.
     * 
     */
    public int getCostoEjecutivo() {
        return costoEjecutivo;
    }

    /**
     * Define el valor de la propiedad costoEjecutivo.
     * 
     */
    public void setCostoEjecutivo(int value) {
        this.costoEjecutivo = value;
    }

    /**
     * Obtiene el valor de la propiedad costoEquipaje.
     * 
     */
    public int getCostoEquipaje() {
        return costoEquipaje;
    }

    /**
     * Define el valor de la propiedad costoEquipaje.
     * 
     */
    public void setCostoEquipaje(int value) {
        this.costoEquipaje = value;
    }

    /**
     * Obtiene el valor de la propiedad vuelos.
     * 
     * @return
     *     possible object is
     *     {@link DtRutasDeVuelo.Vuelos }
     *     
     */
    public DtRutasDeVuelo.Vuelos getVuelos() {
        return vuelos;
    }

    /**
     * Define el valor de la propiedad vuelos.
     * 
     * @param value
     *     allowed object is
     *     {@link DtRutasDeVuelo.Vuelos }
     *     
     */
    public void setVuelos(DtRutasDeVuelo.Vuelos value) {
        this.vuelos = value;
    }

    /**
     * Gets the value of the vuelosSet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the vuelosSet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getVuelosSet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtVuelo }
     * 
     * 
     * @return
     *     The value of the vuelosSet property.
     */
    public List<DtVuelo> getVuelosSet() {
        if (vuelosSet == null) {
            vuelosSet = new ArrayList<>();
        }
        return this.vuelosSet;
    }

    /**
     * Gets the value of the categorias property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the Jakarta XML Binding object.
     * This is why there is not a {@code set} method for the categorias property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getCategorias().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DtCategoria }
     * 
     * 
     * @return
     *     The value of the categorias property.
     */
    public List<DtCategoria> getCategorias() {
        if (categorias == null) {
            categorias = new ArrayList<>();
        }
        return this.categorias;
    }

    /**
     * Obtiene el valor de la propiedad estado.
     * 
     * @return
     *     possible object is
     *     {@link Estado }
     *     
     */
    public Estado getEstado() {
        return estado;
    }

    /**
     * Define el valor de la propiedad estado.
     * 
     * @param value
     *     allowed object is
     *     {@link Estado }
     *     
     */
    public void setEstado(Estado value) {
        this.estado = value;
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
     * Obtiene el valor de la propiedad video.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideo() {
        return video;
    }

    /**
     * Define el valor de la propiedad video.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideo(String value) {
        this.video = value;
    }

    /**
     * Obtiene el valor de la propiedad visitas.
     * 
     */
    public int getVisitas() {
        return visitas;
    }

    /**
     * Define el valor de la propiedad visitas.
     * 
     */
    public void setVisitas(int value) {
        this.visitas = value;
    }


    /**
     * <p>Clase Java para anonymous complex type.
     * 
     * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
     * 
     * <pre>{@code
     * <complexType>
     *   <complexContent>
     *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       <sequence>
     *         <element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           <complexType>
     *             <complexContent>
     *               <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 <sequence>
     *                   <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   <element name="value" type="{http://publicar/}dtVuelo" minOccurs="0"/>
     *                 </sequence>
     *               </restriction>
     *             </complexContent>
     *           </complexType>
     *         </element>
     *       </sequence>
     *     </restriction>
     *   </complexContent>
     * </complexType>
     * }</pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class Vuelos {

        protected List<DtRutasDeVuelo.Vuelos.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the Jakarta XML Binding object.
         * This is why there is not a {@code set} method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link DtRutasDeVuelo.Vuelos.Entry }
         * 
         * 
         * @return
         *     The value of the entry property.
         */
        public List<DtRutasDeVuelo.Vuelos.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<>();
            }
            return this.entry;
        }


        /**
         * <p>Clase Java para anonymous complex type.
         * 
         * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
         * 
         * <pre>{@code
         * <complexType>
         *   <complexContent>
         *     <restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       <sequence>
         *         <element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         <element name="value" type="{http://publicar/}dtVuelo" minOccurs="0"/>
         *       </sequence>
         *     </restriction>
         *   </complexContent>
         * </complexType>
         * }</pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            protected DtVuelo value;

            /**
             * Obtiene el valor de la propiedad key.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Define el valor de la propiedad key.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Obtiene el valor de la propiedad value.
             * 
             * @return
             *     possible object is
             *     {@link DtVuelo }
             *     
             */
            public DtVuelo getValue() {
                return value;
            }

            /**
             * Define el valor de la propiedad value.
             * 
             * @param value
             *     allowed object is
             *     {@link DtVuelo }
             *     
             */
            public void setValue(DtVuelo value) {
                this.value = value;
            }

        }

    }

}
