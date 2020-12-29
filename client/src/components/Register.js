import React from 'react';
import axios from 'axios';

class Register extends React.Component{


    state={
        username:'',
        email:'',
        password:'',
    }

    handleChange = (e) => {
        this.setState({
            [e.target.id]: e.target.value
        })
    }


    handleSubmit = (e) => {
        e.preventDefault();
        this.sendData();
    }

    sendData = () => {
        console.log(this.state)
        
        axios.post('http://localhost:8080/api/user',this.state)
        .then((response) =>{
            this.props.history.push("/");
        })
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>Username:</label>
                    <input type="text" id="username" onChange={this.handleChange}/><br></br>
                    <label>Password:</label>
                    <input type="text" id="password" onChange={this.handleChange}/><br></br>
                    <label>Email: </label>
                    <input style={{marginLeft:'25px'}} type="text" id="email" onChange={this.handleChange}/><br></br>
                    <input className="buttonSend" type="submit" value="Rejestracja"/>
                </form>

            </div>
        )
    }
}

export default Register;