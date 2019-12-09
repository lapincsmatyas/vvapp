import React from "react"
import SeniorService from "../../services/senior.service";
import GroupService from "../../services/group.service";
import CurrentUserContext from "../../CurrentUserContext";
import UserRoleService from "../../services/user-role.service";
import Participation from "../participations/participation";

class Profile extends React.Component{
    static contextType = CurrentUserContext;

    constructor(props) {
        super(props);
        this.state = {
            edit: false,
            groups: [],
            userRoles: [],
            senior: null
        };

        this.seniorService = new SeniorService();
        this.groupService = new GroupService();
        this.userRoleService = new UserRoleService();

        this.handleChange = this.handleChange.bind(this);
        this.onCancel = this.onCancel.bind(this);
        this.editSenior = this.editSenior.bind(this);
    }

    componentDidMount() {
        this.groupService.getAllGroups().then(groups =>{
            this.userRoleService.getAllUserRoles().then(userRoles => {

                if(this.props.senior != null) {
                    this.setState({
                        senior: {
                            ...this.props.senior,
                            group: groups.find(group =>
                                group.groupId == this.props.senior.group.groupId),
                            userRole: userRoles.find(userRole =>
                                userRole.userRoleId == this.props.senior.userRole.userRoleId)
                        }
                    });
                } else {
                    this.seniorService.getSeniorById(this.props.match.params.id).then(senior => {
                        this.setState(
                            {
                                senior: {
                                    ...senior,
                                    group: groups.find(group =>
                                        group.groupId == senior.group.groupId),
                                    userRole: userRoles.find(userRole =>
                                        userRole.userRoleId == senior.userRole.userRoleId)
                                }
                            });
                    });
                }
                this.setState({groups: groups, userRoles: userRoles});
            })

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
            let selectedGroup = this.state.groups.find(group => group.groupId === event.target.value);
            this.setState({
                senior: {
                    ...this.state.senior,
                    group: selectedGroup
                }
            });
        } else if(event.target.name === "userRole") {
            let selectedUserRole = this.state.userRoles.find(userRole => userRole.userRoleId == event.target.value);
            this.setState({
                senior: {
                    ...this.state.senior,
                    userRole: selectedUserRole
                }
            });
        }
        else
            this.setState({senior: {...this.state.senior, [event.target.name]: event.target.value}});
    }

    onCancel(){
        this.setState({edit: false});
        if(this.props.senior != null) {
            this.setState({
                senior: {
                    ...this.props.senior,
                    group: this.state.groups.find(group =>
                        group.groupId == this.props.senior.group.groupId),
                    userRole: this.state.userRoles.find(userRole =>
                        userRole.userRoleId == this.props.senior.userRole.userRoleId)
                }
            });
        } else {
            this.seniorService.getSeniorById(this.props.match.params.id).then(senior => {
                this.setState(
                    {
                        senior: {
                            ...senior,
                            group: this.state.groups.find(group =>
                                group.groupId == senior.group.groupId),
                            userRole: this.state.userRoles.find(userRole =>
                                userRole.userRoleId == senior.userRole.userRoleId)
                        }
                    });
            });
        }
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
                                <select className={"ml-2 col-form-control "} disabled={!this.state.edit} name="group" onChange={this.handleChange} value={this.state.senior.group.groupId} >
                                    {this.state.groups.map((group, index) => (
                                        <option key={group.groupId} value={group.groupId}>{group.name}</option>
                                    ))}
                                </select>
                            </div>
                            <div className="form-group row">
                                <label htmlFor="group" className="col-sm-auto col-form-label font-weight-bold">Szint</label>
                                <select className={"ml-2 col-form-control "} disabled={!this.state.edit || (this.state.edit && !(this.context.current.userRole.name === "VÁRÚR" && this.context.current.group.groupId == this.state.senior.group.groupId))} name="userRole" onChange={this.handleChange} value={this.state.senior.userRole.userRoleId} >
                                    {this.state.userRoles.map((userRole, index) => (
                                        <option key={userRole.userRoleId} value={userRole.userRoleId}>{userRole.name}</option>
                                    ))}
                                </select>
                            </div>
                        </form>
                        {
                            this.state.senior.participations &&
                            (
                                <div className={"mb-3"}>
                                    <div className={"font-weight-bold"}>Részvételek</div>
                                    {
                                        (this.state.senior.participations.length > 0) ? (
                                            <ul className={"mt-2"}>
                                                {this.state.senior.participations.map(participation => (
                                                    <li key={participation.participationId}>
                                                        <Participation participation={participation}/>
                                                    </li>
                                                ))
                                                }
                                            </ul>
                                        ) : <span className={"ml-3"}>Nincs részvétel</span>
                                    }
                                    </div>
                            )
                        }


                            <div>
                                <button className={"btn " + "btn" + (this.state.edit ? "-success" : "-primary") + " btn-sm"}
                                        onClick={this.editSenior}>{(this.state.edit ? "Mentés" : "Szerkesztés")}</button>
                                    {
                                        this.state.edit &&
                                        <button className={"btn btn-danger btn-sm ml-3"}
                                        onClick={this.onCancel}>Mégse</button>
                                    }
                            </div>

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