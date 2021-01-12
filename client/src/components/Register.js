import React from 'react';
import axios from 'axios';
import '../css/Form.css';

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
        
        axios.put('http://localhost:8080/api/user',this.state)
        .then((response) =>{
            this.props.history.push("/");
        })
    }

    render() {
        return (
            <div>
                <form onSubmit={this.handleSubmit}>
                    <label>Username: </label>
                    <input className="txtinput" type="text" id="username" onChange={this.handleChange}/><br></br>
                    <label>Password: </label>
                    <input className="txtinput" type="text" id="password" onChange={this.handleChange}/><br></br>
                    <label>Email: </label>
                    <input className="txtinput" style={{marginLeft:'25px'}} type="text" id="email" onChange={this.handleChange}/><br></br>
                    <input className="submitinput" type="submit" value="Zarejestruj"/>
                </form>

            </div>
        )
    }
}

export default Register;