import React from 'react'
import EventService from "../../services/event.service";
import Participation from "../participations/participation";
import CurrentUserContext from "../../CurrentUserContext";
import AddSeniorToEventForm from "./add-senior-to-event-form";
import SeniorService from "../../services/senior.service";
import ParticipationService from "../../services/participation.service";

class EventDetail extends React.Component{
    static contextType = CurrentUserContext;

    constructor(props){
        super(props);

        this.state = {
            event: null,
            eventRoles: [],
            seniors: [],
            showAddSeniorToEvent: false,
            eventRole: null
        }

        this.seniorService = new SeniorService();
        this.eventService = new EventService();
        this.participationService = new ParticipationService();

        this.onSeniorAdd = this.onSeniorAdd.bind(this);
        this.onAccept = this.onAccept.bind(this);
        this.onDecline = this.onDecline.bind(this);
        this.signUpToEvent = this.signUpToEvent.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        if (event.target.name === "eventRole") {
            let selected = this.state.eventRoles.find(eventRole => eventRole.eventRoleId == event.target.value);
            console.log("eventTargetValue: ", event.target.value);
            console.log(this.state.eventRoles);
            this.setState({eventRole: selected})
        }
    }

    signUpToEvent(){
        this.eventService.addSeniorSignUpToEvent(this.state.event, this.context.current, this.state.eventRole).then(result => {
            this.eventService.getEventById(this.props.match.params.id).then(event => {
                this.setState({event: event});
            });
        })
    }

    componentDidMount() {
        this.eventService.getEventById(this.props.match.params.id).then(event => {
            this.setState({event: event});
        });

        this.eventService.getAllEventRoles().then(result => {
            this.setState({eventRoles: result});
        });

        this.seniorService.getAllSeniors().then(result => {
            this.setState({seniors: result});
        })
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(prevProps.match.params.id !== this.props.match.params.id){
            this.eventService.getEventById(this.props.match.params.id).then(event => {
                this.setState({event: event});
            })
        }
    }

    onSeniorAdd(participation){
        this.eventService.addSeniorToEvent(this.state.event, participation.senior, participation.eventRole).then(result => {
            this.eventService.getEventById(this.state.event.eventId).then(event => {
                this.setState({event: event, showAddSeniorToEvent: false});
            })
        })
    }

    onAccept(participation){
        this.participationService.acceptOrDecline(participation, true).then(result => {
            this.eventService.getEventById(this.props.match.params.id).then(event => {
                this.setState({event: event});
            });
        });
    }

    onDecline(participation){
        this.participationService.acceptOrDecline(participation, false).then(result => {
            this.eventService.getEventById(this.props.match.params.id).then(event => {
                this.setState({event: event});
            });
        });
    }

    render(){
       if(!this.state.event) return null;
        console.log(this.state.event);
       return(
            <div className="m-3 card">
                <div className="card-body">
                    <h5 className="card-title">{this.state.event.name}</h5>
                    <div className="card-text">
                        <p>Típus: {this.state.event.eventType.name}</p>
                        { this.state.event.supervisor &&
                            <p>Felelős: {this.state.event.supervisor.name}</p>
                        }
                        <h6>Részvételek</h6>
                        {
                            this.state.event.participations.length > 0 ? (
                                <ul>
                                    {this.state.event.participations.map(participation => (
                                        <li key={participation.participationId}>

                                            <Participation onAccept={this.onAccept} onDecline={this.onDecline} participation={participation} />

                                        </li>
                                    ))
                                    }
                                </ul>
                            ) : "Nincs részvétel"
                        }
                        {
                            this.state.showAddSeniorToEvent &&
                                <AddSeniorToEventForm event={this.state.event} eventRoles={this.state.eventRoles} seniors={this.state.seniors} onSubmit={this.onSeniorAdd}/>
                        }
                        {
                            !this.state.showAddSeniorToEvent &&
                            <div>
                                <span>
                                    <button onClick={() => this.setState({showAddSeniorToEvent: true})}
                                            className="btn btn-success btn-sm mt-2">Résztvevő hozzáadása
                                    </button>
                                </span>
                                <div className={"mt-2"}>
                                    <label htmlFor="eventRole">Szerep:</label>
                                    <select className="ml-2" name="eventRole" onChange={this.handleChange}>
                                        <option value={null}/>
                                        {
                                            this.state.eventRoles.filter(eventRole => eventRole.eventType.eventTypeId === this.state.event.eventType.eventTypeId)
                                                .map((eventRole, index) => (
                                                    <option key={eventRole.eventRoleId} value={eventRole.eventRoleId}>{eventRole.name}</option>
                                                ))
                                        }
                                    </select>
                                   <button onClick={this.signUpToEvent} className="btn btn-success btn-sm ml-2">Jelentkezés</button>
                                </div>
                            </div>
                        }

                    </div>
                </div>
            </div>
       );
   }
}

export default EventDetail
