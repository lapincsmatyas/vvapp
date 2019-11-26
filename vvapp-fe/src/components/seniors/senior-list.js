import React from 'react'
import { Route, Link } from 'react-router-dom'
import AddSeniorForm from "./add-senior-form";

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
        <>
            <div>
                <h1>Seniorok</h1>
                <ul className="list-group">
                {this.props.seniors.map((senior) => (
                    <Link to={`/seniors/senior/${senior.seniorId}`}>
                        <li key={senior.seniorId} className="list-group-item">
                            <span>{senior.name}</span>
                        </li>
                    </Link>
                    ))}
                </ul>
            </div>
            <div>
                <Link to={`/seniors/add`}>
                    <button className="btn btn-success">Hozzáadás</button>
                </Link>
            </div>

            <Route exact path="/seniors/add"
                   render={(props) => <AddSeniorForm {...props} onSubmit={this.props.onSubmit} />}
            />

        </>
        )
    }
}

export default SeniorList
