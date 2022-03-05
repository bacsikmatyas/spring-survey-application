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

INSERT INTO SURVEY (USER_ID, TITLE, DESCRIPTION, TYPE, OPEN)
VALUES (1, 'Test Title 1', 'Test Descprition for the Test Survey 1.', 'fantasy', true);

INSERT INTO SURVEY (USER_ID, TITLE, DESCRIPTION, TYPE, OPEN)
VALUES (1, 'Test Title 2', 'Test Descprition for the Test Survey 2.', 'cooking', false);

INSERT INTO SURVEY (USER_ID, TITLE, DESCRIPTION, TYPE, OPEN)
VALUES (2, 'Test Title 3', 'Test Descprition for the Test Survey 3.', 'cars', true);

INSERT INTO SURVEY (USER_ID, TITLE, DESCRIPTION, TYPE, OPEN)
VALUES (3, 'Test Title 4', 'Test Descprition for the Test Survey 4.', 'books', true);

----------------- END -------------------
-----------------------------------------

-----------------------------------------
----------- Default Questions -----------
-----------------------------------------

--------------- Survey 1 ----------------
INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (1, 'Test Question 1?', TRUE, FALSE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (1, 'Test Question 2?', FALSE, FALSE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (1, 'Test Question 3?', FALSE, FALSE);

--------------- Survey 2 ----------------
INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (2, 'Test Question 1?', FALSE, FALSE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (2, 'Test Question 2?', FALSE, FALSE);

--------------- Survey 3 ----------------
INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (3, 'Test Question 1?', FALSE, TRUE);

INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (3, 'Test Question 2?', FALSE, FALSE);

--------------- Survey 4 ----------------
INSERT INTO SURVEY_QUESTION (SURVEY_ID, QUESTION_TEXT, MULTISELECT, FREETEXT)
VALUES (4, 'Test Question 1?', FALSE, FALSE);

----------------- END -------------------
-----------------------------------------

-----------------------------------------
----------- Default Answers -----------
-----------------------------------------

--------------- Survey 1 ----------------
------------- S1:Question 1 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (1, 'Example Answer 1!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (1, 'Example Answer 2!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (1, 'Example Answer 3!');

------------- S1:Question 2 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (2, 'Example Answer 1!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (2, 'Example Answer 2!');

------------- S1:Question 3 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (3, 'Example Answer 1!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (3, 'Example Answer 2!');

--------------- Survey 2 ----------------
------------- S2:Question 1 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (4, 'Example Answer 1!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (4, 'Example Answer 2!');

------------- S2:Question 2 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (5, 'Example Answer 1!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (5, 'Example Answer 2!');

--------------- Survey 3 ----------------
------------- S3:Question 1 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (6, 'Example Answer 1!');

------------- S3:Question 2 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (7, 'Example Answer 1!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (7, 'Example Answer 2!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (7, 'Example Answer 3!');

--------------- Survey 4 ----------------
------------- S4:Question 1 -------------
INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (8, 'Example Answer 1!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (8, 'Example Answer 2!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (8, 'Example Answer 3!');

INSERT INTO QUESTION_ANSWER (QUESTION_ID, ANSWER_TEXT)
VALUES (8, 'Example Answer 4!');

----------------- END -------------------
-----------------------------------------