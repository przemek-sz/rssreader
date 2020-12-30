import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import '../../css/Item.css';

class ItemsPerChannel extends React.Component {

    state = {
        items: [],
        channel: null
    }

    componentDidMount() {
        this.getItems();
    }


    getItems = () => {
        if (this.props.auth.auth === null) {
            this.props.history.push("/");
        } else {
            let Authorization = 'Bearer ' + this.props.auth.auth.token;
            axios({
                'method': 'post',
                'url': 'http://localhost:8080/api/item',
                'headers': {
                    'Content-Type': 'application/json',
                    Authorization
                },
                data: this.props.channel.channel
            })
                .then((response) => {

                    console.log(response.data)
                    let items = [];
                    response.data.forEach(element => {
                        element.items.forEach(item => {
                            items.push(item)
                        })
                    });
                    this.setState({ items: items })
                    console.log(this.state.items);
                });
        }

    }

    render() {

        if (this.props.channel.channel !== this.state.channel) {
            this.setState({
                channel: this.props.channel.channel
            })
        }

        console.log("RENderR")
        //console.log(this.props.channel.channel)

        let items = this.state.items.map(e => {
            return (
                <a href={e.link}><div className="item">
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

export default connect(mapStateToProps)(ItemsPerChannel)