import {getUsuario,navigate} from '../../../utils/index.js'
import ControladorUsuario from '../../../controladores/usuarios.js'
class NavBar extends HTMLElement {

    static observedAttributes = ["default-query"]; 
    
    constructor() {
        super();
        this.attachShadow({ mode: 'open' });
        this.template = this.generateTemplate()
        this.shadowRoot.innerHTML = ''
        this.addStyles() // conecta estilos
        this.shadowRoot.appendChild(this.template.content.cloneNode(true))
    }
    
    connectedCallback() {
        this.shadowRoot.getElementById('searchButton').addEventListener('click',(e) =>{
            alert(this.shadowRoot.getElementById('searchInput').value || this.getAttribute("default-query"));
        })
        this.shadowRoot.getElementById("header-logo").addEventListener('click', ()=>{
            navigate("/");
        })
        if(this.shadowRoot.getElementById("loginCliente")){
            this.shadowRoot.getElementById("loginCliente").addEventListener('click',()=>{
                ControladorUsuario.login("cliente","");
            })
            this.shadowRoot.getElementById("loginAerolinea").addEventListener('click',()=>{
                ControladorUsuario.login("aerolinea","");
            })
            this.shadowRoot.getElementById("registrarme").addEventListener('click',()=>{
                navigate("/registro");
            })
            this.shadowRoot.getElementById("log-in").addEventListener('click',()=>{
                navigate("/login");
            })
        }else{
            this.shadowRoot.getElementById("profile").addEventListener('click',()=>{
                this.shadowRoot.getElementById("profile-menu").classList.toggle("hide");
            })
            this.shadowRoot.getElementById("profile-btn").addEventListener("click",()=>{
                navigate("/consultaUsuario",{nickname:getUsuario().nickname});
            });
            this.shadowRoot.getElementById("logout-btn").addEventListener("click",()=>{
                ControladorUsuario.logout();
            })
        }
    }
    attributeChangedCallback(name, oldValue, newValue) {
        if(oldValue == newValue) return; 
        console.log(`Attribute ${name} has changed.`);
        this.shadowRoot.getElementById('searchInput').value = newValue
    }
    
    addStyles(){
        const linkElem = document.createElement("link");
        linkElem.setAttribute("rel", "stylesheet");
        linkElem.setAttribute("href", "./componentes/base/nav-bar/style.css");
        this.shadowRoot.appendChild(linkElem);
    }

    generateTemplate(){
        const template = document.createElement('template');
        // TODO: Aca podriamos cargar el template desde algun html
        template.innerHTML = `
        <nav class="navbar">
            
            <a id="header-logo" class="navbar-logo"><img style="margin-right:10px" src="./assets/logo/logo.svg" height="40px" width="40px"/>Volando<span>.uy</span></a>

            <div class="search-container">
                <input type="text" id="searchInput" class="search-input" placeholder="Origen, Destino, Aerolínea, Paquete">
                <button id="searchButton" class="search-button">
                    <svg fill="#000000" height="20px" width="20px" version="1.1" id="Capa_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="0 0 488.4 488.4" xml:space="preserve"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round"></g><g id="SVGRepo_iconCarrier"> <g> <g> <path d="M0,203.25c0,112.1,91.2,203.2,203.2,203.2c51.6,0,98.8-19.4,134.7-51.2l129.5,129.5c2.4,2.4,5.5,3.6,8.7,3.6 s6.3-1.2,8.7-3.6c4.8-4.8,4.8-12.5,0-17.3l-129.6-129.5c31.8-35.9,51.2-83,51.2-134.7c0-112.1-91.2-203.2-203.2-203.2 S0,91.15,0,203.25z M381.9,203.25c0,98.5-80.2,178.7-178.7,178.7s-178.7-80.2-178.7-178.7s80.2-178.7,178.7-178.7 S381.9,104.65,381.9,203.25z"></path> </g> </g> </g></svg>
                </button>
            </div>

            <div class="right-links">
                ${this.getUserOptionsHtml()}
            </div>
        </nav>
        `
        return template
    }
    getUserOptionsHtml(){
        const usuario = getUsuario();
        if(usuario){
            return `<div id="profile" class="profile">
                        <img src="./assets/imagenes-precargadas/${usuario.imagen}" alt="Profile" class="profile-image">
                        <span class="profile-name">${usuario.nombre} ${usuario.apellido || ""}</span>
                        <div id="profile-menu" class="profile-menu hide">
                            <span id="profile-btn">Perfil</span>
                            <span id="logout-btn">Cerrar sesión</span>
                        </div>
                    </div>`
        }else{
            return `
                <div style="margin-right:5px; display:flex; flex-direction:column;">
                    <button id="loginCliente" class="button login">Cliente</button>
                    <button id="loginAerolinea" class="button login">Aerolinea</button>
                </div>
                <div>
                    <button id="log-in" class="button">Iniciar Sesión</button>
                    <button id="registrarme" class="button">Registrarme</button>
                </div>
            `
        }
    }
}

customElements.define('nav-bar', NavBar);
