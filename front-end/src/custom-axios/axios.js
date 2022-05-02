import axios from 'axios';
import {API_BASE_URL} from "../constants";

const instance = axios.create({
    baseURL: API_BASE_URL,
    headers: {
        'Access-Control-Allow-Origin': '*'
    },
});

export default instance;