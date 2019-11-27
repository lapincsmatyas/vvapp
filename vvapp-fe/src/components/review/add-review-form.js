import React from "react"
import CurrentUserContext from "../../CurrentUserContext";

class AddReviewForm extends React.Component{
    constructor(props) {
        super(props);

    }

    render() {
        return(

                <div className="ml-4 m-1">
                    <h6>Értékelés hozzáadása</h6>

                        <textarea rows="4" cols="50"></textarea>
                        <button onClick={this.props.onSubmit} className="btn btn-success btn-sm">Hozzáadás</button>
                        <button onClick={this.props.onCancel} className="btn btn-danger btn-sm ml-2">Mégsem</button>
                        )}

                </div>
        )
    }
}

export default AddReviewForm;
