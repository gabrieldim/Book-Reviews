import React from 'react';
import {withRouter} from 'react-router-dom';
import { toast } from 'react-toastify';

const SearchBar = (props) => {
    const handleSubmit=(e)=>{
        e.preventDefault();
        let searchword = document.getElementById("searchword").value;
        if(searchword.length <3){
            toast.error("Searchword must be longer than 2");
        }
        else{
        props.history.push("/search/"+searchword);}
    }
    return (
    <form onSubmit={handleSubmit} className="form-inline my-2 my-lg-0 mx-auto">
      <input className="form-control mr-sm-2" type="search" id="searchword" placeholder="Search books" aria-label="Search"/>
      <button className="btn btn-outline-light my-2 my-sm-0" type="submit">Search</button>
    </form>
    );
};

export default withRouter(SearchBar);