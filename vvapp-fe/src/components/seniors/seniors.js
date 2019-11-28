import React from "react"
import SeniorList from "./senior-list";
import SeniorService from "../../services/senior.service";
import SeniorDetail from "./senior-detail";
import {Route, Switch} from "react-router-dom";

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
            this.setState({seniors: seniors});
        })
    }

    render() {
        console.log(this.context);
        return(
            <div>
                <SeniorList onSubmit={this.onSeniorAdd} seniors={this.state.seniors} />

                <Switch>
                    <Route path="/seniors/senior/:id" component={SeniorDetail} />
                </Switch>
            </div>

        )
    }
}

export default Seniors;
