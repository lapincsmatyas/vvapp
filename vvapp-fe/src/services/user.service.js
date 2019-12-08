class UserService{
    async getCurrentUser(){
        console.log(process.env.REACT_APP_SERVER_ADDRESS);
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/oauth2/authorization/authsch`)
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