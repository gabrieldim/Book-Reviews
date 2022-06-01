import apiUtils from "../utils/apiUtils";
import {ACCESS_ROLE, ACCESS_TOKEN} from "../constants";


const Auth = {
    login: (request) => {
        if(request.role === "author") {
            return apiUtils.post('/author/login', request);
        }
        return apiUtils.post('/reviewer/login', request);
    },
    register: (request) => {
        if(request.role === "author") {
            return apiUtils.post('/author/register', request);
        }
        return apiUtils.post('/reviewer/register', request);
    },
    getCurrentUser: () => {
        if (localStorage.getItem(ACCESS_TOKEN)) {
            return apiUtils.get('/' + ACCESS_ROLE + '/' + localStorage.getItem(ACCESS_TOKEN));
        }
        return Promise.reject("You are not logged in");
    }
};

export default Auth;
