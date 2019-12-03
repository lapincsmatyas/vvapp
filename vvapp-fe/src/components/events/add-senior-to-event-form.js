import React from 'react'
import CurrentUserContext from "../../CurrentUserContext";

class AddSeniorToEventForm extends React.Component{
    static contextType = CurrentUserContext;

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
                    <div className="card p-3" style={{width: "18rem"}}>
                        <div className="card-text">
                        <label htmlFor="senior">Senior neve:</label>
                        <select className="ml-2" name="senior" onChange={this.handleChange}>
                            <option value={null}/>
                            {this.props.seniors.filter(senior => senior.group.groupId === this.context.current.group.groupId).map((senior, index) => (
                                <option key={senior.seniorId} value={index}>{senior.name}</option>
                            ))}
                        </select>
                    </div>
                        <div className="form-group">
                            <label htmlFor="eventRole">Szerep:</label>
                            <select className="ml-2" name="eventRole" onChange={this.handleChange}>
                                <option value={null}/>
                                {
                                    this.props.eventRoles.filter(eventRole => eventRole.eventType.eventTypeId === this.props.event.eventType.eventTypeId)
                                        .map((eventRole, index) => (
                                            <option key={eventRole.eventRoleId} value={index}>{eventRole.name}</option>
                                        ))
                                }
                            </select>
                        </div>
                    <button onClick={() => this.props.onSubmit(this.state)} type="submit" className="btn btn-primary btn-sm">Hozzáadás</button>
                    </div>
                </div>
            </>
        )
    }
}

export default AddSeniorToEventForm
