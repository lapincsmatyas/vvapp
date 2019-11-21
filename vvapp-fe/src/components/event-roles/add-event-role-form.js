import React from 'react'

class AddEventRoleForm extends React.Component{
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
                    <h1>Szerep hozzáadása</h1>
                    <div className="form-group">
                        <label htmlFor="eventRoleName">Név:</label>
                        <input name="name" value={this.state.name} onChange={this.handleChange} type="text" className="form-control" id="eventRoleName" />
                    </div>
                    <button onClick={() => this.props.onSubmit(this.state)} type="submit" className="btn btn-primary">Hozzáadás</button>
                </div>
            </>
        )
    }
}

export default AddEventRoleForm
