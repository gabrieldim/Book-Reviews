import React, {useState} from 'react';
import {toast} from 'react-toastify';
import './SearchBar.css';
import {useHistory} from "react-router-dom";

export const SearchBar = () => {

    const history = useHistory();
    const [search_phrase, setSearchPhrase] = useState(0);

    const handleChange = (e) => {
        setSearchPhrase(e.target.value);
    }

    const submitSearch = () => {
        if (search_phrase.length < 3) {
            toast.error("Search phrase must be longer than 2");
        } else {
            history.push("/books?search=" + search_phrase);
        }
    }

    return (
        <div className="input-group w-50 m-auto">
            <input className="form-control book-search" type="search" placeholder="Search books" aria-label="Search"
                   onChange={(e) => handleChange(e)}
                   onKeyDown={(e) => (e.key === "Enter") ? submitSearch() : null}/>
            <button className="btn btn-light" onClick={() => submitSearch()}>Search</button>
        </div>
    );
};

