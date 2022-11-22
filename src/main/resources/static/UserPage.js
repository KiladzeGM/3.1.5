$(async function () {
    await thisUser();
});

async function thisUser() {
    fetch("http://localhost:8080/user/rest")
        .then(res => res.json())
        .then(data => {
            $('#headerUsername').append(data.username);
            let roleList = data.rolesName.map(role => role.substring(1).concat(" ")).toString().replaceAll(",", "");
            $('#headerRoles').append(roleList);

            let user = `$(
            <tr>
                <td>${data.id}</td>
                <td>${data.firstName}</td>
                <td>${data.lastName}</td>
                <td>${data.age}</td>
                <td>${data.username}</td>
                <td>${roleList}</td>)`;
            $('#userPanelBody').append(user);
        })
}