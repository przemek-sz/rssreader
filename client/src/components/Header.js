import React from 'react';
import logoRSS from '../assets/logoRSS.png';
import Logo2 from '../assets/Logo2.png';
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
        let userlist;
        if (this.props.auth.auth == null) {
            authButtons = <div className="authbuttons">
                <NavLink to="/login"><button className="headerbutton">Log in</button></NavLink>
                <NavLink to="/register"><button className="headerbutton">Sign up</button></NavLink>
            </div>
        }
        else {
            if (typeof (this.props.auth.auth.roles.find(e => e === "ROLE_ADMIN")) !== 'undefined') {
                userlist = <NavLink to="/userlist"><button className="headerbutton">List</button></NavLink>
            }

            authButtons =
                <div>
                    <NavLink to="/findchannel"><button id="addbutton"><span>+</span> Dodaj kanał</button></NavLink>
                    <div className="authbuttons">
                        <NavLink to="/usersettings" id="settingsButton">&#x2699;</NavLink>
                        {userlist}
                        <a href="http://localhost:3000/"><button className="headerbutton" onClick={this.onClickLogout}>Logout</button></a>
                    </div>
                </div>
        }


        return (
            <div id="header">
                <NavLink to="/">
                    <img id='logo' src={Logo2} alt='logo'></img>
                </NavLink>
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