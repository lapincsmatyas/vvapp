class EventService{
    async getAllEvents(){
        return fetch('http://localhost:8080/event')
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }

    async getEventById(id){
        return fetch(`http://localhost:8080/event/${id}`)
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }

    async getAllEventTypes(){
        return fetch(`http://localhost:8080/event-type`)
            .then(res => res.json())
            .then(data => {
                return data;
            })
            .catch(error => {
                console.log(error);
            })
    }

    async createEvent(event){
        return fetch('http://localhost:8080/event', {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(event)
        }).then(response => {
            return response.json()
        }).catch(error => {
            console.log(error);
        })
    }

    async createEventType(eventType){
        return fetch('http://localhost:8080/event-type', {
            method: "POST",
            mode: "cors",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(eventType)
        }).then(response => {
            return response.json()
        }).catch(error => {
            console.log(error);
        })
    }

    getAllEventRoles() {
        return fetch(`http://localhost:8080/event-role`)
            .then(res => res.json())
            .then(data => {
                return data;
            })
            .catch(error => {
                console.log(error);
            })
    }
}

export default EventService
