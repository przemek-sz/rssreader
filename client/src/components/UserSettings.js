import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import '../css/Form.css';
import '../css/UserSettings.css';

class UserSettings extends React.Component {

    state = {
        username: '',
        email: '',
        password: '',
    }

    comp

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }

    handleSubmit = (e) => {
        e.preventDefault();
    }

    onChangeButton = (type) => {
        let value;
        if(type==="username"||type==="password"||type==="email")
        switch (type) {
            case "username":
                value = this.state.username;
                break;
            case "password":
                value = this.state.password;
                break;
            case "email":
                value = this.state.email;
                break;
        }
        let action = { type:type, value:value}
        this.sendData(action);
    }

    onClickDelete = (channel) =>{
        this.deleteChannel(channel);
    }


    sendData = (action) => {
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
                data: action
            })
                .then((response) => {
                    if(action.type="username")
                    this.props.login(response.data);
                });
        }
    }

    deleteChannel = (channel) => {
        if (this.props.auth.auth === null) {
            this.props.history.push("/");
        } else {
            let Authorization = 'Bearer ' + this.props.auth.auth.token;
            axios({
                'method': 'delete',
                'url': 'http://localhost:8080/api/channel',
                'headers': {
                    'Content-Type': 'application/json',
                    Authorization
                },
                data: channel
            })
                .then((response) => {
                     this.props.deleteChannelFromSidebar(channel);
                });
        }
    }


    render() {

        let channelsList = this.props.channels.map(e => {
            return (
                <div className="channel">
                    <img className="fav" src={e.url + '/favicon.ico'} alt=""></img>
                    <button className="deleteChButton" onClick={()=>this.onClickDelete(e)}>Delete</button>
                    {e.title}<br></br>
                    {e.description}<br></br>
                    {e.rssUrl}<br></br>
                    <br></br><br></br>
                </div>
            )
        })

        return (
            <div>
                <div>
                    <form className="editForm" onSubmit={this.handleSubmit}>
                        <label>Username: </label>
                        <input className="txtinput" type="text" id="username" onChange={this.handleChange} />
                        <input className="submitinput" style={{marginLeft:'25px'}} type="submit" value="Change" onClick={() => this.onChangeButton("username")} />
                    </form>
                    <form className="editForm" onSubmit={this.handleSubmit}>
                        <label>Password: </label>
                        <input className="txtinput" password="text" id="password" onChange={this.handleChange} />
                        <input className="submitinput" style={{marginLeft:'25px'}} type="submit" value="Change" onClick={() => this.onChangeButton("password")} />
                    </form>
                    <form className="editForm" onSubmit={this.handleSubmit}>
                        <label>Email: </label>
                        <input className="txtinput" style={{marginLeft:'25px'}}  type="text" id="email" onChange={this.handleChange} />
                        <input className="submitinput" style={{marginLeft:'25px'}} type="submit" value="Change" onClick={() => this.onChangeButton("email")} />
                    </form>
                </div><br></br>
                {channelsList}
            </div>);
    }
}
const mapStateToProps = (state) => {
    return {
        auth: state.auth,
        channels: state.sidebar.channels
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        login: (auth) => {dispatch({ type: 'LOGIN', auth: auth })},
        deleteChannelFromSidebar: (channel) => { dispatch({ type: 'DELETE_CHANNEL', channel: channel }) }
    }
}

export default connect(mapStateToProps, mapDispatchToProps)(UserSettings)