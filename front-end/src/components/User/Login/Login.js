import React, {useState} from 'react';
import {ACCESS_ROLE, ACCESS_TOKEN} from "../../../constants/index"
import Auth from "../../../service/authService"
import { toast, ToastPosition } from 'react-toastify';
import './Login.css'

import  {Form} from "react-bootstrap";

export const Login = () => {

    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("reviewer");

    const handleSubmit = (e)=>{
        e.preventDefault();

        let loginRequest={
            usernameOrEmail: email,
            password: password,
            role: role
        };

        Auth.login(loginRequest)
            .then((response)=>{
                console.log(response);
                localStorage.setItem(ACCESS_TOKEN, response.data.id);
                localStorage.setItem(ACCESS_ROLE, response.data.role);
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
                <input id="email" onChange={(e) => {setEmail(e.target.value)}}
                       className="form-control text-center" autoFocus type="text"
                     required />

                <label htmlFor="password">Password:</label>
                <input id="password" onChange={(e) => setPassword(e.target.value)}
                       className="form-control text-center" type="password" required/>

                <div className="col-sm-12  mt-2">
                    <Form.Select size="lg" onChange={(e) => setRole(e.target.value)}>
                        <option value="reviewer"> Reviewer </option>
                        <option value="author"> Author </option>
                    </Form.Select>

                </div>
                <div className="col-sm-8 ml-auto mr-auto mt-5">
                    <button type={'submit'} className="btn btn-info btn-block btnColor">Sign in</button>
                </div>
            </div>
        </form>
    );
};
