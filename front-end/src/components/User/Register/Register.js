import React from 'react';
import Auth from "../../../service/authService"
import {ACCESS_TOKEN} from "../../../constants/index"
import {toast, ToastPosition} from 'react-toastify'
import {withRouter} from 'react-router-dom'

const Register = (props) => {
    const handleSubmit = (e)=>{
        e.preventDefault();
        let name=document.getElementById("name");
        let username=document.getElementById("username");
        let password=document.getElementById("password");
        let email=document.getElementById("email");
        let msgContents=""
        if (name.value.length<=3 || username.value.length<=3 ){
            msgContents+= "Username and name length must not be less than 3. "
        }
        if (password.value.length < 8 || password.value==="")
            msgContents+="Password must not be empty and be less than 8."
        if (msgContents!==""){
            toast.error(msgContents,{autoClose:4000});
        }
        else{
            
        let signUpRequest={
            name:name.value,
            username:username.value,
            email:email.value,
            password: password.value
        };
        Auth.register(signUpRequest)
            .then((response)=>{
                toast.success("You've successfully registered!")
                props.history.push("/signin")
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
                    <input id="name" className="form-control text-center" type="text" required/>
                    <br/>
                    <label htmlFor="name">Username:</label>
                    <input id="username" className="form-control text-center" type="text" required/>
                    <br/>
                    <label htmlFor="email">Email:</label>
                    <input id="email" className="form-control text-center"  type="email" required/>
                <br/>
                <label htmlFor="password">Password:</label>
                <input id="password" className="form-control text-center" type="password" required/>
                <br/>
                <div className="col-sm-8 ml-auto mr-auto">
                    <button type={'submit'} className="btn btn-info btn-block">Register</button>
                </div>
            </div>
        </form>
    );
};

export default withRouter(Register);