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

        this.getEventTypes = this.getEventTypes.bind(this);
        this.getEvents = this.getEvents.bind(this);
        this.onEventAdd = this.onEventAdd.bind(this);
    }

    componentDidMount() {
        this.getEvents();
        this.getEventTypes();
    }

    getEvents(){
        this.eventService.getAllEvents().then(events => {
            this.setState({events: events});
            console.log(events);
        })
    }

    getEventTypes(){
        this.eventService.getAllEventTypes().then(eventTypes => {
            this.setState({eventTypes: eventTypes});
            console.log(eventTypes);
        })
    }

    onEventAdd(event){
        this.eventService.createEvent(event).then(event => {
            this.getEvents();
        })
    }

    render() {
        return(
            <EventList onSubmit={this.onEventAdd} eventTypes={this.state.eventTypes} events={this.state.events} />
        )
    }
}

export default Events;
