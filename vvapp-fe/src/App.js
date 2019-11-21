import React from 'react';
import Seniors from './components/seniors/seniors'
import Events from './components/events/events'
import SeniorService from './services/senior.service'
import EventService from './services/event.service'
import AddSeniorForm from './components/seniors/add-senior-form'
import AddEventForm from './components/events/add-event-form'
import AddEventTypeForm from './components/event-types/add-event-type-form'
import EventTypes from "./components/event-types/event-types";
import EventRoles from "./components/event-roles/event-roles";
import AddEventRoleForm from "./components/event-roles/add-event-role-form";

class App extends React.Component{

  constructor(props){
    super(props);
    this.seniorService = new SeniorService();
    this.eventService = new EventService();

    this.state = {
        events: [],
        seniors: [],
        eventRoles: [],
        eventTypes: []
    }

    this.getSeniors = this.getSeniors.bind(this);
    this.getEvents = this.getEvents.bind(this);
    this.getEventTypes = this.getEventTypes.bind(this);
    this.getEventRoles = this.getEventRoles.bind(this);

    this.onSeniorAdd = this.onSeniorAdd.bind(this);
    this.onEventAdd = this.onEventAdd.bind(this);
    this.onEventTypeAdd = this.onEventTypeAdd.bind(this);
    this.onEventRoleAdd = this.onEventRoleAdd.bind(this);
  }

  componentDidMount(){
    this.getSeniors();
    this.getEvents();
    this.getEventTypes();
    this.getEventRoles();
  }

  onSeniorAdd(senior){
    this.seniorService.addNewSenior(senior).then(senior => {
      this.getSeniors();
    })
  }

  onEventAdd(event){
    this.eventService.createEvent(event).then(event => {
        this.getEvents();
    })
  }

  onEventTypeAdd(eventType){
      this.eventService.createEventType(eventType).then(eventType => {
          this.getEventTypes();
      })
  }

  onEventRoleAdd(eventRole){
      this.eventService.createEventRole(eventRole).then(role => {
          this.getEventRoles();
      })
  }

  render() {
    return (
        <>
            <div>
              <Seniors updateSeniors={this.getSeniors} seniors={this.state.seniors} />
              <AddSeniorForm onSubmit={this.onSeniorAdd}/>
            </div>
            <div>
                <Events events={this.state.events} />
                <AddEventForm onSubmit={this.onEventAdd} eventTypes={this.state.eventTypes} />
            </div>
            <div>
                <EventTypes eventTypes={this.state.eventTypes} />
                <AddEventTypeForm onSubmit={this.onEventTypeAdd} />
            </div>
            <div>
                <EventRoles eventRoles={this.state.eventRoles} />
                <AddEventRoleForm eventTypes={this.state.eventTypes} onSubmit={this.onEventRoleAdd} />
            </div>
        </>
    );
  }

  getSeniors(){
    this.seniorService.getAllSeniors().then(seniors => {
      this.setState({seniors: seniors});
    })
  }

  getEvents(){
      this.eventService.getAllEvents().then(events => {
          this.setState({events: events});
      })
  }

  getEventTypes(){
    this.eventService.getAllEventTypes().then(eventTypes => {
        console.log(eventTypes);
      this.setState({eventTypes: eventTypes});
    })
  }

  getEventRoles(){
      this.eventService.getAllEventRoles().then(eventRoles => {
          this.setState({eventRoles: eventRoles});
      })
  }
}

export default App;
