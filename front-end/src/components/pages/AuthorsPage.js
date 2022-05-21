import React, {useEffect, useState} from 'react';
import {Header} from '../Header/Header';
import instance from "../../custom-axios/axios";
import {AuthorSnippet} from "../AuthorSnippet/AuthorSnippet";

export const AuthorsPage = () => {

    const [authors, setAuthors] = useState([]);

    const getAllAuthors = () => {
        instance.get("/author").then((result) => {
            setAuthors(result.data);
        })
    }

   useEffect(() => {
       getAllAuthors();
   }, []);

    return (
        <div>
            <div className="container-fluid">
                <Header />
                {
                    authors.map(author => {
                        return <AuthorSnippet key={author.id} {...author} />
                    })
                }
            </div>
        </div>
    )
}