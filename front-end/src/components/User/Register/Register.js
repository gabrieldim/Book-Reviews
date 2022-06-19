import React, {useState} from 'react';
import Auth from "../../../service/authService"
import {toast} from 'react-toastify'
import {Form} from "react-bootstrap";
import {Link, useHistory} from "react-router-dom";

export const Register = () => {

    const history = useHistory();

    const [fullName, setFullName] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [msgContents, setMsgContents] = useState("");
    const [role, setRole] = useState("reviewer");

    const handleSubmit = (e) => {
        e.preventDefault();

        if (fullName.length <= 3 || fullName.split(" ").length < 2) {
            setMsgContents("Write your full name");
        }
        if (password.length < 8 || password === "") {
            setMsgContents("Password must not be empty and be less than 8.");
        }
        if (msgContents !== "") {
            toast.error(msgContents, {autoClose: 4000});
        } else {

            let nameParts = fullName.split(" ");
            let signUpRequest = {
                firstName: nameParts[0],
                lastName: nameParts[1],
                email: email,
                password: password,
                role: role
            };

            Auth.register(signUpRequest)
                .then(() => {
                    const message = "You've successfully registered!";
                    toast.success(message);
                    alert(message);
                    history.push("/login");
                }).catch(error => {
                const message = "There was an error when trying to register the user! " + error;
                toast.error(message);
                alert(message);
            });
        }
    }

    return (
        <form onSubmit={handleSubmit}>
            <div className="col-sm-4 ml-auto mr-auto p-5 mt-5 "
                 style={{backgroundColor: '#4fb9b451', border: '0.1em solid #20B2AA', borderRadius: '0.2em'}}>
                <label htmlFor="name">Full Name:</label>
                <input id="name" onChange={(e) => {
                    setFullName(e.target.value)
                }}
                       className="form-control text-center" type="text" required/>
                <br/>
                <label htmlFor="email">Email:</label>
                <input id="email" onChange={(e) => {
                    setEmail(e.target.value)
                }}
                       className="form-control text-center" type="email" required/>
                <br/>
                <label htmlFor="password">Password:</label>
                <input id="password" onChange={(e) => setPassword(e.target.value)}
                       className="form-control text-center" type="password" required/>
                <br/>
                <label htmlFor="role">Role:</label>
                <Form.Select className={"form-control"} id="role" size="lg" onChange={(e) => setRole(e.target.value)}>
                    <option value="reviewer"> Reviewer</option>
                    <option value="author"> Author</option>
                </Form.Select>
                <br/>

                <button type={'submit'} className="btn btn-info btn-block mt-4">Sign up</button>
                <Link to={"/login"} className="btn btn-block btn-outline-dark mt-3">Sign in</Link>
            </div>
        </form>
    );
};
