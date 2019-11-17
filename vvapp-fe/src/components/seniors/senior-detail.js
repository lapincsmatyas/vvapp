import React from 'react'
import styles from './senior-detail.module.css'

class SeniorDetail extends React.Component{
   render(){
       return(
           <div className={styles["senior-detail"]}>
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