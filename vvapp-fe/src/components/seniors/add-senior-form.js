import React from 'react'
import styles from './add-senior-form.module.css'

class AddSeniorForm extends React.Component {

    constructor(props){
        super(props);
        this.state = {name: '', email: ''};

        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event){
        this.setState({[event.target.name]: event.target.value});
    }

    render() {
        return (
            <div className={styles["add-senior-form"]}>
                <div className="form-group">
                    <label htmlFor="seniorName">Név:</label>
                    <input name="name" value={this.state.value} onChange={this.handleChange} type="text" className="form-control" id="seniorName" />
                </div>
                <div className="form-group">
                    <label htmlFor="seniorEmail">Email:</label>
                    <input name="email" type="email" onChange={this.handleChange} className="form-control" id="seniorEmail" />
                </div>
                <button onClick={() => this.props.onSubmit(this.state)} type="submit" className="btn btn-primary">Hozzáadás</button>
            </div>
        )
    }
}

export default AddSeniorForm