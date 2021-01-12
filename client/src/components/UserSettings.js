import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import '../css/Form.css';

class UserSettings extends React.Component {

    state = {
        username: '',
        email: '',
        password: '',
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
        if (this.props.auth.auth === null) {
            this.props.history.push("/");
        } else {
            let Authorization = 'Bearer ' + this.props.auth.auth.token;
            axios({
                'method': 'post',
                'url': 'http://localhost:8080/api/user',
                'headers': {
                    'Content-Type': 'application/json',
                    Authorization
                },
                data: { username: this.state.username, email: this.state.email, password: this.state.password }
            })
                .then((response) => {
                });
        }
    }

    render() {

        return (
            <div>
                <div>
                    <form onSubmit={this.handleSubmit}>
                        <label>Username: </label>
                        <input className="txtinput" type="text" id="username" onChange={this.handleChange} /><br></br>
                        <label>Password: </label>
                        <input className="txtinput" type="text" id="password" onChange={this.handleChange} /><br></br>
                        <label>Email: </label>
                        <input className="txtinput" style={{ marginLeft: '25px' }} type="text" id="email" onChange={this.handleChange} /><br></br>
                        <input className="submitinput" type="submit" value="Edytuj" />
                    </form>
                </div>
            </div>);
    }
}
const mapStateToProps = (state) => {
    return {
        auth: state.auth,
    }
}

export default connect(mapStateToProps)(UserSettings)