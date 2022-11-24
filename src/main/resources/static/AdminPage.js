$(async function () {
    await theseUsers();
});
const table = $('#adminPanelBody');

async function theseUsers() {
    table.empty()
    fetch("http://localhost:8080/admin/rest")
        .then(res => res.json())
        .then(userList => {
            userList.forEach(
                data => {
                    console.log(data);
                    let roleList = data.rolesName.map(role => role.substring(1).concat(" ")).toString().replaceAll(",", "");

                    let users = `$(
                        <tr>
                            <td>${data.id}</td>
                            <td>${data.firstName}</td>
                            <td>${data.lastName}</td>
                            <td>${data.age}</td>
                            <td>${data.username}</td>
                            <td>${roleList}</td>
                            <td>
                                <button type="button" class="btn btn-info" data-toggle="modal" id="buttonDelete"
                                data-action="delete" data-id="${data.id}" data-target="#edit">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" data-toggle="modal" id="buttonDelete"
                                data-action="delete" data-id="${data.id}" data-target="#delete">Delete</button>
                            </td>
                        </tr>)`;
                    $('#adminPanelBody').append(users);
                })
        })
}