# Swap by Sagma

### Members of Sagma:
- Amal Nouman Irshad: amalirshad999
- Abeera Fatima: abeera22
- Jiya Garg: jiyag
- Gaia Micciancio: GaMicc
- Andrew Max Nguyen: MaxNguyen5040
- Shannon Arlene Tanoto: tanotosh

### Project Purpose

##### What Problems can be solved by Swap by Sagma?
Swap by Sagma sets out to help people who rely on meal prepping due to limited time or a dislike of cooking, but grow tired of eating the same thing repeatedly.

##### Why was Swap by Sagma made?
This program was made to provide help people who meal prep diversify their meal options by trading meals with other meal preppers.

##### What does Swap by Sagma do?
This program implements a swipe-based interface, similar to dating apps, to facilitate trading of meal preps. When you’ve prepped a bunch of food but don’t want to eat the same thing for a week, upload information on it and then start swiping! You can filter meals based on what you’re feeling like and your dietary restrictions. If two users like each other's meals, a balanced trade occurs and they get to swap foods and get a diverse blend of cuisines for the week!


### Table of Contents
[Software Features](#software-features)

[Installation Instructions](#installation-instructions)

[Usage Guide](#usage-guide)

[License](#license)

[Feedback](#feedback)

[Contribution](#contribution)

### Software Features

##### 1. Login/Signup
User decides she wants to log in or sign up to access and interact with the app, ensuring that her data is saved and used effectively for app functionalities like swiping, matching, and notifications.. \[Jiya's story\]

##### 2. Upload Food
User wants to upload the food she made for others to see. She clicks the upload food button on the user home page and is prompted to enter the food's name, ingredients, number of servings, dietary restrictions, type of cuisine, and an image. When she clicks upload food, it is stored as her current food. \[Gaia's story\]

##### 3. Set Dietary Restrictions
User wants to register her dietary restriction of being dairy free. She clicks on dietary restrictions and is able to select her dietary restrictions. \[Abeera's story\]

##### 4. Search
User now wants to look for other foods. She runs the software and gets a list of possible “matches” for her preferences. Since she is dairy free, the program only shows her dairy free options. She can also decide what category of cuisine she wants, and she will only be shown meals that are that type of cuisine. \[Shannon's story\]

##### 5. Swiping
User is able to swipe on foods that match her specifications. She can decide yes or no. Her swipes are stored in the database and if she gets a match, this is announced. A serving of each food is taken off the market and the other user is notified via email that they got a match. \[Amal's story\]

##### 6. Rating
Later, the user is prompted to leave a rating for the food they tried. They can select between 1 and 5 stars. This rating is added to the User who made that food's rating. \[Gaia's story\]

##### 7. View Foods and Matches
The user wants to see their matches and the food they have uploaded. They can click the view foods and matches buttons on the user homepage and see this. \[Max's story\]


### Installation Instructions
This program is designed to run on any operating system with Java installed. Before running the application, ensure you have enabled the Gmail API on your Google Cloud Console and downloaded the credentials.json file. In order to install the program, simply fork this repository. Then, replace our credentials.json file with your downloaded file and also replace the email address "swipebysagma@gmail.com" in GmailAPI.java line 89 with yours. Finally, run the program with your preferred Java IDE! 

##### FAQ 
Q: How do I install Swap by Sagma?


A: To install Swap by Sagma, follow these steps:
Fork the repository on GitHub.
Clone the forked repository to your local machine.
Enable the Gmail API on your Google Cloud Console and download the credentials.json file.
Replace the existing credentials.json file in the project with your downloaded file.
In GmailAPI.java, replace the email address "swipebysagma@gmail.com" on line 89 with your own email address.
Run the program using your preferred Java IDE.

Q: What are the system requirements?


A: Swap by Sagma is designed to run on any operating system with Java installed. Ensure you have a compatible Java Development Kit (JDK) installed on your machine.

Q: How do I start using the app?


A: After installation, follow these steps to start using Swap by Sagma:
Run the main file in your Java IDE.
The app will open on the login page.
If you're a new user, click the Signup button to create an account.
Once logged in, you'll be on the UserHomepage where you can access various features like uploading food, setting dietary restrictions, and starting to swipe.

Q: How does the food swapping process work?


A: The food swapping process involves:
Uploading your prepared meals.
Setting your dietary restrictions.
Searching for foods that match your preferences.
Swiping right (Yes) on meals you're interested in.
When two users like each other's meals, a match is made, and you can swap foods.

Q: I got this error "java: invalid source release 22 with --enable-preview
  (preview language features are only supported for release 23)" or something similar


A: Go to your settings -> Project structure -> Modules -> and change your language level to X- Experimental features
  

### Usage Guide
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

Here is a link to a tutorial video on how to use our app


https://github.com/user-attachments/assets/17154a54-ff50-4ff6-a54e-509dbbadd80f




### License
This project is licensed under the MIT License.


Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.


THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

### Feedback
We value your feedback! Help us improve our app by sharing your experiences. We'd love to hear about your thoughts, experiences or issues you've encountered. 
Fill out the form on this link:
https://forms.gle/YksYmetamPNLH1By6.

We accept suggestions for new features and bug reports on user experience and functionality. We won't tolerate spams or inappropriate language used.
Your feedbacks are taken seriously and will be reviewed by our team. We may follow up with you if additional details are needed through email.
Thankyou!


### Contribution
We welcome contributions! Here's how you can get involved:
1. Fork the Repository 
- by clicking the Fork button of this repository on GitHub
2. Clone the forked repository
- by clicking on Code button, there you'll see a web URL. Copy that and paste it when you make a new project on your Java IDE (Project from Version Control....)
3. Implement your changes/features
4. Commit and Push your changes 
5. Submit a Pull Request on our repository with a clear concise message of what you are implementing and how it would help improve the app
- include documentation and test cases for your changes

Our team will review your pull request as soon as possible. Feedback may be provided for adjustments. Once approved, your pull request will be merged into our repository and your contributions will be acknowledged. 
