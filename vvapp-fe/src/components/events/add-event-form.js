import React from 'react'
import EventService from "../../services/event.service";
import {Link} from "react-router-dom";
import SeniorService from "../../services/senior.service";

class AddEventForm extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            event:{
                name: '',
                eventType: null,
                supervisor: null
            },
            eventTypes: [],
            seniors: []
        };

        this.eventService = new EventService();
        this.seniorService = new SeniorService();

        this.handleChange = this.handleChange.bind(this);
    }

    componentDidMount() {
        this.eventService.getAllEventTypes().then(result => {
            this.setState({eventTypes: result});
        })
        this.seniorService.getAllSeniors().then(result => {
            this.setState({seniors: result});
        })
    }

    handleChange(ev){
        if(ev.target.name === "eventType")
            this.setState({
                event: {
                    ...this.state.event,
                    eventType:  this.state.eventTypes[ev.target.value]
                }
            });
        else if(ev.target.name === "supervisor")
            this.setState({
                event: {
                    ...this.state.event,
                    senior:  this.state.seniors[ev.target.value]
                }
            });
        else{
            this.setState({
                event: {
                    ...this.state.event,
                    name:  ev.target.value
                }
            });
        }
    }

    render() {
        return (
            <>
                <div className="card p-3" style={{width: "25rem"}}>
                    <h5 className="card-title">Esemény hozáadása</h5>
                    <div className="card-text">
                        <div className="form-group">
                            <label htmlFor="seniorName">Név:</label>
                            <input className="ml-2" name="name" value={this.state.event.name} onChange={this.handleChange} type="text" id="seniorName" />
                        </div>
                        <div className="form-group">
                            <label htmlFor="seniorEmail">Esemény típusa:</label>
                                <select className="ml-2" name="eventType" onChange={this.handleChange}>
                                    <option value={null}> </option>
                                    {this.state.eventTypes.map((eventType, index) => (
                                        <option key={eventType.eventTypeId} value={index}>{eventType.name}</option>
                                    ))}
                                </select>
                        </div>
                        <div className="form-group">
                            <label htmlFor="seniorEmail">Esemény felelőse:</label>
                            <select className="ml-2" name="supervisor" onChange={this.handleChange}>
                                <option value={null}> </option>
                                {this.state.seniors.map((senior, index) => (
                                    <option key={senior.seniorId} value={index}>{senior.name}</option>
                                ))}
                            </select>
                        </div>
                        <Link to="/events">
                            <button onClick={() => this.props.onSubmit(this.state.event)} type="submit" className="btn btn-success btn-sm">Hozzáadás</button>
                        </Link>
                    </div>
                </div>
            </>
        )
    }
}

export default AddEventForm
