import apiUtils from "../utils/apiUtils";


const BS = {
    listPopular: ()=>{
        return apiUtils.get("/book/popular");
    },
    getBook: (bookName)=>{
        return apiUtils.get("/book/?bookName="+bookName);
    },
    listBooksByGenre:(genreName)=>{
        return apiUtils.get("/book/genre/"+genreName);
    },
    listBooksByAuthor:(authorName)=>{
        return apiUtils.get("/book/author/"+authorName);
    },
    searchBook:(searchWord)=>{
        return apiUtils.get("/book/search/"+searchWord);
    },
    listBooksBySeries:(seriesName)=>{
        return apiUtils.get("/book/series/"+seriesName);
    },
    deleteBook:(bookId)=>{
        return apiUtils.delete("/book/"+bookId);
    },
    createBook:(bookRequest)=>{
        return apiUtils.post("/book/",bookRequest);
    }
}
export default BS;