import React, {useEffect, useState} from 'react';
import {Header} from '../Header/Header';
import instance from "../../custom-axios/axios";
import {BookSnippet} from "../BookSnippet/BookSnippet";
import {ACCESS_ROLE} from "../../constants";
import {Link} from "react-router-dom";

export const BooksPage = (props) => {

    const [books, setBooks] = useState([]);

    const getAllBooks = () => {
        instance.get("/book").then((result) => {
            setBooks(result.data);
        })
    }

    const getFilteredBooks = (filterTerm, filterType) => {
        if (filterType === "search") {
            instance.get("/book/search", {
                params: {
                    searchTerm: filterTerm
                }
            }).then((result) => {
                setBooks(result.data);
            });
        } else if (filterType === "genre") {
            instance.get("/book/genre", {
                params: {
                    genre: filterTerm
                }
            }).then((result) => {
                setBooks(result.data);
            });
        }
    }

    useEffect(() => {
        let searchTerm = props.location.search;

        if (searchTerm.includes("search")) {
            getFilteredBooks(searchTerm.replaceAll("?search=", ""), "search");
        } else if (searchTerm.includes("genre")) {
            getFilteredBooks(searchTerm.replaceAll("?genre=", ""), "genre");
        } else {
            getAllBooks();
        }
    }, []);


    return (
        <div className="container-fluid">
            <Header/>
            {
                (localStorage.getItem(ACCESS_ROLE) === "author") ? (
                    <div className={"row mb-3"}>
                        <div className={"col-sm-12 mt-4"}>
                            <Link to={"/bookAddition"}>
                                <button type={'button'} className="see-more-button">ADD NEW BOOK</button>
                            </Link>
                        </div>
                    </div>
                ) : null
            }


            <div className="row align-content-center">
                {
                    books.map(book => {
                        return <BookSnippet key={book.id} {...book} />;
                    })
                }
            </div>

        </div>
    )
}