class ParticipationService {
    async getParticipationById(id) {
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/participation/${id}`)
            .then(res => res.json())
            .then(data => {
                return data;
            })
            .catch(error => {
                console.log(error)
            });
    }

    async addReviewToParticipation(participation, senior, text){
        console.log(senior);
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/participation/${participation.participationId}/review?seniorId=${senior.seniorId}`, {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            },
            body: text
        }).then(response => {
            return response.json()
        }).catch(error => {
            console.log(error);
        })
    }

    acceptOrDecline(participation, newValue) {
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/participation/${participation.participationId}/accept`, {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            },
            body: newValue
        }).then(response => {
            return response.json()
        }).catch(error => {
            console.log(error);
        })
    }
}

export default ParticipationService
