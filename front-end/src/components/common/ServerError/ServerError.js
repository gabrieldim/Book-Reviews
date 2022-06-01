import React from 'react';
import {Link} from 'react-router-dom'
import "./ServerError.css"

export const ServerError = () => {
    return (
        <div className="col-sm-4 ml-auto mr-auto p-5 mt-5" >
             <h1 className="title text-danger d-block mr-auto ml-auto text-center">
                    500
                </h1>
                <br/>
                <div className="desc text-danger d-block mx-auto">
                   Ooops. There's something wrong with the server. Try again later!
                </div>
                <br/>
                <Link to="/"><button className="btn btn-block btn-large btn-warning d-block mx-auto">Go back</button></Link>
        </div>
    );
};