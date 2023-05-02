# twitter

- Project specifications -
Use cases 
 
 User 
1. Register : allow users to register with an unique username, a first name and a last name, an e-mail and a password 
2. Search : search for other users by username, firstname or lastname
3. Follow : follow another user and start receiving his public posts
4. Unfollow : unfollow a user and stop receiving feeds from this user 
5. Unregister : remove user and all his posts 

  Post 
1. Add post : post a public message – yes
2. Get own posts : return all posts added by the current user. Able to filter posts newer than a timestamp – dependent on user
3. Get feed : return all posts added by users followed by the current user  – dependent on user
4. Delete post - yes
5. Repost : “copy” an existing post from a different user 
6. Get mentions : return all posts in which the current user was mentioned 


 Like 
1. Like post : mark an existing post with a like. The owner of the post will receive in his  “get own posts” call (Posts.2) ok each post, a list of all the likes that message received 
2. Remove like 
3. Cascade: When a post is deleted, remove all its likes 
 
 Reply
1. Add post reply : reply to existing posts or other replies. Able to specify if the reply is public or only for the parent post’s owner 
2. Cascade: When a parent post is deleted, remove all its replies 
*Note: The bold use cases have priority and should be implemented in the order they appear in  this document  

--------------------------------------------------------------------------------------------------------------------------------------------------------------------
![image](https://user-images.githubusercontent.com/93082736/235606106-060f289f-2ce3-4dde-85da-74fe9aaf08b5.png)
