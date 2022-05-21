import React, {useState} from 'react';
import Auth from "../../../service/authService"
import {ACCESS_TOKEN} from "../../../constants/index"
import {toast, ToastPosition} from 'react-toastify'

export const Register = () => {

    const [name, setName] = useState("");
    const [username, setUserName] = useState("");
    const [password, setPassword] = useState("");
    const [email, setEmail] = useState("");
    const [msgContents, setMsgContents] = useState("");

    const handleSubmit = (e)=>{
        e.preventDefault();

        if (name.value.length<=3 || username.value.length<=3 ){
            setMsgContents( "Username and name length must not be less than 3. ");
        }
        if (password.length < 8 || password ==="") {
            setMsgContents("Password must not be empty and be less than 8.");
        }
        if (msgContents!==""){
            toast.error(msgContents,{autoClose:4000});
        }
        else{

            let signUpRequest={
                name:name,
                username:username,
                email:email,
                password: password
            };

            Auth.register(signUpRequest)
                .then((response)=>{
                    toast.success("You've successfully registered!")

                }).catch(error =>{
                    let code=error.message.slice(error.message.length-3).trim();
                    if(code === '400'){
                        toast.error("That username or email is taken!")
                    }
                });
            }
    }

    return (
        <form onSubmit={handleSubmit} >
            <div className="col-sm-4 ml-auto mr-auto p-5 mt-5 "
                 style={{backgroundColor:'#4fb9b451', border:'0.1em solid #20B2AA',borderRadius:'0.2em'}}>
                    <label htmlFor="name">Name:</label>
                    <input id="name" onChange={(e) => {setName(e.target.value)}}
                           className="form-control text-center" type="text" required/>
                    <br/>
                    <label htmlFor="name">Username:</label>
                    <input id="username" onChange={(e) => {setUserName(e.target.value)}}
                           className="form-control text-center" type="text" required/>
                    <br/>
                    <label htmlFor="email">Email:</label>
                    <input id="email" onChange={(e) => {setEmail(e.target.value)}}
                           className="form-control text-center"  type="email" required/>
                <br/>
                <label htmlFor="password">Password:</label>
                <input id="password" onChange={(e) => setPassword(e.target.value)}
                       className="form-control text-center" type="password" required/>
                <br/>
                <div className="col-sm-8 ml-auto mr-auto">
                    <button type={'submit'} className="btn btn-info btn-block">Register</button>
                </div>
            </div>
        </form>
    );
};
