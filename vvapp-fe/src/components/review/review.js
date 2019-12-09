import React from 'react'
import {faTimesCircle} from '@fortawesome/free-solid-svg-icons'
import {FontAwesomeIcon} from "@fortawesome/react-fontawesome";
import CurrentUserContext from "../../CurrentUserContext";

class Review extends React.Component{
    static contextType = CurrentUserContext;

    constructor(props){
        super(props);
    }

    render() {
        return(
            <>
                {this.props.review.senior.name}: {this.props.review.text}
                {this.props.review.senior.email === this.context.email &&
                    <FontAwesomeIcon onClick={() => this.props.onReviewDelete(this.props.review)} className="float-right" style={{color: "red",  cursor:"pointer"}} icon={faTimesCircle}/>
                }
            </>
        )
    }
}

export default Review;
