import { navigate } from "../../../utils/index.js";
class ResourceList extends HTMLElement {

    static observedAttributes = ["items","button","path","paramkey"]; 

    constructor() {
        super();
      this.attachShadow({ mode: 'open' });
        this.shadowRoot.innerHTML = '';
        this.addStyles();
        this.items = [];
        this.button = null;
        this.template = this.getTemplate()
        this.shadowRoot.appendChild(this.template.content.cloneNode(true));  // Add the HTML structure
    }
    
    connectedCallback() {
        this.addClickEvents();
    }

    attributeChangedCallback(name, oldValue, newValue) {
        if(oldValue == newValue) return; 
        
        try{
            this[name] = JSON.parse(newValue);
            this.updateItems();
        }catch(e){
            this[name] = newValue;
            this.addClickEvents()
        }
    }

    addStyles() {
        const linkElem = document.createElement("link");
        linkElem.setAttribute("rel", "stylesheet");
        linkElem.setAttribute("href", "./componentes/base/resource-list/style.css");
        this.shadowRoot.appendChild(linkElem);
    }
    
    getTemplate(){
        const template = document.createElement('template');
        const items = this.items || [];
        const button = this.button || null;
        template.innerHTML = `
            <div class="resource-list">
                ${items
                    .map(item => `<div id="resource-${item.name}" class="resource-item">
                                    <img src="${item.image}" alt="${item.resource}" class="resource-image">
                                    <span style="padding-left:1rem">${item.name}</span>
                                </div>`)
                    .join("")}
                
                ${button ? `<button id='button-${button.text.replace(" ","")}' class="resource-list-button">${button.text}</button>` :''}
            </div>
        `;
        return template;
    }

    updateItems(){
        this.template = this.getTemplate()
        this.shadowRoot.replaceChild(this.template.content.cloneNode(true),this.shadowRoot.children[1]);
    }

    addClickEvents(){
        if(this.path && this.paramkey && this.items){
            this.items.forEach(item =>{
                this.shadowRoot
                    .getElementById(`resource-${item.name}`)
                    ?.addEventListener("click",()=> navigate(this.path,{[this.paramkey]:item.key}));
            })
        }
        if(this.button)
            this.shadowRoot
                .getElementById(`button-${this.button.text.replace(" ","")}`)
                ?.addEventListener("click", () => navigate(this.button.path,this.button.params))
    }
}

// Define the custom element for the sidebar
customElements.define('resource-list', ResourceList);