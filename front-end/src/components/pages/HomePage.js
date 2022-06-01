import React from 'react';
import {Header} from '../Header/Header';
import {BookSnippet} from '../BookSnippet/BookSnippet';
import './HomePage.css';
import BookShell from '../../images/BookShell.jpg';
import {Footer} from "../Footer/Footer";
import GetInTouch from '../../images/GetInTouch.jpg';
import StockMan from "../../images/StockMan.jpg";
import BeyondOrder from "../../images/BeyondOrder.png";
import SinEater from '../../images/SinEater.jpg';
import {ReviewForm} from "../ReviewForm/ReviewForm"
import {SearchBar} from "../Search/SearchBar";
import {Link} from "react-router-dom";
import {ACCESS_ROLE} from "../../constants";

export const HomePage = () => {

    const book_props = {
        book_cover: SinEater,
        book_description: "It is a long established fact that a reader will be distracted by the readable content",
        book_title: "Sin Eater"
    };

    const getMustReadBooks = () => {
        return [
            book_props,
            book_props,
            book_props
        ]
    };

    return (
        <div className="container-fluid">
            <Header/>
            <div className="row p-3 container-1">
                <div className="col-sm-6 mt-3">
                    <img src={BookShell} alt="BookShell" className="book-shell"/>
                </div>
                <div className="col-sm-6 mt-3">
                    <div className="container-intro">
                        <h1 className="bold"> Find the joy you seek, one day at a time.</h1>
                        <p>
                            If you're thinking what to read next then you’re in the right place.
                            Tell us what titles or genres you’ve enjoyed in the past,
                            and we’ll give you surprisingly insightful recommendations.
                        </p>
                        <Link to={"/signup"} className="navbar-element">
                            <button className="not-a-member"> NOT A MEMBER? <br/> SIGN UP</button>
                        </Link>
                    </div>
                </div>
            </div>

            <div className="row p-3 container-2 text-center">
                <div className="col-sm-12 ">
                    <h1 className="bold lt-blue"> Must read books</h1>
                </div>
                <div className="col-sm-12 mt-5 ">
                    {
                        getMustReadBooks().map((book_prop) => {
                            return <BookSnippet key={book_prop.id} {...book_prop}/>;
                        })
                    }
                </div>
                <div className="col-sm-12">
                    <button className="see-more-button "> SEE MORE</button>
                </div>
            </div>

            <div className="row p-3 container-lt-blue">
                <div className="col-sm-6">
                    <h1 className="bold"> Book of the month</h1>
                    <p> Beyond Order is the follow-up to Jordan Peterson's bestselling book 12 Rules for Life and
                        identifies another
                        12 rules to live by that help us live with and even embrace the chaos that we struggle with
                        every day,
                        identifying that too much order can be a problem just as much as too much disorder
                    </p>
                </div>
                <div className="col-sm-4">
                    <img src={BeyondOrder} alt="beyond order"/>
                </div>
            </div>

            <div className="row p-3 container-lt-green">
                <div className="col-sm-4">
                    <img src={StockMan} alt="stock guy"/>
                </div>
                <div className="col-sm-4">
                    <h1 className="lt-blue bold"> Author of the month</h1>
                    <p> Slavoj Žižek is a Slovenian philosopher, cultural critic and psychoanalytic researcher at the
                        Department of Philosophy of the University of Ljubljana Faculty of Arts, and international
                        director of the Birkbeck Institute for the Humanities of the University of London.
                    </p>
                </div>
            </div>
            <div className="row p-3 text-center">
                <div className="col-sm-12">
                    <h1 className="bold"> Search and browse books</h1>
                </div>
                <div className="col-sm-12">
                    <SearchBar/>
                    <div className="d-flex justify-content-center category-container">
                        <ul>
                            <li> Art</li>
                            <li> Biography</li>
                            <li> Business</li>
                            <li> Children's</li>
                            <li> Romance</li>
                        </ul>

                        <ul>
                            <li> Self Help</li>
                            <li> Sports</li>
                            <li> Thriller</li>
                            <li> Travel</li>
                            <li> Science</li>
                        </ul>

                        <ul>
                            <li> Fiction</li>
                            <li> Novels</li>
                            <li> Historical Fiction</li>
                            <li> History</li>
                            <li> Fantasy</li>
                        </ul>
                    </div>
                </div>
            </div>
            {localStorage.getItem(ACCESS_ROLE) === "reviewer" ? <ReviewForm/> : null}

            <div className="row p-3 container-lt-green">
                <div className="col-sm-6">
                    <img src={GetInTouch} alt="get in touch"/>
                </div>
                <div className="col-sm-6 -align-center">
                    <h1 className="bold"> Get in touch with us!</h1>

                    <h2 className="bold"> Spread the word</h2>
                </div>
            </div>
            <Footer/>
        </div>
    )
};