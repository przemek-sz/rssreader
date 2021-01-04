import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';

class UserList extends React.Component {

    state = {
        users: []
    }

    componentDidMount() {
        this.getList();
    }

    handleDelete = (userToDelete) => {
        this.setState({
            users:this.state.users.filter(user=>user!==userToDelete)
        })
        this.deleteUser(userToDelete.id);
    }

    getList = () => {
        let Authorization = 'Bearer ' + this.props.auth.auth.token;
        axios({
            'method': 'get',
            'url': 'http://localhost:8080/api/user',
            'headers': {
                'Content-Type': 'application/json',
                Authorization
            }
        })
            .then((response) => {
                console.log(response.data)
                this.setState({
                    users: response.data
                })
            });
    }

    deleteUser = (id) => {
        let Authorization = 'Bearer ' + this.props.auth.auth.token;
        axios({
            'method': 'delete',
            'url': 'http://localhost:8080/api/user/'+id,
            'headers': {
                'Content-Type': 'application/json',
                Authorization
            }
        })
            .then((response) => {
            });
    }

    render() {
        console.log(this.state.users)
        let users = this.state.users.map(user => {
            return (
                <div>
                    ID: {user.id}<br></br>
                    Nazwa użytkownika: {user.username} <br></br>
                    Email: {user.email}<br></br>
                    <button className="normalbutton" onClick={() => this.handleDelete(user)}>Delete</button><br></br><br></br><br></br>
                </div>
            )
        });
        return (
            <div>
                Lista użytkowników:<br></br><br></br>
                {users}
            </div>
        )
    }

}

const mapStateToProps = (state) => {
    return {
        auth: state.auth,
    }
}
export default connect(mapStateToProps)(UserList);