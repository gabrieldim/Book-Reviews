import React from 'react';
import {
    Route,
    Redirect,
    withRouter
  } from "react-router-dom";
  
  
const PrivateRoute = ({ component: Component, authenticated, ...rest }) => (
    <Route
      {...rest}
      render={props =>
        authenticated ? (
          <Component {...rest} {...props} />
        ) : (
          <Redirect
            to={{
              pathname: '/signin',
              state: { from: props.location }
            }}
          />
        )
      }
    />
);
  
export default withRouter(PrivateRoute);