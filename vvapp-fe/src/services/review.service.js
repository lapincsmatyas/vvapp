class ReviewService{

    deleteReview(review){
        return fetch(`http://localhost:8080/review/${review.reviewId}`, {
            method: "DELETE",
            mode: "cors",
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
