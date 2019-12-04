class UserRoleService{
    async getAllUserRoles(){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/user-role`)
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }
}

export default UserRoleService