class ErrorPopup extends HTMLElement {
    static observedAttributes = ['message','show'] 
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
        <div class="error-popup">
            <span class="error-message"></span>
            <button class="close-button">&times;</button>
        </div>
        `;
        return template;
    }

    connectedCallback() {
        // this.setMessage(this.getAttribute('message') || 'An error occurred');
        this.addEventListeners();
    }

    attributeChangedCallback(name, oldValue, newValue) {
        if(name === 'message')
            this.setMessage(newValue);
    }

    addEventListeners() {
        this.shadowRoot.querySelector('.close-button').addEventListener('click', () => {
            this.hide();
        });
    }

    // Display the error message
    setMessage(message) {
        this.shadowRoot.querySelector('.error-message').innerHTML = message;
        this.show(); // Show the popup when a new message is set
    }

    // Show the error popup
    show() {
        this.shadowRoot.querySelector('.error-popup').classList.add('visible');
        // setTimeout(() => this.hide(), 5000);
    }

    // Hide the error popup
    hide() {
        this.shadowRoot.querySelector('.error-popup').classList.remove('visible');
    }

    addStyles() {    
        const linkElem = document.createElement("link");
        linkElem.setAttribute("rel", "stylesheet");
        linkElem.setAttribute("href", "./componentes/base/error-popup/style.css");
        this.shadowRoot.appendChild(linkElem);
    }
}

// Define the custom element
customElements.define('error-popup', ErrorPopup);