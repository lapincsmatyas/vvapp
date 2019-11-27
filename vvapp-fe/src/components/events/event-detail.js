import React from 'react'
import EventService from "../../services/event.service";
import AddSeniorToEventForm from "./add-senior-to-event-form";
import Participation from "../participations/participation";

class EventDetail extends React.Component{
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
                    </div>
                </div>
            </div>
       );
   }
}

export default EventDetail
