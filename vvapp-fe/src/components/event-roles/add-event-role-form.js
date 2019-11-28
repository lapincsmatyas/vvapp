import React from 'react'

class AddEventRoleForm extends React.Component {
    constructor(props) {
        super(props);
        this.state = {name: ''}

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return (
            <>
                <div>
                    <div className="form-group">
                        <label htmlFor="eventTypeName">Név:</label>
                        <input className="ml-2" name="name" value={this.state.name} onChange={this.handleChange}
                               type="text" id="eventTypeName"/>
                    </div>
                    <button onClick={() => {this.props.onSubmit(this.state);this.setState({name: ""})}} type="submit"
                            className="btn btn-success btn-sm">Hozzáadás
                    </button>
                </div>
            </>
        )
    }
}

export default AddEventRoleForm
