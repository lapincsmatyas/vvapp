class GroupService{
    async getAllGroups(){
        return fetch('http://152.66.178.92:8080/group')
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