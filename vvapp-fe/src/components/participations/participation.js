import React from "react"
import {Link} from "react-router-dom";
import Review from "../review/review";

class Participation extends React.Component{
    constructor(props) {
        super(props);
    }



    render() {
        return (
            <>
                <span className="pl-1">
                    <Link to={`/events/event/${this.props.participation.event.eventId}`}>
                        {this.props.participation.event.name}
                    </Link>
                </span>
                 <span className="ml-1">
                     - {this.props.participation.eventRole.name}
                 </span>
                <span>
                    /*
                    { this.props.participation.review &&
                        <ul>
                            {this.props.participation.reviews.map(review => (
                                <li>
                                    <Review review={review}/>
                                </li>
                            ))}
                        </ul>
                    }

                     */
                    <p>Review</p>
                </span>
                <button className="btn btn-success ml-2">Értékelés</button>
            </>
        );
    }
}

export default Participation;
