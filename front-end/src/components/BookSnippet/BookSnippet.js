import React from 'react';
import './BookSnippet.css';

export const BookSnippet = (props) => {

    return (
        <div className="card col-md-4 mb-3">
            <img className="card-img-top" src={props.pictureLink} style={{height:50+"%"}}alt="Card image cap"/>
                <div className="card-body">
                    <h5 className="card-title">{props.title}</h5>
                </div>

                <div className="card-body">
                    <p className="card-text">{props.genre}</p>
                    <p> {props.description} </p>
                </div>
            <div className="card-footer">
                <blockquote> "{props.quote}" </blockquote>
            </div>

        </div>
    );


}