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
            <div className="card p-3 mt-3" style={{width: "25rem"}}>
                <h1 className="card-title">Senior hozzáadása</h1>
                <div className="card-text">
                    <div className="form-group">
                        <label htmlFor="seniorName">Név:</label>
                        <input name="name" value={this.state.name} onChange={this.handleChange} type="text" className="form-control" id="seniorName" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="seniorEmail">Email:</label>
                        <input name="email" type="email" onChange={this.handleChange} className="form-control" id="seniorEmail" />
                    </div>
                    <Link to="/seniors">
                        <button onClick={() => this.props.onSubmit(this.state)} type="submit" className="btn btn-success btn-sm">Hozzáadás</button>
                    </Link>
                </div>
            </div>
        )
    }
}

export default AddSeniorForm
