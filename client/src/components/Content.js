import React from 'react';
import { Route, BrowserRouter, Switch } from 'react-router-dom'
import '../css/Content.css';
import Login from './Login';
import Register from './Register';
import Channel from './rss/Channel';
import Items from './rss/Items';

class Content extends React.Component{


    render(){

        return(
            <div id="content">
                <Switch>
                    <Route exact path="/login" component={Login}/>
                    <Route exact path="/register" component={Register}/>
                    <Route exact path="/findchannel" component={Channel}/>
                    <Route exact path="/items" component={Items}/>
                </Switch>
            </div>
        );
    }
}

export default Content;