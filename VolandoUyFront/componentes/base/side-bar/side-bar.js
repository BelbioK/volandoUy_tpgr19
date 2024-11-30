import { navigate,getUsuario } from "../../../utils/index.js";
import ControladorPaquete from '../../../controladores/paquetes.js'
import ControladorVuelo from '../../../controladores/vuelos.js'
import ControladorUsuario from '../../../controladores/usuarios.js'

class SidebarComponent extends HTMLElement {

    static observedAttributes = ["update"]; 

    constructor() {
        super();
        this.attachShadow({ mode: 'open' });
        this.shadowRoot.innerHTML = '';
        this.addStyles();  
        this.getTemplate()
    }
    
    connectedCallback() {
        this.connected = true;
        this.addEvents();
    }

    addEvents(){
        if(!this.template) return;

        console.log("carga eventos")
        const links = this.shadowRoot.children[1].children[0].children
        for(let i = 0; i< links.length;i++)
            links[i].addEventListener("click",()=> navigate(links[i].getAttribute('path')));

        var hasBusqueda = !!this.shadowRoot.getElementById('side-bar-busqueda');
        this.shadowRoot.getElementById("side-bar-aplicar-filtros").addEventListener("click",()=>{
            const filters = {}
            if(hasBusqueda)
                filters.busqueda = this.shadowRoot.getElementById('side-bar-busqueda').value
            this.shadowRoot.getElementById("filters").childNodes.forEach(childElement => {
                if(childElement.tagName != "SELECT")
                    return;
                if(!childElement.multiple)
                    filters[childElement.id.replace("side-bar-","")]=childElement.value;
                else{
                    const key = childElement.id.replace("side-bar-","");
                    filters[key] = [];
                    for(let i = 0;i<childElement.options.length;i++)
                        if(childElement.options[i].selected)
                            filters[key].push(childElement.options[i].value)
                }
            })
            localStorage.setItem("filtros",JSON.stringify(filters));
            let params = {};
            try {
                params = JSON.parse(localStorage.getItem("params"));
            } catch (e) {}
            navigate(localStorage.getItem("lastRoute"),params)
        })

        let filters = {};
        try {
            filters = JSON.parse(localStorage.getItem("filtros")) 
        } catch (e) {
        }
        if(hasBusqueda)
            this.shadowRoot.getElementById('side-bar-busqueda').value = filters.busqueda || ""
        this.shadowRoot.getElementById("filters").childNodes.forEach(childElement => {
            if(childElement.tagName != "SELECT")
                return;
            if(!childElement.multiple)
                childElement.value = filters[childElement.id.replace("side-bar-","")] || "";
            else{
                const key = childElement.id.replace("side-bar-","");
                for(let i = 0;i<childElement.options.length;i++)
                    childElement.options[i].selected = filters[key]?.includes(childElement.options[i].value);
            }
        })
    }

    attributeChangedCallback(name, oldValue, newValue) {
        this.getTemplate();
    }

    addStyles() {
        const linkElem = document.createElement("link");
        linkElem.setAttribute("rel", "stylesheet");
        linkElem.setAttribute("href", "./componentes/base/side-bar/style.css");
        this.shadowRoot.appendChild(linkElem);
    }
    
    async getTemplate(){
        const sidebarTemplate = document.createElement('template');
        const userOptions = this.getUserOptions();
        const filters = await this.getPageFilters();
        sidebarTemplate.innerHTML = `
            <div class="side-bar">
                <div id="user-options" class="rounded-rectangle user-options">
                    ${
                        userOptions.map(option => `
                                <span class='link ${option.getSelected() ? 'selected' : ''}' path='${option.path}'>${option.text}</span>
                            `).join('')
                    }
                </div>
                <div id="filters" class="rounded-rectangle filters">
                    ${filters}
                    <button id="side-bar-aplicar-filtros" class='btn-aplicar'>Aplicar</button>                  
                </div>
            </div>
        `;
        this.template = sidebarTemplate;
        if(this.shadowRoot.children[1])
            this.shadowRoot.replaceChild(this.template.content.cloneNode(true),this.shadowRoot.children[1]);
        else
            this.shadowRoot.appendChild(this.template.content.cloneNode(true));  // Add the HTML structure
        if(this.connected)
            this.addEvents();
    }

