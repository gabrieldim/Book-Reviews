import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom'

import {HomePage} from "./components/pages/HomePage";
import {SignupPage} from "./components/pages/SignupPage";
import {LoginPage} from "./components/pages/LoginPage";
import {ProtectedRoute} from "./components/routes/protected.route";
import {NotFoundPage} from "./components/pages/NotFoundPage";
import {BooksPage} from "./components/pages/BooksPage";
import {BookAdditionPage} from "./components/pages/BookAdditionPage";
import {ReviewsPage} from "./components/pages/ReviewsPage";
import {AuthorsPage} from "./components/pages/AuthorsPage";

const App = () => {
    return (
        <div className="app">
          <BrowserRouter>
            <Switch>
              <ProtectedRoute path={"/"} component={HomePage} exact/>
              <Route path={"/login"} component={LoginPage} />
              <Route path={"/signup"} component={SignupPage} />
              <ProtectedRoute path={"/books"} component={BooksPage} />
              <ProtectedRoute path={"/bookAddition"} component={BookAdditionPage} />
              <ProtectedRoute path={"/reviews"} component={ReviewsPage} />
              <ProtectedRoute path={"/authors"} component={AuthorsPage} />
              <Route path={"*"} component={NotFoundPage} />
            </Switch>
          </BrowserRouter>
        </div>
    );
}

export default App;
