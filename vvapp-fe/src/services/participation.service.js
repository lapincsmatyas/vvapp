class ParticipationService {
    async getParticipationById(id) {
        return fetch(`http://localhost:8080/participation/${id}`)
            .then(res => res.json())
            .then(data => {
                return data;
            })
            .catch(error => {
                console.log(error)
            });
    }

    async addReviewToParticipation(participation, senior, text){
        return fetch(`http://localhost:8080/participation/${participation.participationId}/review`, {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => {
            return response.json()
        }).catch(error => {
            console.log(error);
        })
    }
}

export default ParticipationService
