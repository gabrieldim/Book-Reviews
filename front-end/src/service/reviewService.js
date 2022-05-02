import apiUtils from "../utils/apiUtils";

const RS={
    listReviewsByBook(bookName){
        return apiUtils.get("/review/book/"+bookName);
    },
    createReview(request){
        return apiUtils.post("/review/create",request);
    },
    deleteReview(reviewId){
        return apiUtils.delete("/review/"+reviewId);
    },
    listReviewsByUserId(userId){
        return apiUtils.get("/review/user/"+userId);
    }
}
export default RS;