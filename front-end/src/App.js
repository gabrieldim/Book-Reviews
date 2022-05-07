import React from 'react';
import {BrowserRouter, Switch, Route} from 'react-router-dom'

import {HomePage} from "./components/pages/HomePage";
import {SignupPage} from "./components/pages/SignupPage";
import {LoginPage} from "./components/pages/LoginPage";
import {ProtectedRoute} from "./components/routes/protected.route";
import {NotFoundPage} from "./components/pages/NotFoundPage";

const App = () => {
    return (
        <div className="app">
          <BrowserRouter>
            <Switch>
              <ProtectedRoute path={"/"} component={HomePage} exact/>
              <Route path={"/login"} component={LoginPage} />
              <Route path={"/signup"} component={SignupPage} />
              <Route path={"*"} component={NotFoundPage} />
            </Switch>
          </BrowserRouter>
        </div>
    );
}

export default App;
