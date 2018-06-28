(function () {
    const currentDocument = document.currentScript.ownerDocument;
    class Registration extends HTMLElement {

        constructor() {
            super();
        }

        // Called when element is inserted in DOM
        connectedCallback() {
            const shadowRoot = this;//this.attachShadow({ mode: 'open' });
            const template = currentDocument.querySelector('#registration-template');
            const instance = template.content.cloneNode(true);
            shadowRoot.appendChild(instance);
        }
    }

    customElements.define('esame-registration', Registration);
})()