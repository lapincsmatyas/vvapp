class GroupService{
    async getAllGroups(){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/group`)
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }
}

export default GroupService