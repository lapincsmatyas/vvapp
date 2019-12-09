class ReviewService{

    deleteReview(review){
        return fetch(`${process.env.REACT_APP_SERVER_ADDRESS}/review/${review.reviewId}`, {
            method: "DELETE",
            mode: "cors",
            credentials: "include",
            headers: {
                "Content-Type": "application/json"
            }
        }).then(response => {
            return response.json()
        }).catch(error => {
            console.log(error);
        })
    }
}

export default ReviewService
