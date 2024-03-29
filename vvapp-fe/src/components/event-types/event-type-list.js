import React from 'react'
import {Link, Route} from "react-router-dom";
import AddEventTypeForm from "./add-event-type-form";
import CurrentUserContext from "../../CurrentUserContext";

class EventTypeList extends React.Component {
    static contextType = CurrentUserContext;

    constructor(props) {
        super(props);
    }

    componentDidMount() {

    }

    render() {
        return (
            <>
                <div className="m-3">
                    <h4>Esemény típusok</h4>
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
                        {
                            this.context.current.userRole.name === "VÁRÚR" &&
                            <Link to={`/event-types/add`}>
                                <button className="btn btn-success btn-sm m-2">Hozzáadás</button>
                            </Link>
                        }

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
