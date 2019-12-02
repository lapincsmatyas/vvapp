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

                { this.props.current && (<>
                <li className="nav-item">
                    <NavLink activeClassName="active" className="nav-link" to="/seniors">Seniorok</NavLink>
                </li>
                <li className="nav-item">
                    <NavLink activeClassName="active"  className="nav-link" to="/events">Események</NavLink>
                </li>
                <li className="nav-item mr-auto">
                    <NavLink activeClassName="active"  className="nav-link" to="/event-types">Típusok és szerepek</NavLink>
                </li>
                </>)}
                <li className="nav-item ml-auto mr-3">
                    { this.props.current &&
                        <NavLink activeClassName="active" to="/profile">
                            <span className="navbar-text">{this.props.current.name}</span>
                        </NavLink>
                    }
                    {
                        !this.props.current &&
                        <a href="https://auth.sch.bme.hu/site/login?response_type=code&client_id=29717259662843717351&state=123&scope=basic+displayName+sn+givenName+mail+mobile+bmeunitscope" className="navbar-text">Bejelentkezés</a>
                    }
                </li>
            </ul>

        )
    }
}

export default Header;
