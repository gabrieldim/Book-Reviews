import React from 'react';
import {ACCESS_TOKEN} from "../../../constants/index"
import {withRouter} from 'react-router-dom'
import Auth from "../../../service/authService"
import { toast, ToastPosition } from 'react-toastify';
import './Login.css'
const Login = (props) => {

    const handleSubmit = (e)=>{
        e.preventDefault();
        let email = document.getElementById("email");
        let passsword = document.getElementById("password");
        let loginRequest={
            usernameOrEmail: email.value,
            password: passsword.value
        };
        Auth.login(loginRequest)
            .then((response)=>{
                localStorage.setItem(ACCESS_TOKEN, response.data.accessToken);
                props.onLogin();
                props.history.push("/")
            }).catch(error =>{
                let code=error.message.slice(error.message.length-3).trim();
                if(code === '401'){
                    toast.error("Your username/email or password is incorrect! Try again!",{position:ToastPosition.TOP_CENTER})
                }
            });
        
    }
    return (
        <form onSubmit={handleSubmit}>
            <div className="col-sm-4 ml-auto mr-auto p-5 mt-5 login-background"
                 >
                <label htmlFor="address">Email or Username:</label>
                <input id="email" className="form-control text-center" autoFocus type="text"
                     required />
                <br/>
                <label htmlFor="password">Password:</label>
                <input id="password" className="form-control text-center" type="password" required/>
                <br/>
                <div className="col-sm-8 ml-auto mr-auto">
                    <button type={'submit'} className="btn btn-info btn-block btnColor">Sign in</button>
                </div>
            </div>
        </form>
    );
};

export default withRouter(Login);