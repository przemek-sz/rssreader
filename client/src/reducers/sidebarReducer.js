const initState = {channels:[{title:""}]}

const sidebarReducer = (state = initState, action) => {
    if(action.type==='UPDATE_SIDEBAR'){
        return{
            channels:action.channels
        }  
    }

    return state;
}

export default sidebarReducer