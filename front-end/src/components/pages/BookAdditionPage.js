import React, {useEffect, useState} from 'react';
import {toast} from "react-toastify";
import {Form} from "react-bootstrap";
import {useHistory} from "react-router-dom";
import {Header} from "../Header/Header";
import {ACCESS_ROLE, ACCESS_TOKEN} from "../../constants";
import BS from "../../service/bookService";
import instance from "../../custom-axios/axios";

export const BookAdditionPage = () => {
    const history = useHistory();

    const [libraries, setLibraries] = useState([]);

    const [title, setTitle] = useState("");
    const [description, setDescription] = useState("");
    const [genre, setGenre] = useState("");
    const [pictureLink, setPictureLink] = useState("");
    const [quote, setQuote] = useState("");
    const [availability, setAvailability] = useState(true);
    const [libraryId, setLibraryId] = useState();

    const handleSubmit = (e) => {
        e.preventDefault();

        if (title && description && genre && pictureLink && quote) {
            let createBookRequest = {
                title: title,
                description: description,
                genre: genre,
                pictureLink: pictureLink,
                quote: quote,
                availability: availability,
                authorId: localStorage.getItem(ACCESS_TOKEN),
                libraryId: libraryId,
            };

            BS.createBook(createBookRequest)
                .then(() => {
                    const message = "You've successfully created a new book!";
                    toast.success(message);
                    alert(message);
                    history.push("/");
                }).catch(error => {
                const message = "There was an error when trying to create a new book! " + error;
                toast.error(message);
                alert(message);
            });
        }
    }

    const getAllLibraries = () => {
        instance.get("/library").then((result) => {
            setLibraries(result.data);
        })
    }

    useEffect(() => {
        getAllLibraries();
    }, []);

    return (localStorage.getItem(ACCESS_ROLE) === "author") ? (
        <div>
            <div className={"container-fluid"}>
                <Header/>

                <h2 className={"text-center mt-5"} style={{color: "#1a4789"}}>CREATE A NEW BOOK</h2>
                <form onSubmit={handleSubmit}>
                    <div className="col-sm-4 ml-auto mr-auto p-5 mt-5 "
                         style={{backgroundColor: '#4fb9b451', border: '0.1em solid #20B2AA', borderRadius: '0.2em'}}>
                        <label htmlFor="title">Title:</label>
                        <input id="title" onChange={(e) => {
                            setTitle(e.target.value)
                        }}
                               className="form-control text-center" type="text" required/>
                        <br/>
                        <label htmlFor="description">Description:</label>
                        <textarea id="description" onChange={(e) => {
                            setDescription(e.target.value)
                        }}
                                  className="form-control text-center" rows={4} required/>
                        <br/>
                        <label htmlFor="genre">Genre:</label>
                        <input id="genre" onChange={(e) => setGenre(e.target.value)}
                               className="form-control text-center" type="text" required/>
                        <br/>
                        <label htmlFor="pictureLink">Picture URL:</label>
                        <input id="genre" onChange={(e) => setPictureLink(e.target.value)}
                               className="form-control text-center" type="text" required/>
                        <br/>
                        <label htmlFor="quote">Quote:</label>
                        <textarea id="quote" onChange={(e) => {
                            setQuote(e.target.value)
                        }}
                                  className="form-control text-center" rows={2} required/>
                        <br/>
                        <label htmlFor="availability">Availability:</label>
                        <Form.Select className={"form-control"} id="availability" size="lg"
                                     onChange={(e) => setAvailability(JSON.parse(e.target.value))}>
                            <option value="true"> Available</option>
                            <option value="false"> Not Available</option>
                        </Form.Select>
                        <br/>
                        <label htmlFor="library">Library:</label>
                        <Form.Select id={"library"} className={"form-control"} onChange={(e) => {
                            setLibraryId(e.target.value)
                        }}>
                            <option value={""}>[No library selected]</option>
                            {
                                libraries.map(library => {
                                    return <option value={library.id}>{library.name}</option>;
                                })
                            }
                        </Form.Select>
                        <br/>

                        <button type={'submit'} className="btn btn-info btn-block mt-4">Add a New Book</button>
                    </div>
                </form>
            </div>
        </div>
    ) : null;
}