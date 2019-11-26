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

        this.getSeniors = this.getSeniors.bind(this);
        this.onSeniorAdd = this.onSeniorAdd.bind(this);
    }

    onSeniorAdd(senior){
        this.seniorService.addNewSenior(senior).then(senior => {
            this.getSeniors();
        })
    }

    componentDidMount() {
        this.getSeniors();
    }

    getSeniors(){
        this.seniorService.getAllSeniors().then(seniors => {
            console.log("seniorok", seniors);
            this.setState({seniors: seniors});
        })
    }

    render() {
        return(
                <SeniorList onSubmit={this.onSeniorAdd} seniors={this.state.seniors} />
        )
    }
}

export default Seniors;
