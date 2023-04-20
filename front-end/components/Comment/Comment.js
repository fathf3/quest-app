import React from "react";
import { makeStyles } from "@mui/styles";
import { Avatar, CardContent, InputAdornment, OutlinedInput } from "@mui/material";
import { Link } from "react-router-dom";

const useStyles = makeStyles((theme) => ({
     comment : {
        display : "flex",
        flexWrap : "wrap",
        justifyContent : "flex-start",
        alignItems : "center"
     },
     small: {
        
     },
     link: {
        textDecoration : "none",
        boxShadow :"none",
        color : "white",
     }


}));
function Comment(props) {
    
    const {userId, userName, text} = props;
    
    const classes = useStyles();
    return (
        <CardContent className={classes.comment}>
            
            <OutlinedInput
            disabled
            id="outlined-adornment-amount"
            multiline
            inputProps={{ maxLength: 25 }}
            fullWidth
            value={text}
            startAdorment = {
            <InputAdornment position="start">
                    
                <Link className={classes.link} to = {{pathname : '/users/' + userId}}>
                <Avatar aria-aria-label="recipe" className={classes.small}>
                    {userName.charAt(0).toUpperCase()}
                </Avatar>
                </Link>
            </InputAdornment>}
            ></OutlinedInput>
        </CardContent>
    )
}



export default Comment;