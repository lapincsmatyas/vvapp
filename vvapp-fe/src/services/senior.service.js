class SeniorService{
    async getAllSeniors(){
        return fetch('http://localhost:8080/senior')
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }

    async getSeniorById(id){
        return fetch(`http://localhost:8080/senior/${id}`)
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }

    async addNewSenior(newSenior){
        return fetch('http://localhost:8080/senior', {
            method: "POST",
            mode: "cors",
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
}

export default SeniorService