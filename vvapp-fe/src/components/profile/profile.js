import React from "react"
import CurrentUserContext from "../../CurrentUserContext";

class Profile extends React.Component{
    static contextType = CurrentUserContext;

    constructor(props) {
        super(props);
        this.state = {
            edit: false,
            senior: null
        }

        this.handleChange = this.handleChange.bind(this);
        this.onCancel = this.onCancel.bind(this);
    }

    componentDidMount() {
        this.setState({senior: {...this.context}});
        console.log(this.state);
    }

    handleChange(event){
        this.setState({senior: {...this.state.senior, [event.target.name]: event.target.value}});
        console.log(this.context);
    }

    onCancel(){
        this.setState({senior: {...this.context}, edit: false});
        console.log(this.state.senior);
        console.log(this.context);
    }

    render() {
        if(this.state.senior == null) return null;
        return (
            <div className="m-3">
                <h4>Profil</h4>
                <div className="card p-3">
                    <div className="card-text">
                        <form>
                            <div className="form-group row">
                                <label htmlFor="name" className="col-sm-auto col-form-label font-weight-bold">Név</label>
                                <div className="col-sm-10">
                                    <input type="text" className={"form-control" + (this.state.edit ? "": "-plaintext")} onChange={this.handleChange} id="name" name="name" defaultValue={this.state.senior.name}/>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="staticEmail" className="col-sm-auto col-form-label font-weight-bold">Email</label>
                                <div className="col-sm-10">
                                    <input type="text" className={"form-control" + (this.state.edit ? "": "-plaintext")} id="staticEmail" name="email" onChange={this.handleChange}
                                           defaultValue={this.state.senior.email}/>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="mobile" className="col-sm-auto col-form-label font-weight-bold">Mobil</label>
                                <div className="col-sm-10">
                                    <input type="text" className={"form-control" + (this.state.edit ? "": "-plaintext")} id="mobile" name="mobile" onChange={this.handleChange} defaultValue={this.state.senior.mobile}/>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="group" className="col-sm-auto col-form-label font-weight-bold">Gárda</label>
                                <div className="col-sm-10">
                                    <input type="text" className={"form-control" + (this.state.edit ? "": "-plaintext")} id="group" name="group" onChange={this.handleChange} defaultValue={this.state.senior.group.name}/>
                                </div>
                            </div>
                        </form>
                        <button className={"btn " + "btn" + (this.state.edit ? "-success" : "-primary") +  " btn-sm"} onClick={() => this.setState({edit: true})}>Szerkeszt</button>
                        {
                            this.state.edit &&
                            <button className={"btn btn-danger btn-sm ml-3"}
                                 onClick={() => this.onCancel()}>Mégse</button>
                        }
                    </div>
                </div>
            </div>
        );
    }
}

export default Profile