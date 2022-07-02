import React from "react";
import "./AuthorSnippet.css";

export const AuthorSnippet = (props) => {

    const getReviewsCount = (props) => {
        let count = 0;
        props.books.forEach(book => {
            count += book.reviews.length;
        });

        return count;
    }

    return (
        <div className="author-snippet col-sm-12 d-flex justify-content-between">
            <div className="card col-md-6" >
                <div className="card-body">
                    <h5 className="card-title">{props.firstName + " " + props.lastName} - {props.artName}</h5>
                    <p className="card-text">{props.age} years old</p>
                    <p className="card-text">Authored on {props.books.length} books ( {getReviewsCount(props)} reviews )</p>
                    <p className="card-text">Available on <i>{props.email}</i></p>
                </div>
            </div>
            <div class="card col-md-6">
                <div class="card-header">
                    Latest Titles
                </div>
                <ul class="list-group list-group-flush ml-3">{props.books.map(book => {
                    return <li key={book.id}> {book.title} </li>;
                })}
                </ul>
            </div>
        </div>


    );
}