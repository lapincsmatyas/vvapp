class ParticipationService {
    async getParticipationById(id) {
        return fetch(`http://152.66.178.92:8080/participation/${id}`)
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
        return fetch(`http://152.66.178.92:8080/participation/${participation.participationId}/review?seniorId=${senior.seniorId}`, {
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
}

export default ParticipationService
