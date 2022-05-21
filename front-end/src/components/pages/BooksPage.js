import React, {useEffect, useState} from 'react';
import {Header} from '../Header/Header';
import instance from "../../custom-axios/axios";
import {BookSnippet} from "../BookSnippet/BookSnippet";

export const BooksPage = () => {

    const [books, setBooks] = useState([]);

    const getAllBooks = () => {
        instance.get("/book").then((result) => {
            setBooks(result.data);
        })
    }

    useEffect(() => {
        getAllBooks();
    }, []);

    return (
        <div className="container-fluid">
            <Header/>
            {
                books.map(book => {
                   return <BookSnippet key={book.id} {...book } />;
                })
            }
        </div>
    )
}