import React from 'react'
import EventService from "../../services/event.service";
import {Link, Route, Switch} from "react-router-dom";
import AddSeniorToEventForm from "../events/add-senior-to-event-form";
import EventTypeDetail from "./event-type-detail";
import AddEventForm from "../events/add-event-form";
import AddEventTypeForm from "./add-event-type-form";

class EventTypeList extends React.Component {
    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }

    render() {
        return (
            <>
                <div className="m-3">
                    <h1>Esemény típusok</h1>
                        <ul className="list-group">
                        {this.props.eventTypes.map((eventType) => (
                            <li key={eventType.eventTypeId} className="list-group-item">
                                <Link to={`/event-types/event-type/${eventType.eventTypeId}`}>
                                    {eventType.name}
                                </Link>
                            </li>
                        ))}
                    </ul>

                    <div>
                        <Link to={`/event-types/add`}>
                            <button className="btn btn-success btn-sm m-2">Hozzáadás</button>
                        </Link>
                    </div>

                    <Route exact path="/event-types/add"
                           render={(props) => <AddEventTypeForm {...props} onSubmit={this.props.onSubmit} />}
                    />
                </div>


            </>
        )
    }
}

export default EventTypeList
