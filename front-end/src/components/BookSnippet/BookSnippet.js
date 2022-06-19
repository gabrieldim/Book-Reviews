import React from 'react';
import './BookSnippet.css';

export const BookSnippet = (props) => {

    return (
        <div className="book-snippet text-center p-5">
            <img src={props.pictureLink} className="book-cover" alt="A book cover"/>
            <h4 className="text-center mt-3"> {props.title}</h4>
            <p> Genre: {props.genre} </p>
            <blockquote> "{props.quote}" </blockquote>
            <p> {props.description} </p>
        </div>
    );
}