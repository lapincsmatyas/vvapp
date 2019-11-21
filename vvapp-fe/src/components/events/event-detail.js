import React from 'react'
import AddSeniorToEventForm from "./add-senior-to-event-form";

class EventDetail extends React.Component{
    constructor(props){
        super(props);

        this.onParticipationAdd = this.onParticipationAdd.bind(this);
    }

    onParticipationAdd(participation){
        participation.event = this.props.event;
        this.props.onParticipationAdd(participation);
    }

   render(){
       return(
           <>
            <div>
               <h1>{this.props.event.name}</h1>
               <p>{this.props.event.eventType.email}</p>
               <h6>Részvételek</h6>
               <ul>
               {this.props.event.participations.map(participation => (
                   <li key={participation.participationId}>{participation.senior.name} - {participation.eventRole.name}</li>
               ))}
               </ul>
            </div>
               <AddSeniorToEventForm onSubmit={this.onParticipationAdd} event={this.props.event} seniors={this.props.seniors} eventRoles={this.props.eventRoles} />
           </>
       );
   }
}

export default EventDetail
