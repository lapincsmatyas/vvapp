import React from "react"
import SeniorList from "./senior-list";
import SeniorService from "../../services/senior.service";
import SeniorDetail from "./senior-detail";
import {Route, Switch} from "react-router-dom";
import Profile from "../profile/profile";
import AddSeniorForm from "./add-senior-form";

class Seniors extends React.Component{
    constructor(props) {
        super(props);
        this.seniorService = new SeniorService();

        this.state = {
            seniors: []
        }

        this.getSeniors = this.getSeniors.bind(this);
        this.onSeniorAdd = this.onSeniorAdd.bind(this);
        this.onSeniorChange = this.onSeniorChange.bind(this);
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
            this.setState({seniors: seniors});
        })
    }

    onSeniorChange(senior){
        this.seniorService.patchSenior(senior).then(result => {
            this.getSeniors();
        })
    }

    render() {
        console.log(this.context);
        return(
            <div>
                <SeniorList onSubmit={this.onSeniorAdd} seniors={this.state.seniors} />

                <Switch>
                   <Route  exact path="/seniors/senior/:id"
                       render={(props) => <Profile {...props} onSubmit={this.onSeniorChange} />}/>
                </Switch>
            </div>

        )
    }
}

export default Seniors;
