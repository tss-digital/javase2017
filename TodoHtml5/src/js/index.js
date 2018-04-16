let container = document.getElementById('app');
let url = new URL(document.location.href);
const oggi = url.searchParams.get("oggi");
let logout = document.getElementById("logout");

let uri = oggi == null ? 'http://localhost:8080/resources/todo' : 'http://localhost:8080/resources/todo/search/today';
container.innerHTML = '';


if(localStorage.getItem('token') === null){
    logout.classList.add('disabled');
}else{
    logout.classList.remove('disabled');
}

logout.onclick = e =>{
    e.preventDefault();
    localStorage.removeItem('token');
    document.location.href = 'loginForm.html';
}
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


index.html?oggi=true
fetch('http://localhost:8080/resources/todo')
    .then(response => {
        return response.json();
    })
    .then(jsonData => {
        let teconsole.dir(logout);mplate = `
        <div id="all" class="list-group">
            ${jsonData.map(todo=>`<a href="#" class="list-group-item">${todo.titolo}</a>`)
            .join('')}
        </div>
        `
        container.innerHTML = template;
    });

*/

fetch(uri, {
    headers: {
        'Authorization': 'Bearer ' + localStorage.getItem('token')
    }
})
    .then(resp => {
        if (resp.status === 200) {
            return resp.json();
        } else if (resp.status === 403 || resp.status === 401) {
            document.location.href = "loginForm.html";
            throw new Error(resp.statusText);
        } else {
            throw new Error(resp.statusText);
        }
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
    return `<a href="todoForm.html?id=${todo.id}" class="list-group-item">${todo.titolo} - ${todo.testo} - ${todo.ild}</a>`;
}

