const initState = {channel:null}

const channelReducer = (state = initState, action) => {
    console.log(action.channel)
    if(action.type==='SET_CHANNEL'){
        return{
            channel:action.channel
        }  
    }

    return state;
}

export default channelReducer