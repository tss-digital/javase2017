const myForm = document.querySelector('form')
const btnRegistrati = document.querySelector('button[name=Registrati]')
const url = 'http://localhost:8080/resources/utenti/login'

btnRegistrati.addEventListener('click', e => {
    e.preventDefault();
    document.location.href = "registrationForm.html";
})

myForm.addEventListener('submit', evt => {

    const data = {
        "username": document.getElementById("usr").value,
        "password": document.getElementById("pwd").value
    }

    evt.preventDefault()

    fetch(url, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    }).then(resp => {
        if (resp.status === 200) {
            return resp.json();
        } else {
            throw new Error(resp.statusText);
        }
    }).then(jsonData => {
        localStorage.setItem('token', jsonData.token);
        document.location.href = "index.html";
    }).catch(err => {
        console.dir(err)
    })
})

