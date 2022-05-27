import apiUtils from "../utils/apiUtils";

const RS = {
    listReviewsByBook(bookName) {
        return apiUtils.get("/review/book/" + bookName);
    },
    listReviewsByUserId(userId) {
        return apiUtils.get("/review/user/" + userId);
    },
    createReview: (request) => {
        return apiUtils.post("/review/", request);
    },
    deleteReview(reviewId) {
        return apiUtils.delete("/review/" + reviewId);
    },
}
export default RS;