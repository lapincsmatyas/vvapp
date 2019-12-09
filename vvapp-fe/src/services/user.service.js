class UserService{
    async getCurrentUser(code){
        console.log(process.env.REACT_APP_SERVER_ADDRESS);
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/user/current?authorizationCode=${code}`,{
            credentials: 'same-origin',
            mode: "cors"
        })
        .then(res => {
            console.log(res);
            return res.json();
        })
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }
}

export default UserService