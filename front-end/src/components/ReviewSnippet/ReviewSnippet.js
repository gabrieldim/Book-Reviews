import React from "react";
import "./ReviewSnippet.css";

export const ReviewSnippet = (props) => {

    const getDateToLocale = (dateStr) => {
        return new Date(dateStr).toLocaleDateString();
    }

    const getSentimentClass = (sentiment) => {
        return (sentiment.toUpperCase() === 'POSITIVE') ? 'text-success' : 'text-danger';
    }

    return (
        <div className="review-snippet col-sm-12 mt-3">
            <h4> {props.title} </h4>
            <p> {props.rating}/5 stars ({Math.ceil(props.rating/5 * 100)})% </p>
            <p> {props.description}</p>
            <p className={getSentimentClass(props.sentiment)}>The review was estimated as <b>{props.sentiment}</b></p>
            <p className=""> Reviewed on {getDateToLocale(props.date)}</p>
        </div>
    )
}