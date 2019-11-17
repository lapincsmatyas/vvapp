import React from 'react'

class AddSeniorForm extends React.Component {

    constructor(props){
        super(props);
        this.state = {seniorName: '', seniorEmail: ''};

        this.handleChange = this.handleChange.bind(this);

        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleChange(event){
        this.setState({[event.target.name]: event.target.value});
    }

    handleSubmit(event){
        console.log('Name', this.state.seniorName);
        console.log('Email', this.state.seniorEmail);
        event.preventDefault();
    }

    render() {
        return (
            <form onSubmit={this.handleSubmit}>
                <div className="form-group">
                    <label htmlFor="seniorName">Név:</label>
                    <input name="seniorName" value={this.state.value} onChange={this.handleChange} type="text" className="form-control" id="seniorName" />
                </div>
                <div className="form-group">
                    <label htmlFor="seniorEmail">Email:</label>
                    <input name="seniorEmail" type="email" onChange={this.handleChange} className="form-control" id="seniorEmail" />
                </div>
                <button type="submit" className="btn btn-primary">Hozzáadás</button>
            </form>
        )
    }
}

export default AddSeniorForm