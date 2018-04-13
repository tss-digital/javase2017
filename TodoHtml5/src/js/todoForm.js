const myForm = document.querySelector('form')
const btnElimina = document.querySelector('button[name=elimina]')

let url = new URL(document.location.href);
const id = url.searchParams.get("id");

if (id != null) {
    btnElimina.classList.remove("disabled");
    fetch('http://localhost:8080/resources/todo/' + id, {
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    })
        .then(resp => {
            if(resp.status === 200){
                return resp.json();
            }else if (resp.status === 403 || resp.status === 401) {
                document.location.href = "loginForm.html";
                throw new Error(resp.statusText);
            } else {
                throw new Error(resp.statusText);
            }
            
        })
        .then(todo => {
            let d = new Date(todo.il);
            document.getElementById("titolo").value = todo.titolo;
            document.getElementById("testo").value = todo.testo;
            document.getElementById("il").value = d.toLocaleDateString();
        }) .catch(err => {
            console.dir(err)
        })
} else {
    btnElimina.classList.add("disabled");
};

myForm.addEventListener('submit', evt => {

    const data = new URLSearchParams();
    data.append('titolo', document.getElementById("titolo").value);
    data.append('testo', document.getElementById("testo").value);
    data.append('il', document.getElementById("il").value);
    data.append('id', id);

    evt.preventDefault()

    let url = id == null ? 'http://localhost:8080/resources/todo' : 'http://localhost:8080/resources/todo/' + id;
    let method = id == null ? "POST" : "PUT";

    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8',
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        },
        body: data
    })
        .then(res => {
            if (res.status === 200) {
                document.location.href = "index.html";
            }
        })
        .catch(err => {
            console.dir(err)
        })
})

btnElimina.addEventListener("click", evt => {
    evt.preventDefault();
    fetch('http://localhost:8080/resources/todo/' + id, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem('token')
        }
    })
        .then(() => {
            document.location.href = "index.html";
        })
})
