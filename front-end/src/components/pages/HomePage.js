import React  from 'react';
import {Header} from '../Header/Header';
import {BookSnippet} from '../BookSnippet/BookSnippet';

export const HomePage = (props) => {


    return (
        <div className="container-fluid">
            <Header />
            <div className="row p-3">
                <div className="col-sm-2">
                   <BookSnippet />
                </div>
                <div className="col-sm-2">
                    <BookSnippet />
                </div>
                <div className="col-sm-2">
                    <BookSnippet />
                </div>
                <div className="col-sm-2">
                    <BookSnippet />
                </div>
                <div className="col-sm-2">
                    <BookSnippet />
                </div>
                <div className="col-sm-2">
                    <BookSnippet />
                </div>
            </div>
        </div>
    )
};