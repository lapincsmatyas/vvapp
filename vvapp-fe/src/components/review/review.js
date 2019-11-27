import React from 'react'

class Review extends React.Component{
    constructor(props){
        super(props);
    }

    render() {
        return(
            <>
                {this.props.review.senior.name}: {this.props.review.text}
            </>
        )
    }
}

export default Review;
