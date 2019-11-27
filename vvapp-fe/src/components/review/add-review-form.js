import React from "react"

class AddReviewForm extends React.Component{
    constructor(props) {
        super(props);

    }

    render() {
        return(
            <div>
                <textarea rows="4" cols="50"></textarea>
                <button onClick={this.props.onSubmit} className="btn btn-success btn-sm">Hozzáadás</button>
            </div>
        )
    }
}

export default AddReviewForm;
