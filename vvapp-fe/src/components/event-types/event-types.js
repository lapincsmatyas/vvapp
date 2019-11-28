import React from 'react'
import EventService from "../../services/event.service";
import EventDetail from "../events/event-detail";
import {Route, Switch} from "react-router-dom";
import EventTypeDetail from "./event-type-detail";
import EventTypeList from "./event-type-list";

class EventTypes extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            eventTypes: []
        };

        this.eventService = new EventService();
        this.onEventTypeAdd = this.onEventTypeAdd.bind(this);
    }

    componentDidMount() {
        this.eventService.getAllEventTypes().then(result => {
            this.setState({eventTypes: result});
        })
    }

    onEventTypeAdd(eventType){
        this.eventService.createEventType(eventType).then(result => {
            this.eventService.getAllEventTypes().then(result => {
                this.setState({eventTypes: result});
            })
        })
    }

    render() {
        if(!this.state.eventTypes) return null;
        return (
            <div>
                <EventTypeList onSubmit={this.onEventTypeAdd} eventTypes={this.state.eventTypes} />
                <Switch>
                    <Route path="/event-types/event-type/:id" render={(props) => <EventTypeDetail {...props} /> } />
                </Switch>
            </div>
        );
    }
}

export default EventTypes
