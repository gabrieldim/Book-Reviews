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
        <div className="col-sm-6">
            <div className="card">
                <div className="card-body">
                    <h5 className="card-title">{props.title}</h5>
                    <p className="card-text">{props.rating}/5 stars ({Math.ceil(props.rating / 5 * 100)})% </p>
                    <p className="card-text">{props.description}</p>
                </div>
                <div className="card-footer">
                    <p className={getSentimentClass(props.sentiment)}>The review was estimated as <b>{props.sentiment}</b></p>
                    <p className=""> Reviewed on {getDateToLocale(props.date)}</p>
                </div>
            </div>
        </div>
    )
}