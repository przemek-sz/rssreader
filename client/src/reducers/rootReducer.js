import authReducer from './authReducer'
import sidebarReducer from './sidebarReducer'
import { combineReducers } from 'redux'

const rootReducer = combineReducers({
    auth: authReducer,
    sidebar: sidebarReducer
});

export default rootReducer