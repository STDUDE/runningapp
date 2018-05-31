INSERT INTO USER(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (1, 'admin', /*admin1234*/'$2a$08$qvrzQZ7jJ7oy2p/msL4M0.l83Cd0jNsX6AJUitbgRXGzge4j035ha', FALSE, FALSE, FALSE, TRUE);

INSERT INTO USER(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (2, 'reader', /*reader1234*/'$2a$08$dwYz8O.qtUXboGosJFsS4u19LHKW7aCQ0LXXuNlRfjjGKwj5NfKSe', FALSE, FALSE, FALSE, TRUE);

INSERT INTO USER(ID, USER_NAME, PASSWORD, ACCOUNT_EXPIRED, ACCOUNT_LOCKED, CREDENTIALS_EXPIRED, ENABLED)
  VALUES (3, 'modifier', /*modifier1234*/'$2a$08$kPjzxewXRGNRiIuL4FtQH.mhMn7ZAFBYKB3ROz.J24IX8vDAcThsG', FALSE, FALSE, FALSE, TRUE);

INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES (1, 1);
INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES (1, 2);
INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES (1, 3);
INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES (1, 4);

INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES (2, 2);

INSERT INTO USERS_ROLES(USER_ID, ROLE_ID) VALUES (3, 3);