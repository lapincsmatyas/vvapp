import React from 'react'
import AddSeniorForm from './add-senior-form'
import SeniorDetail from './senior-detail'
import SeniorService from '../../services/senior.service';

class Seniors extends React.Component{
    constructor(props){
        super(props);

        this.seniorService = new SeniorService();
        
        this.state = {
            showDetails: false,
            selectedItem: null
        }
    }

    selectSenior(seniorId){
        this.seniorService.getSeniorById(seniorId).then(senior =>{
            console.log(senior);
            this.setState({selectedItem: senior, showDetails: true});
        });
    }

    render(){
        return(
        <>
            <div>
                <center><h1>Seniorok</h1></center>
                {this.props.seniors.map((senior) => (
                    <div key={senior.seniorId} className="card">
                        <div onClick={() => this.selectSenior(senior.seniorId)} className="card-body">
                            <h5 className="card-title">{senior.name}</h5>
                            <h6 className="card-subtitle mb-2 text-muted">{senior.email}</h6>
                        </div>
                    </div>
                ))}
            </div>
            <AddSeniorForm />
            {this.state.showDetails && <SeniorDetail senior={this.state.selectedItem} />}
        </>
        )
    }
}

export default Seniors