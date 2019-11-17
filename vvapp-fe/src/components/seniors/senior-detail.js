import React from 'react'
import SeniorService from '../../services/senior.service';

class SeniorDetail extends React.Component{
   render(){
       return(
           <div>
               <center><h1>{this.props.senior.name}</h1></center>
               <p>{this.props.senior.email}</p>
               <h6>Részvételek</h6>
               <ul>
               {this.props.senior.participations.map(participation => (
                   <li key={participation.participationId}>{participation.event.name}</li>
               ))}
               </ul>
           </div>
       );
   }
}

export default SeniorDetail