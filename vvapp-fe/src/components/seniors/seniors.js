import React from "react"
import SeniorList from "./senior-list";
import SeniorService from "../../services/senior.service";

class Seniors extends React.Component{
    constructor(props) {
        super(props);
        this.seniorService = new SeniorService();

        this.state = {
            seniors: []
        }

        this.onSelectSenior = this.onSelectSenior.bind(this);
        this.getSeniors = this.getSeniors.bind(this);

    }

    componentDidMount() {
        this.getSeniors();
    }

    onSelectSenior(senior){
        this.seniorService.getSeniorById(senior.seniorId).then( senior => {
            this.setState({selectedSenior: senior, showSeniorDetails: true});
        })
    }

    getSeniors(){
        this.seniorService.getAllSeniors().then(seniors => {
            this.setState({seniors: seniors});
        })
    }

    render() {
        return(
            <SeniorList onSelectSenior={this.onSelectSenior} seniors={this.state.seniors} />
        )
    }
}

export default Seniors;
