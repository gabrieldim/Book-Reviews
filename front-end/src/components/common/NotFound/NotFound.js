import React from 'react';
import {Link} from 'react-router-dom';
import './NotFound.css'

const NotFound = (props) => {
    return (
        <div className="col-sm-4 ml-auto mr-auto p-5 mt-5" >
             <h1 className="title text-danger d-block mr-auto ml-auto text-center">
                    404
                </h1>
                <br/>
                <div className="desc text-danger d-block mx-auto">
                    The page you're looking for was not found.
                </div>
                <br/>
                <Link to="/"><button className="btn btn-block btn-large btn-warning d-block mx-auto">Go back</button></Link>
        </div>
    );
};

export default NotFound;