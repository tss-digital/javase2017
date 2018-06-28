class AppRouter {

    constructor(slot) {
        this.init();
        this.slot = document.querySelector('#slot');
        window.addEventListener('hashchange',
            e => this.onRouteChange(e))
    }

    init(){
        this.mapRoute = new Map();
        this.mapRoute.set('Login',{
            path: './components/Login/Login.html',
            tag: '<esame-login></esame-login>'
        });
        this.mapRoute.set('Registration',{
            path: './components/Registration/Registration.html',
            tag: '<esame-registration></esame-registration>'
        });
    }
    onRouteChange(e) {
        console.log(window.location);
        const c = window.location.hash.charAt(1);
        const location = window.location.hash.substring(1);
        switch (c) {
            case '_':
                this.loadComponent(location.substring(1))
                break;
            default:
                this.loadContent(location);
        }
    }

    loadContent(uri) {
        const toload = `${uri}.html`;
        console.log('load html fragment ' + toload);
        fetch(toload)
            .then(r => r.text())
            .then(content => this.updateSlot(content));
    }

    updateSlot(content) {
        this.slot.innerHTML = content;
    }

    loadComponent(uri) {
        if(!this.mapRoute.has(uri)){
            throw new Error('no route to ' + uri);
        }
        const toload = this.mapRoute.get(uri);
        console.log('load component' + toload.path);
        const head = document.head;
        const link = document.createElement('link');
        link.setAttribute('rel', 'import');
        link.setAttribute('href', toload.path);
        link.onload = _ => {
            this.updateSlot(toload.tag);
        };
        document.body.appendChild(link);
    }
}

new AppRouter();