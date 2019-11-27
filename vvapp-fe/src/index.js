import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {BrowserRouter as Router, Route, NavLink} from "react-router-dom";
import Seniors from "./components/seniors/seniors";
import Events from "./components/events/events";
import SeniorDetail from "./components/seniors/senior-detail";
import EventDetail from "./components/events/event-detail";
import AddSeniorToEventForm from "./components/events/add-senior-to-event-form";

const routing = (
    <Router>
        <div>
            <Route path="/" component={App} />
            <Route path="/seniors" component={Seniors} />
            <Route path="/events" component={Events} />
            <Route path="/seniors/senior/:id" component={SeniorDetail} />
            <Route path="/events/event/:id" component={EventDetail} />
            <Route path="/events/event/:id/seniors/add" component={AddSeniorToEventForm} />
        </div>
    </Router>
);

ReactDOM.render(routing, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
