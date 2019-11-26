import React from 'react'

class EventList extends React.Component{
    render() {
        return(
            <div>
                <h1>Események</h1>
                <ul className="list-group">
                    {this.props.events.map((event) => (
                        <li key={event.eventId} className="list-group-item">
                            {event.name}
                        </li>
                    ))}
                </ul>
            </div>
        )
    }
}

export default EventList
