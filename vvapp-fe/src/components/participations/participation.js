import React from "react"
import {Link} from "react-router-dom";
import Review from "../review/review";
import ParticipationService from "../../services/participation.service";
import AddReviewForm from "../review/add-review-form";
import CurrentUserContext from "../../CurrentUserContext";
import ReviewService from "../../services/review.service";

class Participation extends React.Component{
    static contextType = CurrentUserContext;

    constructor(props) {
        super(props);

        this.state = {
            participation: null,
            showAddReview: false
        };

        this.loadReviews = this.loadReviews.bind(this);
        this.onReviewAdd = this.onReviewAdd.bind(this);
        this.onReviewCancel = this.onReviewCancel.bind(this);
        this.onReviewDelete = this.onReviewDelete.bind(this);

        this.participationService = new ParticipationService();
        this.reviewService = new ReviewService();
    }

    loadReviews() {
        this.participationService.getParticipationById(this.props.participation.participationId).then(participation => {
            this.setState({participation: participation});
        });
    }

    onReviewAdd(text){
        console.log(this.context);
        this.participationService.addReviewToParticipation(this.state.participation, this.context, text).then(result =>{
            this.loadReviews();
        })
    }

    onReviewDelete(review){
        this.reviewService.deleteReview(review).then(result => {
           this.loadReviews();
        })
    }

    onReviewCancel(){
        this.setState({showAddReview: false});
    }

    render() {
        return (
            <>
                <span className="pl-1 mr-1">
                    <Link to={`/events/event/${this.props.participation.event.eventId}`}>
                        {this.props.participation.event.name}
                    </Link>
                </span>
                -
                 <span className="ml-1">
                      <Link to={`/seniors/senior/${this.props.participation.senior.seniorId}`}>
                       {this.props.participation.senior.name}
                    </Link>
                     : {this.props.participation.eventRole.name}
                 </span>
                <span>
                    { this.state.participation && this.state.participation.reviews &&
                        <div>
                            <ul>
                                {this.state.participation.reviews.map(review => (
                                    <li key={review.reviewId}>
                                        <Review onReviewDelete={this.onReviewDelete} review={review}/>
                                    </li>
                                ))}
                            </ul>
                        </div>
                    }
                    {
                        this.state.participation && this.state.participation.reviews && this.state.participation.reviews.length === 0 &&
                            <div className="ml-4">
                                Nincsenek értékelések
                            </div>
                    }
                </span>
                {
                    <div>
                        {(this.state.participation && this.state.participation.reviews) && !this.state.showAddReview &&
                            <button className="btn btn-success btn-sm ml-4 m-1" onClick={() => this.setState({showAddReview: true})}>Értékelés</button>
                        }
                        {(this.state.participation && this.state.participation.reviews) && this.state.showAddReview &&
                            <AddReviewForm onCancel={this.onReviewCancel} onSubmit={this.onReviewAdd} className="ml-5"/>
                        }
                        {
                        (!this.state.participation || !this.state.participation.reviews) &&
                            <button className="btn btn-primary btn-sm m-1" onClick={this.loadReviews}>Értékelések betöltése</button>
                        }
                    </div>
                }
            </>
        );
    }
}

export default Participation;
