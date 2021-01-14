import React from 'react';
import axios from 'axios';
import '../css/Form.css';

class Register extends React.Component {


    state = {
        username: '',
        email: '',
        password: '',
        error: false,
        errorMessage: ''
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }


    handleSubmit = (e) => {
        e.preventDefault();
        this.setState({
            error:false,
            errorMessage:""
        })
        let err=false;
        let errMessage="";
        if(this.state.password.length<5){
            err=true;
            errMessage="Password is too short, minimum 5 characters.";
        }
        if (this.state.username.indexOf(' ') >= 0 || this.state.password.indexOf(' ') >= 0 || this.state.email >= 0){
            err=true;
            errMessage="Data cannot contain whitespace";
        }
        
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        if (!re.test(String(this.state.email).toLowerCase())){
            err=true;
            errMessage="Invalid email adress.";
        }  

        if(!err){
            this.sendData();
        }else{
            this.setState({
                error:true,
                errorMessage:errMessage
            })
        }
        
    }

    sendData = () => {

        axios.put('http://localhost:8080/api/user', { username: this.state.username, password: this.state.password, email: this.state.email })
            .then((response) => {
                this.props.history.push("/");
            }).catch(error => {
                let errorMessage="";

                error.response.data.forEach(err => {
                    (error.response.data.length===1)? console.log("jeden"): console.log("dwa");
                    if (err===1) {
                        errorMessage = (error.response.data.length===1)?"Username is taken.":"Username";
                    }
                    if (err===2) {
                        errorMessage = (error.response.data.length===1)?"Email is taken.": errorMessage+" and Email are taken.";
                    }
                });


                this.setState({
                    error: true,
                    errorMessage: errorMessage
                })

                console.log(error.response.data.length)

            })
    }

    render() {
        let errorDiv = (this.state.error) ?
            <div className="errorDiv">{this.state.errorMessage}</div> :
            <div></div>
        return (
            <div>
                {errorDiv}
                <form onSubmit={this.handleSubmit}>
                    <label>Username: </label>
                    <input className="txtinput" type="text" id="username" onChange={this.handleChange} /><br></br>
                    <label>Password: </label>
                    <input className="txtinput" type="password" id="password" onChange={this.handleChange} /><br></br>
                    <label>Email: </label>
                    <input className="txtinput" style={{ marginLeft: '25px' }} type="text" id="email" onChange={this.handleChange} /><br></br>
                    <input className="submitinput" type="submit" value="Sign up" />
                </form>

            </div>
        )
    }
}

export default Register;