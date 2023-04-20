import React, {useState, useRef, useEffect} from 'react';
import "./Post.scss";
import clsx from 'clsx';

import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import { makeStyles } from "@mui/styles";
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
import CommentForm from '../Comment/CommentForm';


const useStyles = makeStyles((theme) => ({
  root: {
    width: 800,
    textAlign : "left",
    margin : 20
  },
  media: {
    height: 0,
    paddingTop: '56.25%', // 16:9
  },
  expand: {
    transform: 'rotate(0deg)',
    marginLeft: 'auto',
    
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

    const {title, text, userName, userId , postId, likes}  = props;
    const classes = useStyles();
    const [expanded, setExpanded] = useState(false);
    const [error, setError] = useState(null);
    const [isLiked, setIsLiked] = useState(false);
    const [isLoaded, setIsLoaded] = useState(false);
    const [commentList , setCommentList] = useState([]);
    const isInitialMount = useRef(true);
    const [likeCount, setLikeCount] = useState(likes.length);
    const [likeId, setLikeId] = useState(null);
    const [refresh, setRefresh] = useState(false);
    let disabled = userId == null ? true:false;

    const commentListe = Array.from(commentList);

    
    const setCommentRefresh = () => {
      setRefresh(true);
    }


    const handleExpandClick = () => {
      setExpanded(!expanded);
      refreshComments();
      
    };


    const refreshComments = () => {
      fetch("/comments?postId="+postId)
      .then(res => res.json())
      .then(
          (result) => {
            setIsLoaded(true);
              setCommentList(result)
          },
          (error) => {
              console.log(error)
              setIsLoaded(true);
              setError(error);
          }
      )
  
      setRefresh(false)
    }



 

  const checkLikes = () => {
    var likeControl = likes.find((like => like.userId === userId));
    
    if(likeControl != null){
      
      setLikeId(likeControl.id)
      setIsLiked(true)
    }
  }

  useEffect(() => {
    if(isInitialMount.current)
      isInitialMount.current = false;
    else
      refreshComments();
}, [refresh])

useEffect(() => {checkLikes()},[])

const saveLike = () => {
  fetch("/likies",{
    method : "POST",
    headers : {
      "Content-Type" : "application/json"
    },
    body : JSON.stringify({
      postId : postId,
      userId : userId
    }),
  })  
    .then((res) => res.json())
    .catch((err) => console.log(err))
}

const deleteLike = () => {
  fetch("/likies/"+likeId,{
    method : "DELETE"
  })
    .catch((err) => console.log(err))
}

const handleLike = () => {
  setIsLiked(!isLiked);
  if(!isLiked){
    saveLike();
    setLikeCount(likeCount + 1)
  }
  else{
    deleteLike();
    setLikeCount(likeCount - 1)
  }
    
 }




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
      {disabled ? 
      <IconButton 
                    disabled
                    onClick={handleLike}
                    aria-label="add to favorites"
                    >
                    <FavoriteIcon style={isLiked? { color: "red" } : null} />
                    </IconButton> :
                    <IconButton 
                    onClick={handleLike}
                    aria-label="add to favorites"
                    >
                    <FavoriteIcon style={isLiked? { color: "red" } : null} />
                    </IconButton>
                  }
                    {likeCount}
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
      
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent>
                    <Container fixed className = {classes.container}>
                    {error? "error" :
                    isLoaded? commentListe.map(comment => (
                      <Comment userId = {comment.userId} userName = {comment.userName} text = {comment.text}></Comment>
                    )) : "Loading"}
                    {disabled? "":
                    <CommentForm postId = {postId} setCommentRefresh={setCommentRefresh} >
                    </CommentForm>}
        </Container>
        </CardContent>
      </Collapse>
    </Card>
        </div>
    )

}

export default Post;