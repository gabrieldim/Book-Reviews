import React from 'react';
import './Footer.css';
import {Link} from "react-router-dom";

export const Footer = () => {

    return(
        <div className="row p-4 footer">
            <div>
                <Link to={"#"}> Terms & Support </Link>
            </div>
        </div>
    )
}