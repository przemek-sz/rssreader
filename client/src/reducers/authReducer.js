const initState = {
    auth:(localStorage.getItem("username")!=="")? {
        username: localStorage.getItem("username"),
        token: localStorage.getItem("token"),
        expiration: localStorage.getItem("tokenexpirationdate"),
        roles: [...localStorage.getItem("roles")]
    }:null
}

const authReducer = (state = initState, action) => {
    if (action.type === 'LOGIN') {
        console.log(action.auth)
        localStorage.setItem("username",action.auth.username);
        localStorage.setItem("token",action.auth.token);
        localStorage.setItem("tokenexpirationdate",action.auth.tokenexpirationdate);
        localStorage.setItem("roles",action.auth.roles);
        return {
            auth: {
                username: action.auth.username,
                token: action.auth.token,
                expiration: action.auth.tokenexpirationdate,
                roles: action.auth.roles
            }
        }
    }

    if (action.type === 'LOGOUT') {
        localStorage.setItem("username","");
        localStorage.setItem("token","");
        localStorage.setItem("tokenexpirationdate","");
        localStorage.setItem("roles","");
        return {
            auth: null
        }
    }
    return state;
}

export default authReducer
/*
const initState = {
    auth: (typeof localStorage.getItem("auth")!=='undefined') ? {
        username: localStorage.getItem("auth").username,
        token: localStorage.getItem("auth").token,
        expiration: localStorage.getItem("auth").tokenexpirationdate,
        roles: [localStorage.getItem("auth").roles]
    } : null
}
    
const authReducer = (state = initState, action) => {
    if (action.type === 'LOGIN') {
        localStorage.setItem("auth", {
            username: action.auth.username,
            token: action.auth.token,
            expiration: action.auth.tokenexpirationdate,
            roles: action.auth.roles
        });
        return {
            auth: {
                username: action.auth.username,
                token: action.auth.token,
                expiration: action.auth.tokenexpirationdate,
                roles: action.auth.roles
            }
        }
    }

    if (action.type === 'LOGOUT') {
        localStorage.setItem("auth", 'undefined');
        console.log('Tutajjjjjj!!! '+localStorage.getItem("auth"))

        return {
            auth: null
        }
    }
    return state;
}

export default authReducer*/