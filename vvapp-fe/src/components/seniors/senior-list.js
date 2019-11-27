import React from 'react'
import { Route, Link } from 'react-router-dom'
import AddSeniorForm from "./add-senior-form";
import EventService from "../../services/event.service";

class SeniorList extends React.Component{
    constructor(props){
        super(props);

        this.state = {
            showDetails: false,
            selectedItem: null
        }

    }

    render(){
        return(

        <div className="m-3">
                <div>
                    <h1>Seniorok</h1>

                        <ul className="list-group">
                            {this.props.seniors.map((senior) => (
                                <Link key={senior.seniorId} to={`/seniors/senior/${senior.seniorId}`}>
                                    <li className="list-group-item">
                                        <span>{senior.name}</span>
                                    </li>
                                </Link>
                            ))}
                        </ul>
                </div>
            <div>
                <Link to={`/seniors/add`}>
                    <button className="btn btn-success btn-sm mt-2">Hozzáadás</button>
                </Link>
            </div>

            <Route exact path="/seniors/add"
                   render={(props) => <AddSeniorForm {...props} onSubmit={this.props.onSubmit} />}
            />
        </div>
        )
    }
}

export default SeniorList
