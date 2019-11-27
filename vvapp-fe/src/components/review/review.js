import React from 'react'

class Review extends React.Component{
    constructor(props){
        super(props);
    }

    render() {
        return(
            <>
                <p>{this.props.review.text}</p>
            </>
        )
    }
}

export default Review;
