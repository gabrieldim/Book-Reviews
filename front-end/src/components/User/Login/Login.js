import React, {useState} from 'react';
import {ACCESS_ROLE, ACCESS_TOKEN} from "../../../constants/index"
import Auth from "../../../service/authService"
import {toast, ToastPosition} from 'react-toastify';
import './Login.css'

import {Form} from "react-bootstrap";
import {Link, useHistory} from "react-router-dom";

export const Login = () => {

    const history = useHistory();
    const [email, setEmail] = useState("");
    const [password, setPassword] = useState("");
    const [role, setRole] = useState("reviewer");

    const handleSubmit = (e) => {
        e.preventDefault();

        let loginRequest = {
            email: email,
            password: password,
            role: role
        };

        Auth.login(loginRequest)
            .then((response) => {
                localStorage.setItem(ACCESS_TOKEN, response.data.id);
                localStorage.setItem("userEmail", response.data.email);
                localStorage.setItem(ACCESS_ROLE, loginRequest.role);

                history.push("/");
            }).catch(error => {
            let code = error.message.slice(error.message.length - 3).trim();
            if (code.includes("40")) {
                toast.error("Your email or password is incorrect! Try again!", {position: ToastPosition.TOP_CENTER})
                alert("Your email or password is incorrect! Try again!");
            }
        });

    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="col-sm-4 ml-auto mr-auto p-5 mt-5 login-background"
            >
                <label htmlFor="address">Email:</label>
                <input id="email" onChange={(e) => {
                    setEmail(e.target.value)
                }}
                       className="form-control text-center mt-1" autoFocus type="text"
                       required/>

                <label htmlFor="password" className={"mt-3"}>Password:</label>
                <input id="password" onChange={(e) => setPassword(e.target.value)}
                       className="form-control text-center mt-1" type="password" required/>

                <label htmlFor="role" className={"mt-3"}>Role:</label>
                <Form.Select id={"role"} className={"form-control mt-1"} size="lg"
                             onChange={(e) => setRole(e.target.value)}>
                    <option value="reviewer"> Reviewer</option>
                    <option value="author"> Author</option>
                </Form.Select>

                <button type={'submit'} className="btn btn-info btn-block btnColor mt-5">Sign in</button>
                <Link to={"/signup"} className="btn btn-block btn-outline-dark mt-3">Sign up</Link>
            </div>
        </form>
    );
};
