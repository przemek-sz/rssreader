import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import '../../css/Form.css';

class Channel extends React.Component{

    state={
        url:'',
        channels:[]
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }


    handleSubmit = (e) => {
        e.preventDefault();
        this.searchChannel();
    }

    add = (channel) => {
        let updatedList = this.state.channels.filter(e=>{
            if(channel!==e)
            return e;
        })
        this.setState({channels:updatedList});
        this.addChannel(channel);
    }

    searchChannel = () => {
        
        let Authorization = 'Bearer '+this.props.token;
        axios({
            'method': 'post',
            'url': 'http://localhost:8080/api/channel/new',
            'headers': {
                'Content-Type': 'application/json',
                Authorization
            },
            'data': this.state.url
        })
            .then((response) => {
                this.setState({channels:response.data})
            });
    }

    addChannel = (channel) => {
        let Authorization = 'Bearer '+this.props.token;
        axios({
            'method': 'post',
            'url': 'http://localhost:8080/api/channel',
            'headers': {
                'Content-Type': 'application/json',
                Authorization
            },
            'data': channel
        })
            .then((response) => {
                this.props.addChannelToSidebar(channel);
            });
    }

    render(){
        
        let channels = this.state.channels.map(channel => {
            return(
                <div>
                    {channel.titel}<br></br>
                    {channel.rssUrl}<br></br>
                    {channel.description}<br></br>
                    <button onClick={() => this.add(channel)}>Add</button>
                </div>
            )
        });
        console.log(channels)
        return(
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>Podaj rss lub adres strony: </label>
                    <input className="txtinput" type="text" id="url" onChange={this.handleChange}/><br></br>
                    <input className="submitinput" type="submit" value="Szukaj"/>
                </form><br></br><br></br>

                {channels}

            </div>

        )
    }
}

const mapStateToProps = (state) => {
    return {
        token: state.auth.auth.token
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        addChannelToSidebar: (newChannel) => {dispatch({ type: 'ADD_CHANNEL', channel: newChannel })}
    }
}

export default connect(mapStateToProps,mapDispatchToProps)(Channel);