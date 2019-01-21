# TwitterProject

Create a Java/ Scala project in proper structure using Class, Interface, configuration files, unit tests etc that retrieves tweets from a twitter account (eg @realDonaldTrump) in a continuous manner (streaming) and store the tweets in a csv file with an extra field/ column called "Status" along with the original fields of the tweet. The value of the column "Status" can be one of the three values:

 

1. "Retweet" if the tweet is a retweet of an another tweet.

2. "Reply Tweet" if the tweet is a reply to an existing tweet.

3. "Original Tweet" if the tweet is neither a "Retweet" or a "Reply Tweet"


The developer can make any kind of assumptions regarding the project in order to make it work.

 

Documentation of the twitter api can be found here:

https://developer.twitter.com/en/docs/tweets/data-dictionary/overview/tweet-object.html

 

The developer may need to create a twitter account, if they don't have one and do some prerequisites  like creating access_token and tonken_secret in order to start consuming timeline of a twitter account.

#RUN

Voici les 5 entrees à spécifier en entrée de l'application:
- consumerKey
- consumerSecret
- accessToken
- accessTokenSecret
- twitterUserId

Comment obtenir les 4 premières entrées : consumerKey, consumerSecret, accessToken, accessTokenSecret

1/Create a new Twitter account to use as the event's Twitter account at https://twitter.com/signup. The shorter the handle, the better — allows for longer questions/comments to be submitted.

2/Create a new application
While signed in under this new Twitter account, visit dev.twitter.com/apps.

Click Create an application.

Assign a name, description, and URL to the application. If you don't know the URL of your application yet, that's fine, you can change it later when you know. Click the Yes, I agree checkbox, fill out the CAPTCHA, and click Create your Twitter application.

The name you assign the application is what will appear as the source attribute on tweet metadata, and will be shown in the via field on twitter.com. Instead of via web, it will read via [your app name]. You can change this name at any time.

Application settings
Once the application has successfully been created, visit the Settings tab for the application. Select the Read and Write radio button and click Update this Twitter application's settings. This sets the proper permissions for the application to query and post new tweets to the account.

Visit the Details tab again. Take note of the consumer key, consumer secret, access token, and access secret — you'll need these when customizing Ospriet. If the access token/secret are not shown, click Create my access token at the bottom of the page.

Comment obtenir le: twitterUserId

1/ Ouvrer le lien ci-dessous: http://gettwitterid.com/

2/ Entrer votre "Twitter Username" (exemple: realDonaldTrump)

3/ Vous obtiendrez le "Twitter User Id" (exemple pour realDonaldTrump: 25073877)

Vous obtenez donc le twitterUserId qui est à spécifier en entrée de l'Appli pour pouvoir filtrer sur un compte twitter en particulier.

