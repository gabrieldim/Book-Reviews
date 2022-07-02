import React, {useEffect, useState} from 'react';
import {Header} from '../Header/Header';
import {BookSnippet} from '../BookSnippet/BookSnippet';
import './HomePage.css';
import BookShell from '../../images/BookShell.jpg';
import {Footer} from "../Footer/Footer";
import GetInTouch from '../../images/GetInTouch.jpg';
import StockMan from "../../images/StockMan.jpg";
import {ReviewForm} from "../ReviewForm/ReviewForm"
import {SearchBar} from "../Search/SearchBar";
import {Link, useHistory} from "react-router-dom";
import {ACCESS_ROLE, GENRE_LIST} from "../../constants";
import instance from "../../custom-axios/axios";

export const HomePage = () => {

    const history = useHistory();

    const [mustReadBooks, setMustReadBooks] = useState([]);
    const [highestRatedBook, setHighestRatedBook] = useState([]);
    const [trendingAuthor, setTrendingAuthor] = useState([]);

    const getMustReadBooks = () => {
        instance.get("/book/page", {
            params: {
                page: 0,
                size: 3
            }
        }).then((result) => {
            setMustReadBooks(result.data.content);
        })
    }

    const getHighestRatedBook = () => {
        instance.get("/review/page", {
            params: {
                "page": 0,
                "size": 1,
                "sort": "rating,sentiment,date,desc"
            }
        }).then((result) => {
            let bestUpToDateReview = result.data.content[0];
            instance.get("/book/" + bestUpToDateReview.bookId).then((result) => {
                setHighestRatedBook(result.data);
            })
        })
    }

    const getTrendingAuthor = () => {
        instance.get("/author/page", {
            params: {
                "page": 0,
                "size": 1,
                "sort": "numberOfBooks,birthDate,desc"
            }
        }).then((result) => {
            setTrendingAuthor(result.data.content[0]);
        })
    }

    useEffect(() => {
        getMustReadBooks();
        getHighestRatedBook();
        getTrendingAuthor();
    }, []);

    const searchByGenre = (genre) => {
        if (genre && genre.length > 0) {
            history.push("/books?genre=" + genre);
        }
    }

    return (
        <div className="container-fluid">
            <Header/>
            <div className="row p-3 container-1 align-items-center">
                <div className="col-sm-5 mt-3">
                    <img src={BookShell} alt="BookShell" className="book-shell"/>
                </div>
                <div className="col-sm-7 mt-3">
                    <div className="container-intro" >
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

            <div className="row text-center align-items-center">
                <div className="col-sm-12 ">
                    <h1 className="bold lt-blue"> Must read books</h1>
                </div>
                <div className="row">
                    {
                        mustReadBooks.map((book) => {
                            return <BookSnippet key={book.id} {...book}/>;
                        })
                    }
                </div>
                <div className="col-sm-12 mb-3">
                    <Link to={"/books"}>
                        <button className="see-more-button">
                            See More
                        </button>
                    </Link>
                </div>
            </div>

            <div className="row p-3 container-lt-blue align-items-center">
                <div className="col-sm-8">
                    <h1 className="lt-green bold"> The most trending book</h1>
                    <h3> {highestRatedBook.title}</h3>
                    <h5> {highestRatedBook.genre}</h5>
                    <blockquote> {highestRatedBook.quote}</blockquote>
                    <p> {highestRatedBook.description}</p>
                </div>
                <div className="col-sm-4">
                    <img src={highestRatedBook.pictureLink} alt="highest rated book"/>
                </div>
            </div>

            <div className="row p-3 container-lt-green align-items-center">
                <div className="col-sm-4">
                    <img src={StockMan} alt="stock guy"/>
                </div>
                <div className="col-sm-8">
                    <h1 className="lt-blue bold mb-3"> Author of the month</h1>
                    <h2 className={"mb-3"}>
                        {trendingAuthor.firstName} {trendingAuthor.lastName} <b>({trendingAuthor.artName})</b>
                    </h2>
                    <h5 className={"mb-3"}>
                        {trendingAuthor.numberOfBooks} books authored
                    </h5>
                    <p>
                        Biography: {trendingAuthor.bioDescription}
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
                            {
                                GENRE_LIST.map((genre) => {
                                    return <li onClick={() => searchByGenre(genre)}>{genre}</li>;
                                })
                            }
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