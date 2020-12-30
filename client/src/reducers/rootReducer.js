import authReducer from './authReducer'
import sidebarReducer from './sidebarReducer'
import channelReducer from './channelReducer'
import { combineReducers } from 'redux'

const rootReducer = combineReducers({
    auth: authReducer,
    sidebar: sidebarReducer,
    channel:channelReducer
});

export default rootReducer