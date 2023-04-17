import React, { useState } from 'react';
import "./Post.scss";

import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import { makeStyles, styled } from "@mui/styles";
import CardContent from '@mui/material/CardContent';

import Collapse from '@mui/material/Collapse';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';

import MoreVertIcon from '@mui/icons-material/MoreVert';
import { Avatar, Button, InputAdornment } from '@mui/material';
import { red } from '@mui/material/colors';
import { Link } from 'react-router-dom';
import OutlinedInput from '@mui/material/OutlinedInput';
import Snackbar from '@mui/material/Snackbar';
import MuiAlert from '@mui/material/Alert';
const Alert = React.forwardRef(function Alert(props, ref) {
    return <MuiAlert elevation={6} ref={ref} variant="filled" {...props} />;
});

const useStyles = makeStyles((theme) => ({
    root: {
        width: 800,
        textAlign: "left",
        margin: 20,

    },
    media: {
        height: 0,
        paddingTop: '56.25%', // 16:9
    },

    avatar: {
        background: 'linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)',
    },
    link: {
        textDecoration: "none",
        boxShadow: "none",
        color: "white"
    }
}));




function PostForm(props) {

    const { userName, userId, refreshPosts } = props;
    const classes = useStyles();
    const [text, setText] = useState("");
    const [title, setTitle] = useState("");
    const [isSent, setIsSent] = useState(false);

    const savePost = () => {
        fetch("/posts",
            {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                },
                body: JSON.stringify({
                    title: title,
                    userId: userId,
                    text: text
                }),
            })
            .then((res) => res.json())
            .catch((err) => console.log("eror"))
    }



    const handleSubmit = () => {
        savePost();
        setIsSent(true);
        setText("");
        setTitle("");
        refreshPosts();
    }

    const handleTitle = (value) => {
        setTitle(value);
        setIsSent(false);

    }

    const handleText = (value) => {
        setText(value);
        setIsSent(false);
    }

    const handleClose = (event, reason) => {
        if (reason === 'clickaway') {
            return;
        }

        setIsSent(false);
    };


    return (
        <div >

            <Snackbar  autoHideDuration={6000} onClose={handleClose}>
                <Alert onClose={handleClose} severity="success" sx={{ width: '100%' }}>
                    Your post is sent!
                </Alert>
            </Snackbar>

            <Card className={classes.root}>
                <CardHeader
                    avatar={
                        <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
                            <Link to={{ pathname: '/users/' + userId }}>{userName.charAt(0).toUpperCase()}</Link>
                        </Avatar>
                    }
                    action={
                        <IconButton aria-label="settings">
                            <MoreVertIcon />
                        </IconButton>
                    }
                    title={<OutlinedInput id="outlined-adornment-amount"

                        multiline
                        placeholder="Title"
                        inputProps={{ maxLength: 25 }}
                        fullWidth
                        value={title}
                        onChange={(i) => handleTitle(i.target.value)}
                    >
                    </OutlinedInput>}

                />

                <CardContent>
                    <Typography variant="body2" color="text.secondary">
                        {<OutlinedInput
                            id="outlined-adornment-amount"
                            multiline
                            placeholder="Text"
                            inputProps={{ maxLength: 250 }}
                            fullWidth
                            value={text}
                            onChange={(i) => handleText(i.target.value)}
                            endAdornment={
                                <InputAdornment>
                                    <Button

                                        variant="contained"
                                        onClick={handleSubmit}
                                    >Post
                                    </Button>
                                </InputAdornment>
                            }
                        >
                        </OutlinedInput>}
                    </Typography>
                </CardContent>

                <Collapse timeout="auto" unmountOnExit>

                </Collapse>
            </Card>
        </div>
    )

}

export default PostForm;