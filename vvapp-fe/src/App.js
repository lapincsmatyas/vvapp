import React from 'react';
import SeniorList from './components/seniors/senior-list'
import EventList from './components/events/event-list'
import SeniorService from './services/senior.service'
import EventService from './services/event.service'

class App extends React.Component{

  constructor(props){
    super(props);
    this.seniorService = new SeniorService();
    this.eventService = new EventService();

    this.state = {
        events: [],
        seniors: [],
        eventRoles: [],
        eventTypes: [],
        showDetails: false,
        selectedSenior: null
    }

    this.getEventTypes = this.getEventTypes.bind(this);
    this.getEventRoles = this.getEventRoles.bind(this);

    this.onSeniorAdd = this.onSeniorAdd.bind(this);
    this.onEventAdd = this.onEventAdd.bind(this);
    this.onEventTypeAdd = this.onEventTypeAdd.bind(this);
    this.onEventRoleAdd = this.onEventRoleAdd.bind(this);
    this.onParticipationAdd = this.onParticipationAdd.bind(this);
  }

  componentDidMount(){

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

  onParticipationAdd(participation){
    this.eventService.addSeniorToEvent(participation.event, participation.senior, participation.eventRole).then(participation => {
        console.log(participation);
    })
  }

  render() {
    return (
        <div>
            <h1>Home</h1>
        </div>

        /*
        <>
            <div>

            </div>
            <div>

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
        */
    );
  }



  getEventTypes(){
    this.eventService.getAllEventTypes().then(eventTypes => {
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
