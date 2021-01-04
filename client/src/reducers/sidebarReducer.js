const initState = {channels:[{title:""}]}

const sidebarReducer = (state = initState, action) => {
    if(action.type==='UPDATE_SIDEBAR'){
        return{
            channels:action.channels
        }  
    }
    if(action.type==='ADD_CHANNEL'){
        return{
            channels:[
                ...state.channels,
                action.channel
            ]
        }  
    }

    return state;
}

export default sidebarReducer