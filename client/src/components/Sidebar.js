import React from 'react';
import { NavLink } from 'react-router-dom';
import '../css/Sidebar.css';
import { connect } from 'react-redux';


class Sidebar extends React.Component {

    state = {

    }

    chooseChannel = (channel) => {
        this.props.setChannel(channel);
    }


    render() {
        let channels = this.props.channels.map(e => {
            console.log(e)
            console.log('e makarena')
            if (e.title !== "") {
                return (
                    <div onClick={() => this.chooseChannel(e)}>
                        <NavLink to="/items" activeStyle={{ 'text-decoration': 'none' }}>
                            <img className="favicon" src={e.url + '/favicon.ico'} alt=""></img><br></br>
                            {e.title}<br></br>
                            {e.description}
                        </NavLink>

                        <br></br><br></br>
                    </div>
                )
            }else return("");

        })
        return (
            <div id="sidebar">
                <div onClick={() => this.chooseChannel(null)}>
                    <NavLink to="/items" activeStyle={{ 'text-decoration': 'none' }}>All<br></br></NavLink>
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