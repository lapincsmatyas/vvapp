import React from 'react'
import SeniorService from '../../services/senior.service';

class Seniors extends React.Component{
    constructor(props){
        super(props);
        
        this.state = {
            showDetails: false,
            selectedItem: null
        }
    }


    render(){
        return(
        <>
            <div>
                <h1>Seniorok</h1>
                {this.props.seniors.map((senior) => (
                    <div key={senior.seniorId} className="card">
                        <div onClick={() => this.props.onSelectSenior(senior)} className="card-body">
                            <h5 className="card-title">{senior.name}</h5>
                            <h6 className="card-subtitle mb-2 text-muted">{senior.email}</h6>
                        </div>
                    </div>
                ))}
            </div>
        </>
        )
    }
}

export default Seniors
