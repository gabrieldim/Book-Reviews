import React, {useEffect, useState} from 'react';
import {Redirect, Route} from 'react-router-dom';
import Auth from "../../service/authService";
import {ACCESS_TOKEN} from "../../constants";

export const ProtectedRoute = ({component: Component, ...rest}) => {

    const [user_data, setUserData] = useState({});

    useEffect(() => {
        if (!localStorage.getItem(ACCESS_TOKEN)) {
            Auth.getCurrentUser()
                .then((response) => {
                        setUserData(response.data);
                    },
                    () => {

                    });
        }
    });

    return (
        <Route
            {...rest}
            render={props => {
                if (localStorage.getItem(ACCESS_TOKEN)) {
                    props.user_data = user_data;
                    return <Component {...props}/>;
                } else {
                    return <Redirect to={{
                        pathname: "/login",
                        from: props.location
                    }}/>;
                }
            }
            }/>
    );
}