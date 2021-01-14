import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import '../css/Form.css';

class Login extends React.Component {

    state = {
        username: "",
        password: "",
        error: false
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
        if (this.state.username !== "" && this.state.password !== "")
            this.sendData();
    }

    sendData = () => {
        let Authorization = 'Bearer';
        axios({
            'method': 'post',
            'url': 'http://localhost:8080/authenticate',
            'headers': {
                'Content-Type': 'application/json',
                Authorization
            },
            'data': { username: this.state.username, password: this.state.password }
        })
            .then((response) => {
                this.props.login(response.data);
                this.getChannelsForUser();
                this.props.history.push("/items");
            }).catch(error => {
                this.setState({
                    error: true
                })
            });
    }

    getChannelsForUser = () => {
        let Authorization = 'Bearer ' + this.props.auth.auth.token;
        axios({
            'method': 'get',
            'url': 'http://localhost:8080/api/channel',
            'headers': {
                'Content-Type': 'application/json',
                Authorization
            }
        })
            .then((response) => {
                this.props.getChannels(response.data);
            });
    }

    render() {
        let errorDiv = (this.state.error) ?
            <div className="errorDiv">Invalid username or password</div> :
            <div></div>
        return (
            <div>
                {errorDiv}
                <form onSubmit={this.handleSubmit}>
                    <label>Username: </label>
                    <input className="txtinput" type="text" id="username" onChange={this.handleChange} /><br></br>

                    <label>Password: </label>
                    <input className="txtinput" type="password" id="password" onChange={this.handleChange} /><br></br>

                    <input className="submitinput" type="submit" value="Log in" />
                </form>
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
        login: (auth) => { dispatch({ type: 'LOGIN', auth: auth }) },
        getChannels: (newChannels) => { dispatch({ type: 'UPDATE_SIDEBAR', channels: newChannels }) }
    }
}
export default connect(mapStateToProps, mapDispatchToProps)(Login);