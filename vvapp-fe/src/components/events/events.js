import React from 'react'

class Events extends React.Component{
    render() {
        return(
            <div>
                <h1>Események</h1>
                {this.props.events.map((event) => (
                    <div key={event.eventId} className="card">
                        <div onClick={() => this.props.onSelectEvent(event)} className="card-body">
                            <h5 className="card-title">{event.name}</h5>
                            <h6 className="card-subtitle mb-2 text-muted">{event.eventType.name}</h6>
                        </div>
                    </div>
                ))}
            </div>
        )
    }
}

export default Events
