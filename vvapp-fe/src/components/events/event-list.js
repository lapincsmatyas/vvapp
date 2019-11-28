import React from 'react'
import {Link, Route} from "react-router-dom";
import AddEventForm from "./add-event-form";

class EventList extends React.Component{
    render() {
        return(
            <div className="m-3">
                <div>
                    <h1>Események</h1>
                    <ul className="list-group">
                        {this.props.events.map((event) => (
                            <li key={event.eventId} className="list-group-item">
                                <Link to={`/events/event/${event.eventId}`}>
                                    {event.name}
                                </Link>
                            </li>
                        ))}
                    </ul>
                </div>

                <div>
                    <Link to={`/events/add`}>
                        <button className="btn btn-success btn-sm m-2">Hozzáadás</button>
                    </Link>
                </div>

                <Route exact path="/events/add"
                       render={(props) => <AddEventForm {...props} onSubmit={this.props.onSubmit} />}
                />

            </div>
        )
    }
}

export default EventList
