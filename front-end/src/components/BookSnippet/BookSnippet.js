import React from 'react';
import './BookSnippet.css';

export const BookSnippet = (props) => {

    return (
        <div className="book-snippet text-center p-5">
            <img src={props.book_cover} className="book-cover" alt="book cover"/>
            <h4 className="text-center"> {props.title}</h4>
            <p> Genre: {props.genre} </p>
            <p> "{props.quote}" </p>
            <p> {props.description} </p>

        </div>
    );
}