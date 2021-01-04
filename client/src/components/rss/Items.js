import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import '../../css/Item.css';
import gifLoading from '../../assets/gifLoading.gif';

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
                        /*
                        let items = [];
                        response.data.forEach(element => {
                            element.items.forEach(item => {
                                items.push(item)
                            })
                        });
                        this.setState({ items: items })*/
                        console.log(response.data)
                        this.setState({items:response.data})
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
                        this.setState({items:response.data})
                    });
            }
        }
    }

    render() {

        if (this.state.channel !== this.props.channel.channel) {
            this.setState({ channel: this.props.channel.channel })
            this.getItems();
        }

        let items = this.state.items.map(e => {
            let img;
            if(e.imgLink==="https://fakeimg.pl/640x360")
            img="";
            else
            img=<img src={e.imgLink} alt={"img"} />
            return (
                <a href={e.link} target="_blank"><div className="item">
                    {img}
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