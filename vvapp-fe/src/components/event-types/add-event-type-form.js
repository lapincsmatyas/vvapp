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
            <>
                <div>
                    <h1>Esemény típus hozzáadása</h1>
                    <div className="form-group">
                        <label htmlFor="eventTypeName">Név:</label>
                        <input name="name" value={this.state.name} onChange={this.handleChange} type="text" className="form-control" id="eventTypeName" />
                    </div>
                    <button onClick={() => this.props.onSubmit(this.state)} type="submit" className="btn btn-primary">Hozzáadás</button>
                </div>
            </>
        )
    }
}

export default AddEventTypeForm
