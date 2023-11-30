# Run the project

If you already have the JDK and IntelliJ installed, clone the project
from GitHub to IntelliJ and run it by hitting the play button from the
App.java file.

But if you have neither of those installed, follow these steps:

1.  For this project, I have used IntelliJ under a school license. In
    your case, you may use Eclipse to run this application. I am not
    familiar with Eclipse but I'm guessing it's the same process. But
    IntelliJ gives you a 30-day free trial so you can use it to run the
    application and then delete it afterwards.

2.  You can download IntelliJ from this url:
    <https://www.jetbrains.com/idea/download/?section=windows>

3.  Once the installation is finished, you will have to run IntelliJ.
    You should see something like this:\
    \
    ![](https://ibb.co/KrTtXGy){width="6.268055555555556in"
    height="5.11875in"}

Yours won't have these projects.

4.  Next, you must click on the option "Get from VCS". Then you will see
    this.\
    \
    ![](https://ibb.co/XF0Jft8){width="6.268055555555556in"
    height="1.6826388888888888in"}\
    \
    On the URL text field, you will have to paste the link to the remote
    repository.

5.  You will need the link to the remote repository on github. So you
    will have to copy it.\
    \
    ![](https://ibb.co/PxXf1G4){width="3.4586329833770777in"
    height="1.1417650918635172in"}\
    \
    \
    \
    Then click on Code and copy the HTTPS link.\
    \
    ![](https://ibb.co/4T05nhw){width="4.442051618547682in"
    height="2.800242782152231in"}

6.  Once you have pasted this link to the URL field in IntelliJ, you
    should be good to go. I will advise you to build the project first
    by click on the menu icon in the top left corner then follow this
    path\
    \
    Build \> Build Project

7.  It might give you an error saying that it can't detect a JDK so you
    must click on the button to install the jdk when a prompt appears at
    the top, round about this area:\
    \
    ![A screen shot of a computer Description automatically
    generated](vertopal_5b53b9e4fb344732b22bfc212c304156/media/image5.png){width="6.268055555555556in"
    height="3.5256944444444445in"}\
    \
    It is not shown in this image because I have installed a JDK.

8.  Now you need MySQL and I promise it'll be the last bit of setting up
    you will need. You will need the MySQL installer:\
    \
    Click here to install it:
    <https://dev.mysql.com/get/Downloads/MySQLInstaller/mysql-installer-community-8.0.35.0.msi>\
    \
    Then you will need to run the installer and it'll open something
    like this without the **server** and **workbench**. Click on the Add
    button\
    \
    ![](https://ibb.co/dcSWQxJ){width="6.268055555555556in"
    height="4.695833333333334in"}

9.  Install the server following these steps:\
    \
    MySQL Servers \> MySQL Server 8.0 \> MySQL Server 8.0.35 x64\
    \
    Then click the arrow pointing to the right to select the MySQL
    Server\
    \
    e.g\
    \
    ![](https://ibb.co/ckHLw2Y){width="5.683825459317585in"
    height="1.633474409448819in"}

10. During you installation process you will get to a point where you
    will be asked to enter a password for your server. Set it to
    **graphic4** and set the username to **root**. You can set it to
    whatever you want but you will have to modify the
    application.properties file in IntelliJ before running the project
    to match your credentials.

11. Once everything is ready, you can go to IntelliJ and click the play
    button from the App.java file.

12. If you made it here, congratulations, you're destined for greatness.

# Now it's time to test the API endpoints in Postman
To test the API, you will need to log in on your Postman account. Here
are the available API endpoints and their descriptions:

## API endpoints you can access without authentication 

1.  Read a post\
    \
    <http://localhost:8080/post/read/post_id> -\> This endpoint will
    read a specific post. The parameter **post_id** can be replaced with
    the actual post id.\
    \
    e.g
    <http://localhost:8080/post/read/2ca790a7-eab5-4b7f-9601-b5717742c814>

2.  Create an account\
    \
    <http://localhost:8080/user/create> -\> This endpoint is passed
    along in the body with an object that you want to send to the server
    to **create an account**.\
    \
    e.g

    <pre>
    {
        "username": "EnemyOfDinesh",
        "password": "Password123",
        "firstName": "Bertram",
        "lastName": "Gilfoyle"
    }
    </pre>

