import React from 'react';
import '../css/Sidebar.css';
import { connect } from 'react-redux';


class Sidebar extends React.Component{

    state={

    }


    render(){

        let channels=this.props.channels.map(e=>{
            return(
                <div>
                    {e.title}<br></br>
                    {e.description}<br></br><br></br>
                </div>
            )
        })
        return(
            <div id="sidebar">
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

export default connect(mapStateToProps)(Sidebar);