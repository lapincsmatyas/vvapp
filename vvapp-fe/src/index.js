import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';
import {BrowserRouter as Router, Route, Link} from "react-router-dom";
import Seniors from "./components/seniors/seniors";
import Events from "./components/events/events";
import SeniorDetail from "./components/seniors/senior-detail";

const routing = (
    <Router>
    <ul className="nav nav-tabs">
        <li className="nav-item">
            <Link className="nav-link" to="/seniors">Seniorok</Link>
        </li>
        <li className="nav-item">
            <Link className="nav-link" to="/events">Események</Link>
        </li>
    </ul>

        <div>
            <Route exact path="/" component={App} />
            <Route path="/seniors" component={Seniors} />
            <Route path="/events" component={Events} />
            <Route exact path="/seniors/:id" component={SeniorDetail} />
        </div>
    </Router>




)

ReactDOM.render(routing, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
