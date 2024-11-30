class Card extends HTMLElement {

    static observedAttributes = ["data"]; 
    
    constructor() {
        super();
        this.attachShadow({ mode: 'open' });
        this.template = this.generateTemplate()
        this.shadowRoot.innerHTML = ''
        this.addStyles() // conecta estilos
        this.shadowRoot.appendChild(this.template.content.cloneNode(true))
    }
    
    connectedCallback() {
        
    }
    attributeChangedCallback(name, oldValue, newValue) {
        try{
            this[name] = JSON.parse(newValue);
            this.updateCardData();
        }catch(e){
            if(newValue)
                console.error(e)
        }
    }
    
    addStyles(){
        const linkElem = document.createElement("link");
        linkElem.setAttribute("rel", "stylesheet");
        linkElem.setAttribute("href", "./componentes/base/card/style.css");
        this.shadowRoot.appendChild(linkElem);
    }

    generateTemplate(){
        const template = document.createElement('template');
        const data = this.data || {image:"",title:"",subtitle:"",content:""}
        template.innerHTML = `
            <div class="card">
                <div class="card-image">
                    <img src="${data.image}"/>
                </div>
                <div class="card-title">${data.title}</div>
                <div class="card-subtitle">${data.subtitle ? data.subtitle : '<slot name="subtitle"></slot>'}</div>
                <div class="card-content">
                    ${data.content}
                </div>
                <div class="card-additionalInfo">
                    <slot name='additionalInfo'/>
                </div>
            </div>
        `
        return template
    }

    updateCardData(){
        this.template = this.generateTemplate()
        this.shadowRoot.replaceChild(this.template.content.cloneNode(true),this.shadowRoot.children[1]);
    }
}

customElements.define('info-card', Card);
