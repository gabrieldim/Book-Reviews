import React from "react";
import "./AuthorSnippet.css";

export const AuthorSnippet = (props) => {

    const getReviewsCount = (props) => {
        let count = 0;
        props.books.forEach( book => {
            count += book.reviews.length;
        });

        return count;
    }

    return (
        <div className="author-snippet col-sm-12 d-flex justify-content-between">
            <div>
                <h4 className="author-name"> { props.firstName + " " + props.lastName}  - {props.artName}</h4>
                <p> {props.age} years old</p>
                <p> Authored on { props.books.length } books ( {getReviewsCount(props) } reviews )</p>
                <p> Available at { props.email }</p>
            </div>
            <div className="author-titles">
                <h5> Latest titles</h5>
                <ul> {props.books.map(book => {
                        return <li key={book.id}> {book.title} </li>;
                    })}
                </ul>
            </div>
        </div>
    );
}