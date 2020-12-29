import React from 'react';
import { NavLink } from 'react-router-dom';
import '../css/Header.css';
import { connect } from 'react-redux';

class Header extends React.Component {


    onClickLogout = () => {
        console.log(this.props.auth.auth)
        this.props.logout();

        //window.location.reload();
    }

    render() {

        let authButtons;
        if (this.props.auth.auth == null) {
            authButtons = <div id="authbuttons">
                <NavLink to="/login"><button>Login</button></NavLink>
                <NavLink to="/register"><button>Register</button></NavLink>
            </div>
        }
        else
            authButtons = <div id="authbuttons">
                 <NavLink to="/findchannel"><button>Znajdz kanał</button></NavLink>
                <button onClick={this.onClickLogout}>Logout</button>
            </div>
       
            return (
                <div id="header">
                    {authButtons}
                </div>
            );
    }
}

const mapStateToProps = (state) => {
    return {
        auth: state.auth
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        logout: () => { dispatch({ type: 'LOGOUT' }) }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(Header);