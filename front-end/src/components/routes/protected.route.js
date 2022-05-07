import React, {useEffect, useState} from 'react';
import {Route, Redirect} from 'react-router-dom';
import Auth from "../../service/authService";

export const ProtectedRoute = ({component: Component, ...rest}) => {

    const [is_auth, setAuth] = useState(false);
    const [user_data, setUserData] = useState({});

    useEffect(() => {
        Auth.getCurrentUser()
            .then((response) => {
                setAuth(true);
                setUserData(response.data);
            });
    });

    return (
        <Route
            {...rest}
            render={props => {
                if(is_auth) {
                    return <Component {...props}/>;
                }
                else{
                    return <Redirect to={{
                        pathname: "/login",
                        from: props.location
                    }}/>;
                }
            }
        }/>
    );
}