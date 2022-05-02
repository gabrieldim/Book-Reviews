import { ACCESS_TOKEN} from '../constants';
import axios from "../custom-axios/axios";
import jwt from 'jsonwebtoken'

const apiUtils = {
    get: (url) => {
        return axios.get(url, {
            headers: {
                Authorization: token()
            }
        });
    },
    post: (url, body) => {
        const data = JSON.stringify(body);
        return axios.post(url, data, {
            headers: {
                Authorization: token(),
                'content-type': 'application/json'
            }
        });
    },
    delete: (url) =>{
        return axios.delete(url,{
            headers:{
                Authorization: token()
            }
        });
    }
    
};

function token() {
    if(localStorage.getItem(ACCESS_TOKEN)) {
        let token=localStorage.getItem(ACCESS_TOKEN);
        if(isExpired(token)){
            localStorage.removeItem(ACCESS_TOKEN);
            return '';
        }
        return 'Bearer ' + token;
    }
    return '';
}

function isExpired(token){
    var decodedToken=jwt.decode(token, {complete: true});
    var dateNow = new Date();
    return decodedToken.exp < dateNow.getTime();
}
export default apiUtils;