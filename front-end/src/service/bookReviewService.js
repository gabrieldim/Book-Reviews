import BS from '../service/bookService';
import RS from '../service/reviewService'

const BR={
    getBookAndReviews:(bookName)=>{
        return Promise.all([BS.getBook(bookName),RS.listReviewsByBook(bookName)]);
    }
}
export default BR;