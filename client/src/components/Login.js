import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import '../css/Form.css';

class Login extends React.Component {

    state = {
        username: null,
        password: null
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
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
            'data': this.state
        })
            .then((response) => {
                this.props.login(response.data);
                this.getChannelsForUser();
                this.props.history.push("/items");
            });
    }

    getChannelsForUser = () =>{
        let Authorization = 'Bearer '+this.props.auth.auth.token;
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
        return (
            <form onSubmit={this.handleSubmit}>
                <label>Username: </label>
                <input className="txtinput" type="text" id="username" onChange={this.handleChange} /><br></br>

                <label>Password: </label>
                <input className="txtinput" type="text" id="password" onChange={this.handleChange} /><br></br>

                <input className="submitinput" type="submit" value="Zaloguj" />
            </form>
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
        login: (auth) => {dispatch({ type: 'LOGIN', auth: auth })},
        getChannels: (newChannels) => {dispatch({ type: 'UPDATE_SIDEBAR', channels: newChannels })}
    }
}
export default connect(mapStateToProps,mapDispatchToProps)(Login);