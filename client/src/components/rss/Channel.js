import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import '../../css/Form.css';
import '../../css/ChannelAdd.css';

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
        
        let channels = this.state.channels.map(e => {
            return(
                <div className="channel">
                    <img className="fav" src={e.url + '/favicon.ico'} alt=""></img>
                    <button className="addChButton" onClick={()=>this.add(e)}>Add</button>
                    {e.title}<br></br>
                    {e.description}<br></br>
                    {e.rssUrl}<br></br>
                    <br></br><br></br>
                </div>
            )
        });
        console.log(channels)
        return(
            <div>
                <form id="searchForm" onSubmit={this.handleSubmit}>
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