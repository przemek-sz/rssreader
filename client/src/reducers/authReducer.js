const initState = {auth:null}

const authReducer = (state = initState, action) => {
    if(action.type==='LOGIN'){
        console.log(action.auth)
        return{
            auth:{
                username:action.auth.username,
                token:action.auth.token,
                expiration:action.auth.expiration
            }
        }  
    }

    if(action.type==='LOGOUT'){
        return{
            auth:null
        }  
    }
    return state;
}

export default authReducer