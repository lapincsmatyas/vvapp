import React from 'react'
import EventService from "../../services/event.service";
import Participation from "../participations/participation";
import CurrentUserContext from "../../CurrentUserContext";

class EventDetail extends React.Component{
    static contextType = CurrentUserContext;

    constructor(props){
        super(props);

        this.state = {
            event: null,
            showAddSeniorToEvent: false
        }

        this.eventService = new EventService();
    }

    componentDidMount() {
        this.eventService.getEventById(this.props.match.params.id).then(event => {
            this.setState({event: event});
        })
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(prevProps.match.params.id !== this.props.match.params.id){
            this.eventService.getEventById(this.props.match.params.id).then(event => {
                this.setState({event: event});
            })
        }
    }

    signUpToEvent(){
        //this.eventService.addSeniorToEvent(this.props.event, this.context, )
    }

    render(){
       if(!this.state.event) return null;
        console.log(this.state.event);
       return(
            <div className="m-3 card">
                <div className="card-body">
                    <h5 className="card-title">{this.state.event.name}</h5>
                    <div className="card-text">
                        <p>{this.state.event.eventType.email}</p>
                        <h6>Részvételek</h6>
                        {
                            this.state.event.participations.length > 0 ? (
                                <ul>
                                    {this.state.event.participations.map(participation => (
                                        <li key={participation.participationId}>
                                            <Participation participation={participation} />
                                        </li>
                                    ))
                                    }
                                </ul>
                            ) : "Nincs részvétel"
                        }
                        <div>
                            <button onClick={() => this.setState({showAddSeniorToEvent: true})} className="btn btn-success btn-sm mt-2">Résztvevő hozzáadása</button>
                        </div>
                        <div>
                            {/*<span>
                                <label htmlFor="eventRole">Szerep:</label>
                                <select name="eventRole" onChange={this.handleChange}>
                                    <option value={null}/>
                                    {
                                        this.state.eventRoles.filter(eventRole => eventRole.eventType.eventTypeId === this.props.event.eventType.eventTypeId)
                                            .map((eventRole, index) => (
                                                <option key={eventRole.eventRoleId} value={index}>{eventRole.name}</option>
                                            ))
                                    }
                                </select>
                            </span>*/}
                            <button onClick={this.signUpToEvent} className="btn btn-success btn-sm mt-2">Jelentkezés</button>
                        </div>
                    </div>
                </div>
            </div>
       );
   }
}

export default EventDetail
