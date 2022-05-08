import React from 'react';
import {Link} from 'react-router-dom'
import {SearchBar} from '../Search/SearchBar'
import "./Header.css"

export const Header = (props) => {

    return (
        <nav className="row navbar navbar-expand-lg app-navigation">
            <Link to={"/"} className="navbar-brand text-brown">Book Reviews</Link>

            <SearchBar/>
        </nav>
    );
};
