import React, {useEffect, useState} from 'react';
import {Header} from '../Header/Header';
import instance from "../../custom-axios/axios";
import {ReviewSnippet} from "../ReviewSnippet/ReviewSnippet";

export const ReviewsPage = () => {

    const [reviews, setReviews] = useState([]);

    const getAllReviews = () => {
        instance.get("/review").then((result) => {
            setReviews(result.data)
        })
    }

    useEffect(() => {
        getAllReviews();
    }, []);

    return (
        <div className="container-fluid">
            <Header/>
            {
                <div className="row mt-3">{
                    reviews.map(review => {
                        return <ReviewSnippet key={review.id}{...review} />;
                    })
                }
                </div>
            }
        </div>
    )
}