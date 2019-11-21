import React from 'react'

class EventTypes extends React.Component {
    render() {
        return (
            <div>
                <h1>Esemény típusok</h1>
                {this.props.eventTypes.map((eventType) => (
                    <div key={eventType.eventTypeId} className="card">
                        <div className="card-body">
                            <h5 className="card-title">{eventType.name}</h5>
                        </div>
                    </div>
                ))}
            </div>
        )
    }
}

export default EventTypes
