import React from 'react'
import EventService from "../../services/event.service";
import {Link} from "react-router-dom";

class AddEventForm extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            name: '',
            eventType: null
        };

        this.eventTypes = [];

        this.eventService = new EventService();

        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        this.eventService.getAllEventTypes().then(result => {
            this.eventTypes = result;
        })
    }

    handleChange(event){
        if(event.target.name === "eventType")
            this.setState({[event.target.name]: this.eventTypes[event.target.value]});
        else
            this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return (
            <>
                <div className="card p-3" style={{width: "25rem"}}>
                    <h5 className="card-title">Esemény hozáadása</h5>
                    <div className="card-text">
                        <div className="form-group">
                            <label htmlFor="seniorName">Név:</label>
                            <input className="ml-2" name="name" value={this.state.name} onChange={this.handleChange} type="text" id="seniorName" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="seniorEmail">Esemény típusa:</label>
                                <select className="ml-2" name="eventType" onChange={this.handleChange}>
                                    <option value={null}> </option>
                                    {this.eventTypes.map((eventType, index) => (
                                        <option key={eventType.eventTypeId} value={index}>{eventType.name}</option>
                                    ))}
                                </select>
                        </div>
                        <Link to="/events">
                            <button onClick={() => this.props.onSubmit(this.state)} type="submit" className="btn btn-success btn-sm">Hozzáadás</button>
                        </Link>
                    </div>
                </div>
            </>
        )
    }
}

export default AddEventForm
