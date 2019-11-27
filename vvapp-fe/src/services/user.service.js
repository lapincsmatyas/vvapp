class UserService{
    async getCurrentUser(){
        return fetch('http://localhost:8080/user/current')
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