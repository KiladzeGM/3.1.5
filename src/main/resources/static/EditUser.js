$(async function () {
    editUser();
});

function editUser() {


    const selected_options = document.querySelector('#rolesEditUser').selectedOptions;

    const form = document.forms["formEditUser"];

    form.addEventListener('submit', addNewUser)

    function addNewUser(e) {
        e.preventDefault();
        let listRol = [];
        for (let i = 0; i < selected_options.length; i++) {
            listRol.push(selected_options[i].value);
        }
        console.log(listRol);
        fetch("http://localhost:8080/admin/rest", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: form.id.value,
                firstName: form.firstName.value,
                lastName: form.lastName.value,
                age: form.age.value,
                username: form.username.value,
                password: form.password.value,
                singleRole: listRol
            })
        }).then(() => {
            $('#editFormCloseButton').click();
            theseUsers();
        })

    }
}