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
        if(event.target.name === "eventRole"){
            let selected = this.props.eventRoles.find(eventRole => eventRole.eventRoleId == event.target.value);
            console.log("eventTargetValue: ", event.target.value);
            console.log(this.props.eventRoles);
            this.setState({eventRole: selected})
        }
        else if(event.target.name === "senior") {
            let selected = this.props.seniors.find(senior => senior.seniorId == event.target.value);
            this.setState({senior: selected});
        }

        console.log("state: ", this.state);
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
                            {this.props.seniors.map((senior, index) => (
                                <option key={senior.seniorId} value={senior.seniorId}>{senior.name}</option>
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
                                            <option key={eventRole.eventRoleId} value={eventRole.eventRoleId}>{eventRole.name}</option>
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
