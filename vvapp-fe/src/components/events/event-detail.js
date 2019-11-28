import React from 'react'
import EventService from "../../services/event.service";
import Participation from "../participations/participation";
import CurrentUserContext from "../../CurrentUserContext";
import AddSeniorToEventForm from "./add-senior-to-event-form";
import SeniorService from "../../services/senior.service";

class EventDetail extends React.Component{
    static contextType = CurrentUserContext;

    constructor(props){
        super(props);

        this.state = {
            event: null,
            eventRoles: [],
            seniors: [],
            showAddSeniorToEvent: false
        }

        this.seniorService = new SeniorService();
        this.eventService = new EventService();

        this.onSeniorAdd = this.onSeniorAdd.bind(this);
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

    render(){
       if(!this.state.event) return null;
        console.log(this.state.event);
       return(
            <div className="m-3 card" style={{width: "30rem"}}>
                <div className="card-body">
                    <h5 className="card-title">{this.state.event.name}</h5>
                    <div className="card-text">
                        <p>Típus: {this.state.event.eventType.name}</p>

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
                                <span>
                                   <button onClick={this.signUpToEvent} className="btn btn-success btn-sm ml-2 mt-2">Jelentkezés</button>
                                </span>
                            </div>
                        }

                    </div>
                </div>
            </div>
       );
   }
}

export default EventDetail
