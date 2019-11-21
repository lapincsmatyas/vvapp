import React from 'react'

class SeniorDetail extends React.Component{
   render(){
       console.log(this.props.senior);
       return(
           <div>
               <h1>{this.props.senior.name}</h1>
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
