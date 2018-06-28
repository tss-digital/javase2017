(function () {
    const currentDocument = document.currentScript.ownerDocument;
    class Login extends HTMLElement {

        constructor() {
            super();
        }

        // Called when element is inserted in DOM
        connectedCallback() {
            const shadowRoot = this;//this.attachShadow({ mode: 'open' });
            const template = currentDocument.querySelector('#login-template');
            const instance = template.content.cloneNode(true);
            shadowRoot.appendChild(instance);
        }

        attributeChanngedCallback(attributeName,oldValue,newValue){
            console.log(`attribute change listener..${attributeName} , ${oldValue} , ${newValue} ` )
        }

        static get observedAttribute(){
            return ['']
        }

        get login(){
            return this.getAttribute('loginEndPoint')
        }

        set loginEndPoint(value){
            this.setAttribute('loginEndPoint', value)
        }
    }

    customElements.define('esame-login', Login);
})()
