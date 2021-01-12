import React from 'react';
import { NavLink } from 'react-router-dom';
import '../css/Sidebar.css';
import { connect } from 'react-redux';
import bookmark from '../assets/bookmark.png';


class Sidebar extends React.Component {

    state = {

    }

    chooseChannel = (channel) => {
        this.props.setChannel(channel);
    }


    render() {
        let channels = this.props.channels.map(e => {
            if (e.title !== "") {
                return (
                    <div onClick={() => this.chooseChannel(e)}>
                        <NavLink to="/items" activeStyle={{ 'text-decoration': 'none' }}>
                            <img className="favicon" src={e.url + '/favicon.ico'} alt=""></img><br></br>
                            {e.description===""?e.title:e.description}
                        </NavLink>

                        <br></br><br></br>
                    </div>
                )
            }else return("");

        })
        return (
            <div id="sidebar">
                <div onClick={() => this.chooseChannel(null)}>
                    <NavLink to="/items" activeStyle={{ 'text-decoration': 'none' }}><span className="bookmarkSymbol">&#9776;</span> All<br></br></NavLink>
                    <br></br><br></br>
                </div>
                <div onClick={() => this.chooseChannel("today")}>
                    <NavLink to="/items" activeStyle={{ 'text-decoration': 'none' }}><span className="bookmarkSymbol">&#9202;</span> Today<br></br></NavLink>
                    <br></br><br></br>
                </div>
                <div onClick={() => this.chooseChannel("readlater")}>
                <NavLink to="/items" activeStyle={{ 'text-decoration': 'none' }}><img className="bookmarkSymbol" style={{ height: '15px', width: '20px'}} src={bookmark} alt={"img"} /> Read Later<br></br></NavLink>
                    <br></br><br></br>
                </div>
                <div onClick={() => this.chooseChannel("favorite")}>
                    <NavLink to="/items" activeStyle={{ 'text-decoration': 'none' }}><span className="bookmarkSymbol">&#9734;</span> Favorite<br></br></NavLink>
                    <br></br><br></br>
                </div>
                {channels}
            </div>
        );
    }
}

const mapStateToProps = (state) => {
    return {
        channels: state.sidebar.channels
    }
}

const mapDispatchToProps = (dispatch) => {
    return {
        setChannel: (channel) => { dispatch({ type: 'SET_CHANNEL', channel: channel }) }
    }
}


export default connect(mapStateToProps, mapDispatchToProps)(Sidebar);