const initState = {channels:[{title:""}]}

const sidebarReducer = (state = initState, action) => {

    switch(action.type){
        case"UPDATE_SIDEBAR":
        return {channels:action.channels}

        case"ADD_CHANNEL":
        return{
            channels:[
                ...state.channels,
                action.channel
            ]
        }


        case"DELETE_CHANNEL":
        return{
            channels:state.channels.filter(e=>{
                if(e!==action.channel)
                return true;
            })
        }
    }

    return state;
}

export default sidebarReducer