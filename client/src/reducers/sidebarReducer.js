const initState = {
    channels: (localStorage.getItem("channels") !== null&&localStorage.getItem("channels")!=="") ?
        JSON.parse(localStorage.getItem("channels")) : 
        [{ title: "" }]
}

const sidebarReducer = (state = initState, action) => {

    switch (action.type) {
        case "UPDATE_SIDEBAR":
            localStorage.setItem('channels', JSON.stringify(action.channels));
            console.log(action.channels)
            return { channels: action.channels }
            

        case "ADD_CHANNEL":
            return {
                channels: [
                    ...state.channels,
                    action.channel
                ]
            }


        case "DELETE_CHANNEL":

            return {
                channels: state.channels.filter(e => {
                    if (e !== action.channel)
                        return true;
                })
            }
        case "CLEAR_CHANNELS":
            console.log('clear')
            localStorage.setItem('channels', "");
    }

    return state;
}

export default sidebarReducer