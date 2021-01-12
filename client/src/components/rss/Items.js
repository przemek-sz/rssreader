import React from 'react';
import axios from 'axios';
import { connect } from 'react-redux';
import '../../css/Item.css';
import gifLoading from '../../assets/gifLoading.gif';
import bookmark from '../../assets/bookmark.png';
import bookmarkTrue from '../../assets/bookmarkTrue.png';

class Items extends React.Component {

    state = {
        items: [],
        channel: null
    }

    componentDidMount() {
        this.setState({ channel: this.props.channel.channel })
        this.getItems();
    }

    onClickReadLater = (item) => {
        console.log(item);
        item.readlater = !item.readlater;
        this.updateItem(item);
    }

    onClickFavorite = (item) => {
        console.log(item);
        item.favorite = !item.favorite;
        this.updateItem(item);
    }

    onClickReaded = (item) => {
        console.log(item);
        item.readed = !item.readed;
        this.updateItem(item);
    }

    onClickHide = (item) => {
        console.log(item);
        item.hide = !item.hide;
        this.updateItem(item);
    }

    updateState = (item) => {
        this.setState({
            items: this.state.items.map(e => {

                if (item.id === e.id)
                    return item;

                return e;
            })
        })
    }


    getItems = () => {
        let method = 'get'
        if (this.props.auth.auth === null) {
            this.props.history.push("/");
        } else {
            let Authorization = 'Bearer ' + this.props.auth.auth.token;
            let channel = this.props.channel.channel;
            if (channel !== null && !(typeof channel == 'string' || channel instanceof String)) {
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
                        /*
                        let items = [];
                        response.data.forEach(element => {
                            element.items.forEach(item => {
                                items.push(item)
                            })
                        });
                        this.setState({ items: items })*/
                        this.setState({ items: response.data })
                    });
            } else {
                let url="http://localhost:8080/api/item";
                console.log(this.props.channel.channel)
                switch(this.props.channel.channel){
                    case"favorite":
                    url="http://localhost:8080/api/item/favorite";
                    break;
                    case"readlater":
                    url="http://localhost:8080/api/item/readlater";
                    break;
                    case"today":
                    url="http://localhost:8080/api/item/today";
                    break;
                }
                axios({
                    'method': 'get',
                    'url': url,
                    'headers': {
                        'Content-Type': 'application/json',
                        Authorization
                    }
                })
                    .then((response) => {
                        this.setState({ items: response.data })
                    });
            }
        }
    }

    updateItem = (item) => {
        if (this.props.auth.auth === null) {
            this.props.history.push("/");
        } else {
            let Authorization = 'Bearer ' + this.props.auth.auth.token;

            axios({
                'method': 'post',
                'url': 'http://localhost:8080/api/item/update',
                'headers': {
                    'Content-Type': 'application/json',
                    Authorization
                },
                data: item
            })
                .then((response) => {
                    this.updateState(item);
                });

        }


    }

    render() {


        if (this.state.channel !== this.props.channel.channel) {
            this.setState({ channel: this.props.channel.channel })
            this.getItems();
        }

        
        let sortedItems=this.state.items.sort((a, b) => Date.parse(b.pubdate) - Date.parse(a.pubdate));
      
        let items = sortedItems.map(e => {
            if(e.hide)
            return("");
     


            let img = (e.imgLink === "https://fakeimg.pl/640x360") ? "" : <img className="itemImage" src={e.imgLink} alt={"img"} />;
            let readLaterImg = (e.readlater) ? bookmarkTrue : bookmark;
            let favoriteColor = (e.favorite) ? 'gold' : 'white';
            let readedColor = (e.readed) ? '#2bb14c' : 'white';
            let pubdate=e.pubdate.split(",")[1].split(" ",5).map(f=>{
                return(f+" ");
            });

            return (
                <div className="item" style={e.readed?{opacity:'0.3'}:{opacity:'1'}}>
                    <div className="bookmarks">
                        <img onClick={() => this.onClickReadLater(e)} className="bookmark" style={{ height: '15px', width: '20px' }} src={readLaterImg} alt={"img"} />
                        <span onClick={() => this.onClickFavorite(e)} className="bookmark" style={{ color: favoriteColor }}>&#9734;</span>
                        <span onClick={() => this.onClickReaded(e)} className="bookmark" style={{ color: readedColor }}>&#10004;</span>
                        <span onClick={() => this.onClickHide(e)} className="bookmark">&#10006;</span>
                        <div className="pubDate">
                        {pubdate}
                        </div>
                    </div>
                    
                    <br></br>
                    <a href={e.link} target="_blank">
                        {img}
                        <strong className="title">{e.title}</strong><br></br><br></br>
                        <span className="description">{e.description}</span>
                    </a>
                </div>
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