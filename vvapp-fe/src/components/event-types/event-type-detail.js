import React from 'react'
import EventService from "../../services/event.service";
import AddEventTypeForm from "./add-event-type-form";
import AddEventRoleForm from "../event-roles/add-event-role-form";

class EventTypeDetail extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            eventType: null,
            showAddEventTypeForm: false
        }

        this.eventService = new EventService();

        this.onEventRoleAdd = this.onEventRoleAdd.bind(this);
    }

    componentDidMount() {
        this.eventService.getEventTypeById(this.props.match.params.id).then(eventType => {
            this.setState({eventType: eventType});
        });
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(prevProps.match.params.id !== this.props.match.params.id){
            this.eventService.getEventTypeById(this.props.match.params.id).then(eventType => {
                this.setState({eventType: eventType});
            })
        }
    }

    onEventRoleAdd(eventRole){
        eventRole.eventType = this.state.eventType;
        this.eventService.createEventRole(eventRole).then(result => {
            this.eventService.getEventTypeById(this.props.match.params.id).then(eventType => {
                this.setState({eventType: eventType});
            })
        });
    }

    render() {
        if(!this.state.eventType) return null;
        return (
            <div className="card m-3 p-3">
                <div className="card-title">
                    <h6> {this.state.eventType.name} </h6>
                </div>
                <div className="card-text">
                    <p>Szerepek:</p>
                    <ul>
                        {this.state.eventType.eventRoles.map(eventRole => (
                            <li>{eventRole.name}</li>
                        ))}
                    </ul>
                </div>
                { this.state.showAddEventTypeForm &&
                    <span className="ml-4">
                        <AddEventRoleForm onSubmit={this.onEventRoleAdd}  />
                    </span>
                }
                { !this.state.showAddEventTypeForm &&
                    <div>
                        <button type="submit" onClick={() => this.setState({showAddEventTypeForm: true})} className="btn btn-success btn-sm ml-4">Hozzáadás</button>
                    </div>
                }
            </div>
        );
    }
}

export default EventTypeDetail