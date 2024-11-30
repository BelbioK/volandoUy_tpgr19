class SuccessPopup extends HTMLElement {
    static observedAttributes = ['titulo','subtitulo','hide'] 
    constructor() {
        super();
        this.attachShadow({ mode: 'open' });
        this.shadowRoot.appendChild(this.generateTemplate().content.cloneNode(true));
        this.addStyles();
    }

    // Add a dynamic template for the component
    generateTemplate() {
        const template = document.createElement('template');
        template.innerHTML = `
        <div id="confirmar-popup" style="display:none; opacity:${!this.hide || this.hide=='true' ? '0' : '1'}">
            <span id="titulo-confirmar">${this.titulo}</span>
            ${this.subtitulo ? `<span id="subtitulo-confirmar">${this.subtitulo}</span>` : ''}
            <button class="close-button" id="btn-cerrar-confirmar">Cerrar</button>
        </div>
        `;
        return template;
    }

    connectedCallback() {
        // this.setMessage(this.getAttribute('message') || 'An error occurred');
        this.addEventListeners();
    }

    attributeChangedCallback(name, oldValue, newValue) {
        console.log(name,newValue)
        this[name] = newValue;
        if(name != 'hide'){
            this.template = this.generateTemplate()
            this.shadowRoot.replaceChild(this.template.content.cloneNode(true),this.shadowRoot.children[0]);
            this.addEventListeners();
        }else{
            this.shadowRoot.getElementById('confirmar-popup').style.display = this.hide == 'true' ? 'none' : '';
            setTimeout(()=> this.shadowRoot.getElementById('confirmar-popup').style.opacity = this.hide == 'true' ? 0 : 1, 10 )
            
        }
    }

    addEventListeners() {
        this.shadowRoot.querySelector('#btn-cerrar-confirmar').addEventListener('click', () => {
            this.hide = 'true'
            this.shadowRoot.getElementById('confirmar-popup').style.opacity = 0;
            setTimeout(()=>  this.shadowRoot.getElementById('confirmar-popup').style.display = 'none',300 )
            this.addEventListeners();
        });
    }

    addStyles() {    
        const linkElem = document.createElement("link");
        linkElem.setAttribute("rel", "stylesheet");
        linkElem.setAttribute("href", "./componentes/base/success-popup/style.css");
        this.shadowRoot.appendChild(linkElem);
    }
}

// Define the custom element
customElements.define('success-popup', SuccessPopup);