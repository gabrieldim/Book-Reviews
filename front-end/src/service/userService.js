import apiUtils from "../utils/apiUtils";


const UServ = {
    getProfile:(username)=>{
        return apiUtils.get("/user/"+username);
    },
    likeBook:(bookId)=>{
        return apiUtils.get("/user/like/"+bookId);
    },
    unlikeBook:(bookId)=>{
        return apiUtils.get("user/unlike/"+bookId);
    },
    getUserSummary:(username)=>{
        return apiUtils.get("/user/me");
    }
}
export default UServ;