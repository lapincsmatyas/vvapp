import React from "react"

class AddReviewForm extends React.Component{
    constructor(props) {
        super(props);

        this.state = {
            reviewText: ""
        }

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return(
                <div className="ml-4 m-1">
                    <h6>Értékelés hozzáadása</h6>
                        <textarea name="reviewText" value={this.state.reviewText} onChange={this.handleChange} className="form-control" id="reviewText" rows="4" cols="50" />
                        <button onClick={() => {this.props.onSubmit(this.state.reviewText); this.props.onCancel()}} className="btn btn-success btn-sm">Hozzáadás</button>
                        <button onClick={this.props.onCancel} className="btn btn-danger btn-sm ml-2">Mégsem</button>
                </div>
        )
    }
}

export default AddReviewForm;
