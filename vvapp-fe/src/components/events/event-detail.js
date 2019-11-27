import React from 'react'
import EventService from "../../services/event.service";

class EventDetail extends React.Component{
    constructor(props){
        super(props);

        this.state = {
            event: null
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

       return(
           <>
            <div>
               <h1>{this.state.event.name}</h1>
               <p>{this.state.event.eventType.email}</p>
               <h6>Részvételek</h6>
               <ul>
               {this.state.event.participations.map(participation => (
                   <li key={participation.participationId}>{participation.senior.name} - {participation.eventRole.name}</li>
               ))}
               </ul>
            </div>
           </>
       );
   }
}

export default EventDetail
