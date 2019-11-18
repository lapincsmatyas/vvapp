import React from 'react'

class AddEventForm extends React.Component{
    constructor(props) {
        super(props);
        this.state = {name: '', eventType: null }

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        if(event.target.name == "eventType")
            this.setState({[event.target.name]: this.props.eventTypes[event.target.value]});
        else
            this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return (
            <>
                <div>
                    <h1>Esemény hozzáadása</h1>
                    <div className="form-group">
                        <label htmlFor="seniorName">Név:</label>
                        <input name="name" value={this.state.name} onChange={this.handleChange} type="text" className="form-control" id="seniorName" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="seniorEmail">Esemény típusa:</label>
                            <select name="eventType" onChange={this.handleChange}>
                                <option value={null}></option>
                                {this.props.eventTypes.map((eventType, index) => (
                                    <option key={eventType.eventTypeId} value={index}>{eventType.name}</option>
                                ))}
                            </select>
                    </div>
                    <button onClick={() => this.props.onSubmit(this.state)} type="submit" className="btn btn-primary">Hozzáadás</button>
                </div>
            </>
        )
    }
}

export default AddEventForm
