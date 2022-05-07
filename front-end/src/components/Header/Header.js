import React from 'react';
import {Link, withRouter} from 'react-router-dom'
import SearchBar from '../Search/SearchBar'
import "./Header.css"

export const Header = (props) => {
    const handleLogout = () => {
        props.history.push('/');
        props.onLogout();
    };
    let menuItems = []

    if (props.currentUser) {
        menuItems = [

            <ul className="navbar-nav ml-auto">
                <li className="nav-item dropdown">
            <span className="nav-link dropdown-toggle d-block mr-5 text-brown" href="#" id="navbarDropdown"
                  role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            {props.currentUser.username}
            </span>
                    <div className="dropdown-menu dropdown-menu-right" aria-labelledby="navbarDropdown">
                        <div className="dropdown-divider"></div>
                        <Link to={"#"} className="nav-link text-dark" onClick={handleLogout}>Logout</Link>
                    </div>
                </li>
            </ul>
        ];
    } else {
        menuItems = [
            <ul className="navbar-nav ml-auto">
                <li className="nav-item">
                    <Link to={"/signin"} className="nav-link text-brown">Login</Link>
                </li>
                <li className="nav-item">
                    <Link to={"/signup"} className="nav-link text-brown">Register</Link>
                </li>
            </ul>
        ];
    }


    return (
        <nav className="navbar navbar-expand-lg app-navigation">
            <Link to={"/"} className="navbar-brand text-brown">Book Reviews</Link>
            {props.currentUser !== null && props.currentUser.roleName === "ROLE_ADMIN" ?
                <span className="nav-link text-brown">
            <Link to={"/books/create"} className="text-brown"><b>Add book</b></Link>
            </span> : ""}

            <button className="navbar-toggler" data-toggle="collapse" data-target="#navbarSupportedContent"
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span className="navbar-toggler-icon"></span>
            </button>

            <div className="collapse navbar-collapse" id="navbarSupportedContent">
                <ul className="navbar-nav mr-auto">
                </ul>
                <SearchBar/>
                {menuItems}
            </div>
        </nav>
    );
};
