
package publicar;

import javax.xml.namespace.QName;
import jakarta.xml.bind.JAXBElement;
import jakarta.xml.bind.annotation.XmlElementDecl;
import jakarta.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the publicar package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DatoInvalido_QNAME = new QName("http://publicar/", "DatoInvalido");
    private final static QName _InstanciaNoExiste_QNAME = new QName("http://publicar/", "InstanciaNoExiste");
    private final static QName _InstanciaRepetida_QNAME = new QName("http://publicar/", "InstanciaRepetida");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: publicar
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DtCliente }
     * 
     * @return
     *     the new instance of {@link DtCliente }
     */
    public DtCliente createDtCliente() {
        return new DtCliente();
    }

    /**
     * Create an instance of {@link DtCliente.Paquetes }
     * 
     * @return
     *     the new instance of {@link DtCliente.Paquetes }
     */
    public DtCliente.Paquetes createDtClientePaquetes() {
        return new DtCliente.Paquetes();
    }

    /**
     * Create an instance of {@link DtRutasDeVuelo }
     * 
     * @return
     *     the new instance of {@link DtRutasDeVuelo }
     */
    public DtRutasDeVuelo createDtRutasDeVuelo() {
        return new DtRutasDeVuelo();
    }

    /**
     * Create an instance of {@link DtRutasDeVuelo.Vuelos }
     * 
     * @return
     *     the new instance of {@link DtRutasDeVuelo.Vuelos }
     */
    public DtRutasDeVuelo.Vuelos createDtRutasDeVueloVuelos() {
        return new DtRutasDeVuelo.Vuelos();
    }

    /**
     * Create an instance of {@link DtAerolinea }
     * 
     * @return
     *     the new instance of {@link DtAerolinea }
     */
    public DtAerolinea createDtAerolinea() {
        return new DtAerolinea();
    }

    /**
     * Create an instance of {@link DtAerolinea.Rutas }
     * 
     * @return
     *     the new instance of {@link DtAerolinea.Rutas }
     */
    public DtAerolinea.Rutas createDtAerolineaRutas() {
        return new DtAerolinea.Rutas();
    }

    /**
     * Create an instance of {@link DatoInvalido }
     * 
     * @return
     *     the new instance of {@link DatoInvalido }
     */
    public DatoInvalido createDatoInvalido() {
        return new DatoInvalido();
    }

    /**
     * Create an instance of {@link InstanciaNoExiste }
     * 
     * @return
     *     the new instance of {@link InstanciaNoExiste }
     */
    public InstanciaNoExiste createInstanciaNoExiste() {
        return new InstanciaNoExiste();
    }

    /**
     * Create an instance of {@link InstanciaRepetida }
     * 
     * @return
     *     the new instance of {@link InstanciaRepetida }
     */
    public InstanciaRepetida createInstanciaRepetida() {
        return new InstanciaRepetida();
    }

    /**
     * Create an instance of {@link DtListaAerolineas }
     * 
     * @return
     *     the new instance of {@link DtListaAerolineas }
     */
    public DtListaAerolineas createDtListaAerolineas() {
        return new DtListaAerolineas();
    }

    /**
     * Create an instance of {@link DtUsuario }
     * 
     * @return
     *     the new instance of {@link DtUsuario }
     */
    public DtUsuario createDtUsuario() {
        return new DtUsuario();
    }

    /**
     * Create an instance of {@link LocalTime }
     * 
     * @return
     *     the new instance of {@link LocalTime }
     */
    public LocalTime createLocalTime() {
        return new LocalTime();
    }

    /**
     * Create an instance of {@link DtCiudad }
     * 
     * @return
     *     the new instance of {@link DtCiudad }
     */
    public DtCiudad createDtCiudad() {
        return new DtCiudad();
    }

    /**
     * Create an instance of {@link DtVuelo }
     * 
     * @return
     *     the new instance of {@link DtVuelo }
     */
    public DtVuelo createDtVuelo() {
        return new DtVuelo();
    }

    /**
     * Create an instance of {@link DtReserva }
     * 
     * @return
     *     the new instance of {@link DtReserva }
     */
    public DtReserva createDtReserva() {
        return new DtReserva();
    }

    /**
     * Create an instance of {@link DtPasajero }
     * 
     * @return
     *     the new instance of {@link DtPasajero }
     */
    public DtPasajero createDtPasajero() {
        return new DtPasajero();
    }

    /**
     * Create an instance of {@link DtCompraPaquete }
     * 
     * @return
     *     the new instance of {@link DtCompraPaquete }
     */
    public DtCompraPaquete createDtCompraPaquete() {
        return new DtCompraPaquete();
    }

    /**
     * Create an instance of {@link DtPaquete }
     * 
     * @return
     *     the new instance of {@link DtPaquete }
     */
    public DtPaquete createDtPaquete() {
        return new DtPaquete();
    }

    /**
     * Create an instance of {@link DtPaqueteRuta }
     * 
     * @return
     *     the new instance of {@link DtPaqueteRuta }
     */
    public DtPaqueteRuta createDtPaqueteRuta() {
        return new DtPaqueteRuta();
    }

    /**
     * Create an instance of {@link DtCompraRuta }
     * 
     * @return
     *     the new instance of {@link DtCompraRuta }
     */
    public DtCompraRuta createDtCompraRuta() {
        return new DtCompraRuta();
    }

    /**
     * Create an instance of {@link DtCategoria }
     * 
     * @return
     *     the new instance of {@link DtCategoria }
     */
    public DtCategoria createDtCategoria() {
        return new DtCategoria();
    }

    /**
     * Create an instance of {@link DtListaRutas }
     * 
     * @return
     *     the new instance of {@link DtListaRutas }
     */
    public DtListaRutas createDtListaRutas() {
        return new DtListaRutas();
    }

    /**
     * Create an instance of {@link DtListaCompraRutas }
     * 
     * @return
     *     the new instance of {@link DtListaCompraRutas }
     */
    public DtListaCompraRutas createDtListaCompraRutas() {
        return new DtListaCompraRutas();
    }

    /**
     * Create an instance of {@link DtBusquedaRutaYPaquete }
     * 
     * @return
     *     the new instance of {@link DtBusquedaRutaYPaquete }
     */
    public DtBusquedaRutaYPaquete createDtBusquedaRutaYPaquete() {
        return new DtBusquedaRutaYPaquete();
    }

    /**
     * Create an instance of {@link DtListaVuelos }
     * 
     * @return
     *     the new instance of {@link DtListaVuelos }
     */
    public DtListaVuelos createDtListaVuelos() {
        return new DtListaVuelos();
    }

    /**
     * Create an instance of {@link DtListaUsuarios }
     * 
     * @return
     *     the new instance of {@link DtListaUsuarios }
     */
    public DtListaUsuarios createDtListaUsuarios() {
        return new DtListaUsuarios();
    }

    /**
     * Create an instance of {@link DtListaString }
     * 
     * @return
     *     the new instance of {@link DtListaString }
     */
    public DtListaString createDtListaString() {
        return new DtListaString();
    }

    /**
     * Create an instance of {@link DtListaPaquetes }
     * 
     * @return
     *     the new instance of {@link DtListaPaquetes }
     */
    public DtListaPaquetes createDtListaPaquetes() {
        return new DtListaPaquetes();
    }

    /**
     * Create an instance of {@link DtListaReservas }
     * 
     * @return
     *     the new instance of {@link DtListaReservas }
     */
    public DtListaReservas createDtListaReservas() {
        return new DtListaReservas();
    }

    /**
     * Create an instance of {@link DtCliente.Paquetes.Entry }
     * 
     * @return
     *     the new instance of {@link DtCliente.Paquetes.Entry }
     */
    public DtCliente.Paquetes.Entry createDtClientePaquetesEntry() {
        return new DtCliente.Paquetes.Entry();
    }

    /**
     * Create an instance of {@link DtRutasDeVuelo.Vuelos.Entry }
     * 
     * @return
     *     the new instance of {@link DtRutasDeVuelo.Vuelos.Entry }
     */
    public DtRutasDeVuelo.Vuelos.Entry createDtRutasDeVueloVuelosEntry() {
        return new DtRutasDeVuelo.Vuelos.Entry();
    }

    /**
     * Create an instance of {@link DtAerolinea.Rutas.Entry }
     * 
     * @return
     *     the new instance of {@link DtAerolinea.Rutas.Entry }
     */
    public DtAerolinea.Rutas.Entry createDtAerolineaRutasEntry() {
        return new DtAerolinea.Rutas.Entry();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatoInvalido }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link DatoInvalido }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "DatoInvalido")
    public JAXBElement<DatoInvalido> createDatoInvalido(DatoInvalido value) {
        return new JAXBElement<>(_DatoInvalido_QNAME, DatoInvalido.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstanciaNoExiste }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InstanciaNoExiste }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "InstanciaNoExiste")
    public JAXBElement<InstanciaNoExiste> createInstanciaNoExiste(InstanciaNoExiste value) {
        return new JAXBElement<>(_InstanciaNoExiste_QNAME, InstanciaNoExiste.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InstanciaRepetida }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InstanciaRepetida }{@code >}
     */
    @XmlElementDecl(namespace = "http://publicar/", name = "InstanciaRepetida")
    public JAXBElement<InstanciaRepetida> createInstanciaRepetida(InstanciaRepetida value) {
        return new JAXBElement<>(_InstanciaRepetida_QNAME, InstanciaRepetida.class, null, value);
    }

}
