class EventService{
    async getAllEvents(){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/event`)
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }

    async getEventById(id){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/event/${id}`)
        .then(res => res.json())
        .then(data => {
            return data;
        })
        .catch( error => {
            console.log(error)
        });
    }

    async getAllEventTypes(){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/event-type`)
            .then(res => res.json())
            .then(data => {
                return data;
            })
            .catch(error => {
                console.log(error);
            })
    }

    async getEventTypeById(id){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/event-type/${id}`)
            .then(res => res.json())
            .then(data => {
                return data;
            })
            .catch(error => {
                console.log(error);
            })
    }

    async createEvent(event){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/event`, {
            method: "POST",
            mode: "cors",
            credentials: "include",
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
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/event-type`, {
            method: "POST",
            mode: "cors",
            credentials: "include",
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
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/event-role`)
            .then(res => res.json())
            .then(data => {
                return data;
            })
            .catch(error => {
                console.log(error);
            })
    }

    createEventRole(eventRole) {
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/event-role`, {
            method: "POST",
            mode: "cors",
            credentials: "include",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(eventRole)
        }).then(response => {
            return response.json()
        }).catch(error => {
            console.log(error);
        })
    }

    addSeniorToEvent(event, senior, role){
        console.log(event, senior, role);
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/event/${event.eventId}/seniors?seniorId=${senior.seniorId}&eventRoleId=${role.eventRoleId}`, {
            method: "POST",
            credentials: "include",
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

    addSeniorSignUpToEvent(event, senior, role) {
        console.log(event, senior, role);
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/event/${event.eventId}/pending?seniorId=${senior.seniorId}&eventRoleId=${role.eventRoleId}`, {
            method: "POST",
            credentials: "include",
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

export default EventService
