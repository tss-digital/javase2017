const myForm = document.querySelector('form')

myForm.addEventListener('submit', evt => {

    const data = new FormData(myForm);
    /*
    data.append('titolo', document.getElementById("titolo").value);
    data.append('testo', document.getElementById("testo").value);
    data.append('il', document.getElementById("il").value);
    */

    evt.preventDefault()

    fetch('http://localhost:8080/resources/todo/mp', {
        method: 'POST',
        body: data
    })
        .then(res => {
            document.location.href = "index.html";
        })
})