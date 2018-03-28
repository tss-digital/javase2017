let container = document.getElementById('app');
let url = new URL(document.location.href);
const oggi = url.searchParams.get("oggi");

let uri = oggi == null ? 'http://localhost:8080/resources/todo' : 'http://localhost:8080/resources/todo/search/today';
container.innerHTML = '';

/*
fetch('./todos.json')
    .then(response => {
        return response.json();
    })
    .then(jsonData => {
        jsonData.forEach(todo => {
            let el = document.createElement('a');
            el.classList.add('list-group-item');
            el.innerHTML = `${todo.titolo} - ${todo.testo}`
            container.appendChild(el);
        });
    });



fetch('http://localhost:8080/resources/todo')
    .then(response => {
        return response.json();
    })
    .then(jsonData => {
        let template = `
        <div id="all" class="list-group">
            ${jsonData.map(todo=>`<a href="#" class="list-group-item">${todo.titolo}</a>`)
            .join('')}
        </div>
        `
        container.innerHTML = template;
    });

*/

fetch(uri)
    .then(response => {
        return response.json();
    })
    .then(jsonData => {
        let template = `
        <div id="all" class="list-group">
            ${jsonData.map(todo => render(todo)).join('')}
        </div>
        `;
        container.innerHTML = template;
    });

const render = (todo) => {
        return `<a href="todoForm.html?id=${todo.id}" class="list-group-item">${todo.titolo}</a>`;
}

