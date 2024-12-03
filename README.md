**Swap by Sagma**

**Members of Sagma:** 
Amal Nouman Irshad: amalirshad999
Abeera Fatima: abeera22
Jiya Garg: jiyag
Gaia Micciancio: GaMicc
Andrew Max Nguyen: MaxNguyen5040
Shannon Arlene Tanoto: tanotosh

**Project Purpose**
What does Swap by Sagma do?
This program implements a swipe-based interface, similar to dating apps, to facilitate trading of meal preps. When you’ve prepped a bunch of food but don’t want to eat the same thing for a week, upload a picture, description, and list of ingredients and then start swiping! You can filter meals based on what you’re feeling like and your dietary restrictions. If you get a match, you get to swap food and get a diverse blend of cuisines for the week! After you get a meal, you can rate it. 

Why was Swap by Sagma made?

What Problems can be solved by Swap by Sagma?


**Table of Contents**
//TODO


**Software Features**

1. Login/Signup
Bartholomew Twilight Sparkle logs in to her profile. She can see the status of her foods and any matches. 
Pinkie Pie decides she wants to join the app so she creates a profile and log in info.
View  “Your Foods” & “Your Matches”

2. Upload Food
Tim has heard of this new meal plan prep app and wants to upload the poutine he just made onto the website for others to see. He wants to upload a short description as well as an image and an ingredients list.
Set “Your Dietary Restrictions”

3. Search
Katie is a university student who prepares one dish every week as a meal prep, she wants to trade her meal with another student with the same intention. She runs the software and gets a list of possible “matches” for her preferences. Since she only eats halal, she only wants to be shown meals that fulfill this dietary restriction. She puts on the filter for that particular dietary restriction, and now is shown and matched exclusively with meals that are halal.
“Start Swiping!”

4. “You’ve matched!”
Zach swipes right on Cody’s soup and it’s a match! A serving of each of their foods is taken off the market. If this was the last available serving of the food, it’s removed from their profile (added to the past foods) and no longer available in the swiping database. A Match is Displayed and an email is sent to the inactive user, notifying them a match has been made.

5. Rate Your Food
Giulia recently finished eating a sandwich she got from a swap with Henry. She found it disgusting. She uses the software to leave Henry a low rating that reflects her low opinion of his sandwich


**Installation Instructions**
This program is designed to run on any operating system with Java installed. Before running the application, ensure you have enabled the Gmail API on your Google Cloud Console and downloaded the credentials.json file. In order to install the program, simply fork this repository. Then, replace our credentials.json file with your downloaded file and also replace the email address "swipebysagma@gmail.com" in GmailAPI.java line 89 with yours. Finally, run the program with your preferred Java IDE! 

FAQ: 



**Usage Guide**
1. Run the main file on your preferred Java IDE
2. A screen will pop up. The app starts at the login page. 
- If you don't have an account, proceed to the signup page by clicking the Signup button. Once you are in the Signup Page, fill out a new username and password then clicked the Signup button and it will take you back to the Login Page.
- Otherwise, fill out your username and password and click the Login button.
3. You will now be on the UserHomepage.
- To view foods you have uploaded or if you've made a match, click Your Foods/Matches button
  - On the Your Food/Matches Page, click the Back button to return to the homepage.
- To upload new food, click Upload Food button
  - On the Upload Food Page, fill in all the required data then click Select Food button to return to the homepage.
- To set your dietary restrictions, click Dietary Restrictions button 
  - On the Your Dietary Restriction Page, fill in all the required data then click Back button to return to the homepage.
4. To start looking for foods, click Start Swiping! button, which will take you to the Search Page
5. On the Search page choose a food category with the drop-down menu and click the Search button.
6. You will now be at the Swiping Page which will display foods based on your preferred category and your dietary restrictions.
- If you are interested in a trade with the food displayed, click Yes.
- If you are not interested in a trade with the food displayed, click No.
7. Repeat step 6 until you are satisfied.
- You will be brought to a Matched Page when you've clicked Yes on a food of a user that have also clicked Yes on your food.
- Or, you can exit the app and wait for an email notification if a match have been made.
8. If you got an email notification, run the app again and login into your account and view your matches with the Your Foods/Matches button. 
9. After a match have been made, do the trade! And enjoy your food! Don't forget to leave a rating on our Rating Page :)


**License**


**Feedback**
Please leave us any feedback you have on our app by this link:
https://forms.gle/YksYmetamPNLH1By6.




**Contribution**
We welcome contributions! Fork this repository, make your changes, and submit a pull request. 