3.  Retrieve posts posted by a user using their username\
    \
    <http://localhost:8080/post/get_posts_of/username> -\> This endpoint
    will retrieve all the posts created by a specific user using their
    username. The parameter **username** can be replaced by the actual
    username.\
    \
    e.g
    [http://localhost:8080/post/get_posts_of/Viggo](http://localhost:8080/post/get_posts_of/Viggoe)

4.  Log in/authenticate\
    \
    <http://localhost:8080/auth/authenticate> -\> this endpoint is used
    to authenticate credentials and in return you get a JWT token.\
    \
    You send a request to this endpoint with a JSON object with username
    and password keys and their respective values that you are trying to
    authenticate.\
    \
    e.g
    
    <pre>
    {
        "username":"someUser",
        "password":"myPassword"
    }
    </pre>    

    If you exist in the database, you will receive an encoded JWT token
    which if you decode, you will notice that part of the object
    contains your user details.

## API endpoints that require authentication

1.  Create a post\
    \
    <http://localhost:8080/post/create> -\> This endpoint is used to
    create a post. The request to the endpoint is passed along with a
    JSON object in the body.\
    
    <pre>
    {
        "postMessage": "Hey guy. I'm having issues Deploying to AWS"
    }
    </pre>

2.  Update a post\
    \
    <http://localhost:8080/post/update/post_id> -\> This endpoint is
    used to update a post. The parameter **post_id** can be replaced
    with the actual post id.\
    \
    e.g
    <http://localhost:8080/post/update/2ca790a7-eab5-4b7f-9601-b5717742c814>\
    \
    It is sent along with a JSON object that contains the postMessage
    key that you are trying to update and its respective value.
    
    <pre>
    {
        "postMessage": "Edit: I found the solution"
    }
    </pre>

3.  Delete a post\
    \
    <http://localhost:8080/post/delete/id> -\> This endpoint is used to
    delete a post. The parameter **id** can be replaced with the actual
    id of the post.\
    \
    e.g
    <http://localhost:8080/post/delete/2ca790a7-eab5-4b7f-9601-b5717742c814>

4.  See all posts the current user has upvoted or downvoted\
    \
    <https://localhost:8080/user/see_voted_posts> -\> This endpoint will
    retrieve all the posts that the current logged in user has voted
    for.

5.  Upvoting a post\
    \
    <http://localhost:8080/vote/upvote/post_id> -\> This endpoint is
    used to upvote a post. The parameter **post_id** can be replaced
    with the actual post id.\
    \
    e.g
    <http://localhost:8080/vote/upvote/2ca790a7-eab5-4b7f-9601-b5717742c814>

6.  Downvoting a post\
    \
    <https://localhost:8080/vote/downVote/post_id> -\> This endpoint is
    used downvote a post. The parameter **post_id** can be replaced with
    the actual post id.\
    \
    e.g
    <https://localhost:8080/vote/downVote/2ca790a7-eab5-4b7f-9601-b5717742c814>

7.  Writing a comment\
    \
    <http://localhost:8080/comment/create> -\> This endpoint is used to
    write a comment to a specific post. The request to this endpoint
    goes with a JSON object which contains the the commetMessage key,
    and the post key. The commentMessage contains the value of the
    message you are trying to write as a comment to the post. The post
    key is an object that represents the post you are commenting to but
    you only need to pass in the post id key and not the rest of the
    properties.\
    \
    e.g

    <pre>
    {
        "commentMessage": "Please let me know how you figured it out",
        "post": {
            "postId": "e3c1e724-c86a-46eb-8983-f3a6996c3369"
        }
    }
    </pre>

8.  Upvoting a comment\
    \
    <http://localhost:8080/comment_vote/upvote/comment_id> -\> This
    endpoint is used upvote a comment. The parameter **comment_id** can
    be replaced with the actual comment id.\
    \
    e.g
    [http://localhost:8080/comment_vote/upvote/4bdf8d23-0e4a-45f8-aa7d-4ac234daff59](http://localhost:8080/comment_vote/upvote/comment_id)

9.  Downvoting a comment\
    \
    <http://localhost:8080/comment_vote/downVote/comment_id> -\> This
    endpoint is used downvote a comment. The parameter **comment_id**
    can be replaced with the actual comment id.\
    \
    e.g
    [http://localhost:8080/comment_vote/upvote/4bdf8d23-0e4a-45f8-aa7d-4ac234daff59](http://localhost:8080/comment_vote/upvote/comment_id)

10. Getting posts of posted by the current logged in user\
    \
    <http://localhost:8080/user/get_my_posts> -\> This endpoint will
    retrieve all the current logged in user's posts. It will get all the
    posts they created. There is no need for a special url parameter or
    JSON body object. As long as you're authenticated.
