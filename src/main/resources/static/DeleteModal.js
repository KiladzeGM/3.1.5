$('#delete').on('show.bs.modal', ev => {
    let button = $(ev.relatedTarget);
    let id = button.data('id');
    showDeleteModal(id);
})

async function showDeleteModal(id) {
    let user = await getUser(id);
    let form = document.forms["formDeleteUser"];
    let roleList = user.rolesName.map(role => role.substring(1).concat(" ")).toString().replaceAll(",", "");
    let role = roleList.split(" ");
    form.id.value = user.id;
    form.firstName.value = user.firstName;
    form.lastName.value = user.lastName;
    form.age.value = user.age;
    form.username.value = user.username;


    $('#rolesDeleteUser').empty();


    for (let i = 0; i < role.length - 1; i++) {
        let el = document.createElement("option");
        el.text = role[i];
        el.value = i + "";
        $('#rolesDeleteUser')[0].appendChild(el);
    }
}

async function getUser(id) {
    let url = "http://localhost:8080/admin/rest/" + id;
    let response = await fetch(url);
    return await response.json();
}