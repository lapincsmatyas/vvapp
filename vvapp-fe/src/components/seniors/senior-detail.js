import React from 'react'
import SeniorService from "../../services/senior.service";

class SeniorDetail extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            senior: null
        };

        this.seniorService = new SeniorService();
    }

    componentDidMount() {
        this.seniorService.getSeniorById(this.props.match.params.id).then(senior => {
            this.setState({senior: senior});
        })
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(prevProps.match.params.id !== this.props.match.params.id){
            this.seniorService.getSeniorById(this.props.match.params.id).then(senior => {
                this.setState({senior: senior});
            })
        }
    }

    render(){
       if(!this.state.senior) return null;

       return(

                   <div>
                       <h1>{this.state.senior.name}</h1>
                       <p>{this.state.senior.email}</p>
                       <h6>Részvételek</h6>
                       <ul>
                           {this.state.senior.participations.map(participation => (
                               <li key={participation.participationId}>{participation.event.name}</li>
                           ))}
                       </ul>
                   </div>

       );
   }
}

export default SeniorDetail
