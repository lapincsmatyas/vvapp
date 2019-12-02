class UserService{
    async getCurrentUser(code){
        return fetch(`http://152.66.178.92:8080/user/current?authorizationCode=${code}`)
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }
}

export default UserService