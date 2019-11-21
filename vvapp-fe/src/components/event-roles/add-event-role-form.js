import React from 'react'

class AddEventRoleForm extends React.Component{
    constructor(props) {
        super(props);
        this.state = {name: '', eventType: ''}

        this.onSubmit = this.onSubmit.bind(this);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        if(event.target.name === "eventType")
            this.setState({[event.target.name]: this.props.eventTypes[event.target.value]});
        else
            this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return (
            <>
                <div>
                    <h1>Szerep hozzáadása</h1>
                    <div className="form-group">
                        <label htmlFor="eventRoleName">Név:</label>
                        <input name="name" value={this.state.name} onChange={this.handleChange} type="text" className="form-control" id="eventRoleName" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="eventType">Esemény típus:</label>
                        <select name="eventType" onChange={this.handleChange}>
                            <option value={null}></option>
                            {this.props.eventTypes.map((eventType, index) => (
                                <option key={eventType.eventTypeId} value={index}>{eventType.name}</option>
                            ))}
                        </select>
                    </div>
                    <button onClick={this.onSubmit} type="submit" className="btn btn-primary">Hozzáadás</button>
                </div>
            </>
        )
    }

    onSubmit(){
        this.props.onSubmit(this.state);
        this.setState({name: '', eventType: ''})
    }
}

export default AddEventRoleForm
