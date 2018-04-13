const myForm = document.querySelector('form')
const btnLogin = document.querySelector('button[name=Login]')
const url = 'http://localhost:8080/resources/utenti/'

btnLogin.addEventListener('click', e => {
    e.preventDefault();
    document.location.href = "loginForm.html";
})

myForm.addEventListener('submit', evt => {

    const data = new URLSearchParams();
    data.append('usr', document.getElementById("usr").value);
    data.append('pwd', document.getElementById("pwd").value);

    evt.preventDefault()

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded;charset=UTF-8'
        },
        body: data
    }).then(res => {
        if (res.status === 200) {
            document.location.href = "loginForm.html";
        }
    })
        .catch(err => {
            console.dir(err)
        })
})

