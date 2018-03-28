const ALL_URL = "http://localhost:8080/resources/todo";
const TODAY_URL = "http://localhost:8080/resources/todo/today";
class IndexController {

    constructor(ul) {
        this.ulElement = document.getElementById(ul);
        console.dir(this.ulElement);
        this.loadAll();
    }

    loadAll() {
        fetch(ALL_URL)
            .then( (response) => {
                return response.json();
            })
            .then( (myJson)  => {
                console.log(myJson);
                this._render(myJson);
            });

    }

    _render(data) {
        this.ulElement.innerHTML = ''
        data.map(d => this._createElement(d))
            .forEach(item => {
                this.ulElement.appendChild(item)
            });
    }

    _createElement(todo) {
        let el = document.createElement('a');
        el.classList.add('list-group-item');
        el.setAttribute("data-id",todo.id);
        el.setAttribute("href",'#');
        el.innerHTML = `${todo.titolo} - ${todo.testo}`;
        return el;
    }
}

new IndexController('all');