import React, {useState, useEffect} from "react";
import Post from "../Post/Post";
import { makeStyles } from "@mui/styles";
import PostForm from "../Post/PostForm";




const useStyles = makeStyles((theme) => ({

    container:{
        display : "flex",
        flexWrap: "wrap",
        justifyContent : "center",
        alignItems : "center",
        
    }

}));


function Home() {

    const [error, setError] = useState(null);
    const [isLoaded, setIsLoaded] = useState(false);
    const [postList, setPostList] = useState([]);
    const classes = useStyles();

    const refreshPosts =() =>{
        fetch("/posts")
            .then(res => res.json())
            .then(
                (result) => {
                    setIsLoaded(true);
                    setPostList(result);
                },
                (error) => {
                    setIsLoaded(true);
                    setError(error);
                }
            )
    }


    useEffect(() => {
        refreshPosts()
    }, [postList])

    if (error) {
        return <div> Eror </div>
    } else if (!isLoaded) {
        return <div> Loading....</div>
    }
    else {
        return (

            <div className= {classes.container}>
                
                <PostForm userId = {1} userName="fatih"  refreshPosts = {refreshPosts}></PostForm>
                {postList.map(post => (
                    <Post postId = {post.id} title={post.title}  text ={post.text} 
                    userId = {post.userId} userName = {post.userName} 
                    ></Post>
                ))}

            </div>
        );
    }
}


export default Home;