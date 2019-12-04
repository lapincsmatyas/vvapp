import React from "react"
import SeniorService from "../../services/senior.service";
import GroupService from "../../services/group.service";
import CurrentUserContext from "../../CurrentUserContext";

class Profile extends React.Component{
    static contextType = CurrentUserContext;

    constructor(props) {
        super(props);
        this.state = {
            edit: false,
            groups: [],
            senior: null
        };

        this.seniorService = new SeniorService();
        this.groupService = new GroupService();

        this.handleChange = this.handleChange.bind(this);
        this.onCancel = this.onCancel.bind(this);
        this.editSenior = this.editSenior.bind(this);
    }

    componentDidMount() {
        console.log(this.props.senior);
        if(this.props.senior != null) {
            this.setState({senior: this.props.senior});
        } else {
            this.seniorService.getSeniorById(this.props.match.params.id).then(senior => {
                this.setState({senior: senior});
            });
        }

        this.groupService.getAllGroups().then(groups =>{
            this.setState({groups: groups.sort((a,b) => a.groupId > b.groupId)});
        });
    }

    componentDidUpdate(prevProps, prevState, snapshot) {
        if(prevProps.match) {
            if (prevProps.match.params.id !== this.props.match.params.id) {
                this.seniorService.getSeniorById(this.props.match.params.id).then(senior => {
                    this.setState({senior: senior});
                });
            }
        }
    }

    handleChange(event){
        if(event.target.name === "group") {
            this.setState({
                senior: {
                    ...this.state.senior,
                    group: this.state.groups[event.target.value - 1]
                }
            });
            console.log(this.state.senior.group);
        }
        else
            this.setState({senior: {...this.state.senior, [event.target.name]: event.target.value}});

    }

    onCancel(){
        this.setState({senior: this.props.senior, edit: false});
    }

    render() {
        if(this.state.senior == null) return null;
        console.log(this.context.current);
        return (
            <div className="m-3">
                <h4>Profil</h4>
                <div className="card p-3">
                    <div className="card-text">
                        <form>
                            <div className="form-group row">
                                <label htmlFor="name" className="col-sm-auto col-form-label font-weight-bold">Név</label>
                                <div className="col-sm-10">
                                    <input type="text" className={"form-control" + (this.state.edit ? "": "-plaintext")} disabled={!this.state.edit} onChange={this.handleChange} id="name" name="name" value={this.state.senior.name}/>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="staticEmail" className="col-sm-auto col-form-label font-weight-bold">Email</label>
                                <div className="col-sm-10">
                                    <input type="text" className={"form-control" + (this.state.edit ? "": "-plaintext")} disabled={!this.state.edit} id="staticEmail" name="email" onChange={this.handleChange}
                                           value={this.state.senior.email}/>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="mobile" className="col-sm-auto col-form-label font-weight-bold">Mobil</label>
                                <div className="col-sm-10">
                                    <input type="text" className={"form-control" + (this.state.edit ? "": "-plaintext")} disabled={!this.state.edit} id="mobile" name="mobile" onChange={this.handleChange} value={this.state.senior.mobile}/>
                                </div>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="group" className="col-sm-auto col-form-label font-weight-bold">Gárda</label>
                                <select className={"ml-2 col-form-control "} disabled={!this.state.edit} name="group" onChange={this.handleChange} value={this.state.senior.group ? this.state.senior.groupId : 0} >
                                    {this.state.groups.map((group, index) => (
                                        <option key={group.groupId} value={group.groupId}>{group.name}</option>
                                    ))}
                                </select>
                            </div>
                        </form>
                        { (this.state.senior.seniorId === this.context.current.seniorId || this.context.current.role === "ADMIN") &&
                            <div>
                                <button className={"btn " + "btn" + (this.state.edit ? "-success" : "-primary") + " btn-sm"}
                                        onClick={this.editSenior}>{(this.state.edit ? "Mentés" : "Szerkesztés")}</button>
                                    {
                                        this.state.edit &&
                                        <button className={"btn btn-danger btn-sm ml-3"}
                                        onClick={this.onCancel}>Mégse</button>
                                    }
                            </div>
                        }
                    </div>
                </div>
            </div>
        );
    }

    editSenior() {
        if(this.state.edit === false)
            this.setState({edit: true});
        else{
            this.setState({senior: {...this.state.senior, ...this.state.inputs}});
            this.seniorService.patchSenior(this.state.senior);
            this.setState({edit: false});
            this.props.onSubmit(this.state.senior);
        }
    }
}

export default Profile