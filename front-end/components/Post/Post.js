import React, {useState, useRef, useEffect} from 'react';
import "./Post.scss";
import clsx from 'clsx';

import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import { makeStyles, styled } from "@mui/styles";
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import FavoriteIcon from '@mui/icons-material/Favorite';
import ShareIcon from '@mui/icons-material/Share';
import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Avatar, Container } from '@mui/material';
import { red } from '@mui/material/colors';


import { Link } from 'react-router-dom';
import Comment from '../Comment/Comment';


const useStyles = makeStyles((theme) => ({
  root: {
    width: 800,
    textAlign : "left",
    margin : 20,
    
  },
  media: {
    height: 0,
    paddingTop: '56.25%', // 16:9
  },

  avatar: {
    background: 'linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)',
  },
  link: {
      textDecoration : "none",
      boxShadow : "none",
      color : "white"
  }
}));




function Post(props){

    const {title, text, userName, userId , postId}  = props;
    const classes = useStyles();
    const [expanded, setExpanded] = useState(false);
    const [liked , setLiked] = useState(false);
    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [commentList , setCommentList] = useState([]);
    const isInitialMount = useRef(true);
    const handleLike = () => {
        setLiked(!liked);
    }
    

    const refreshComments =() =>{
      console.log(postId)
      fetch("/api/comments?postId="+postId)
          .then(res => res.json())
          .then(
              (result) => {
                  setIsLoaded(true);
                  setCommentList([result]);
              },
              (error) => {
                  setIsLoaded(true);
                  setError(error);
              }
          )
  }


  const handleExpandClick = () => {
    setExpanded(!expanded);
    refreshComments();
    console.log(commentList);
  };

  useEffect(() => {
    if(isInitialMount.current)
      isInitialMount.current = false;
    else
      refreshComments();
}, [commentList])



    return(
        <div >
         <Card className={classes.root}>
      <CardHeader
      avatar={
        <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
         <Link to={{pathname : '/users/' + userId}}>{userName.charAt(0).toUpperCase()}</Link>
        </Avatar>
      }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title={title}

      />
      
      <CardContent>
        <Typography variant="body2" color="text.secondary">
          {text}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
        <IconButton
        onClick={handleLike}
        aria-label="add to favorites">
          <FavoriteIcon style={liked? {color : "red"}: null} />
        </IconButton>
        <IconButton
                    className={clsx(classes.expand, {
                        [classes.expandOpen]: expanded,
                    })}
                    onClick={handleExpandClick}
                    aria-expanded={expanded}
                    aria-label="show more"
                    >
                    <ShareIcon />
                    </IconButton>
      
      </CardActions>
      <Collapse in={expanded}  timeout="auto" unmountOnExit>
        <Container fixed className={classes.container}>
          {error?"error" :
          isLoaded? commentList.map(comment => (
            <Comment userId = {1} userName = {"user"} text = {comment.text}></Comment>
          )) :" Loading" }
        </Container>
      </Collapse>
    </Card>
        </div>
    )

}

export default Post;