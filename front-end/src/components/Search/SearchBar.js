import React, {useState} from 'react';
import { toast } from 'react-toastify';

export const SearchBar = (props) => {

    const [search_phrase, setSearchPhrase] = useState(0);

    const handleChange = (e) => {
        setSearchPhrase(e.target.value);
    }

    const submitSearch = () => {
        if(search_phrase.length <3){
            toast.error("Search phrase must be longer than 2");
        }
        else{
            props.history.push("/search/"+search_phrase);
        }
    }
    return (
        <div className="input-group w-50 m-auto">
          <input className="form-control" type="search" placeholder="Search books" aria-label="Search"
                 onChange={(e) => handleChange(e)} />
          <button className="btn btn-outline-light" onClick={() => submitSearch()}>Search</button>
        </div>
    );
};

