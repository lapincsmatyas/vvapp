import React from 'react'
import SeniorService from "../../services/senior.service";
import Participation from "../participations/participation";

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
                   <div className="card m-3" style={{width: "30rem"}}>
                       <div className="card-body">
                           <h5 className="card-title">{this.state.senior.name}</h5>
                           <div className="card-text">
                               <p>{this.state.senior.email}</p>
                               <h6>Részvételek</h6>
                                   {
                                       this.state.senior.participations.length > 0 ? (
                                       <ul>
                                           {this.state.senior.participations.map(participation => (
                                               <li key={participation.participationId}>
                                                    <Participation participation={participation} />
                                               </li>
                                           ))
                                           }
                                       </ul>
                                    ) : "Nincs részvétel"
                                   }
                           </div>
                       </div>
                   </div>

       );
   }
}

export default SeniorDetail
