import apiUtils from "../utils/apiUtils";
import {ACCESS_TOKEN} from "../constants";


const Auth = {
    login: (request) => {
        return apiUtils.post('/auth/signin', request);
    },
    register: (request) => {
        return  apiUtils.post('/auth/signup', request);
    },
    getCurrentUser: () => {
        if(!localStorage.getItem(ACCESS_TOKEN)) {
            return Promise.reject("No access token set.");
        }
        return apiUtils.get('/user/me')
    }
};

export default Auth;