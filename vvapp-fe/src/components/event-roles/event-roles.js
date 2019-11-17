import React from 'react'

class EventRoles extends React.Component{
    render(){
        return(
            <div>
                <center><h1>Szerepek</h1></center>
                {this.props.eventRoles.map((eventRole) => (
                    <div key={eventRole.eventRoleId} className="card">
                    <div className="card-body">
                        <h5 className="card-title">{eventRole.name}</h5>
                        <h6 className="card-subtitle mb-2 text-muted">{eventRole.eventType.name}</h6>
                    </div>
                </div>
                ))}
            </div>
        )
    }
}

export default EventRoles