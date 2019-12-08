import React from "react"
import {NavLink} from "react-router-dom";
import CurrentUserContext from "../../CurrentUserContext";

class Header extends React.Component{
    static contextType = CurrentUserContext;

    constructor(props) {
        super(props);

    }

    render() {
        return(

            <ul className="nav nav-tabs">

                <li className="nav-item">
                    <NavLink activeClassName="active" className="nav-link" to="/seniors">Seniorok</NavLink>
                </li>
                <li className="nav-item">
                    <NavLink activeClassName="active"  className="nav-link" to="/events">Események</NavLink>
                </li>
                <li className="nav-item mr-auto">
                    <NavLink activeClassName="active"  className="nav-link" to="/event-types">Típusok és szerepek</NavLink>
                </li>

            </ul>

        )
    }
}

export default Header;
