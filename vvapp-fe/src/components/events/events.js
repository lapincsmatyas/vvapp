import React from "react"
import EventService from "../../services/event.service";
import EventList from "./event-list";

class Events extends React.Component{
    constructor(props) {
        super(props);
        this.eventService = new EventService();

        this.state = {
            events: []
        };

        this.onSelectEvent = this.onSelectEvent.bind(this);
        this.getEvents = this.getEvents.bind(this);
    }

    componentDidMount() {
        this.getEvents();
    }

    onSelectEvent(event){
        this.eventService.getEventById(event.eventId).then( event => {
            this.setState({selectedEvent: event, showEventDetails: true});
        })
    }

    getEvents(){
        this.eventService.getAllEvents().then(events => {
            this.setState({events: events});
            console.log(events);
        })
    }

    render() {
        console.log(this.state.events);
        return(
            <EventList onSelectEvent={this.onSelectEvent} events={this.state.events} />
        )
    }
}

export default Events;
