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
}

export default SeniorService