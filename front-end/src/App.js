import React, { Component } from 'react';
import {BrowserRouter as Router, Route, Redirect} from 'react-router-dom'
import Auth from "./service/authService"
import {ACCESS_TOKEN} from "./constants/index"
import {toast, ToastPosition} from 'react-toastify'
import Header from "./components/Header/Header"
import Login from "./components/User/Login/Login"
import bg from './images/Wallpaper.webp';

import Register from "./components/User/Register/Register"
import LoadingIndicator from "./components/common/LoadingIndicator/LoadingIndicator"



class App extends Component {
  
  constructor(props){
    toast.configure({position:ToastPosition.TOP_CENTER,autoClose:3000});
    super(props);
    this.state={
      currentUser: null,
      isAuthenticated: false,
      isLoading: false,
      genres:[]
    };
    this.handleLogout = this.handleLogout.bind(this);
    this.loadCurrentUser = this.loadCurrentUser.bind(this);
    this.handleLogin = this.handleLogin.bind(this);
   
  }
  loadCurrentUser = () => {
    this.setState({
      isLoading: true
    });
    Auth.getCurrentUser()
        .then(response => {
            this.setState({
                currentUser: response.data,
                isAuthenticated: true,
                isLoading:false
            });
        }).catch(error => {
          this.setState({
            isLoading: false
          });
    });
  };
  handleLogin = (request) => {
    toast.success("You're successfully logged in.")
    this.loadCurrentUser();
  };
  handleLogout = () => {
    localStorage.removeItem(ACCESS_TOKEN);
    toast.success("You've successfully logged out.")
    this.setState({
        currentUser: null,
        isAuthenticated: false
    });
  };

  componentDidMount(){
    this.loadCurrentUser();

   
  }
  render() {
    if(this.state.isLoading){
      return <LoadingIndicator/>
    }
    let loginUser=[];
    if(!this.state.isAuthenticated)
      loginUser=<Login onLogin={this.handleLogin} getUser={this.loadCurrentUser}/>
    else
      loginUser=<Redirect to="/"/>
    return (
      <Router>
        <div className="App" style={  {minHeight:'100vh', width:'100%',padding:'0',
          backgroundPosition: 'center center',backgroundSize: 'cover',backgroundAttachment: 'fixed', backgroundImage: `url(${bg})`}}>
          <Header genreNames={this.state.genres} isAuthenticated={this.state.isAuthenticated} currentUser={this.state.currentUser} onLogout={this.handleLogout}/>
          <div className="container">
            <Route path={"/signin"} exact>
              {loginUser}
            </Route>
            <Route path={"/signup"} exact>
              <Register></Register>
            </Route>
          </div>
        </div>
        </Router>
    );
  }
}

export default App;