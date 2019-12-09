class SeniorService{
    async getAllSeniors(){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/senior`)
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }

    async getSeniorById(id){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/senior/${id}`)
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }

    async addNewSenior(newSenior){
        console.log(newSenior);
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/senior`, {
            method: "POST",
            mode: "cors",
            credentials: "include",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(newSenior)
        }).then(response => {
            return response.json()
        }).catch(error => {
            console.log(error);
        })
    }

    async patchSenior(senior){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/senior/${senior.seniorId}`, {
            method: "PATCH",
            mode: "cors",
            credentials: 'include',
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(senior)
        })
            .then(res => res.json())
            .then(data => {
                return data;
            })
            .catch( error => {
                console.log(error)
            });
    }
}

export default SeniorService