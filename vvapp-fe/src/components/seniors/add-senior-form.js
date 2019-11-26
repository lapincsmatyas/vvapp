import React from 'react'
import {Link} from "react-router-dom";

class AddSeniorForm extends React.Component {

    constructor(props){
        super(props);
        this.state = {name: '', email: ''};

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return (
            <div>
                <h1>Senior hozzáadása</h1>
                <div className="form-group">
                    <label htmlFor="seniorName">Név:</label>
                    <input name="name" value={this.state.name} onChange={this.handleChange} type="text" className="form-control" id="seniorName" />
                </div>
                <div className="form-group">
                    <label htmlFor="seniorEmail">Email:</label>
                    <input name="email" type="email" onChange={this.handleChange} className="form-control" id="seniorEmail" />
                </div>
                <Link to="/seniors">
                    <button onClick={() => this.props.onSubmit(this.state)} type="submit" className="btn btn-success">Hozzáadás</button>
                </Link>
            </div>
        )
    }
}

export default AddSeniorForm
