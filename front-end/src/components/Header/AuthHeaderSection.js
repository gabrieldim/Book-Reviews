import React from 'react';
import {ACCESS_TOKEN} from "../../constants";
import {Link} from "react-router-dom";

export const AuthHeaderSection = () => {
    const signOut = () => {
        localStorage.setItem(ACCESS_TOKEN, "");
        window.location.reload(false);
    }

    if (localStorage.getItem(ACCESS_TOKEN)) {
        let username = localStorage.getItem("userEmail");
        return (
            <div className="mt-3 pr-2">
                <Link to={"/"} className="navbar-element"> Welcome, {username}</Link>
                <Link to={"/"} className="navbar-element " onClick={signOut}> Sign out</Link>
            </div>
        );
    } else {
        return (
            <div className="mt-3 pr-2">
                <Link to={"/login"} className="navbar-element"> Sign in</Link>
                <Link to={"/signup"} className="navbar-element"> Sign up</Link>
            </div>
        );
    }
}