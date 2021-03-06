import React from 'react';
import { Route, Switch } from 'react-router-dom'
import '../css/Content.css';
import Login from './Login';
import Register from './Register';
import Channel from './rss/Channel';
import Items from './rss/Items';
import UserList from './admin/Userlist';
import UserSettings from './UserSettings';

class Content extends React.Component{


    render(){

        return(
            <div id="content">
                <Switch>
                    <Route exact path="/login" component={Login}/>
                    <Route exact path="/register" component={Register}/>
                    <Route exact path="/findchannel" component={Channel}/>
                    <Route exact path="/items" component={Items}/>
                    <Route exact path="/userlist" component={UserList}/>
                    <Route exact path="/usersettings" component={UserSettings}/>
                </Switch>
            </div>
        );
    }
}

export default Content;