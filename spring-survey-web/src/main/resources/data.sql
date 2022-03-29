-----------------------------------------
------------ Default Users --------------
-----------------------------------------

INSERT INTO USER (NAME, PASSWORD, ROLE)
VALUES ('Test John', 'password', 'USER');

INSERT INTO USER (NAME, PASSWORD, ROLE)
VALUES ('Test Tom', 'password', 'USER');

INSERT INTO USER (NAME, PASSWORD, ROLE)
VALUES ('Test Eve', 'password', 'USER');

INSERT INTO USER (NAME, PASSWORD, ROLE)
VALUES ('Test Jack', 'password', 'USER');

----------------- END -------------------
-----------------------------------------

-----------------------------------------
------------ Default Surveys ------------
-----------------------------------------

INSERT INTO SURVEY (USER_ID, TITLE, DESCRIPTION, TYPE, OPEN, SURVEY_TAKEN)
VALUES (1, 'Races of Middle-earth', 'This survey contains some question about the fictional races that appear in J. R. R. Tolkien`s fantasy world of Middle-earth.', 'fantasy', true, 0);

INSERT INTO SURVEY (USER_ID, TITLE, DESCRIPTION, TYPE, OPEN, SURVEY_TAKEN)
VALUES (1, 'Easy recipes!', 'I want to gather some information about the famous and easy recipes that people use every day in their life.', 'cooking', false, 0);

INSERT INTO SURVEY (USER_ID, TITLE, DESCRIPTION, TYPE, OPEN, SURVEY_TAKEN)
VALUES (2, 'What about cars?', 'Let me ask some questions about your preferences when you are looking fo a new car.', 'cars', true, 0);

INSERT INTO SURVEY (USER_ID, TITLE, DESCRIPTION, TYPE, OPEN, SURVEY_TAKEN)
VALUES (3, 'Reading habits', 'Tell me about your reading habits!', 'books', true, 0);

INSERT INTO SURVEY (USER_ID, TITLE, DESCRIPTION, TYPE, OPEN, SURVEY_TAKEN)
VALUES (1, 'Survey for students', 'Please fill this anonymous survey', 'school', true, 0);

----------------- END -------------------
-----------------------------------------

-----------------------------------------
----------- Default Questions -----------
-----------------------------------------

--------------- Survey 1 (fantasy)----------------
INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (1, 'What do you think about the races in the list below? Please select the ones you think are existing in the universe!', TRUE, FALSE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (1, 'Which one do you prefer?', FALSE, FALSE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (1, 'Please tell me which one is your favourite fantasy race!', FALSE, TRUE);

--------------- Survey 2 (cooking)----------------
INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (2, 'What is your favourite quick recipe?', FALSE, TRUE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (2, 'How quick is the recipe mentioned above?', FALSE, FALSE);

--------------- Survey 3 (cars)----------------
INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (3, 'What is your favourite car brand?', FALSE, TRUE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (3, 'Would you like to use a car of that brand on a daily basis (going to work, shopping, etc.)?', FALSE, FALSE);

--------------- Survey 4 (books)----------------
INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (4, 'How often do you read books?', FALSE, FALSE);

--------------- Survey 5 (school)----------------

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (5, 'When my teacher is explaining the lesson I`m listening.', FALSE, FALSE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (5, 'How often do you do your homework?', FALSE, FALSE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (5, 'Select your favourite class(es)!', TRUE, FALSE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (5, 'What is your opinion on the educational system in your country? (Please keep your answers short!)', FALSE, TRUE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (5, 'How much time do you spend studying a day at home?', FALSE, FALSE);

----------------- END -------------------
-----------------------------------------

-----------------------------------------
----------- Default Answers -----------
-----------------------------------------

--------------- Survey 1 ----------------
------------- S1:Question 1 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (1, 'Ents');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (1, 'Orcs');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (1, 'Taurens');

------------- S1:Question 2 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (2, 'Elves');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (2, 'Hobbits');

------------- S1:Question 3 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (3, 'This is a free text question.');

--------------- Survey 2 ----------------
------------- S2:Question 1 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (4, 'This is a free text question.');

------------- S2:Question 2 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (5, '15-30 minutes');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (5, '30-60 minutes');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (5, '60+ minutes');

--------------- Survey 3 ----------------
------------- S3:Question 1 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (6, 'This is a free text question.');

------------- S3:Question 2 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (7, 'Yes.');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (7, 'No.');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (7, 'No, because bad fuel consumption.');

--------------- Survey 4 ----------------
------------- S4:Question 1 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (8, 'Nearly every day, if I have the chance.');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (8, 'Couple of times a week.');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (8, 'Couple of times a month.');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (8, 'Very rarely, only if I have to, or found something interesting.');

--------------- Survey 5 ----------------
------------- S5:Question 1 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (9, 'True');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (9, 'False');

------------- S5:Question 2 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (10, 'Always');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (10, 'Most of the times');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (10, 'Rarely');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (10, 'Never');

------------- S5:Question 3 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (11, 'Math');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (11, 'Geography');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (11, 'P.E.');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (11, 'English');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (11, 'Music');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (11, 'Art');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (11, 'Art');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (11, 'History');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (11, 'Chemistry');

------------- S5:Question 4 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (12, 'This is a free text question.');

------------- S5:Question 5 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (13, '2-3 hours');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (13, '1-2 hour(s)');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (13, 'less than 1 hour');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (13, 'None');

----------------- END -------------------
-----------------------------------------