import React from 'react';
import {BrowserRouter as Router, NavLink} from "react-router-dom";
import CurrentUserContext from "./CurrentUserContext";
import UserService from "./services/user.service";

class App extends React.Component{

  constructor(props){
    super(props);
    this.state = {
        current: null
    };

    this.userService = new UserService();
  }

  componentDidMount() {
      this.userService.getCurrentUser().then(result => {
          this.setState({current: result})
      })
  }

    render() {
      if(!this.state.current) return null;
      return (
           <CurrentUserContext.Provider value={this.state.current}>
               <ul className="nav nav-tabs">
                   <li className="nav-item">
                       <NavLink activeClassName="active" className="nav-link" to="/seniors">Seniorok</NavLink>
                   </li>
                   <li className="nav-item mr-auto">
                       <NavLink activeClassName="active"  className="nav-link" to="/events">Események</NavLink>
                   </li>
                   <li className="nav-item ml-auto mr-3">
                       <span className="navbar-text">{this.state.current.name}</span>
                   </li>
               </ul>
           </CurrentUserContext.Provider>
      );
  }
}

export default App;
