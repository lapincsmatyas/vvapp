import React from 'react';
import Seniors from './components/seniors/seniors'
import Events from './components/events/events'
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
        eventTypes: []
    }

    this.getSeniors = this.getSeniors.bind(this);
    this.getEvents = this.getEvents.bind(this);
  }

  componentDidMount(){
    this.getSeniors();
    this.getEvents();
  }

  render() {
    if(!this.state.seniors) return null;

    return (
        <>
            <div>
                <Seniors updateSeniors={this.getSeniors} seniors={this.state.seniors} />
            </div>
            <div>
                <Events events={this.state.events} />
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
}

export default App;
