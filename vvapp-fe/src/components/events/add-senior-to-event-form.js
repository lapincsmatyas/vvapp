import React from 'react'

class AddSeniorToEventForm extends React.Component{
    constructor(props) {
        super(props);
        this.state = {senior: null, eventRole: null }

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        if(event.target.name === "eventRole")
            this.setState({eventRole: this.props.eventRoles[event.target.value]});
        else if(event.target.name === "senior")
            this.setState({senior: this.props.seniors[event.target.value]});
    }

    render() {
        return (

            <>
                <div>
                    <h1>Részvétel hozzáadása</h1>
                    <div className="form-group">
                        <label htmlFor="eventName">Esemény neve:</label>
                        <input name="eventName" value={this.props.event.name} disabled type="text" className="form-control" id="eventName" />
                    </div>
                    <div className="form-group">
                        <label htmlFor="senior">Senior neve:</label>
                        <select name="senior" onChange={this.handleChange}>
                            <option value={null}/>
                            {this.props.seniors.map((senior, index) => (
                                <option key={senior.seniorId} value={index}>{senior.name}</option>
                            ))}
                        </select>
                    </div>
                        <div className="form-group">
                            <label htmlFor="eventRole">Szerep:</label>
                            <select name="eventRole" onChange={this.handleChange}>
                                <option value={null}/>
                                {
                                    this.props.eventRoles.filter(eventRole => eventRole.eventType.eventTypeId === this.props.event.eventType.eventTypeId)
                                        .map((eventRole, index) => (
                                            <option key={eventRole.eventRoleId} value={index}>{eventRole.name}</option>
                                        ))
                                }
                            </select>
                        </div>
                    <button onClick={() => this.props.onSubmit(this.state)} type="submit" className="btn btn-primary">Hozzáadás</button>
                </div>
            </>
        )
    }
}

export default AddSeniorToEventForm
