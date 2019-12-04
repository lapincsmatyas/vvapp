import React from "react"
import Profile from "../profile/profile";
import CurrentUserContext from "../../CurrentUserContext";

class User extends React.Component{
    render() {
        console.log("asf");
        return (
            <CurrentUserContext.Consumer>
                {({ current, setCurrent }) =>(
                    <Profile senior={current} onSubmit={setCurrent}></Profile>
                )}
            </CurrentUserContext.Consumer>
        )
    }

}

export default User