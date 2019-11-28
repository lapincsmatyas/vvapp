import React from 'react'

class AddEventTypeForm extends React.Component{
    constructor(props) {
        super(props);
        this.state = {name: ''}

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return (
            <div className="card p-3">
                <div className="card-title">
                    <h6>Esemény típus hozzáadása</h6>
                </div>
                <div className="card-text">
                        <div className="form-group">
                            <label htmlFor="eventTypeName">Név:</label>
                            <input className="ml-2" name="name" value={this.state.name} onChange={this.handleChange}
                                   type="text" id="eventTypeName"/>
                        </div>
                        <button onClick={() => {this.props.onSubmit(this.state); this.setState({name: ""})}} type="submit"
                                className="btn btn-success btn-sm">Hozzáadás
                        </button>
                    </div>
            </div>
        )
    }
}

export default AddEventTypeForm
