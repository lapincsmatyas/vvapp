import React from 'react';
import {BrowserRouter as Router, NavLink, Route, Switch} from "react-router-dom";
import CurrentUserContext from "./CurrentUserContext";
import UserService from "./services/user.service";
import Seniors from "./components/seniors/seniors";
import Events from "./components/events/events";
import SeniorDetail from "./components/seniors/senior-detail";
import EventDetail from "./components/events/event-detail";
import AddSeniorToEventForm from "./components/events/add-senior-to-event-form";
import Header from "./components/header/header";
import EventTypes from "./components/event-types/event-types";
import Profile from "./components/profile/profile";
import User from "./components/user/user";
import EventService from "./services/event.service";

class App extends React.Component{
    setCurrent = current => {
        console.log("lol");
        this.setState({current: current});
    }

  constructor(props){
    super(props);
    this.state = {
        current: null,
        events: [],
        setCurrent: this.setCurrent
    };
    this.eventService = new EventService();
    this.userService = new UserService();
  }

  componentDidMount() {
        this.userService.getCurrentUser().then(result => {

        })
        /*
        console.log(process.env.REACT_APP_SERVER_PORT);
      const code =
          window.location.href.match(/code=(.*)/) &&
          window.location.href.match(/code=(.*)/)[1];
      if(code) {
          this.userService.getCurrentUser(code).then(result => {
              this.setState({current: result});
          })
      };

         */
  }



    render() {
        return(
            <>

            </>
        )
        /*
      return (

          <>
              <Header current={this.state.current} />
              {this.state.current &&
                  <CurrentUserContext.Provider value={this.state}>
                      <div style={{width: "75%", margin: "0 auto"}}>
                          <Switch>
                              <Route exact path="/"/>
                              <Route path="/seniors" component={Seniors}/>
                              <Route path="/events" component={Events}/>
                              <Route path="/event-types" component={EventTypes}/>
                              <Route path="/profile" component={User}/>
                              <Route path="/events/event/:id/seniors/add" component={AddSeniorToEventForm}/>
                          </Switch>
                      </div>
                  </CurrentUserContext.Provider>
              }
        </>


      );

         */
  }
}

export default App;
