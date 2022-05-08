import React from 'react';
import {Link} from 'react-router-dom';
import '../../style/NotFound.css'

export const NotFoundPage = () => {
    return (
        <div className="container-fluid">
            <div className="row">
                <div className="col-sm-12 text-center">
                    <h1 className="text-danger">
                        404
                    </h1>
                    <p className="desc text-danger">
                        The page you're looking for was not found.
                    </p>
                    <Link to="/">
                        <button className="btn btn-block btn-large btn-warning w-25 m-auto">Go back</button>
                    </Link>
                </div>
            </div>
        </div>
    );
};