    getUserOptions(){
        const usuario = getUsuario();
        const consultaRuta = {path:"/consultaRuta",text:"ConsultaRuta"}
        const listarUsuarios = {path:"/listarUsuarios",text:"Usuarios", getSelected:()=>["/listarUsuarios","/consultaUsuario"].includes(localStorage.getItem("lastRoute"))}
        const listarRutas = {path:"/listarRutas",text:"Rutas de vuelo",getSelected:()=>["/listarRutas","/consultaRuta"].includes(localStorage.getItem("lastRoute"))}
        const listarVuelos = {path:"/listarVuelos",text:"Vuelos",getSelected:()=>["/listarVuelos","/consultaVuelo"].includes(localStorage.getItem("lastRoute"))}
        const listarPaquetes = {path:"/listarPaquetes",text:"Paquetes",getSelected:()=>["/listarPaquetes","/consultaPaquete"].includes(localStorage.getItem("lastRoute"))}
        const listarReservas = {path:"/listarReservas",text:"Reservas",getSelected:()=>["/listarReservas","/consultaReserva"].includes(localStorage.getItem("lastRoute"))}
        if(!usuario)
            return [listarUsuarios,listarRutas,listarVuelos,listarPaquetes];
        else if(usuario.tipo == 'cliente')
            return [
                listarUsuarios,listarRutas,listarVuelos,listarPaquetes,listarReservas,
                {
                    path:"/reservarVuelo",
                    text:"Reservar vuelo",
                    getSelected:()=>["/reservarVuelo"].includes(localStorage.getItem("lastRoute"))
                },
                {
                    path:"/comprarPaquete",
                    text:"Comprar paquete",
                    getSelected:()=>["/comprarPaquete"].includes(localStorage.getItem("lastRoute"))
                }
            ];
        else if(usuario.tipo == 'aerolinea')
            return [
                listarUsuarios,listarRutas,listarVuelos,listarReservas,
                {
                    path:"/altaRuta",
                    text:"Crear ruta de vuelo",
                    getSelected:()=>["/altaRuta"].includes(localStorage.getItem("lastRoute"))
                },
                {
                    path:"/altaVuelo",
                    text:"Agregar vuelo",
                    getSelected:()=>["/altaVuelo"].includes(localStorage.getItem("lastRoute"))
                }
            ];

        return [];
    }
    async getPageFilters(){
        const route = localStorage.getItem("lastRoute");
        switch(route){
            case "/listarPaquetes":
                return this.getSearchInput() 
                        + this.getSelect("Rutas de vuelo","ruta",(await ControladorVuelo.listarRutasDeVuelo()).map(a => ({value:a.nombre,text:a.nombre})));
            case "/listarUsuarios":
                return this.getSearchInput()
                        + this.getSelect("Tipo","tipo",[{text:"Cliente",value:"cliente"},{text:"Aerolinea",value:"aerolinea"}]);
            case "/listarVuelos":
                return this.getSearchInput() 
                        + this.getSelect("Aerolinea","aerolinea",(await ControladorUsuario.listarAerolineas()).map(a => ({value:a.nickname,text:a.nombre}))) 
                        + this.getSelect("Ruta de vuelo","ruta",(await ControladorVuelo.listarRutasDeVuelo()).map(a => ({value:a.nombre,text:a.nombre})));
            case "/listarRutas":
                return this.getSearchInput() 
                        + this.getSelect("Aerolinea","aerolinea",(await ControladorUsuario.listarAerolineas()).map(a => ({value:a.nickname,text:a.nombre}))) 
//                        + this.getSelect("Estado","estado",[{text:"Confirmada",value:"confirmada"},{text:"Rechazada",value:"rechazada"},{text:"Pendiente",value:"pendiente"}])
                        + await this.getFiltroCategorias();
            case "/listarReservas":
                return this.getSearchInput() 
                        + getUsuario().tipo == 'aerolinea' ? '' : this.getSelect("Aerolinea","aerolinea",(await ControladorUsuario.listarAerolineas()).map(a => ({value:a.nickname,text:a.nombre}))) 
                        + this.getSelect("Ruta de vuelo","ruta",(await ControladorVuelo.listarRutasDeVuelo()).map(a => ({value:a.nombre,text:a.nombre})))
                        + this.getSelect("Vuelo","vuelo",(await ControladorVuelo.listarVuelos()).map(a => ({value:a.nombre,text:a.nombre})))
        }

        return "";
    }

    getSearchInput(){
        return `<span>Búsqueda</span>
                <input id='side-bar-busqueda'/>`;
    }

    getSelect(label,filter,items){
        return `<span>${label}</span>
                <select class="filter-select" id="side-bar-${filter}">
                    <option value=""></option>
                    ${
                        items.map(item => `<option value="${item.value}">${item.text}</option>`)
                    }
                </select>`
    }

    async getFiltroCategorias(){
        const categorias =await ControladorVuelo.listarCategorias();
        return `<span>Categorias</span>
                <select multiple id="side-bar-categorias" style="overflow-y: auto;">
                    ${categorias.map(c => `<option value="${c}">${c}</option>`)}
                </select>
                `
    }
}

// Define the custom element for the sidebar
customElements.define('sidebar-component', SidebarComponent);
