import React from 'react'
import AddSeniorForm from './add-senior-form'
import SeniorDetail from './senior-detail'
import SeniorService from '../../services/senior.service';
import styles from './seniors.module.css'

class Seniors extends React.Component{
    constructor(props){
        super(props);

        this.seniorService = new SeniorService();
        
        this.state = {
            showDetails: false,
            selectedItem: null
        }

        this.selectSenior = this.selectSenior.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    selectSenior(seniorId){
        this.seniorService.getSeniorById(seniorId).then(senior =>{
            this.setState({selectedItem: senior, showDetails: true});
        });
    }

    handleSubmit(newSenior){
        console.log(this.seniorService);
        this.seniorService.addNewSenior(newSenior).then(senior => {
            this.props.updateSeniors();
        })
    }

    render(){
        return(
        <>
            <div className={styles["senior-list"]}>
                <h2>Seniorok</h2>
                {this.props.seniors.map((senior) => (
                    <div key={senior.seniorId} className="card">
                        <div onClick={() => this.selectSenior(senior.seniorId)} className="card-body">
                            <h5 className="card-title">{senior.name}</h5>
                            <h6 className="card-subtitle mb-2 text-muted">{senior.email}</h6>
                        </div>
                    </div>
                ))}
            </div>
            <AddSeniorForm onSubmit={this.handleSubmit} className={styles.seniorForm} />
            {this.state.showDetails && <SeniorDetail senior={this.state.selectedItem} />}
        </>
        )
    }
}

export default Seniors