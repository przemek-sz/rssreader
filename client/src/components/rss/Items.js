import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import '../../css/Item.css';

class Items extends React.Component {

    state = {
        items: [],
        channel: null
    }

    componentDidMount() {
        this.setState({ channel: this.props.channel.channel })
        this.getItems();
    }


    getItems = () => {
        let method = 'get'
        if (this.props.auth.auth === null) {
            this.props.history.push("/");
        } else {
            let Authorization = 'Bearer ' + this.props.auth.auth.token;
            if (this.props.channel.channel !== null) {
                axios({
                    'method': 'post',
                    'url': 'http://localhost:8080/api/item',
                    'headers': {
                        'Content-Type': 'application/json',
                        Authorization
                    },
                    data:this.props.channel.channel
                })
                    .then((response) => {
                        let items = [];
                        response.data.forEach(element => {
                            element.items.forEach(item => {
                                items.push(item)
                            })
                        });
                        this.setState({ items: items })
                    });
            } else {
                axios({
                    'method': 'get',
                    'url': 'http://localhost:8080/api/item',
                    'headers': {
                        'Content-Type': 'application/json',
                        Authorization
                    }
                })
                    .then((response) => {
                        let items = [];
                        response.data.forEach(element => {
                            element.items.forEach(item => {
                                items.push(item)
                            })
                        });
                        this.setState({ items: items })
                    });
            }
        }
    }

    render() {

        if (this.state.channel !== this.props.channel.channel) {
            console.log('nie takie same');
            this.setState({ channel: this.props.channel.channel })
            this.getItems();
        }

        let items = this.state.items.map(e => {
            return (
                <a href={e.link} target="_blank"><div className="item">
                    <img src={e.imgLink} alt={"img"} />
                    <strong>{e.title}</strong><br></br><br></br>
                    <span className="description">{e.description}</span>
                </div></a>
            )
        })

        return (
            <div>
                {items}
            </div>
        )
    }
}

const mapStateToProps = (state) => {
    return {
        auth: state.auth,
        channel: state.channel
    }
}

export default connect(mapStateToProps)(Items)