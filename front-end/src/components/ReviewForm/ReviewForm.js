import React, {useEffect, useState} from 'react';
import {Form} from 'react-bootstrap';
import {toast} from "react-toastify";
import instance from "../../custom-axios/axios";
import RS from "../../service/reviewService";
import {ACCESS_TOKEN} from "../../constants";

export const ReviewForm = () => {

    const [books, setBooks] = useState([]);

    const [title, setTitle] = useState();
    const [description, setDescription] = useState();
    const [rating, setRating] = useState();
    const [bookId, setBookId] = useState();

    const handleSubmit = (e) => {
        e.preventDefault();

        let createReviewRequest = {
            title: title,
            description: description,
            rating: rating,
            bookId: (!bookId && books && books[0]) ? books[0].id : bookId,
            reviewerId: localStorage.getItem(ACCESS_TOKEN),
        };

        RS.createReview(createReviewRequest)
            .then((response) => {
                toast.info("Your review was successfully sent");
                window.alert("Your review was successfully sent, and it was estimated as: " + response.data.sentiment);
                window.location.reload(false);
            }).catch(error => {
            let errorMessage = "There was an error when trying to create a review";
            window.alert(errorMessage);
            toast.info(errorMessage, error.message);
            window.location.reload(false);
        });
    }

    const getAllBooks = () => {
        instance.get("/book").then((result) => {
            setBooks(result.data);
        })
    }

    useEffect(() => {
        getAllBooks();
    }, []);

    return (
        <div className="row p-3 container-lt-blue text-center pt-5 pb-5">
            <div className="col-sm-12">
                <h1 className="bold text-white"> Your opinion matters!</h1>
            </div>
            <div className="d-flex justify-content-center w-100">
                <div className={"container"}>
                    <Form onSubmit={handleSubmit}>
                        <div className={"row mt-5"}>
                            <div className={"col-sm-6 mb-3"}>
                                <Form.Group>
                                    <Form.Select id={"bookId"} className={"form-control"} onChange={(e) => {
                                        setBookId(e.target.value)
                                    }}>
                                        <option value={""} disabled={"disabled"}>Choose the book you want to review</option>
                                        {
                                            books.map(book => {
                                                return <option value={book.id}>{book.title}</option>;
                                            })
                                        }
                                    </Form.Select>
                                </Form.Group>
                            </div>
                            <div className="col-sm-6 mb-3">
                                <Form.Group>
                                    <Form.Control
                                        id="rating" onChange={(e) => {
                                        setRating(e.target.value)
                                    }}
                                        type="number" min={1} max={5} placeholder="Review Rating..."/>
                                </Form.Group>
                            </div>
                        </div>
                        <div className={"row"}>
                            <div className="col-sm-12 mb-3">
                                <Form.Group>
                                    <Form.Control
                                        id="title" onChange={(e) => {
                                        setTitle(e.target.value)
                                    }}
                                        required type="text" placeholder="Review Title..."/>
                                </Form.Group>
                            </div>
                        </div>
                        <div className={"row"}>
                            <div className="col-sm-12 mb-3">
                                <Form.Group>
                                    <Form.Control id="description" onChange={(e) => {
                                        setDescription(e.target.value)
                                    }}
                                                  required as="textarea" rows={5} placeholder={"Review Description"}/>
                                </Form.Group>
                            </div>
                        </div>
                        <div className={"row"}>
                            <div className={"col-sm-12 mt-4"}>
                                <button type={'submit'} className="see-more-button">LEAVE REVIEW</button>
                            </div>
                        </div>

                    </Form>
                </div>
            </div>
        </div>
    )
}